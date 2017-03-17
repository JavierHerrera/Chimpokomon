package chimpokomon.com.example.pep.chimpokomon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectCharacter extends AppCompatActivity {

    // Variable para la musica
    private MediaPlayer mpStreetFighter;
    // Declarar instancias globales de RecyclerView
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        //Inicia audio de streetfighter
        mpStreetFighter = MediaPlayer.create(SelectCharacter.this, R.raw.streetfightersong);
        mpStreetFighter.start();

        // Inicializar Animes
        List items = new ArrayList();

        items.add(new Characters(R.drawable.gridview_mousetik, "Mousetik", 230));
        items.add(new Characters(R.drawable.gridview_pengin, "Penguin", 456));
        items.add(new Characters(R.drawable.gridview_shoe, "Shoe", 342));

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
}
