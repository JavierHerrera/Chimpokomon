package chimpokomon.com.example.pep.chimpokomon;

public class Personaje {

    String [][] c = new String[3][7];

    public  void inicializar_datos(){

        //ID, Nombre, tipo, daño, hp, atauqe1, ataque 2
        c[0][0] = "1";
        c[0][1] = "Mousetik";
        c[0][2] = "grass";
        c[0][3] = "13";
        c[0][4] = "130";
        c[0][5] ="Quick Attack";
        c[0][6] ="Vine Whip";

        c[1][0] = "2";
        c[1][1] = "Penguin";
        c[1][2] = "water";
        c[1][3] = "10";
        c[1][4] = "150";
        c[1][5] ="Scratch";
        c[1][6] ="Bubble";

        c[2][0] = "3";
        c[2][1] = "Shoe";
        c[2][2] = "fire";
        c[2][3] = "15";
        c[2][4] = "110";
        c[2][5] ="Tackle";
        c[2][6] ="Ember";

    }
}
