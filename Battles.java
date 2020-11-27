import java.util.*;

/**Battles
 * This is where the monster and boss battles take place. It holds methods for health display during combat and other methods
 * used for the combat system.
 * It also holds methods for inputting your choice of what you do during battle and so on.
 * 
 */
public class Battles{
    private boolean battleOver = false; //Loop conditional for fights
    private double playerInitiative; //Initiative is taken from D&D on who goes first in the rounds.
    private double monsterInitiative;
    private double bossInitiative;
    private static Scanner s; //Used for player input for choices.

    /**Battles
     * The default, no argument constructor used for being initiallized and then used for calling the battle methods 
     * from a different class file.
     * 
     * @return Returns nothing.
     */
    public Battles(){}

    /**battleMonster
     * This is the method where the player battles a monster enemy. It keeps keeps track of who goes first and second in the fight.
     * 
     * @param mons The monster character that the player will be battling.
     * @param play The player character that will be battling the monster.
     * @return Returns what the player's health is at the end of the battle.
     */
    public int battleMonster(Monster mons, Player play){
        Monster monster = mons;
        Player player = play;
        char Choice;
        int damageReturn;
        System.out.println(toStringHero(player) + "\n");
        System.out.println(mons.toString());
        playerInitiative = Math.random(); //Number for who goes first.
        monsterInitiative = Math.random();
        boolean successfullBlock = false; //Used for skipping the monster turn if the player successfully blocks, and making the player take no damage.
        boolean successfullCounter = false; //Used for 'skipping' the monster's turn if the player successfully counters the attack, and make the monster take a crit hit.
        boolean skipMonsterTurn = false; //Used for output and skipping moster turn.
        while(playerInitiative == monsterInitiative){ //If the player and monster initiative are somehow equal, keep rerolling the players number.
            playerInitiative = Math.random();
        }
        if (playerInitiative > monsterInitiative){ //Conditional for if the player gets higher initiative, making player first.
            System.out.println("\nThe player rolled a higher priority, so they go first for each round.");
            while (!battleOver){ //Loop for battling until the player wins or dies.
                successfullBlock = false;
                successfullCounter = false;
                skipMonsterTurn = false;
                Choice = playerChoiceTurn();
                switch (Choice){
                    case 'A': //If the player chooses to just attack the monster.
                        if (Math.random() > 0.6){ //Chance that the monster will attempt to dodge the attack.
                            System.out.println("\nThe monster attempts to dodge your attack.");
                            damageReturn = monsterDodgeAttempt(play, mons);
                            if (damageReturn == 0){ // If the monster successfully dodges the attack, it takes no damage.
                                System.out.println("The monster successfully dodged your attack.");
                            }
                            else{ //If the monster doesn't successfully dodge, it takes a crit hit.
                                System.out.println("The monster failed to dodge your attack, you land a critical hit.");
                                mons.setHealth(mons.getHealth() - damageReturn);
                                System.out.println(monsterUpdate(mons));
                            }
                        }
                        else{ //If the monster doesn't attempt a dodge, it takes the hit for regular damage.
                            System.out.println("\nYou attack the monster.");
                            mons.setHealth(mons.getHealth()-play.getDmg());
                            System.out.println(monsterUpdate(mons));
                        }
                        battleOver = fightLoopMons(play, mons);
                        break;
                    case 'B': //If the player attempts to block the monster and daze it and take no damage.
                        System.out.println("\nThe monster attacks you!");
                        damageReturn = playerBlockM(mons, Math.random());
                        if (damageReturn == 0){ //Successful block against monster.
                            System.out.println("You successfully block the monster, stunning it.");
                            successfullBlock = true;
                        }
                        else{ //Failed block, player loses half damage.
                            System.out.println("You can't completely block the attack. You take some damage.");
                            player.setHealth(player.getHealth() - damageReturn);
                            skipMonsterTurn = true;
                        }
                        break;
                    case 'C': //If the player chooses to attempt a counter.
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
                if (successfullBlock || successfullCounter){ //If the player successfully blocked/countered.
                    if (successfullBlock){
                        System.out.println("\nThe monster is dazed and misses a turn!");
                    }
                    if (successfullCounter){ //If the player successfully countered, it lands a critical hit.
                        System.out.println("\nBy countering the monster, you land a critical hit!");
                        mons.setHealth(mons.getHealth() - counterSuccess(play));
                        System.out.println(monsterUpdate(mons));
                    }
                }
                if (!successfullBlock && !successfullCounter && !skipMonsterTurn){ //If the monster didn't get countered/blocked.
                    System.out.println("\nThe monster attacks you!");
                    Choice = playerChoiceNot();
                    switch (Choice){
                        case 'B': //If the player attempets to block the attack.
                            System.out.println("\nThe monster attacks you!");
                            damageReturn = playerBlockM(mons, Math.random());
                            if (damageReturn == 0){ //Success, take only half damage.
                                System.out.println("You partially block the attack, taking some damage.");
                                play.setHealth(play.getHealth()-(mons.getDamage()/2));
                            }
                            else{ //Fail, take full damage.
                                System.out.println("You fail to quickly block the attack, taking full damage.");
                                play.setHealth(play.getHealth() - mons.getDamage());
                            }
                            break;
                        case 'D': //If the player attempts to dodge the attack.
                            System.out.println("\nThe monster attacks you!");
                            damageReturn = playerCounterM(mons, Math.random());
                            if (damageReturn == 0){ //Success, take no damage.
                                System.out.println("You successfully dodge the monster.");
                            }
                            else{ //Fail, take a critical hit (2x damage)
                                System.out.println("You try and fail to dodge the attack, leaving you wide open for a critical hit.");
                                play.setHealth(play.getHealth() - damageReturn);
                            }
                            break;
                    }
                }
                if (!successfullBlock && !successfullCounter)
                    System.out.println(playerUpdate(play));
                battleOver = fightLoopMons(play, mons);
            }
        }
        else { //If the monster rolled a higher initiative, going first.
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
                                System.out.println("You partially block the attack, taking some damage.");
                                play.setHealth(play.getHealth()-(mons.getDamage()/2));
                            }
                            else{
                                System.out.println("You fail to quickly block the attack, taking full damage.");
                                play.setHealth(play.getHealth() - mons.getDamage());
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
                            }
                        }
                        else{
                            System.out.println("\nYou attack the monster.");
                            mons.setHealth(mons.getHealth()-play.getDmg());
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
                                System.out.println("You partially block the attack, taking some damage.");
                                play.setHealth(play.getHealth()-(bos.getDamage()/2));
                            }
                            else{
                                System.out.println("You fail to quickly block the attack, taking full damage.");
                                play.setHealth(play.getHealth() - bos.getDamage());
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
                    System.out.println("\nThe boss attacks you!");
                    Choice = playerChoiceNot();
                    switch (Choice){
                        case 'B':
                            System.out.println("\nThe boss attacks you!");
                            damageReturn = playerBlockB(bos, Math.random());
                            if (damageReturn == 0){
                                System.out.println("You partially block the attack, taking some damage.");
                                play.setHealth(play.getHealth()-(bos.getDamage()/2));
                            }
                            else{
                                System.out.println("You fail to quickly block the attack, taking full damage.");
                                play.setHealth(play.getHealth() - bos.getDamage());
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
                            }
                        }
                        else{
                            System.out.println("\nYou attack the boss.");
                            bos.setHealth(bos.getHealth()-play.getDmg());
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

    /**monsterDodgeAttempt
     * The code for determining if the monster successfully dodges the players attack.
     * 
     * @param play The player object for getting the players damage.
     * @param mons The monster object used for getting the monster's dodge variable.
     * @return Returns the damage the monster should take.
     */
    private static int monsterDodgeAttempt(Player play, Monster mons){
        double attemptDodge = Math.random();
        int damageReturn = 0;
        if (attemptDodge > mons.getDodge())
            damageReturn = play.getDmg()*2;
        return damageReturn;
    }

    /** bossDodgeAttempt
     * The code for determining if the boss successfully dodges the players attack.
     * 
     * @param play The player object for getting the player's damage.
     * @param bos The boss object for getting the bosses dodge value.
     * @return Returns the damage the boss should take.
     */
    private static int bossDodgeAttempt(Player play, Boss bos){
        double attemptDodge = Math.random();
        int damageReturn = 0;
        if (attemptDodge > bos.getDodge())
            damageReturn = play.getDmg()*2;
        return damageReturn;
    }

    /**playerBlockM
     * This method determines if the player successfully blocks the monster's attack.
     * 
     * @param mons The monster object for getting the monsters damage.
     * @param blockChance The randomized value used for comparison for if the player successfully blocks.
     * @return Returns the damage the player should take.
     */
    private static int playerBlockM(Monster mons, double blockChance){
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < blockChance)
			if (mons.getDamage() == 1)
				damageReturn = 1;
			else
				damageReturn = ((mons.getDamage()/2));
        return damageReturn;
    }

    /**playerBlockB
     * This method determines if the player successfully blocks the boss's attack.
     * 
     * @param bo The boss object for getting the bosses damage.
     * @param blockChance The randomized value used for comparison for if the player successfully blocks.
     * @return Returns the damage the player should take.
     */
    private static int playerBlockB(Boss bo, double blockChance){
        double successBlock = blockChance;
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < successBlock)
			if (bo.getDamage() == 1)
				damageReturn = 1;
			else
				damageReturn = ((bo.getDamage()/2));
        return damageReturn;
    }

    /**playerCounterM
     * This method determines if the player successfully counters the monster's attack.
     * 
     * @param mons The monster object for getting the monster's damage value.
     * @param blockChance The randomized value used for comparison for if the player successfully counter the attack.
     * @return Returns how much damage the player should take.
     */
    private static int playerCounterM(Monster mons, double blockChance){
        double successBlock = blockChance;
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < successBlock)
            damageReturn = (mons.getDamage()*2);
        return damageReturn;
    }

