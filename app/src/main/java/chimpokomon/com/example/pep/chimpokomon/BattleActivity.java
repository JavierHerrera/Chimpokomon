package chimpokomon.com.example.pep.chimpokomon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewBattle;
    ProgressBar pgrHPPlayer2, pgrHPPlayer1;
    MediaPlayer mpMusicBattle;
    Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4;
    //BOTONES DE PRUEBA
    Button btn_full_Restore, btn_cpu_Attak;

    //Textos que se utilizan para cagar los display
    TextView textView_HPplayer1, textView_HPplayer2,textView_HPmax_Player1;
    TextView textViewNombreChinpoPlayer1,textViewNombreChinpoPlayer2;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

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

    }

    private void cargar_Elementos_Player1(){

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

        //TextView de HP
        textView_HPplayer1 = (TextView) findViewById(R.id.textView_HPcurrent_Player1);
        textView_HPmax_Player1 = (TextView) findViewById(R.id.textView_HPmax_Player1);
        textView_HPplayer1.setText(""+ (int)motorBatalla.player1_Actual.hp);
        textView_HPmax_Player1.setText("/"+ (int)motorBatalla.player1_Actual.hpMax );

        //Se crea ProgresBar y texto de batalla TEST
        pgrHPPlayer1 = (ProgressBar) findViewById(R.id.progressBarHPPlayer1);
        //Cargar barras hp
        pgrHPPlayer1.setMax((int) motorBatalla.player1_Actual.hpMax);
        pgrHPPlayer1.setProgress((int) motorBatalla.player1_Actual.hp);
        //Nombres de Chinpokomon
        textViewNombreChinpoPlayer1 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer1);
        textViewNombreChinpoPlayer1.setText(motorBatalla.player1_Actual.nombre);
    }

    private void cargar_Elementos_Player2(){

        //TextView de HP
        textView_HPplayer2 = (TextView) findViewById(R.id.textViewHPViewChinpoCPU);
        textView_HPplayer2.setText(""+ (int)motorBatalla.player2_Actual.hp);

        //Se crea ProgresBar y texto de batalla
        pgrHPPlayer2 = (ProgressBar) findViewById(R.id.progressBarHPPlayer2);
        //Cargar barras hp
        pgrHPPlayer2.setMax((int) motorBatalla.player2_Actual.hpMax);
        pgrHPPlayer2.setProgress((int) motorBatalla.player2_Actual.hp);
        //Nombres de Chinpokomon
        textViewNombreChinpoPlayer2 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer2);
        textViewNombreChinpoPlayer2.setText(motorBatalla.player2_Actual.nombre);
    }

    public void cargar_Imagenes(){

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_attack1:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque1.getText(),1);
                actualizarProgresBarHP((int)motorBatalla.player1_Actual.hp,(int)motorBatalla.player2_Actual.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;

            case R.id.button_attack2:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque2.getText(),1);
                actualizarProgresBarHP((int)motorBatalla.player1_Actual.hp,(int)motorBatalla.player2_Actual.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;

            case R.id.button_attack3:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque3.getText(),1);
                actualizarProgresBarHP((int)motorBatalla.player1_Actual.hp,(int)motorBatalla.player2_Actual.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;

            case R.id.button_attack4:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque4.getText(),1);
                actualizarProgresBarHP((int)motorBatalla.player1_Actual.hp,(int)motorBatalla.player2_Actual.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;

            //Los siguientes 3 casos, cambian al chinpokomon actual por el seleccionado en la imagen
            case R.id.img_Seleccion1:
                cambiar_personaje(motorBatalla.player1_Personaje1);
                break;

            case R.id.img_Seleccion2:
                cambiar_personaje(motorBatalla.player1_Personaje2);
                break;

            case R.id.img_Seleccion3:
                cambiar_personaje(motorBatalla.player1_Personaje3);
                break;

            //BOTONES DE TEST
            case R.id.button_fullRestore:
                fullRestore();
                break;

            case R.id.button_attackCPU:

                //Inicializa el combate de CPU prueba
                String ataqueCPU;
                if ( 1 == (int) (Math.random() * 2) )
                {ataqueCPU = motorBatalla.player2_Actual.move1;}
                else
                {ataqueCPU = motorBatalla.player2_Actual.move2;}

                motorBatalla.combate(ataqueCPU,2);
                actualizarProgresBarHP((int)motorBatalla.player1_Actual.hp,(int)motorBatalla.player2_Actual.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);                break;
        }
    }

    public void actualizarProgresBarHP(int player1HP, int player2HP ){
        pgrHPPlayer1.setProgress(player1HP);
        pgrHPPlayer2.setProgress(player2HP);

        actualizar_contadores_HP();
    }

    public void fullRestore(){
        pgrHPPlayer1.setProgress(pgrHPPlayer1.getMax());
        pgrHPPlayer2.setProgress(pgrHPPlayer2.getMax());

        motorBatalla.player1_Actual.hp = pgrHPPlayer1.getMax();
        motorBatalla.player2_Actual.hp = pgrHPPlayer2.getMax();

        actualizar_contadores_HP();
    }

    public void actualizar_contadores_HP()   {
        textView_HPplayer1.setText(""+ (int)motorBatalla.player1_Actual.hp);
        textView_HPplayer2.setText(""+ (int)motorBatalla.player2_Actual.hp);
    }

    public void esperar (int segundos) {
        try {
            Thread.sleep (segundos*1000);
        } catch (Exception e) {
// Mensaje en caso de que falle
        }
    }

    public void cambiar_personaje(Personaje personaje){
        motorBatalla.player1_Actual = personaje;
        cargar_Elementos_Player1();
        cargar_Imagenes();
    }
}

