package chimpokomon.com.example.pep.chimpokomon;

/**
 * Created by Pishi on 29/04/2017.
 */

public class Move {
    private DatosMoves datosMoves = new DatosMoves();

    public String name;
    public String type;
    public int damage;
    public int acuracy;
    public String effect;
    public int effectRate;


    public Move(String nameMove){
        int id = findID(nameMove);

        name = datosMoves.ataques[id][0];
        type = datosMoves.ataques[id][1];
        damage = Integer.parseInt(datosMoves.ataques[id][2]);
        acuracy = Integer.parseInt(datosMoves.ataques[id][3]);
        effect = datosMoves.ataques[id][4];
        effectRate = Integer.parseInt(datosMoves.ataques[id][5]);

    }

    private int findID(String nameMove){
        int id = 0;

        while (nameMove != datosMoves.ataques[id][0]){
            id++;
        }
        return id;
    }
}
