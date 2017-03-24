package chimpokomon.com.example.pep.chimpokomon;

import java.util.Scanner;

public class MotorBatalla {

    //Datos del personaje del jugador
    private String player_nombre;
    private String player_tipo;
    private double player_damage;
    private double player_hp;
    private String player_move1;
    private String player_move2;

    //Datos del personaje del -PU
    private String cpu_nombre;
    private String cpu_tipo;
    private double cpu_damage;
    private double cpu_hp;
    private String cpu_move1;
    private String cpu_move2;

    //El modificador se utiliza para bd_ataques super efectivos
    double modificador = 1;
    String modificador_leyenda;

    //Variables del ataque
    int move_damage;
    String move_used;

    Personaje personaje = new Personaje();
    Moves moves = new Moves();


    public void cargar_personajes(int id, int id2){

        //Se cargan todos los dataos de los dos personajes
        personaje.inicializar_datos();
        moves.inicializar_moves();

        player_nombre = personaje.c[id][1];
        player_tipo = personaje.c[id][2];
        player_damage = Integer.parseInt(personaje.c[id][3]);
        player_hp = Integer.parseInt(personaje.c[id][4]);
        player_move1 = personaje.c[id][5];
        player_move2 = personaje.c[id][6];

        //Se cargan todos los dataos de CPU
        cpu_nombre = personaje.c[id2][1];
        cpu_tipo = personaje.c[id2][2];
        cpu_damage = Integer.parseInt(personaje.c[id2][3]);
        cpu_hp = Integer.parseInt(personaje.c[id2][4]);
        cpu_move1 = personaje.c[id][5];
        cpu_move2 = personaje.c[id][6];

        combate();
    }

    private void combate(){

        System.out.println(player_nombre + " VS " + cpu_nombre);

        //Se rompe el ciclo hasta que el hp sea 0
        while (cpu_hp >0){

            //Inicializa modificador a 1 por default
            modificador = 1;
            modificador_leyenda = "";
            move_damage = 1;
            move_used = "";

            seleccionar_ataque();
            super_efective();
            System.out.println(player_nombre + " used " + move_used );
            evasion();
            System.out.println(cpu_nombre + " HP " + cpu_hp);
        }
    }

    private void seleccionar_ataque() {

        System.out.println("1:" + player_move1 + " 2;" + player_move2);
        Scanner scanner = new Scanner(System.in);
        Integer move_selected = scanner.nextInt();

        if (move_selected == 1) {move_used = player_move1;}
        else {move_used = player_move2;}
    }

    private void super_efective(){

        String move_type;
        int i=0;
        //se toman los datos de la clase Moves
        moves.inicializar_moves();

        while (move_used != moves.bd_ataques[i][0]){
            i = i + 1;
        }
        move_type = moves.bd_ataques[i][1];
        move_damage = Integer.parseInt(moves.bd_ataques[i][2]);

        if ((move_type == "grass" && cpu_tipo == "water") ||
                (move_type == "water" && cpu_tipo == "fire") ||
                (move_type == "fire" && cpu_tipo == "grass")){
            modificador = 2;
            modificador_leyenda = "It's Super Effective! ";
        }
        else if ((move_type == "grass" && cpu_tipo == "fire") ||
                (move_type == "water" && cpu_tipo == "grass") ||
                (move_type == "fire" && cpu_tipo == "water")){
            modificador = modificador * 0.5;
            modificador_leyenda = "It's Not Very Effective!";
        }
        else {
            modificador = 1;
            modificador_leyenda = "";
        }
    }

    private void evasion(){
        if ( 1 == (int) (Math.random() * 10) ){
            System.out.println(player_nombre + "'s attack missed!");
        }
        else {
            critical();
        }
    }

    private void critical(){

        if ( 1 == (int) (Math.random() * 10) ){
            cpu_hp = cpu_hp  - (move_damage * 1.5) * modificador;
            System.out.println("A critical hit! " + modificador_leyenda);
        }
        //Con este else if se evita que se imprima una linea en blanco cuando el modificador es 1
        else if (modificador != 1){
            cpu_hp = cpu_hp - move_damage * modificador;
            System.out.println(modificador_leyenda);
        }
        else{ cpu_hp = cpu_hp - move_damage * modificador;}
    }


}
