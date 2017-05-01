package chimpokomon.com.example.pep.chimpokomon;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener {
    DatosMoves moves = new DatosMoves();
    Animaciones animaciones;
    MediaPlayer mpMusicBattle;
    Context context;
    Move move2;

    //Elementos que se utilizan en los display de ambos jugadores
    private ImageView imgPlayer1;                   //Personaje actual player1
    private ImageView imgPlayer2;                   //Personaje actual player2
    private ImageView imgSeleccion1;                //Primer personaje de player1
    private ImageView imgSeleccion2;                //Segundo personaje  de player1
    private ImageView imgSeleccion3;                //Tercer personaje player1
    private ImageView imgSeleccion4;                //Primer personaje de player2
    private ImageView imgSeleccion5;                //Segundo personaje de player2
    private ImageView imgSeleccion6;                //Tercer  personaje de player2
    private TextView txtViewBattle;                 //Muestra la informacion de los ataques realizados
    private TextView txtInformacionAtacar1;        //Muestra el daño al atacar de player1
    private TextView txtInformacionAtacar2;        //Muestra el daño al atacar de player2
    private ProgressBar pgrHPPlayer1;
    private ProgressBar pgrHPPlayer2;
    private ProgressBar prgAtkSpeedPlayer1;
    private ProgressBar prgAtkSpeedPlayer2;
    private TextView txtHPPlayer1;                   //Muestra el HP actual de player1
    private TextView txtHPPlayer2;                   //Muestra el HP actual de player2
    private TextView txtHPMaxPlayer1;                //Muestra el HP Maximo de player1
    private TextView txtNombrePersonajePlayer1;      //Muestra el nombre del presonaje de player1
    private TextView txtNombrePersonajePlayer2;      //Muestra el nombre del presonaje de player2

    //Botones de ataques
    private Button btnAtaque1;
    private Button btnAtaque2;
    private Button btnAtaque3;
    private Button btnAtaque4;

    //Variables para la velocidad de ataque de los 2 jugadores
    int progresStatusPlayer1;                       //Indica si esta cargando la barra de veolicdad
    int progresStatusPlayer2;                       //Indica si esta cargando la barra de veolicdad
    int tokenPlayer1Atacando;                       //Se utiliza para cancelar progresbar atkspeed
    int tokenPlayer2Atacando;                       //Se utiliza para cancelar progresbar atkspeed

    //Se inicializa objeto motorBatalla y se pasan los ID de los 6 personajes
    MotorBatalla motorBatalla = new MotorBatalla(
            SelectCharacterActivity.seleccion1,
            SelectCharacterActivity.seleccion2,
            SelectCharacterActivity.seleccion3,
            (int) (Math.random() * 5),
            (int) (Math.random() * 5),
            (int) (Math.random() * 5));

    // Se utiliza para los hilos que cargan las progessbar de la velocidad de ataque
    final Handler mHandler = new Handler();

    //BOTONES DE PRUEBA
    Button btnFullRestore, btn_cpu_Attak;
    int chinpokomonPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_battle);

        context = this;
        //Inicializar personajes actuales
        motorBatalla.player1_Actual = motorBatalla.player1_Personaje1;
        motorBatalla.player2_Actual = motorBatalla.player2_Personaje2;

        //Inicia musica de batalla
        mpMusicBattle = MediaPlayer.create(this, R.raw.trainer_battle_song);
        mpMusicBattle.start();

        //Se pasan los datos de los personajes para cargar sus datos
        cargar_Elementos_Player1();
        cargar_Elementos_Player2();
        cargar_Imagenes();

        //Inicializa texto
        txtViewBattle = (TextView) findViewById(R.id.textoBatalla);
        txtInformacionAtacar1 = (TextView) findViewById(R.id.texto_informacion_atacarP1);
        txtInformacionAtacar2 = (TextView) findViewById(R.id.texto_informacion_atacarP2);

        //Se inicia
        tokenPlayer2Atacando = 1;
        tokenPlayer1Atacando = 1;
        habilitarDeshabilitarBotones(false);
        attackSpeedCPU();
        attackSpeedPlayer1();
        chinpokomonPlayer2 = 1;

    }

    private void cargar_Elementos_Player1() {

        //Se crean los botones y se cambia el nombre segun el ataque
        btnAtaque1 = (Button) findViewById(R.id.button_attack1);
        btnAtaque2 = (Button) findViewById(R.id.button_attack2);
        btnAtaque3 = (Button) findViewById(R.id.button_attack3);
        btnAtaque4 = (Button) findViewById(R.id.button_attack4);

        //TEST DE BOTONES
        btnFullRestore = (Button) findViewById(R.id.button_fullRestore);
        btn_cpu_Attak = (Button) findViewById(R.id.button_attackCPU);
        btnFullRestore.setOnClickListener(this);
        btn_cpu_Attak.setOnClickListener(this);

        btnAtaque1.setOnClickListener(this);
        btnAtaque2.setOnClickListener(this);
        btnAtaque3.setOnClickListener(this);
        btnAtaque4.setOnClickListener(this);
        btnAtaque1.setText(motorBatalla.player1_Actual.move1.name);
        btnAtaque2.setText(motorBatalla.player1_Actual.move2.name);
        btnAtaque3.setText(motorBatalla.player1_Actual.move3.name);
        btnAtaque4.setText(motorBatalla.player1_Actual.move4.name);
        cargar_Tipo_Boton(btnAtaque1);
        cargar_Tipo_Boton(btnAtaque2);
        cargar_Tipo_Boton(btnAtaque3);
        cargar_Tipo_Boton(btnAtaque4);

        //TextView de HP
        txtHPPlayer1 = (TextView) findViewById(R.id.textView_HPcurrent_Player1);
        txtHPMaxPlayer1 = (TextView) findViewById(R.id.textView_HPmax_Player1);
        txtHPPlayer1.setText("" + (int) motorBatalla.player1_Actual.hp);
        txtHPMaxPlayer1.setText("/" + (int) motorBatalla.player1_Actual.hpMax);

        //Se crea ProgresBar y texto de batalla TEST
        pgrHPPlayer1 = (ProgressBar) findViewById(R.id.progressBarHPPlayer1);
        prgAtkSpeedPlayer1 = (ProgressBar) findViewById(R.id.progressBarSpeedPlayer1);
        //Cargar barras hp
        pgrHPPlayer1.setMax((int) motorBatalla.player1_Actual.hpMax);
        pgrHPPlayer1.setProgress((int) motorBatalla.player1_Actual.hp);
        //Nombres de Chinpokomon
        txtNombrePersonajePlayer1 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer1);
        txtNombrePersonajePlayer1.setText(motorBatalla.player1_Actual.nombre);
    }

    private void cargar_Elementos_Player2() {

        //TextView de HP
        txtHPPlayer2 = (TextView) findViewById(R.id.textViewHPViewChinpoCPU);
        txtHPPlayer2.setText("" + (int) motorBatalla.player2_Actual.hp);

        //Se crea ProgresBar de HP, speed y texto de batalla
        pgrHPPlayer2 = (ProgressBar) findViewById(R.id.progressBarHPPlayer2);
        prgAtkSpeedPlayer2 = (ProgressBar) findViewById(R.id.progressBarSpeedPlayer2);

        //Cargar barras hp
        pgrHPPlayer2.setMax((int) motorBatalla.player2_Actual.hpMax);
        pgrHPPlayer2.setProgress((int) motorBatalla.player2_Actual.hp);
        //Nombres de Chinpokomon
        txtNombrePersonajePlayer2 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer2);
        txtNombrePersonajePlayer2.setText(motorBatalla.player2_Actual.nombre);
    }

    public void cargar_Imagenes() {

        //Cargar imagen de chinpokomon
        imgPlayer1 = (ImageView) findViewById(R.id.imagePlayer1);
        imgPlayer2 = (ImageView) findViewById(R.id.imagePlayer2);

        String name10 = "gridview_" + motorBatalla.player1_Actual.nombre.toLowerCase();
        int resId10 = getResources().getIdentifier(name10, "drawable", getPackageName());
        imgPlayer1.setImageResource(resId10);

        String name20 = "gridview_" + motorBatalla.player2_Actual.nombre.toLowerCase();
        int resId20 = getResources().getIdentifier(name20, "drawable", getPackageName());
        imgPlayer2.setImageResource(resId20);

        //Cargar Imagenes de los 6 seleccionados
        imgSeleccion1 = (ImageView) findViewById(R.id.img_Seleccion1);
        imgSeleccion2 = (ImageView) findViewById(R.id.img_Seleccion2);
        imgSeleccion3 = (ImageView) findViewById(R.id.img_Seleccion3);
        imgSeleccion1.setOnClickListener(this);
        imgSeleccion2.setOnClickListener(this);
        imgSeleccion3.setOnClickListener(this);

        //Iconos player2
        imgSeleccion4 = (ImageView) findViewById(R.id.img_Seleccion4);
        imgSeleccion5 = (ImageView) findViewById(R.id.img_Seleccion5);
        imgSeleccion6 = (ImageView) findViewById(R.id.img_Seleccion6);
        imgSeleccion4.setOnClickListener(this);
        imgSeleccion5.setOnClickListener(this);
        imgSeleccion6.setOnClickListener(this);

        //Hacer imagen transparente si muere personaje
        if(motorBatalla.player1_Personaje1.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion1);
        }
        if(motorBatalla.player1_Personaje2.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion2);
        }
        if(motorBatalla.player1_Personaje3.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion3);
        }
        if(motorBatalla.player2_Personaje1.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion4);
        }
        if(motorBatalla.player2_Personaje2.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion5);
        }
        if(motorBatalla.player2_Personaje3.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion6);
        }

        String name1 = "gridview_" + motorBatalla.player1_Personaje1.nombre.toLowerCase();
        int resId1 = getResources().getIdentifier(name1, "drawable", getPackageName());
        imgSeleccion1.setImageResource(resId1);

        String name2 = "gridview_" + motorBatalla.player1_Personaje2.nombre.toLowerCase();
        int resId2 = getResources().getIdentifier(name2, "drawable", getPackageName());
        imgSeleccion2.setImageResource(resId2);

        String name3 = "gridview_" + motorBatalla.player1_Personaje3.nombre.toLowerCase();
        int resId3 = getResources().getIdentifier(name3, "drawable", getPackageName());
        imgSeleccion3.setImageResource(resId3);

        String name4 = "gridview_" + motorBatalla.player2_Personaje1.nombre.toLowerCase();
        int resId4 = getResources().getIdentifier(name4, "drawable", getPackageName());
        imgSeleccion4.setImageResource(resId4);

        String name5 = "gridview_" + motorBatalla.player2_Personaje2.nombre.toLowerCase();
        int resId5 = getResources().getIdentifier(name5, "drawable", getPackageName());
        imgSeleccion5.setImageResource(resId5);

        String name6 = "gridview_" + motorBatalla.player2_Personaje3.nombre.toLowerCase();
        int resId6 = getResources().getIdentifier(name6, "drawable", getPackageName());
        imgSeleccion6.setImageResource(resId6);

    }

    public void cargar_Tipo_Boton(Button button){
        String move_type;
        int i=0;
        while ( button.getText() != moves.ataques[i][0]){
            i = i + 1;
        }
        move_type = moves.ataques[i][1];
        String name = "button_style_" + move_type;
        int resId = getResources().getIdentifier(name, "drawable", getPackageName());
        button.setBackgroundResource( resId );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_attack1:
                atacarPlayer1(motorBatalla.player1_Actual.move1);
                break;

            case R.id.button_attack2:
                atacarPlayer1(motorBatalla.player1_Actual.move2);
                break;

            case R.id.button_attack3:
                atacarPlayer1(motorBatalla.player1_Actual.move3);
                break;

            case R.id.button_attack4:
                atacarPlayer1(motorBatalla.player1_Actual.move4);
                break;

            //Los siguientes 3 casos, cambian al chinpokomon actual por el seleccionado en la imagen
            case R.id.img_Seleccion1:
                if (motorBatalla.player1_Personaje1.hp >0) {
                    cambiarPersonajePlayer1(motorBatalla.player1_Personaje1);
                }
                break;

            case R.id.img_Seleccion2:
                if (motorBatalla.player1_Personaje2.hp >0) {
                    cambiarPersonajePlayer1(motorBatalla.player1_Personaje2);
                };
                break;

            case R.id.img_Seleccion3:
                if (motorBatalla.player1_Personaje3.hp >0) {
                    cambiarPersonajePlayer1(motorBatalla.player1_Personaje3);
                }
                break;

            //BOTONES DE TEST
            case R.id.button_fullRestore:
                fullRestore();
                break;

            case R.id.button_attackCPU:
                tokenPlayer2Atacando +=1;
                break;
        }
    }

    public void atacarPlayer1(Move move) {
        animaciones = new Animaciones(imgPlayer1, imgPlayer2, context);
        if (motorBatalla.player1_Actual.hp > 0 && motorBatalla.player2_Actual.hp > 0) {
            //Inicializa el ataque solo si los 2 personajes tiene hp, se pasa el nombre del ataque
            motorBatalla.combate(move, 1);
            txtViewBattle.setText(motorBatalla.leyenda_de_textView + " Daño:" + motorBatalla.damage_done);
            actualizarProgresBarHP((int) motorBatalla.player1_Actual.hp, (int) motorBatalla.player2_Actual.hp);
            habilitarDeshabilitarBotones(false);
            attackSpeedPlayer1();
            animaciones.animationPlayer1ATK(txtInformacionAtacar2, motorBatalla.flag_ataque,Integer.toString(motorBatalla.damage_done));
        }
    }

    //Este metodo se utiliza para que un contador active el ataque del CPU
    public void atacarCPU() {
        animaciones = new Animaciones(imgPlayer1, imgPlayer2, context);
        AI ai = new AI();
        String  ataqueCPU = ai.bestMove(motorBatalla.player1_Actual,motorBatalla.player2_Actual );
        move2 = new Move(ataqueCPU);
        motorBatalla.combate(move2, 2);
        txtViewBattle.setText(motorBatalla.leyenda_de_textView + " Daño:" + motorBatalla.damage_done);
        actualizarProgresBarHP((int) motorBatalla.player1_Actual.hp, (int) motorBatalla.player2_Actual.hp);
        animaciones.animationPlayer2ATK(txtInformacionAtacar1, motorBatalla.flag_ataque,Integer.toString(motorBatalla.damage_done));
    }

    //Actualizar ProgersBarHP cambia la barra de HP y el contador de abajo
    public void actualizarProgresBarHP(int player1HP, int player2HP) {
        pgrHPPlayer1.setProgress(player1HP);
        pgrHPPlayer2.setProgress(player2HP);
        txtHPPlayer1.setText("" + (int) motorBatalla.player1_Actual.hp);
        txtHPPlayer2.setText("" + (int) motorBatalla.player2_Actual.hp);

        //Valida si la batalla finalizo y/o realiza el cambio de pesonaje de CPU
        finalizarBatalla();


    }

    //Metodo para cambiar de personaje de player1 y activa el ataque de CPU cambiando su flag y activando su metodo atacar:CPU
    public void cambiarPersonajePlayer1(Personaje personaje) {
        motorBatalla.player1_Actual = personaje;
        cargar_Elementos_Player1();
        cargar_Imagenes();
        habilitarDeshabilitarBotones(false);
        attackSpeedPlayer1();

        if (progresStatusPlayer2 == 0) {
            attackSpeedCPU();
        }
    }

    public void cambiarPersonajeCPU() {

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

        attackSpeedCPU();


    }

    public void finalizarBatalla() {

        //Hacer imagen transparente si muere personaje
        if(motorBatalla.player1_Personaje1.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion1);
        }
        if(motorBatalla.player1_Personaje2.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion2);
        }
        if(motorBatalla.player1_Personaje3.hp ==0){
            animaciones.animationIconoMorir(imgSeleccion3);
        }

        //Si no quedan personajes vivos, manda mensaje final o cambia de personaje
        if ((motorBatalla.player1_Personaje1.hp + motorBatalla.player1_Personaje2.hp + motorBatalla.player1_Personaje3.hp) == 0) {
            txtViewBattle.setText("PERDISTE");
        } else if ((motorBatalla.player2_Personaje1.hp + motorBatalla.player2_Personaje2.hp + motorBatalla.player2_Personaje3.hp) == 0) {
            txtViewBattle.setText("GANASTE");
        } else if (motorBatalla.player2_Actual.hp == 0) {
            cambiarPersonajeCPU();
        }
    }

    public void habilitarDeshabilitarBotones(boolean a) {
        btnAtaque1.setEnabled(a);
        btnAtaque2.setEnabled(a);
        btnAtaque3.setEnabled(a);
        btnAtaque4.setEnabled(a);
    }

    //HILOS que utilizan la velocidad de ataque para habilitar los ataques
    public void attackSpeedCPU(){
        tokenPlayer2Atacando +=1;
        progresStatusPlayer2 = 0;
        final int token = tokenPlayer2Atacando;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( progresStatusPlayer2 < 100 && token == tokenPlayer2Atacando && motorBatalla.player1_Actual.hp > 0 && motorBatalla.player2_Actual.hp > 0){
                    progresStatusPlayer2 +=1;

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            prgAtkSpeedPlayer2.setProgress(progresStatusPlayer2);
                            if (progresStatusPlayer2 == 100 ){
                                atacarCPU();
                                progresStatusPlayer2 = 0;
                                if ( motorBatalla.player1_Actual.hp > 0){
                                    attackSpeedCPU();
                                }
                            }
                        }
                    });
                    try{
                        //Speed promedio 6000 y se le resta la velocidad de personaje x 10
                        Thread.sleep((long) (60 - motorBatalla.player2_Actual.speed / 10));
                    }
                    catch (InterruptedException e){e.printStackTrace();}
                }
                progresStatusPlayer2 = 0;
            }
        }).start();

    }

    public void attackSpeedPlayer1(){
        tokenPlayer1Atacando +=1;
        progresStatusPlayer1 = 0;
        final int token2 = tokenPlayer1Atacando;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( progresStatusPlayer1 < 100 && token2 == tokenPlayer1Atacando && motorBatalla.player1_Actual.hp > 0 && motorBatalla.player2_Actual.hp > 0){
                    progresStatusPlayer1 +=1;

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            prgAtkSpeedPlayer1.setProgress(progresStatusPlayer1);
                            if (progresStatusPlayer1 == 100 ){
                                habilitarDeshabilitarBotones(true);
                            }
                        }
                    });
                    try{
                        //Speed promedio 6000 y se le resta la velocidad de personaje x 10
                        Thread.sleep((long) (60 - motorBatalla.player1_Actual.speed / 10));
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
        txtHPPlayer1.setText("" + (int) motorBatalla.player1_Actual.hp);
        txtHPPlayer2.setText("" + (int) motorBatalla.player2_Actual.hp);
    }
}




