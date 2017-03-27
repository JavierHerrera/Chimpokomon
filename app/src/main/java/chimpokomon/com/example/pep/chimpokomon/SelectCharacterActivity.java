package chimpokomon.com.example.pep.chimpokomon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SelectCharacterActivity extends AppCompatActivity implements View.OnClickListener {

    //Boton de prueba para cambiar a layout de batalla
    Button test_battle_button;

    // Variable para la musica
    private MediaPlayer mpStreetFighter;
    private MediaPlayer mpSeleccionarPersonaje;

    // Declarar instancias globales de RecyclerView
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        //Inicializar botton dep rueba
        test_battle_button = (Button) findViewById(R.id.test_battle_button);
        test_battle_button.setOnClickListener(this);

        //Inicia audio de streetfighter
        mpStreetFighter = MediaPlayer.create(SelectCharacterActivity.this, R.raw.streetfightersong);
        mpStreetFighter.start();

        // Inicializar Animes
        List items = new ArrayList();

        items.add(new CharactersCardView(R.drawable.gridview_mousetik, "Mousetik", 230, R.drawable.type_grass));
        items.add(new CharactersCardView(R.drawable.gridview_pengin, "Penguin", 456, R.drawable.type_water));
        items.add(new CharactersCardView(R.drawable.gridview_shoe, "Shoe", 342, R.drawable.type_fire));

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new CharactersAdapter(items);
        recycler.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_battle_button:

                //Detener musica e inicia sonido de seleccion de caracter
                mpSeleccionarPersonaje = MediaPlayer.create(this, R.raw.super_street_fighter_personaje_seleccionado);
                mpStreetFighter.stop();
                mpSeleccionarPersonaje.start();



                mpSeleccionarPersonaje.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        finish(); // al finalizar el audio realiza lo siguiente

                    //Cambia de activity a batalla
                    Intent intent = new Intent(SelectCharacterActivity.this, BattleActivity.class);
                    startActivity(intent);
                    }
                });

                break;
        }
    }
}
