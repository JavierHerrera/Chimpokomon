package chimpokomon.com.example.pep.chimpokomon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton imgButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen activity
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Cambia de activity
        imgButton = (ImageButton) findViewById(R.id.imgButtonLetsdothis);
        imgButton.setOnClickListener(this);

        //Inicia audio intro

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgButtonLetsdothis:


                //Audio audio = new Audio();
                //audio.stopIntroChimpoko(this);

                Intent intent = new Intent(MainActivity.this,SelectCharacter.class);
                startActivity(intent);

                //Detiene audio de intro


                //Inicia audio de seleccion de chimpokomon
                //MediaPlayer mpStreetFighter = MediaPlayer.create(MainActivity.this, R.raw.streetfightersong);
                //mpStreetFighter.start();
            break;
        }
    }
}
