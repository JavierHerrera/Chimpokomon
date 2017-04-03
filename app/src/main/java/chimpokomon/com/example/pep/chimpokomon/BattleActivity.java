package chimpokomon.com.example.pep.chimpokomon;

import android.content.res.Resources;
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
    ProgressBar pgrHPCPU,pgrHPPlayer;
    MediaPlayer mpMusicBattle;
    Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4;

    //Textos que se utilizan para cagar los display
    TextView textView_HPplayer1, textView_HPplayer2,textView_HPmax_Player1;
    TextView textViewNombreChinpoPlayer1,textViewNombreChinpoPlayer2;
    ImageView imagePlayer1, imagePlayer2;

    //Se inicializa objeto motorBatalla y se pasan los ID de los 2 personajes
    MotorBatalla motorBatalla = new MotorBatalla(SelectCharacterActivity.seleccion1,(int) (Math.random() * 3));
    BDMoves moves = new BDMoves();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        //Se pasan los datos de los personajes apra cargar sus datos
        cargarElementos();
    }

    private void cargarElementos(){

        //Inicia musica de batalla
        mpMusicBattle = MediaPlayer.create(this, R.raw.trainer_battle_song);
        mpMusicBattle.start();

        //Se crean los botones y se cambia el nombre segun el ataque
        btnAtaque1 = (Button) findViewById(R.id.button_attack1);
        btnAtaque2 = (Button) findViewById(R.id.button_attack2);
        btnAtaque3 = (Button) findViewById(R.id.button_attack3);
        btnAtaque4 = (Button) findViewById(R.id.button_attack4);
        btnAtaque1.setOnClickListener(this);
        btnAtaque2.setOnClickListener(this);
        btnAtaque3.setOnClickListener(this);
        btnAtaque4.setOnClickListener(this);
        btnAtaque1.setText(motorBatalla.player1.move1);
        btnAtaque2.setText(motorBatalla.player1.move2);
        btnAtaque3.setText("Full Restore");
        btnAtaque4.setText("CPU Attak");

        //Se inicializa el texto en la batalla y se pone como prueba el nombre del primer ataque
        textViewBattle = (TextView) findViewById(R.id.textoBatalla);
        //TextView de HP
        textView_HPplayer1 = (TextView) findViewById(R.id.textView_HPcurrent_Player1);
        textView_HPplayer1.setText(""+ (int)motorBatalla.player1.hp);
        textView_HPplayer2 = (TextView) findViewById(R.id.textViewHPViewChinpoCPU);
        textView_HPplayer2.setText(""+ (int)motorBatalla.player2.hp);
        textView_HPmax_Player1 = (TextView) findViewById(R.id.textView_HPmax_Player1);
        textView_HPmax_Player1.setText("/"+ (int)motorBatalla.player1.hp );

        //Se crea ProgresBar y texto de batalla TEST
        pgrHPPlayer = (ProgressBar) findViewById(R.id.progressBarHPPlayer);
        pgrHPCPU = (ProgressBar) findViewById(R.id.progressBarHPPlayer2);
        //Cargar barras hp
        pgrHPPlayer.setMax((int) motorBatalla.player1.hp);
        pgrHPCPU.setMax((int) motorBatalla.player2.hp);
        pgrHPPlayer.setProgress((int) motorBatalla.player1.hp);
        pgrHPCPU.setProgress((int) motorBatalla.player2.hp);
        //Nombres de Chinpokomon
        textViewNombreChinpoPlayer1 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer1);
        textViewNombreChinpoPlayer2 = (TextView) findViewById(R.id.textViewNombreChinpoPlayer2);
        textViewNombreChinpoPlayer1.setText(motorBatalla.player1.nombre);
        textViewNombreChinpoPlayer2.setText(motorBatalla.player2.nombre);

        cargarImagen();
    }

    public void cargarImagen(){

        //Cargar imagen de chinpokomon
        imagePlayer1 = (ImageView) findViewById(R.id.imagePlayer1);
        imagePlayer2 = (ImageView) findViewById(R.id.imagePlayer2);

        String name1 = "gridview_" + motorBatalla.player1.nombre.toLowerCase();
        int resId1 = getResources().getIdentifier(name1, "drawable", getPackageName());
        imagePlayer1.setImageResource(resId1);

        String name2 = "gridview_" + motorBatalla.player2.nombre.toLowerCase();
        int resId2 = getResources().getIdentifier(name2, "drawable", getPackageName());
        imagePlayer2.setImageResource(resId2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_attack1:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque1.getText(),1);
                actualizarProgresBarHP((int)motorBatalla.player1.hp,(int)motorBatalla.player2.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;

            case R.id.button_attack2:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque2.getText(),1);
                actualizarProgresBarHP((int)motorBatalla.player1.hp,(int)motorBatalla.player2.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;

            case R.id.button_attack3:

                //fullRestore();
                break;

            case R.id.button_attack4:

                //Inicializa el combate de CPU prueba
                String ataqueCPU;
                if ( 1 == (int) (Math.random() * 2) )
                {ataqueCPU = motorBatalla.player2.move1;}
                else
                {ataqueCPU = motorBatalla.player2.move2;}

                motorBatalla.combate(ataqueCPU,2);
                actualizarProgresBarHP((int)motorBatalla.player1.hp,(int)motorBatalla.player2.hp);
                textViewBattle.setText(motorBatalla.leyenda + " Daño:" + motorBatalla.damage_done);
                break;
        }
    }

    public void actualizarProgresBarHP(int player1HP, int player2HP ){
        pgrHPPlayer.setProgress(player1HP);
        pgrHPCPU.setProgress(player2HP);

        actualizar_contadores_HP();
    }

    public void fullRestore(){
        pgrHPPlayer.setProgress(pgrHPPlayer.getMax());
        pgrHPCPU.setProgress(pgrHPCPU.getMax());

        motorBatalla.player1.hp = pgrHPPlayer.getMax();
        motorBatalla.player2.hp = pgrHPCPU.getMax();

        actualizar_contadores_HP();
    }

    public void actualizar_contadores_HP()   {
        textView_HPplayer1.setText(""+ (int)motorBatalla.player1.hp);
        textView_HPplayer2.setText(""+ (int)motorBatalla.player2.hp);
    }

    public void esperar (int segundos) {
        try {
            Thread.sleep (segundos*1000);
        } catch (Exception e) {
// Mensaje en caso de que falle
        }
    }
}

