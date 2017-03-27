package chimpokomon.com.example.pep.chimpokomon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewBattle;
    ProgressBar pgrHPCPU,pgrHPPlayer;
    MediaPlayer mpMusicBattle;
    Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4;

    //Se inicializa objeto motorBatalla y se pasan los ID de los 2 personajes
   MotorBatalla motorBatalla = new MotorBatalla(1,2);
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
        textViewBattle.setText((String)motorBatalla.player1.move1);


        //Se crea ProgresBar y texto de batalla TEST
        pgrHPCPU = (ProgressBar) findViewById(R.id.progressBarHPCPU);
        pgrHPPlayer = (ProgressBar) findViewById(R.id.progressBarHPPlayer);

        //Cargar barras hp
        setPgrBoth( (int) motorBatalla.player1.hp,(int)motorBatalla.player2.hp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_attack1:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque1.getText(),1);
                setPgrHPCPU((int)motorBatalla.defensor.hp);
                textViewBattle.setText(motorBatalla.leyenda + " HP:" + motorBatalla.defensor.hp);
                break;

            case R.id.button_attack2:

                //Inicializa el combate
                motorBatalla.combate((String) btnAtaque2.getText(),1);
                setPgrHPCPU((int)motorBatalla.defensor.hp);
                textViewBattle.setText(motorBatalla.leyenda + " HP:" + motorBatalla.defensor.hp);
                break;

            case R.id.button_attack3:

                pgrHPCPU.setProgress(pgrHPCPU.getMax());
                motorBatalla.player1.hp = pgrHPCPU.getMax();
                pgrHPPlayer.setProgress(pgrHPPlayer.getMax());
                motorBatalla.player2.hp=pgrHPPlayer.getMax();
                break;

            case R.id.button_attack4:

                //Inicializa el combate de CPU prueba
                String ataqueCPU;
                if ( 1 == (int) (Math.random() * 2) ) {
                ataqueCPU = motorBatalla.player2.move1;
                }
                else{
                    ataqueCPU = motorBatalla.player2.move2;
                }

                motorBatalla.combate(ataqueCPU,2);
                setPgrHPPlayer((int)motorBatalla.atacante.hp);
                textViewBattle.setText(motorBatalla.leyenda + " HP:" + motorBatalla.atacante.hp);
                break;
        }
    }

    public void setPgrHPCPU(int cpu_hp){
        pgrHPCPU.setProgress(cpu_hp);

    }
    public void setPgrHPPlayer(int player_hp){
        pgrHPPlayer.setProgress(player_hp);

    }
    public void setPgrBoth(int player_hp, int cpu_hp){
        pgrHPPlayer.setMax(player_hp);
        pgrHPCPU.setMax(cpu_hp);
        pgrHPPlayer.setProgress(player_hp);
        pgrHPCPU.setProgress(cpu_hp);


    }
}

