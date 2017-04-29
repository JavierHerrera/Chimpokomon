package chimpokomon.com.example.pep.chimpokomon;

public class Personaje {

    public int id;
    public String nombre;
    public String tipo;
    public double damage;
    public double hp;
    public double hpMax;
    public String move1;
    public String move2;
    public String move3;
    public String move4;
    public double defence;
    public double speed;

    public Personaje(int ID){

        DatosChinpokomones BDChinpo = new DatosChinpokomones();

        id = Integer.parseInt(BDChinpo.c[ID][0]);
        nombre = BDChinpo.c[ID][1];
        tipo = BDChinpo.c[ID][2];
        damage =  Double.parseDouble(BDChinpo.c[ID][3]);
        hp = Double.parseDouble(BDChinpo.c[ID][4]);
        hpMax = hp;
        move1 = BDChinpo.c[ID][5];
        move2 = BDChinpo.c[ID][6];
        move3 = BDChinpo.c[ID][7];
        move4 = BDChinpo.c[ID][8];
        speed = Double.parseDouble(BDChinpo.c[ID][9]);
        defence = Double.parseDouble(BDChinpo.c[ID][10]);

    }
}
