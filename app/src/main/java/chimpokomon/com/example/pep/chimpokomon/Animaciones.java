package chimpokomon.com.example.pep.chimpokomon;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Animaciones {

    ImageView imagePlayer1, imagePlayer2;
    Context context;


    public  Animaciones(ImageView imagePlayer1,ImageView imagePlayer2, Context context){
        this.imagePlayer1 = imagePlayer1;
        this.imagePlayer2 = imagePlayer2;
        this.context = context;
    }

    public void animation_ATK_player1(){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_atacar_player1);
        imagePlayer1.startAnimation(animation);
        animation_recibir_golpe(imagePlayer2);
    }

    public void animation_ATK_player2(){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_atacar_player2);
        imagePlayer2.startAnimation(animation);
        animation_recibir_golpe(imagePlayer1);
    }

    public void animation_recibir_golpe(ImageView m){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_recibir_golpe);
        m.startAnimation(animation);
    }

    public void animation_texto_atacar(TextView textView, String s ){

        Animation animation;
        animation = AnimationUtils.loadAnimation(context,R.anim.anim_texto_atacar);
        textView.setText(s);
        textView.startAnimation(animation);
    }
}
