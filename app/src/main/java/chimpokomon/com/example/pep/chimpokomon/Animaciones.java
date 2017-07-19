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
    private Animation animation,animationTest;

    public  Animaciones(ImageView imagePlayer1,ImageView imagePlayer2, Context context){
        this.imagePlayer1 = imagePlayer1;
        this.imagePlayer2 = imagePlayer2;
        this.context = context;
    }

    //Mueve hacia enfrente la imagen del personaje player1
    public void animationPlayer1ATK(TextView texto, int flag_ataque, String damage_done){
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_atacar_player1);
        imagePlayer1.startAnimation(animation);
        animationTakeHit(imagePlayer2);
        animationText(texto, flag_ataque, damage_done);
    }

    //Mueve hacia enfrente la imagen del personaje player1
    public void animationPlayer2ATK(TextView texto, int flag_ataque, String damage_done){
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_atacar_player2);
        imagePlayer2.startAnimation(animation);
        animationTakeHit(imagePlayer1);
        animationText(texto, flag_ataque, damage_done);
    }

    //Mueve la imagen del personaje al recibir un golpe
    public void animationTakeHit(ImageView m){
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_recibir_golpe);
        m.startAnimation(animation);
    }

    //Muestra el daño realizado con diferentes animacioens segun el caso
    public void animationText(TextView texto, int flag_ataque, String damage_done){
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_texto_atacar);

        if (flag_ataque == 0){
            texto.setTextColor(Color.parseColor("#778899"));
            texto.setTextSize(17);
            texto.setText("Miss");
            texto.startAnimation(animation);                        //Miss
        }else if(flag_ataque == 1){
            texto.setTextColor(Color.parseColor("#FF4500"));
            texto.setTextSize(17);
            texto.setText(damage_done);
            texto.startAnimation(animation);                       //Normal hit
        }else if(flag_ataque == 2){
            texto.setTextColor(Color.parseColor("#FF0000"));
            texto.setTextSize(22);
            texto.setText(damage_done);
            texto.startAnimation(animation);                       //Super effective hit
        }else if(flag_ataque == 3){
            texto.setTextColor(Color.parseColor("#FFA500"));
            texto.setTextSize(12);
            texto.setText(damage_done);                            //Not very effective hit
            texto.startAnimation(animation);
        }else if(flag_ataque == 11){
            texto.setTextColor(Color.parseColor("#FF4500"));
            texto.setTextSize(17);
            texto.setText(damage_done);
            critical(texto);                                      //Normal critical hit
        }else if(flag_ataque == 12){
            texto.setTextColor(Color.parseColor("#FF0000"));
            texto.setTextSize(22);
            texto.setText(damage_done);
            critical(texto);                                       //Super effective critical hit
        }else if(flag_ataque == 13){
            texto.setTextColor(Color.parseColor("#FFA500"));
            texto.setTextSize(12);
            texto.setText(damage_done);
            critical(texto);                                       //Not very effective critical hit
        }
    }

    //Incrementa el tamaño del texto cuando es critical
    private void critical(TextView texto){
        animation = AnimationUtils.loadAnimation(context,R.anim.aim_critical);
        texto.startAnimation(animation);
    }

    //Desvanece el icono del personaque muerto
    public void animationIconoMorir(ImageView m){
        animation = AnimationUtils.loadAnimation(context,R.anim.aim_transparencia_icono_morir);


    animation.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {


            }

        @Override
        public void onAnimationRepeat(Animation animation) {
            TextView texto = null;
            texto.setText("yeeei");
            texto.startAnimation(animation);
        }
    });

        m.startAnimation(animation);

    }
}