    /**playerCounterB
     * This method determines if the player successfully counters the boss's attack.
     * 
     * @param bo The boss object for getting the boss's damage value.
     * @param blockChance The randomized value used for comparison for if the player successfully counter the attack.
     * @return Returns how much damage the player should take.
     */
    private static int playerCounterB(Boss bo, double blockChance){
        double successBlock = blockChance;
        double playerChance = Math.random();
        int damageReturn = 0;
        if (playerChance < successBlock)
            damageReturn = (bo.getDamage()*2);
        return damageReturn;
    }

    /**counterSuccess
     * This method is used for if the player successfully counter's an attack, to calculate the damage that should be taken.
     * 
     * @param play The player object is used for getting the player's damage value.
     * @return Returns the player's damage*2 for the damage the monster will take.
     */
    private static int counterSuccess(Player play){
        return (play.getDmg()*2);
    }

    /**playerChoiceTurn
     * This method holds the choices the player can make when it is their turn.
     * 
     * @return Returns the choice of (A)ttack, (B)lock, or (C)ounter.
     */
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

    /**playerChoiceNot
     * This method holds the choices that the player can make when it is NOT their turn.
     * 
     * @return Returns the player's choice. (B)lock or (D)odge
     */
    private static char playerChoiceNot(){
        s = new Scanner(System.in);
        char choiceChar;
        System.out.print("\nOptions during attack: "
                            + "\nB)lock *Success: (Block half damage) | Failure:(Take full damage)"
                            + "\nD)odge *Success:(Take no damage) | Failure:(Take double damage)"
                            + "\nYou choose: ");
        String choiceString = s.nextLine().toUpperCase();
        String [] choice = choiceString.split("");
        while(!choice[0].equals("B") && !choice[0].equals("D")){
            System.out.print("\nInvalid input! Options for move are: "
                                + "\nB)lock *Success: (Block half damage) | Failure:(Take full damage)"
                                + "\nD)odge *Success:(Take no damage) | Failure:(Take double damage)"
                                + "\nYou choose: ");
            choiceString = s.nextLine().toUpperCase();
            choice = choiceString.split("");
        }
        choiceChar = choiceString.charAt(0);
        return choiceChar;
    }

