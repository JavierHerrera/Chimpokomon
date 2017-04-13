package chimpokomon.com.example.pep.chimpokomon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static chimpokomon.com.example.pep.chimpokomon.R.attr.background;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView textViewBattle, texto_informacion_atacar2,texto_informacion_atacar1;
    ProgressBar pgrHPPlayer2, pgrHPPlayer1, progressBarTestAtkSpeedCPU, progressBarTestAtkSpeedPlayer1;
    MediaPlayer mpMusicBattle;
    Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4;
    //Variables para la velocidad de ataque
    int progresStatusCPU, progresStatusPlayer1, Token_CPU_Atacando, Token_Player1_Atacando;


    //BOTONES DE PRUEBA
    Button btn_full_Restore, btn_cpu_Attak;
    Context context;
    int chinpokomon_player2;

    //Textos que se utilizan para cagar los display
    TextView textView_HPplayer1, textView_HPplayer2, textView_HPmax_Player1;
    TextView textViewNombreChinpoPlayer1, textViewNombreChinpoPlayer2;
    ImageView imagePlayer1, imagePlayer2;
    ImageView icon_Seleccion1, icon_Seleccion2, icon_Seleccion3;

    //Se inicializa objeto motorBatalla y se pasan los ID de los 6 personajes
    MotorBatalla motorBatalla = new MotorBatalla(
            SelectCharacterActivity.seleccion1,
            SelectCharacterActivity.seleccion2,
            SelectCharacterActivity.seleccion3,
            (int) (Math.random() * 3),
            (int) (Math.random() * 3),
            (int) (Math.random() * 3));

    BDMoves moves = new BDMoves();
    Animaciones animaciones;


    // Se instancias para los hilos de atacar por tiempo
    final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        context = this;
        //Inicializar personajes actuales
        motorBatalla.player1_Actual = motorBatalla.player1_Personaje1;
        motorBatalla.player2_Actual = motorBatalla.player2_Personaje2;

        //Inicia musica de batalla
        mpMusicBattle = MediaPlayer.create(this, R.raw.trainer_battle_song);
        mpMusicBattle.start();

        //Se pasan los datos de los personajes apra cargar sus datos
        cargar_Elementos_Player1();
        cargar_Elementos_Player2();
        cargar_Imagenes();

        //Inicializa texto
        textViewBattle = (TextView) findViewById(R.id.textoBatalla);
        texto_informacion_atacar1 = (TextView) findViewById(R.id.texto_informacion_atacarP1);
        texto_informacion_atacar2 = (TextView) findViewById(R.id.texto_informacion_atacarP2);

        //Se inicia
        Token_CPU_Atacando = 1;
        Token_Player1_Atacando = 1;
        habilitar_deshabilitar_botones(false);
        attack_speed_CPU();
        attack_speed_player1();
        chinpokomon_player2 = 1;

    }

    private void cargar_Elementos_Player1() {

        //Se crean los botones y se cambia el nombre segun el ataque
        btnAtaque1 = (Button) findViewById(R.id.button_attack1);
        btnAtaque2 = (Button) findViewById(R.id.button_attack2);
        btnAtaque3 = (Button) findViewById(R.id.button_attack3);
        btnAtaque4 = (Button) findViewById(R.id.button_attack4);

        //TEST DE BOTONES
        btn_full_Restore = (Button) findViewById(R.id.button_fullRestore);
        btn_cpu_Attak = (Button) findViewById(R.id.button_attackCPU);
        btn_full_Restore.setOnClickListener(this);
        btn_cpu_Attak.setOnClickListener(this);

        btnAtaque1.setOnClickListener(this);
        btnAtaque2.setOnClickListener(this);
        btnAtaque3.setOnClickListener(this);
        btnAtaque4.setOnClickListener(this);
        btnAtaque1.setText(motorBatalla.player1_Actual.move1);
        btnAtaque2.setText(motorBatalla.player1_Actual.move2);
        btnAtaque3.setText(motorBatalla.player1_Actual.move3);
        btnAtaque4.setText(motorBatalla.player1_Actual.move4);
        cargarTipoBoton(btnAtaque1);
        cargarTipoBoton(btnAtaque2);
        cargarTipoBoton(btnAtaque3);
        cargarTipoBoton(btnAtaque4);

        //btnAtaque1.setTextAppearance(R.style.button_bug);





        //TextView de HP
        textView_HPplayer1 = (TextView) findViewById(R.id.textView_HPcurrent_Player1);
        textView_HPmax_Player1 = (TextView) findViewById(R.id.textView_HPmax_Player1);
        textView_HPplayer1.setText("" + (int) motorBatalla.player1_Actual.hp);
        textView_HPmax_Player1.setText("/" + (int) motorBatalla.player1_Actual.hpMax);

        //Se crea ProgresBar y texto de batalla TEST
        pgrHPPlayer1 = (ProgressBar) findViewById(R.id.progressBarHPPlayer1);
        progressBarTestAtkSpeedPlayer1 = (ProgressBar) findViewById(R.id.progressBarSpeedPlayer1);
        //Cargar barras hp
        pgrHPPlayer1.setMax((int) motorBatalla.player1_Actual.hpMax);
        pgrHPPlayer1.setProgress((int) motorBatalla.player1_Actual.hp);
        //Nombres de Chinpokomon
        textViewNombreChinpoPlayer1 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer1);
        textViewNombreChinpoPlayer1.setText(motorBatalla.player1_Actual.nombre);
    }

    private void cargar_Elementos_Player2() {

        //TextView de HP
        textView_HPplayer2 = (TextView) findViewById(R.id.textViewHPViewChinpoCPU);
        textView_HPplayer2.setText("" + (int) motorBatalla.player2_Actual.hp);

        //Se crea ProgresBar de HP, speed y texto de batalla
        pgrHPPlayer2 = (ProgressBar) findViewById(R.id.progressBarHPPlayer2);
        progressBarTestAtkSpeedCPU = (ProgressBar) findViewById(R.id.progressBarSpeedPlayer2);

        //Cargar barras hp
        pgrHPPlayer2.setMax((int) motorBatalla.player2_Actual.hpMax);
        pgrHPPlayer2.setProgress((int) motorBatalla.player2_Actual.hp);
        //Nombres de Chinpokomon
        textViewNombreChinpoPlayer2 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer2);
        textViewNombreChinpoPlayer2.setText(motorBatalla.player2_Actual.nombre);
    }

    public void cargar_Imagenes() {

        //Cargar imagen de chinpokomon
        imagePlayer1 = (ImageView) findViewById(R.id.imagePlayer1);
        imagePlayer2 = (ImageView) findViewById(R.id.imagePlayer2);

        String name10 = "gridview_" + motorBatalla.player1_Actual.nombre.toLowerCase();
        int resId10 = getResources().getIdentifier(name10, "drawable", getPackageName());
        imagePlayer1.setImageResource(resId10);

        String name20 = "gridview_" + motorBatalla.player2_Actual.nombre.toLowerCase();
        int resId20 = getResources().getIdentifier(name20, "drawable", getPackageName());
        imagePlayer2.setImageResource(resId20);

        //Cargar Imagenes de los 3 seleccionados
        icon_Seleccion1 = (ImageView) findViewById(R.id.img_Seleccion1);
        icon_Seleccion2 = (ImageView) findViewById(R.id.img_Seleccion2);
        icon_Seleccion3 = (ImageView) findViewById(R.id.img_Seleccion3);
        icon_Seleccion1.setOnClickListener(this);
        icon_Seleccion2.setOnClickListener(this);
        icon_Seleccion3.setOnClickListener(this);

        //Hacer imagen transparente si muere personaje
        if(motorBatalla.player1_Personaje1.hp ==0){
            animaciones.animation_icono_morir(icon_Seleccion1);
        }
        if(motorBatalla.player1_Personaje2.hp ==0){
            animaciones.animation_icono_morir(icon_Seleccion2);
        }
        if(motorBatalla.player1_Personaje3.hp ==0){
            animaciones.animation_icono_morir(icon_Seleccion3);
        }

        String name1 = "gridview_" + motorBatalla.player1_Personaje1.nombre.toLowerCase();
        int resId1 = getResources().getIdentifier(name1, "drawable", getPackageName());
        icon_Seleccion1.setImageResource(resId1);

        String name2 = "gridview_" + motorBatalla.player1_Personaje2.nombre.toLowerCase();
        int resId2 = getResources().getIdentifier(name2, "drawable", getPackageName());
        icon_Seleccion2.setImageResource(resId2);

        String name3 = "gridview_" + motorBatalla.player1_Personaje3.nombre.toLowerCase();
        int resId3 = getResources().getIdentifier(name3, "drawable", getPackageName());
        icon_Seleccion3.setImageResource(resId3);

    }

    public void cargarTipoBoton(Button button){
        String move_type;
        int i=0;
        while ( button.getText() != moves.bd_ataques[i][0]){
            i = i + 1;
        }
        move_type = moves.bd_ataques[i][1];
        String name = "button_style_" + move_type;
        int resId = getResources().getIdentifier(name, "drawable", getPackageName());
        button.setBackgroundResource( resId );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_attack1:

                atacar_player1((String) btnAtaque1.getText());
                break;

            case R.id.button_attack2:

                atacar_player1((String) btnAtaque2.getText());
                break;

            case R.id.button_attack3:

                atacar_player1((String) btnAtaque3.getText());
                break;

            case R.id.button_attack4:

                atacar_player1((String) btnAtaque4.getText());
                break;

            //Los siguientes 3 casos, cambian al chinpokomon actual por el seleccionado en la imagen
            case R.id.img_Seleccion1:
                if (motorBatalla.player1_Personaje1.hp >0) {
                    cambiar_personaje_player1(motorBatalla.player1_Personaje1);
                }
                break;

            case R.id.img_Seleccion2:
                if (motorBatalla.player1_Personaje2.hp >0) {
                    cambiar_personaje_player1(motorBatalla.player1_Personaje2);
                };
                break;

            case R.id.img_Seleccion3:
                if (motorBatalla.player1_Personaje3.hp >0) {
                    cambiar_personaje_player1(motorBatalla.player1_Personaje3);
                }
                break;

            //BOTONES DE TEST
            case R.id.button_fullRestore:
                Token_CPU_Atacando +=1;
                //attack_speed_CPU();

                break;

            case R.id.button_attackCPU:


                break;
        }
    }


    public void atacar_player1(String move) {
        animaciones = new Animaciones(imagePlayer1, imagePlayer2, context);

        if (motorBatalla.player1_Actual.hp > 0 && motorBatalla.player2_Actual.hp > 0) {
            //Inicializa el ataque solo si los 2 personajes tiene hp, se pasa el nombre del ataque
            motorBatalla.combate(move, 1);
            textViewBattle.setText(motorBatalla.leyenda_de_textView + " Daño:" + motorBatalla.damage_done);
            actualizarProgresBarHP((int) motorBatalla.player1_Actual.hp, (int) motorBatalla.player2_Actual.hp);
            habilitar_deshabilitar_botones(false);
            attack_speed_player1();
            animaciones.animation_ATK_player1(texto_informacion_atacar2, motorBatalla.flag_ataque,Integer.toString(motorBatalla.damage_done));
        }
    }

    //Este metodo se utiliza para que un contador active el ataque del CPU
    public void atacar_CPU() {
        animaciones = new Animaciones(imagePlayer1, imagePlayer2, context);

        //Inicializa el combate de CPU prueba
        String ataqueCPU;
        if (1 == (int) (Math.random() * 2)) {
            ataqueCPU = motorBatalla.player2_Actual.move1;
        } else {
            ataqueCPU = motorBatalla.player2_Actual.move2;
        }

        motorBatalla.combate(ataqueCPU, 2);
        textViewBattle.setText(motorBatalla.leyenda_de_textView + " Daño:" + motorBatalla.damage_done);
        actualizarProgresBarHP((int) motorBatalla.player1_Actual.hp, (int) motorBatalla.player2_Actual.hp);
        animaciones.animation_ATK_player2(texto_informacion_atacar1, motorBatalla.flag_ataque,Integer.toString(motorBatalla.damage_done));
    }

    //Actualizar ProgersBarHP cambia la barra de HP y el contador de abajo
    public void actualizarProgresBarHP(int player1HP, int player2HP) {
        pgrHPPlayer1.setProgress(player1HP);
        pgrHPPlayer2.setProgress(player2HP);

        textView_HPplayer1.setText("" + (int) motorBatalla.player1_Actual.hp);
        textView_HPplayer2.setText("" + (int) motorBatalla.player2_Actual.hp);

        //Valida si la batalla finalizo y/o realiza el cambio de pesonaje de CPU
        finalizar_batalla(); //NO SIRVE!!


    }

    //Metodo para cambiar de personaje de player1 y activa el ataque de CPU cambiando su flag y activando su metodo atacar:CPU
    public void cambiar_personaje_player1(Personaje personaje) {
        motorBatalla.player1_Actual = personaje;
        cargar_Elementos_Player1();
        cargar_Imagenes();
        habilitar_deshabilitar_botones(false);
        attack_speed_player1();

        if (progresStatusCPU == 0) {
            attack_speed_CPU();
        }
    }

    public void cambiar_personaje_CPU() {

        do {
            int a = (int) (Math.random() * 3);
            if (a == 1) {
                motorBatalla.player2_Actual = motorBatalla.player2_Personaje1;
            } else if (a == 2) {
                motorBatalla.player2_Actual = motorBatalla.player2_Personaje2;
            } else motorBatalla.player2_Actual = motorBatalla.player2_Personaje3;
        } while (motorBatalla.player2_Actual.hp == 0);

        cargar_Elementos_Player2();
        cargar_Imagenes();

        attack_speed_CPU();


    }

    public void finalizar_batalla() {

        //Hacer imagen transparente si muere personaje
        if(motorBatalla.player1_Personaje1.hp ==0){
            animaciones.animation_icono_morir(icon_Seleccion1);
        }
        if(motorBatalla.player1_Personaje2.hp ==0){
            animaciones.animation_icono_morir(icon_Seleccion2);
        }
        if(motorBatalla.player1_Personaje3.hp ==0){
            animaciones.animation_icono_morir(icon_Seleccion3);
        }


        //Si no quedan personajes visos, manda mensaje final o cambia de personaje
        if ((motorBatalla.player1_Personaje1.hp + motorBatalla.player1_Personaje2.hp + motorBatalla.player1_Personaje3.hp) == 0) {
            textViewBattle.setText("PERDISTE");
        } else if ((motorBatalla.player2_Personaje1.hp + motorBatalla.player2_Personaje2.hp + motorBatalla.player2_Personaje3.hp) == 0) {
            textViewBattle.setText("GANASTE");
        } else if (motorBatalla.player2_Actual.hp == 0) {
            cambiar_personaje_CPU();
        }

    }

    public void habilitar_deshabilitar_botones(boolean a) {
        btnAtaque1.setEnabled(a);
        btnAtaque2.setEnabled(a);
        btnAtaque3.setEnabled(a);
        btnAtaque4.setEnabled(a);
    }


    //HILOS que utilizan la velocidad de ataque para habilitar los ataques
    public void attack_speed_CPU(){
        Token_CPU_Atacando +=1;
        progresStatusCPU = 0;
        final int token = Token_CPU_Atacando;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( progresStatusCPU < 100 && token == Token_CPU_Atacando && motorBatalla.player1_Actual.hp > 0 && motorBatalla.player2_Actual.hp > 0){
                    progresStatusCPU +=1;

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBarTestAtkSpeedCPU.setProgress(progresStatusCPU);
                            if (progresStatusCPU == 100 ){
                                atacar_CPU();
                                progresStatusCPU = 0;
                                if ( motorBatalla.player1_Actual.hp > 0){
                                    attack_speed_CPU();
                                }
                            }
                        }
                    });
                    try{
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e){e.printStackTrace();}
                }
                progresStatusCPU = 0;
            }
        }).start();

    }

    public void attack_speed_player1(){
        Token_Player1_Atacando +=1;
        progresStatusPlayer1 = 0;
        final int token2 = Token_Player1_Atacando;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( progresStatusPlayer1 < 100 && token2 == Token_Player1_Atacando && motorBatalla.player1_Actual.hp > 0 && motorBatalla.player2_Actual.hp > 0){
                    progresStatusPlayer1 +=1;

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBarTestAtkSpeedPlayer1.setProgress(progresStatusPlayer1);
                            if (progresStatusPlayer1 == 100 ){
                                habilitar_deshabilitar_botones(true);
                            }
                        }
                    });
                    try{
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e){e.printStackTrace();}
                }
            }
        }).start();

    }





    //FullRestore es de prueba, actualizar ProgersBarHP cambia la barra de HP y el contador de abajo
    public void fullRestore() {
        pgrHPPlayer1.setProgress(pgrHPPlayer1.getMax());
        pgrHPPlayer2.setProgress(pgrHPPlayer2.getMax());

        motorBatalla.player1_Actual.hp = pgrHPPlayer1.getMax();
        motorBatalla.player2_Actual.hp = pgrHPPlayer2.getMax();

        textView_HPplayer1.setText("" + (int) motorBatalla.player1_Actual.hp);
        textView_HPplayer2.setText("" + (int) motorBatalla.player2_Actual.hp);
    }
}




