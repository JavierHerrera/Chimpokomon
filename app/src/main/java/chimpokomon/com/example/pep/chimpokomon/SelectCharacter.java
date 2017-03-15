package chimpokomon.com.example.pep.chimpokomon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class SelectCharacter extends AppCompatActivity {

    public MediaPlayer mpStreetFighter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        //Inicia audio de streetfighter
        mpStreetFighter = MediaPlayer.create(SelectCharacter.this, R.raw.streetfightersong);
        mpStreetFighter.start();
    }
}
