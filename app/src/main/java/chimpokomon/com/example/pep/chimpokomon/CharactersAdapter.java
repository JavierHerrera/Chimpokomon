package chimpokomon.com.example.pep.chimpokomon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {
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

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView hp, speed, attack, defecne;
        public ImageView tipo_elemento;

        public ViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            hp = (TextView) v.findViewById(R.id.hp);
            speed = (TextView) v.findViewById(R.id.speed);
            attack = (TextView) v.findViewById(R.id.attack);
            defecne = (TextView) v.findViewById(R.id.defence);
            tipo_elemento = (ImageView) v.findViewById(R.id.tipo_elemento);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v)
                {
                    try {
                        if ( SelectCharacterActivity.flag_Selecction == 1 ) {
                            SelectCharacterActivity.seleccion1 =  getAdapterPosition();
                            mAdapterCallback.foo();
                        }
                        else if (SelectCharacterActivity.flag_Selecction == 2 && SelectCharacterActivity.seleccion1 !=  getAdapterPosition())  {
                            SelectCharacterActivity.seleccion2 = getAdapterPosition();
                            mAdapterCallback.foo();
                        }
                        else if (SelectCharacterActivity.flag_Selecction == 3 && (SelectCharacterActivity.seleccion1 !=  getAdapterPosition() && SelectCharacterActivity.seleccion2 !=  getAdapterPosition())){
                            SelectCharacterActivity.seleccion3 = getAdapterPosition();
                            mAdapterCallback.foo();
                        }


                    } catch (ClassCastException exception) {

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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.imagen.setImageResource(items.get(position).getImagen());
        viewHolder.nombre.setText(items.get(position).getNombre());
        viewHolder.hp.setText("Hp "+String.valueOf(items.get(position).getHp()));
        viewHolder.speed.setText("Speed "+String.valueOf(items.get(position).getSpeed()));
        viewHolder.attack.setText("Atk "+String.valueOf(items.get(position).getAttack()));
        viewHolder.defecne.setText("Def "+String.valueOf(items.get(position).getDefence()));
        viewHolder.tipo_elemento.setImageResource(items.get(position).getType());

    }

}