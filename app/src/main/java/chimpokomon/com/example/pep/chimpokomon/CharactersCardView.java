package chimpokomon.com.example.pep.chimpokomon;


public class CharactersCardView {
    private int imagen;
    private String nombre;
    private String hp, speed, attack, defence;
    private int tipo_elemento;

    public CharactersCardView(int imagen, String nombre, String hp, String speed,String attack,String defence, int tipo_elemento) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.hp = hp;
        this.speed = speed;
        this.attack = attack;
        this.defence = defence;
        this.tipo_elemento = tipo_elemento;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHp() {
        return hp;
    }

    public String getSpeed() {
        return speed;
    }

    public String getAttack() {
        return attack;
    }

    public String getDefence() {
        return defence;
    }

    public int getType() {
        return tipo_elemento;
    }
}


