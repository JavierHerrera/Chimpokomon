package chimpokomon.com.example.pep.chimpokomon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.AnimeViewHolder> {
    private List<CharactersCardView> items;
    //Se crea eel objeto de MyInterface para llamar al metodo en SelectCharacter
    private static MyInterface mAdapterCallback;

    //Se recibe el context de SelectCharacter
    public CharactersAdapter(Context context) {
        try {
            this.mAdapterCallback = ((MyInterface) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder
    {

        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;
        public ImageView tipo_elemento;

        public AnimeViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            visitas = (TextView) v.findViewById(R.id.visitas);
            tipo_elemento = (ImageView) v.findViewById(R.id.tipo_elemento);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try {
                       SelectCharacterActivity.seleccion1=getAdapterPosition();
                        mAdapterCallback.foo();
                    } catch (ClassCastException exception) {
                        // do something
                    }
                }
            });
        }
    }

    //Metodo usado para hacer referencia al a interface
    public static interface AdapterCallback {
        void foo();
    }

    public CharactersAdapter(List<CharactersCardView> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_cardview, viewGroup, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder viewHolder, int position) {
        viewHolder.imagen.setImageResource(items.get(position).getImagen());
        viewHolder.nombre.setText(items.get(position).getNombre());
        viewHolder.visitas.setText("Visitas:"+String.valueOf(items.get(position).getVisitas()));
        viewHolder.tipo_elemento.setImageResource(items.get(position).getType());

    }

}