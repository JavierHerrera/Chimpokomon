package chimpokomon.com.example.pep.chimpokomon;

public class MotorBatalla {

    //El modificador se utiliza para bd_ataques super efectivos
    private double modificador = 1;
    public String leyenda_de_textView;

    //Variables del ataque
    private int move_damage;
    private String move_used;
    public int damage_done;
    public int flag_ataque; //0 miss,1 nomal, 2 super E, 3 not very E
    public int flag_ataque_critico; //  0 no critico, 1 citico

    //Cargar datos
    BDMoves BDMoves = new BDMoves();

    //Cargar los 2 objetos chinpokomones
    int flag_quienAtacayDefiende;
    Personaje player1_Personaje1, player1_Personaje2, player1_Personaje3;
    Personaje player2_Personaje1, player2_Personaje2, player2_Personaje3;
    Personaje atacante, defensor;
    Personaje player1_Actual,player2_Actual;


    public  MotorBatalla( int id, int id2, int id3, int id4, int id5, int id6){

        player1_Personaje1 = new Personaje(id);
        player1_Personaje2 = new Personaje(id2);
        player1_Personaje3 = new Personaje(id3);
        player2_Personaje1 = new Personaje(id4);
        player2_Personaje2 = new Personaje(id5);
        player2_Personaje3 = new Personaje(id6);
    }

    public void combate(String ataque, int a) {

        flag_quienAtacayDefiende = a;

        //Inicializa modificador a 1 por default
        modificador = 1;
        leyenda_de_textView = "";
        move_damage = 1;
        move_used = ataque;
        damage_done = 0;
        flag_ataque = 1;
        flag_ataque_critico = 0;


        //Seleccionar quien es el que ataca y defiende
        if (a==1){
            atacante = player1_Actual;
            defensor = player2_Actual;
        }
        else {
            atacante = player2_Actual;
            defensor = player1_Actual;
        }

        if (defensor.hp >0 && atacante.hp >0){
            evasion(atacante);}
    }

    private void evasion(Personaje atacante){
        if ( 1 == (int) (Math.random() * 30) ){
            leyenda_de_textView = atacante.nombre + " used " + move_used+ ". Attack missed!";
            flag_ataque = 0;
        }
        else {
            super_efective(move_used);
        }
    }

    private void super_efective( String ataque){

        String move_type;
        int i=0;

        //Se busca el ataque en BD para trabajar con su informacion
        while (ataque != BDMoves.bd_ataques[i][0]){
            i = i + 1;
        }

        //Se consiguen los datos de move_type para comparar y de
        // move_dmg para modificarlo de ser necesario
        move_type = BDMoves.bd_ataques[i][1];
        move_damage = Integer.parseInt(BDMoves.bd_ataques[i][2]);

        if ((move_type == "grass" && defensor.tipo == "water") ||
                (move_type == "water" && defensor.tipo == "fire") ||
                (move_type == "fire" && defensor.tipo == "grass")){
            modificador = modificador * 2;
            leyenda_de_textView = ", it's Super Effective! ";
            flag_ataque = 2;
        }
        else if ((move_type == "grass" && defensor.tipo == "fire") ||
                (move_type == "water" && defensor.tipo == "grass") ||
                (move_type == "fire" && defensor.tipo == "water")){
            modificador = modificador * 0.5;
            leyenda_de_textView = ", it's Not Very Effective!";
            flag_ataque = 3;
        }
        else {
            modificador = 1;
            leyenda_de_textView = "";
            flag_ataque = 1;
        }
        critical();
    }

    private void critical(){
//
        if ( 1 == (int) (Math.random() * 15 )){
            move_damage = (int) (move_damage * 1.5);
            leyenda_de_textView = atacante.nombre + " used " + move_used + leyenda_de_textView + " Citical hit!";
            flag_ataque = flag_ataque + 10;
        }
        else{
            leyenda_de_textView = atacante.nombre + " used " + move_used + leyenda_de_textView;
        }
        applyATK();
    }

    private void applyATK(){

        //Se resta el hp del daño y se evita que sea negativo
        defensor.hp = defensor.hp - move_damage * modificador;
        if ( defensor.hp < 0){
            defensor.hp = 0;
        }

        damage_done = (int) (move_damage * modificador);

        //Se regresan los datos
        if (flag_quienAtacayDefiende==1){
            player1_Actual = atacante;
            player2_Actual = defensor;
        }
        else {
            player2_Actual = atacante;
            player1_Actual = defensor;
        }
    }
}
