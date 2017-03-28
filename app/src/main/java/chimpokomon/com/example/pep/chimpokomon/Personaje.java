package chimpokomon.com.example.pep.chimpokomon;

public class Personaje {


    public String nombre;
    public String tipo;
    public double damage;
    public double hp;
    public String move1;
    public String move2;

    public Personaje(int ID){

        ID = ID;
        BDChinpokomones test = new BDChinpokomones();

        nombre = test.c[ID][1];
        tipo = test.c[ID][2];
        damage =  Double.parseDouble(test.c[ID][3]);
        hp = Double.parseDouble(test.c[ID][4]);
        move1 = test.c[ID][5];
        move2 = test.c[ID][6];


    }
}
