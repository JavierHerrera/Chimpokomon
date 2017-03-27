package chimpokomon.com.example.pep.chimpokomon;

public class MotorBatalla {

    //El modificador se utiliza para bd_ataques super efectivos
    private double modificador = 1;
    private String modificador_leyenda;

    //Variables del ataque
    private int move_damage;
    private String move_used;
    public String leyenda;

    //Cargar datos
    BDMoves BDMoves = new BDMoves();

    //Cargar los 2 objetos chinpokomones
    int id,id2,flag_quienAtacayDefiende;
    Personaje player1 = new Personaje(1);
    Personaje player2 = new Personaje(2);
    Personaje atacante;
    Personaje defensor;


    public  MotorBatalla( int id, int id2){

        this.id = id;
        this.id2 = id;
    }

    public void combate(String ataque, int a) {

        flag_quienAtacayDefiende = a;

        //Inicializa modificador a 1 por default
        modificador = 1;
        modificador_leyenda = "";
        move_damage = 1;
        move_used = ataque;

        //Seleccionar quien es el que ataca y defiende
        if (a==1){
            atacante = player1;
            defensor = player2;
        }
        else {
            atacante = player2;
            defensor = player1;
        }
        evasion(atacante);
    }

    private void evasion(Personaje atacante){
        if ( 1 == (int) (Math.random() * 30) ){
            modificador_leyenda= atacante.nombre + " used " + move_used+ ". Attack missed!";
            leyenda= modificador_leyenda;
        }
        else {
            super_efective(move_used);
            leyenda = modificador_leyenda;
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
            modificador_leyenda = ", it's Super Effective! ";
        }
        else if ((move_type == "grass" && defensor.tipo == "fire") ||
                (move_type == "water" && defensor.tipo == "grass") ||
                (move_type == "fire" && defensor.tipo == "water")){
            modificador = modificador * 0.5;
            modificador_leyenda = ", it's Not Very Effective!";
        }
        else {
            modificador = 1;
            modificador_leyenda = "";
        }
        critical();
    }

    private void critical(){

        if ( 1 == (int) (Math.random() * 15) ){
            move_damage = (int) (move_damage * 1.5);
            modificador_leyenda = atacante.nombre + " used " + move_used + modificador_leyenda + " Citical hit!";
        }
        else{
            modificador_leyenda = atacante.nombre + " used " + move_used + modificador_leyenda;
        }
        applyATK();
    }

    private void applyATK(){

        //Se resta el hp del daño
        defensor.hp = defensor.hp - move_damage * modificador;
        leyenda = modificador_leyenda;

        //Se regresan los datos
        if (flag_quienAtacayDefiende==1){
            player1 = atacante;
            player2 = defensor;
        }
        else {
            player2 = atacante;
            player1 = defensor;
        }
    }

}
