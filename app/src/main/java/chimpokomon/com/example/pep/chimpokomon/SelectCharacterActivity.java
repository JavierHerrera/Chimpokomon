package chimpokomon.com.example.pep.chimpokomon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SelectCharacterActivity extends AppCompatActivity implements View.OnClickListener,MyInterface {

    //Boton de prueba para cambiar a layout de batalla
    Button test_battle_button;

    // Variable para la musica
    private MediaPlayer mpStreetFighter, mpFinalizarSeleccion, mpSeleccionarPersonaje;

    // Declarar instancias globales de RecyclerView
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    //Variables static para seleccionar el equipo
    static int flag_Selecction = 1;
    static int seleccion1, seleccion2, seleccion3;
    ImageView icon1_Player, icon2_Player,icon3_Player;
    BDChinpokomones BDChinpo = new BDChinpokomones();
    private CharactersAdapter mMyAdapter;

    //foo es la conexion para llamar metodos, entre el adapter y activity a travez de una interface
    @Override
    public void foo() {
        callbackclick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se pasa el context al objeto mMyAdapter
        this.mMyAdapter = new CharactersAdapter(this);

        //Iniciar Iconos
        seleccion1 = 99;
        seleccion2 = 99;
        seleccion3 = 99;

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
        items.add(new CharactersCardView(R.drawable.gridview_penguin, "Penguin", 456, R.drawable.type_water));
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

    //Sin uso por el momento
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_battle_button:

                break;
        }
    }

    //Se activa desde el adaptador al realizar un click en una cardview
    public  void callbackclick() {

        if (seleccion1 < 99 && flag_Selecction == 1) {

            mpSeleccionarPersonaje = MediaPlayer.create(SelectCharacterActivity.this, R.raw.super_street_fighter_cambio_personaje);
            mpSeleccionarPersonaje.start();
            cargar_Icono(icon1_Player,seleccion1,"icon1_Player");
            flag_Selecction = flag_Selecction +1;

        } else if (seleccion2 < 99 && flag_Selecction ==2) {

            mpSeleccionarPersonaje = MediaPlayer.create(this, R.raw.super_street_fighter_cambio_personaje);
            mpSeleccionarPersonaje.start();
            cargar_Icono(icon2_Player,seleccion2,"icon2_Player");
            flag_Selecction = flag_Selecction +1;

        } else if (seleccion3 < 99 && flag_Selecction ==3){

            cargar_Icono(icon3_Player,seleccion3,"icon3_Player");

            //Unavez Insertado las 3 imagenes se cambia de activity
            //Detener musica e inicia sonido de seleccion de caracter
            mpFinalizarSeleccion = MediaPlayer.create(this, R.raw.super_street_fighter_personaje_seleccionado);
            mpStreetFighter.stop();
            mpFinalizarSeleccion.start();

            mpFinalizarSeleccion.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    finish(); // al finalizar el audio realiza lo siguiente

                    //Cambia de activity a batalla
                    Intent intent = new Intent(SelectCharacterActivity.this, BattleActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    public  void cargar_Icono(ImageView icon, int seleccion, String nombre_ImageView){

        //Buscar nombre del chinpokomon y usarlo para buscar e insertar su imagen
        int ID = getResources().getIdentifier(nombre_ImageView, "id", getPackageName());
        icon = (ImageView) findViewById(ID);
        String name = "gridview_" +BDChinpo.c[seleccion][1].toLowerCase();
        int resId = getResources().getIdentifier(name, "drawable", getPackageName());
        icon.setImageResource(resId);

    }
}

