package chimpokomon.com.example.pep.chimpokomon;

public class DatosMoves {

    String [][] ataques = new String[20][6];
    double [][] typechar = new double[17][17];
    String [] types_Names = new String [17];


    public DatosMoves(){

        //Nombre de los ataques
        types_Names = new String[]{"normal","fire","water","electric","grass","ice","fighting","posion","ground","flying","psychic","bug","rock","ghost","dragon","dark","steel","fairy"};

         //Tabla de los ataques vs tipos
         typechar = new double[][]{
                {1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0.5,	0,	1,	1,	0.5,	1},
                {1,	0.5,	0.5,	1,	2,	2,	1,	1,	1,	1,	1,	2,	0.5,	1,	0.5,	1,	2,	1},
                {1,	2,	0.5,	1,	0.5,	1,	1,	1,	2,	1,	1,	1,	2,	1,	0.5,	1,	1,	1},
                {1,	1,	2,	0.5,	0.5,	1,	1,	1,	0,	2,	1,	1,	1,	1,	0.5,	1,	1,	1},
                {1,	0.5,	2,	1,	0.5,	1,	1,	0.5,	2,	0.5,	1,	0.5,	2,	1,	0.5,	1,	0.5,	1},
                {1,	0.5,	0.5,	1,	2,	0.5,	1,	1,	2,	2,	1,	1,	1,	1,	2,	1,	0.5,	1},
                {2,	1,	1,	1,	1,	2,	1,	0.5,	1,	0.5,	0.5,	0.5,	2,	0,	1,	2,	2,	0.5},
                {1,	1,	1,	1,	2,	1,	1,	0.5,	0.5,	1,	1,	1,	0.5,	0.5,	1,	1,	0,	2},
                {1,	2,	1,	2,	0.5,	1,	1,	2,	1,	0,	1,	0.5,	2,	1,	1,	1,	2,	1},
                {1,	1,	1,	0.5,	2,	1,	2,	1,	1,	1,	1,	2,	0.5,	1,	1,	1,	0.5,	1},
                {1,	1,	1,	1,	1,	1,	2,	2,	1,	1,	0.5,	1,	1,	1,	1,	0,	0.5,	1},
                {1,	0.5,	1,	1,	2,	1,	0.5,	0.5,	1,	0.5,	2,	1,	1,	0.5,	1,	2,	0.5,	0.5,},
                {1,	2,	1,	1,	1,	2,	0.5,	1,	0.5,	2,	1,	2,	1,	1,	1,	1,	0.5,	1},
                {0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	2,	1,	1,	2,	1,	0.5,	1,	1},
                {1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	2,	1,	0.5,	0},
                {1,	1,	1,	1,	1,	1,	0.5,	1,	1,	1,	2,	1,	1,	2,	1,	0.5,	1,	0.5,},
                {1,	0.5,	0.5,	0.5,	1,	2,	1,	1,	1,	1,	1,	1,	2,	1,	1,	1,	0.5,	2},
                {1,	0.5,	1,	1,	1,	1,	2,	0.5,	1,	1,	1,	1,	1,	1,	2,	2,	0.5,	1}};

        //Nombre, tipo, daño, accuracy, efect, effect %
        ataques	[0]	[0]=	"Ember";
        ataques	[0]	[1]=	"fire";
        ataques	[0]	[2]=	"40";
        ataques	[0]	[3]=	"100";
        ataques	[0]	[4]=	"burn";
        ataques	[0]	[5]=	"10";

        ataques	[1]	[0]=	"Bubble";
        ataques	[1]	[1]=	"water";
        ataques	[1]	[2]=	"40";
        ataques	[1]	[3]=	"100";
        ataques	[1]	[4]=	"low speed";
        ataques	[1]	[5]=	"10";

        ataques	[2]	[0]=	"Vine Whip";
        ataques	[2]	[1]=	"grass";
        ataques	[2]	[2]=	"45";
        ataques	[2]	[3]=	"100";
        ataques	[2]	[4]=	"no effect";
        ataques	[2]	[5]=	"0";

        ataques	[3]	[0]=	"Quick Attack";
        ataques	[3]	[1]=	"normal";
        ataques	[3]	[2]=	"40";
        ataques	[3]	[3]=	"1000";
        ataques	[3]	[4]=	"no miss";
        ataques	[3]	[5]=	"0";

        ataques	[4]	[0]=	"Scratch";
        ataques	[4]	[1]=	"normal";
        ataques	[4]	[2]=	"40";
        ataques	[4]	[3]=	"100";
        ataques	[4]	[4]=	"no effect";
        ataques	[4]	[5]=	"0";

        ataques	[5]	[0]=	"Tackle";
        ataques	[5]	[1]=	"normal";
        ataques	[5]	[2]=	"40";
        ataques	[5]	[3]=	"100";
        ataques	[5]	[4]=	"no effect";
        ataques	[5]	[5]=	"0";

        ataques	[6]	[0]=	"Bite";
        ataques	[6]	[1]=	"dark";
        ataques	[6]	[2]=	"60";
        ataques	[6]	[3]=	"100";
        ataques	[6]	[4]=	"flinch";
        ataques	[6]	[5]=	"30";

        ataques	[7]	[0]=	"Metal Claw";
        ataques	[7]	[1]=	"steel";
        ataques	[7]	[2]=	"50";
        ataques	[7]	[3]=	"95";
        ataques	[7]	[4]=	"rise user ATK";
        ataques	[7]	[5]=	"10";

        ataques	[8]	[0]=	"Ice Beam";
        ataques	[8]	[1]=	"ice";
        ataques	[8]	[2]=	"90";
        ataques	[8]	[3]=	"100";
        ataques	[8]	[4]=	"freeze";
        ataques	[8]	[5]=	"10";

        ataques	[9]	[0]=	"Sludge";
        ataques	[9]	[1]=	"posion";
        ataques	[9]	[2]=	"65";
        ataques	[9]	[3]=	"100";
        ataques	[9]	[4]=	"poison";
        ataques	[9]	[5]=	"30";

        ataques	[10]	[0]=	"Giga Drain";
        ataques	[10]	[1]=	"grass";
        ataques	[10]	[2]=	"75";
        ataques	[10]	[3]=	"100";
        ataques	[10]	[4]=	"drain 50";
        ataques	[10]	[5]=	"100";

        ataques	[11]	[0]=	"Spark";
        ataques	[11]	[1]=	"electric";
        ataques	[11]	[2]=	"65";
        ataques	[11]	[3]=	"100";
        ataques	[11]	[4]=	"paralyze";
        ataques	[11]	[5]=	"30";

        ataques	[12]	[0]=	"Thunderbolt";
        ataques	[12]	[1]=	"electric";
        ataques	[12]	[2]=	"90";
        ataques	[12]	[3]=	"100";
        ataques	[12]	[4]=	"paralyze";
        ataques	[12]	[5]=	"10";

        ataques	[13]	[0]=	"Brick Break";
        ataques	[13]	[1]=	"fighting";
        ataques	[13]	[2]=	"75";
        ataques	[13]	[3]=	"100";
        ataques	[13]	[4]=	"no effect";
        ataques	[13]	[5]=	"0";

        ataques	[14]	[0]=	"Magical Leaf";
        ataques	[14]	[1]=	"grass";
        ataques	[14]	[2]=	"75";
        ataques	[14]	[3]=	"100";
        ataques	[14]	[4]=	"no miss";
        ataques	[14]	[5]=	"0";

        ataques	[15]	[0]=	"Rock Slide";
        ataques	[15]	[1]=	"rock";
        ataques	[15]	[2]=	"75";
        ataques	[15]	[3]=	"90";
        ataques	[15]	[4]=	"flinch";
        ataques	[15]	[5]=	"30";

        ataques	[16]	[0]=	"Slam";
        ataques	[16]	[1]=	"normal";
        ataques	[16]	[2]=	"80";
        ataques	[16]	[3]=	"75";
        ataques	[16]	[4]=	"no miss";
        ataques	[16]	[5]=	"0";

        ataques	[17]	[0]=	"Bug Bite";
        ataques	[17]	[1]=	"bug";
        ataques	[17]	[2]=	"60";
        ataques	[17]	[3]=	"100";
        ataques	[17]	[4]=	"no effect";
        ataques	[17]	[5]=	"0";

        ataques	[18]	[0]=	"Aerial Ace";
        ataques	[18]	[1]=	"flying";
        ataques	[18]	[2]=	"60";
        ataques	[18]	[3]=	"100";
        ataques	[18]	[4]=	"no miss";
        ataques	[18]	[5]=	"0";

        ataques	[19]	[0]=	"Poison Jab";
        ataques	[19]	[1]=	"posion";
        ataques	[19]	[2]=	"80";
        ataques	[19]	[3]=	"100";
        ataques	[19]	[4]=	"posion";
        ataques	[19]	[5]=	"30";
    }

    //Enuentra las coordenadas en la matriz de elementos
    public double efective(String type1, String type2){
        int a=0;
        int b=0;

        while ( type1 != types_Names[a]){
            a++;
        }
        while ( type2 != types_Names[b]){
            b++;
        }

        return typechar[a][b];
    }

    public String getType(String move){
        String type = "";

        int i=0;
        while (move != ataques[i][0]){
            i = i + 1;
        }
        type = ataques[i][1];
        return type;
    }

    public int getDamage(String move){
        int damage = 0;

        int i=0;
        while (move != ataques[i][0]){
            i = i + 1;
        }
        damage = Integer.parseInt(ataques[i][2]);
        return damage;
    }

    public double getCoreDamage(String move,String targetType){

        double core = 1;

        if (move == targetType){
            core = 1.5;
        }

        return core;
    }
}
