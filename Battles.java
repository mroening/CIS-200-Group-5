import java.util.*;

/**Battles
 * This is where the monster and boss battles take place.
 * 
 */
public class Battles{
    private boolean battleOver = false; //Loop conditional for fights
    private double playerInitiative; //Initiative is taken from D&D on who goes first in the rounds.
    private double monsterInitiative;
    private double bossInitiative;
    private static Scanner s;

    public Battles(){}

    public int battleMonster(Monster mons, Player play){
        Monster monster = mons;
        Player player = play;
        char Choice;
        int damageReturn;
        System.out.println(toStringHero(player) + "\n");
        System.out.println(mons.toString());
        playerInitiative = Math.random();
        monsterInitiative = Math.random();
        boolean successfullBlock = false;
        boolean successfullCounter = false;
        boolean skipMonsterTurn = false;
        while(playerInitiative == monsterInitiative){
            playerInitiative = Math.random();
        }
        if (playerInitiative > monsterInitiative){
            System.out.println("\nThe player rolled a higher priority, so they go first for each round.");
            while (!battleOver){
                successfullBlock = false;
                successfullCounter = false;
                skipMonsterTurn = false;
                Choice = playerChoiceTurn();
                switch (Choice){
                    case 'A':
                        if (Math.random() > 0.6){
                            System.out.println("\nThe monster attempts to dodge your attack.");
                            damageReturn = monsterDodgeAttempt(play, mons);
                            if (damageReturn == 0){
                                System.out.println("The monster successfully dodged your attack.");
                            }
                            else{
                                System.out.println("The monster failed to dodge your attack, you land a critical hit.");
                                mons.setHealth(mons.getHealth() - damageReturn);
                                System.out.println(monsterUpdate(mons));
                            }
                        }
                        else{
                            System.out.println("\nYou attack the monster.");
                            mons.setHealth(mons.getHealth()-play.getDmg());
                            System.out.println(monsterUpdate(mons));
                        }
                        battleOver = fightLoopMons(play, mons);
                        break;
                    case 'B':
                        System.out.println("\nThe monster attacks you!");
                        damageReturn = playerBlockM(mons, Math.random());
                        if (damageReturn == 0){
                            System.out.println("You successfully block the monster, stunning it.");
                            successfullBlock = true;
                        }
                        else{
                            System.out.println("You can't completely block the attack. You take some damage.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                    case 'C':
                        System.out.println("\nThe monster attacks you!");
                        damageReturn = playerCounterM(mons, Math.random());
                        if (damageReturn == 0){
                            successfullCounter = true;
                        }
                        else{
                            System.out.println("You try and fail to counter the attack, leaving you wide open for a critical hit.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                }
                if (battleOver)
                    break;
                if (successfullBlock || successfullCounter){
                    if (successfullBlock){
                        System.out.println("\nThe monster is dazed and misses a turn!");
                    }
                    if (successfullCounter){
                        System.out.println("\nBy countering the monster, you land a critical hit!");
                        mons.setHealth(mons.getHealth() - counterSuccess(play));
                        System.out.println(monsterUpdate(mons));
                    }
                }
                if (!successfullBlock && !successfullCounter && !skipMonsterTurn){
                    System.out.println("\nThe monster attacks you!");
                    Choice = playerChoiceNot();
                    switch (Choice){
                        case 'B':
                            System.out.println("\nThe monster attacks you!");
                            damageReturn = playerBlockM(mons, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully block the monster.");
                            }
                            else{
                                System.out.println("You can't completely block the attack. You take some damage.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                        case 'D':
                            System.out.println("\nThe monster attacks you!");
                            damageReturn = playerCounterM(mons, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully dodge the monster.");
                            }
                            else{
                                System.out.println("You try and fail to dodge the attack, leaving you wide open for a critical hit.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                    }
                }
                if (!successfullBlock && !successfullCounter)
                    System.out.println(playerUpdate(play));
                battleOver = fightLoopMons(play, mons);
                //if (battleOver)
                    //break;
            }
        }
        else {
            System.out.println("\nThe monster rolled a higher priority, so they go first for each round.");
            while (!battleOver){
                if (!successfullBlock && !successfullCounter && !skipMonsterTurn){
                    System.out.println("\nThe monster attacks you!");
                    Choice = playerChoiceNot();
                    switch (Choice){
                        case 'B':
                            System.out.println("\nThe monster attacks you!");
                            damageReturn = playerBlockM(mons, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully block the monster.");
                            }
                            else{
                                System.out.println("You can't completely block the attack. You take some damage.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                        case 'D':
                            System.out.println("\nThe monster attacks you!");
                            damageReturn = playerCounterM(mons, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully dodge the monster.");
                            }
                            else{
                                System.out.println("You try and fail to dodge the attack, leaving you wide open for a critical hit.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                    }
                }
                if (!successfullBlock && !successfullCounter)
                    System.out.println(playerUpdate(play));
                battleOver = fightLoopMons(play, mons);
                if (battleOver)
                    break;
                successfullBlock = false;
                successfullCounter = false;
                skipMonsterTurn = false;
                Choice = playerChoiceTurn();
                switch (Choice){
                    case 'A':
                        if (Math.random() > 0.6){
                            System.out.println("\nThe monster attempts to dodge your attack.");
                            damageReturn = monsterDodgeAttempt(play, mons);
                            if (damageReturn == 0){
                                System.out.println("The monster successfully dodged your attack.");
                            }
                            else{
                                System.out.println("The monster failed to dodge your attack, you land a critical hit.");
                                mons.setHealth(mons.getHealth() - damageReturn);
                                System.out.println(monsterUpdate(mons));
                            }
                        }
                        else{
                            System.out.println("\nYou attack the monster.");
                            mons.setHealth(mons.getHealth()-play.getDmg());
                            System.out.println(monsterUpdate(mons));
                        }
                        battleOver = fightLoopMons(play, mons);
                        break;
                    case 'B':
                        System.out.println("\nThe monster attacks you!");
                        damageReturn = playerBlockM(mons, Math.random());
                        if (damageReturn == 0){
                            System.out.println("You successfully block the monster, stunning it.");
                            successfullBlock = true;
                        }
                        else{
                            System.out.println("You can't completely block the attack. You take some damage.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                    case 'C':
                        System.out.println("\nThe monster attacks you!");
                        damageReturn = playerCounterM(mons, Math.random());
                        if (damageReturn == 0){
                            successfullCounter = true;
                        }
                        else{
                            System.out.println("You try and fail to counter the attack, leaving you wide open for a critical hit.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                }
                if (battleOver)
                    break;
                if (successfullBlock || successfullCounter){
                    if (successfullBlock){
                        System.out.println("\nThe monster is dazed and misses a turn!");
                    }
                    if (successfullCounter){
                        System.out.println("\nBy countering the monster, you land a critical hit!");
                        mons.setHealth(mons.getHealth() - counterSuccess(play));
                        System.out.println(monsterUpdate(mons));
                    }
                }
                battleOver = fightLoopMons(play, mons);
                if (battleOver)
                    break;
                if (!skipMonsterTurn && (!successfullBlock && !successfullCounter)){
                    System.out.println(monsterUpdate(mons));
                }
            }
        }
        return player.getHealth();
    }

    /**battleBoss 
     * This method of battle is for the boss fights.
     * 
     * @param bos Boss object being used for fight
     * @param play Player object being used for fight
     * @return Returns the players health.
     */
    public int battleBoss(Boss boss, Player play){
        Boss bos = boss;
        Player player = play;
        char Choice;
        int damageReturn;
        System.out.println(toStringHero(player) + "\n");
        System.out.println(bos.toString());
        playerInitiative = Math.random();
        bossInitiative = Math.random();
        boolean successfullBlock = false;
        boolean successfullCounter = false;
        boolean skipMonsterTurn = false;
        while(playerInitiative == bossInitiative){
            playerInitiative = Math.random();
        }
        if (playerInitiative > bossInitiative){
            System.out.println("\nThe player rolled a higher priority, so they go first for each round.");
            while (!battleOver){
                successfullBlock = false;
                successfullCounter = false;
                skipMonsterTurn = false;
                Choice = playerChoiceTurn();
                switch (Choice){
                    case 'A':
                        if (Math.random() > 0.6){
                            System.out.println("\nThe boss attempts to dodge your attack.");
                            damageReturn = bossDodgeAttempt(play, bos);
                            if (damageReturn == 0){
                                System.out.println("The boss successfully dodged your attack.");
                            }
                            else{
                                System.out.println("The boss failed to dodge your attack, you land a critical hit.");
                                bos.setHealth(bos.getHealth() - damageReturn);
                                System.out.println(bossUpdate(bos));
                            }
                        }
                        else{
                            System.out.println("\nYou attack the boss.");
                            bos.setHealth(bos.getHealth()-play.getDmg());
                            System.out.println(bossUpdate(bos));
                        }
                        battleOver = fightLoopBos(play, bos);
                        break;
                    case 'B':
                        System.out.println("\nThe boss attacks you!");
                        damageReturn = playerBlockB(bos, Math.random());
                        if (damageReturn == 0){
                            System.out.println("You successfully block the boss, stunning it.");
                            successfullBlock = true;
                        }
                        else{
                            System.out.println("You can't completely block the attack. You take some damage.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                    case 'C':
                        System.out.println("\nThe boss attacks you!");
                        damageReturn = playerCounterB(bos, Math.random());
                        if (damageReturn == 0){
                            successfullCounter = true;
                        }
                        else{
                            System.out.println("You try and fail to counter the attack, leaving you wide open for a critical hit.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                }
                if (battleOver)
                    break;
                if (successfullBlock || successfullCounter){
                    if (successfullBlock){
                        System.out.println("\nThe boss is dazed and misses a turn!");
                    }
                    if (successfullCounter){
                        System.out.println("\nBy countering the boss, you land a critical hit!");
                        bos.setHealth(bos.getHealth() - counterSuccess(play));
                        System.out.println(bossUpdate(bos));
                    }
                }
                if (!successfullBlock && !successfullCounter && !skipMonsterTurn){
                    System.out.println("\nThe boss attacks you!");
                    Choice = playerChoiceNot();
                    switch (Choice){
                        case 'B':
                            System.out.println("\nThe boss attacks you!");
                            damageReturn = playerBlockB(bos, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully block the boss.");
                            }
                            else{
                                System.out.println("You can't completely block the attack. You take some damage.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                        case 'D':
                            System.out.println("\nThe boss attacks you!");
                            damageReturn = playerCounterM(bos, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully dodge the boss.");
                            }
                            else{
                                System.out.println("You try and fail to dodge the attack, leaving you wide open for a critical hit.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                    }
                }
                if (!successfullBlock && !successfullCounter)
                    System.out.println(playerUpdate(play));
                battleOver = fightLoopBos(play, bos);
                //if (battleOver)
                    //break;
            }
        }
        else {
            System.out.println("\nThe boss rolled a higher priority, so they go first for each round.");
            while (!battleOver){
                if (!successfullBlock && !successfullCounter && !skipMonsterTurn){
                    System.out.println("\nThe monster attacks you!");
                    Choice = playerChoiceNot();
                    switch (Choice){
                        case 'B':
                            System.out.println("\nThe boss attacks you!");
                            damageReturn = playerBlockB(bos, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully block the boss.");
                            }
                            else{
                                System.out.println("You can't completely block the attack. You take some damage.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                        case 'D':
                            System.out.println("\nThe boss attacks you!");
                            damageReturn = playerCounterB(bos, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You successfully dodge the boss.");
                            }
                            else{
                                System.out.println("You try and fail to dodge the attack, leaving you wide open for a critical hit.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                    }
                }
                if (!successfullBlock && !successfullCounter)
                    System.out.println(playerUpdate(play));
                battleOver = fightLoopBos(play, bos);
                if (battleOver)
                    break;
                successfullBlock = false;
                successfullCounter = false;
                skipMonsterTurn = false;
                Choice = playerChoiceTurn();
                switch (Choice){
                    case 'A':
                        if (Math.random() > 0.6){
                            System.out.println("\nThe boss attempts to dodge your attack.");
                            damageReturn = bossDodgeAttempt(play, bos);
                            if (damageReturn == 0){
                                System.out.println("The boss successfully dodged your attack.");
                            }
                            else{
                                System.out.println("The boss failed to dodge your attack, you land a critical hit.");
                                bos.setHealth(bos.getHealth() - damageReturn);
                                System.out.println(bossUpdate(bos));
                            }
                        }
                        else{
                            System.out.println("\nYou attack the boss.");
                            bos.setHealth(bos.getHealth()-play.getDmg());
                            System.out.println(bossUpdate(bos));
                        }
                        battleOver = fightLoopBos(play, bos);
                        break;
                    case 'B':
                        System.out.println("\nThe boss attacks you!");
                        damageReturn = playerBlockB(bos, Math.random());
                        if (damageReturn == 0){
                            System.out.println("You successfully block the boss, stunning it.");
                            successfullBlock = true;
                        }
                        else{
                            System.out.println("You can't completely block the attack. You take some damage.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                    case 'C':
                        System.out.println("\nThe boss attacks you!");
                        damageReturn = playerCounterB(bos, Math.random());
                        if (damageReturn == 0){
                            successfullCounter = true;
                        }
                        else{
                            System.out.println("You try and fail to counter the attack, leaving you wide open for a critical hit.");
                            play.setHealth(play.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                }
                if (battleOver)
                    break;
                if (successfullBlock || successfullCounter){
                    if (successfullBlock){
                        System.out.println("\nThe boss is dazed and misses a turn!");
                    }
                    if (successfullCounter){
                        System.out.println("\nBy countering the boss, you land a critical hit!");
                        bos.setHealth(bos.getHealth() - counterSuccess(play));
                        System.out.println(bossUpdate(bos));
                    }
                }
                battleOver = fightLoopBos(play, bos);
                if (battleOver)
                    break;
                if (!skipMonsterTurn && (!successfullBlock && !successfullCounter)){
                    System.out.println(bossUpdate(bos));
                }
            }
        }
        return player.getHealth();
    }

    private static int monsterDodgeAttempt(Player play, Monster mons){
        double attemptDodge = Math.random();
        int damageReturn = 0;
        if (attemptDodge > mons.getDodge())
            damageReturn = play.getDmg()*2;
        return damageReturn;
    }

    private static int bossDodgeAttempt(Player play, Boss bos){
        double attemptDodge = Math.random();
        int damageReturn = 0;
        if (attemptDodge > bos.getDodge())
            damageReturn = play.getDmg()*2;
        return damageReturn;
    }

    private static int playerBlockM(Monster mons, double blockChance){
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < blockChance)
            damageReturn = ((mons.getDamage()/2) + 1);
        return damageReturn;
    }

    private static int playerBlockB(Boss bo, double blockChance){
        double successBlock = blockChance;
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < successBlock)
            damageReturn = ((bo.getDamage()/2) + 1);
        return damageReturn;
    }

    private static int playerCounterM(Monster mons, double blockChance){
        double successBlock = blockChance;
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < successBlock)
            damageReturn = (mons.getDamage()*2);
        return damageReturn;
    }

    private static int playerCounterB(Boss bo, double blockChance){
        double successBlock = blockChance;
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < successBlock)
            damageReturn = (bo.getDamage()*2);
        return damageReturn;
    }

    private static int counterSuccess(Player play){
        return (play.getDmg()*2);
    }

    //Decides the players choice on if they attack, block, or attempt a counter.
    private static char playerChoiceTurn(){
        s = new Scanner(System.in);
        char choiceChar;
        System.out.print("\nOptions for move: "
                            + "\nA)ttack"
                            + "\nB)lock *Success: (Stun enemy/take no damage) | Failure:(Block half damage/get dazed)"
                            + "\nC)ounter *Success:(Take no damage, deal double) | Failure:(Take double damage)"
                            + "\nYou choose: ");
        String choiceString = s.nextLine().toUpperCase();
        String [] choice = choiceString.split("");
        while(!choice[0].equals("A") && !choice[0].equals("B") && !choice[0].equals("C")){
            System.out.print("\nInvalid input! Options for move are: "
                                + "\nA)ttack"
                                + "\nB)lock *Success: (Stun enemy/take no damage) | Failure:(Block half damage)"
                                + "\nC)ounter *Success:(Take no damage, deal double) | Failure:(Take double damage)"
                                + "\nYou choose to: ");
            choiceString = s.nextLine().toUpperCase();
            choice = choiceString.split("");
        }
        choiceChar = choiceString.charAt(0);
        return choiceChar;
    }

    //Chooses if the player tries to dodge or block the attack when it's not their turn.
    private static char playerChoiceNot(){
        s = new Scanner(System.in);
        char choiceChar;
        System.out.print("\nOptions during attack: "
                            + "\nB)lock *Success: (Take no damage) | Failure:(Block half damage)"
                            + "\nD)odge *Success:(Take no damage) | Failure:(Take double damage)"
                            + "\nYou choose: ");
        String choiceString = s.nextLine().toUpperCase();
        String [] choice = choiceString.split("");
        while(!choice[0].equals("B") && !choice[0].equals("D")){
            System.out.print("\nInvalid input! Options for move are: "
                                + "\nB)lock *Success: (Take no damage) | Failure:(Block half damage)"
                                + "\nD)odge *Success:(Take no damage) | Failure:(Take double damage)"
                                + "\nYou choose: ");
            choiceString = s.nextLine().toUpperCase();
            choice = choiceString.split("");
        }
        choiceChar = choiceString.charAt(0);
        return choiceChar;
    }

    private static String monsterUpdate(Monster mons){
        return ("\nMonster's health: " + mons.getHealth());
    }

    private static String bossUpdate(Boss bos){
        return ("\nMonster's health: " + bos.getHealth());
    }

    private static String playerUpdate(Player play){
        return ("\nPlayer's health: " + play.getHealth());
    }

    private static String toStringHero(Player play){
        return ("Player's name: " + play.getName() + "\nHealth: " + play.getHealth() + "\nDamage: " + play.getDmg());
    }

    private static boolean fightLoopMons(Player play, Monster mons){
        if (mons.getHealth() <= 0 || play.getHealth() <= 0)
            return true;
        else
            return false;
    }

    private static boolean fightLoopBos(Player play, Boss bo){
        if (bo.getHealth() <= 0 || play.getHealth() <= 0)
            return true;
        else
            return false;
    }
}