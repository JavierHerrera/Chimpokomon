package chimpokomon.com.example.pep.chimpokomon;

import java.util.Timer;

public class BDMoves {

    String [][] bd_ataques = new String[12][3];
    public   BDMoves(){

        //Nombre, tipo, daño,accuracy
        bd_ataques[0][0] = "Ember";
        bd_ataques[0][1] = "fire";
        bd_ataques[0][2] = "40";

        bd_ataques[1][0] = "Bubble";
        bd_ataques[1][1] = "water";
        bd_ataques[1][2] = "40";

        bd_ataques[2][0] = "Vine Whip";
        bd_ataques[2][1] = "grass";
        bd_ataques[2][2] = "45";

        bd_ataques[3][0] = "Quick Attack";
        bd_ataques[3][1] = "normal";
        bd_ataques[3][2] = "40";

        bd_ataques[4][0] = "Scratch";
        bd_ataques[4][1] = "normal";
        bd_ataques[4][2] = "40";

        bd_ataques[5][0] = "Tackle";
        bd_ataques[5][1] = "normal";
        bd_ataques[5][2] = "40";

        bd_ataques[6][0] = "Bite";
        bd_ataques[6][1] = "dark";
        bd_ataques[6][2] = "60";

        bd_ataques[7][0] = "Metal Claw";
        bd_ataques[7][1] = "steel";
        bd_ataques[7][2] = "40";

        bd_ataques[8][0] = "Ice Beam";
        bd_ataques[8][1] = "ice";
        bd_ataques[8][2] = "90";

        bd_ataques[9][0] = "Rock Tomb";
        bd_ataques[9][1] = "rock";
        bd_ataques[9][2] = "60";

        bd_ataques[10][0] = "Sludge";
        bd_ataques[10][1] = "posion";
        bd_ataques[10][2] = "65";

        bd_ataques[11][0] = "Energy Ball";
        bd_ataques[11][1] = "grass";
        bd_ataques[11][2] = "60";


    }
}