    /**monsterUpdate
     * A method for displaying the monster's health after each time it takes damage.
     * 
     * @param mons The monster object for getting the monster's health.
     * @return Returns a string of the monster's current health.
     */
    private static String monsterUpdate(Monster mons){
        return ("\nMonster's health: " + mons.getHealth());
    }

    /**bossUpdate
     * A method for displaying the boss's current health.
     * 
     * @param bos The boss object for getting the current health.
     * @return Returns a string of the boss's current health.
     */
    private static String bossUpdate(Boss bos){
        return ("\nBoss's health: " + bos.getHealth());
    }

    /**playerUpdate
     * A method for displaying the player's current health.
     * 
     * @param play The player object for getting the current health.
     * @return Returns a string of the current player health.
     */
    private static String playerUpdate(Player play){
        return ("\nPlayer's health: " + play.getHealth());
    }

    /**toStringHero
     * A method made for displaying the current stats and name of the player.
     * 
     * @param play The player object that is used for name/health/damage display.
     * @return Returns a string of the player's current stats.
     */
    private static String toStringHero(Player play){
        return ("Player's name: " + play.getName() + "\nHealth: " + play.getHealth() + "\nDamage: " + play.getDmg());
    }

    /**fightLoopMons
     * A method used for determining if the battle needs to end.
     * 
     * @param play The player object for getting the health for comparison.
     * @param mons The monster object for getting the health for comparison.
     * @return Returns a boolean for if the battles needs to end or not.
     */
    private static boolean fightLoopMons(Player play, Monster mons){
        if (mons.getHealth() <= 0 || play.getHealth() <= 0)
            return true;
        else
            return false;
    }

    /**fightLoopBos
     * A method used for determining if the battle needs to end.
     * 
     * @param play The player object for getting the health for comparison.
     * @param bo The boss object for getting the health for comparison.
     * @return Returns a boolean for if the battles needs to end or not.
     */
    private static boolean fightLoopBos(Player play, Boss bo){
        if (bo.getHealth() <= 0 || play.getHealth() <= 0)
            return true;
        else
            return false;
    }
}