package chimpokomon.com.example.pep.chimpokomon;

import android.content.Context;
import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Animaciones {

    private ImageView imagePlayer1, imagePlayer2;
    private Context context;


    public  Animaciones(ImageView imagePlayer1,ImageView imagePlayer2, Context context){
        this.imagePlayer1 = imagePlayer1;
        this.imagePlayer2 = imagePlayer2;
        this.context = context;
    }

    public void animation_ATK_player1(TextView texto, int flag_ataque, String damage_done){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_atacar_player1);
        imagePlayer1.startAnimation(animation);
        animation_recibir_golpe(imagePlayer2);
        animation_texto_atacar(texto, flag_ataque, damage_done);
    }

    public void animation_ATK_player2(TextView texto, int flag_ataque, String damage_done){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_atacar_player2);
        imagePlayer2.startAnimation(animation);
        animation_recibir_golpe(imagePlayer1);
        animation_texto_atacar(texto, flag_ataque, damage_done);
    }

    public void animation_recibir_golpe(ImageView m){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_recibir_golpe);
        m.startAnimation(animation);
    }

    public void animation_texto_atacar(TextView texto, int flag_ataque, String damage_done){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_texto_atacar);



        if (flag_ataque == 0){
            texto.setTextColor(Color.parseColor("#778899"));
            texto.setTextSize(17);
            texto.setText("Miss");
            texto.startAnimation(animation);
        }
        else if(flag_ataque == 1){
            texto.setTextColor(Color.parseColor("#FF4500"));
            texto.setTextSize(17);
            texto.setText(damage_done);
            texto.startAnimation(animation);
        }

        else if(flag_ataque == 2){
            texto.setTextColor(Color.parseColor("#FF0000"));
            texto.setTextSize(22);
            texto.setText(damage_done);
            texto.startAnimation(animation);
        }

        else if(flag_ataque == 3){
            texto.setTextColor(Color.parseColor("#FFA500"));
            texto.setTextSize(12);
            texto.setText(damage_done);
            texto.startAnimation(animation);
        }

        else if(flag_ataque == 11){
            texto.setTextColor(Color.parseColor("#FF4500"));
            texto.setTextSize(17);
            texto.setText(damage_done);
            critical(texto);
        }
        else if(flag_ataque == 12){
            texto.setTextColor(Color.parseColor("#FF0000"));
            texto.setTextSize(22);
            texto.setText(damage_done);
            critical(texto);
        }

        else if(flag_ataque == 13){
            texto.setTextColor(Color.parseColor("#FFA500"));
            texto.setTextSize(12);
            texto.setText(damage_done);
            critical(texto);
        }

    }

    private void critical(TextView texto){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.aim_critical);
        texto.startAnimation(animation);
    }

    public void animation_icono_morir(ImageView m){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.aim_transparencia_icono_morir);
        m.startAnimation(animation);
    }
}
