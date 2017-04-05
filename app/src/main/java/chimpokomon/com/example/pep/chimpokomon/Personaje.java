package chimpokomon.com.example.pep.chimpokomon;

public class Personaje {


    public String nombre;
    public String tipo;
    public double damage;
    public double hp;
    public double hpMax;
    public String move1;
    public String move2;
    public String move3;
    public String move4;

    public Personaje(int ID){

        BDChinpokomones BDChinpo = new BDChinpokomones();

        nombre = BDChinpo.c[ID][1];
        tipo = BDChinpo.c[ID][2];
        damage =  Double.parseDouble(BDChinpo.c[ID][3]);
        hp = Double.parseDouble(BDChinpo.c[ID][4]);
        move1 = BDChinpo.c[ID][5];
        move2 = BDChinpo.c[ID][6];
        move3 = BDChinpo.c[ID][7];
        move4 = BDChinpo.c[ID][8];
        hpMax = hp;

    }
}
