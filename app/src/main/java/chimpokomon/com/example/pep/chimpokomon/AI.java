package chimpokomon.com.example.pep.chimpokomon;


public class AI {

    DatosMoves moves = new DatosMoves();
    DatosChinpokomones chinpo = new DatosChinpokomones();

    // Simula ataques con todos los movimientos y regresa el que realizo mas daño
    public String bestMove(Personaje player1_Actual, Personaje player2_Actual){

        double efective;
        double damage;
        double  bestDmg= 0;
        String bestMove = "";
        String type,move;
        for (int a = 0; a <=3; a++) {

            move = chinpo.c[player2_Actual.id-1][5+a];
            type = moves.getType(move);

           // se pasa el tipo de movimiento para ver si es super efectivo
            efective = moves.efective(type, player1_Actual.tipo);
            damage = efective * player2_Actual.damage * moves.getDamage(move) * moves.getCoreDamage(type, player1_Actual.tipo);
            if (damage >= bestDmg){
                bestMove = move;
                bestDmg = damage;
            }
        }

        return bestMove;
    }
}
