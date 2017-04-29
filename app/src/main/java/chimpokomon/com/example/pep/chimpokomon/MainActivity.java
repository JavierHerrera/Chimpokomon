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
    public MediaPlayer mp_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen activity
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Boton que cambia de activity
        imgButton = (ImageButton) findViewById(R.id.imgButtonLetsdothis);
        imgButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgButtonLetsdothis:
                imgButton.setEnabled(false);

                //Inicia audio inicio
                mp_intro = MediaPlayer.create(this, R.raw.chinpokomon_roar);
                mp_intro.start();

                //Verifica que se termine el audio para realizar alguna accion
                mp_intro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        finish(); // al finalizar el audio realiza lo siguiente

                        //Cambia de activity a seleccion de chinpokomon
                        Intent intent = new Intent(MainActivity.this, SelectCharacterActivity.class);
                        startActivity(intent);
                    }
                });
            break;
        }
    }
}
