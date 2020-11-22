public class Monster {
    protected int health;
    protected int damage;
    protected String name;
    protected int difficulty;
    protected double dodge;

    private final static double dodgeChance = 0.4;

    private final static int defaultDamage = 1;
    private final static int defaultHealth = 3;
    private final static int defaultDifficulty = 1;

    public Monster(){
        health = defaultHealth;
        damage = defaultDamage;
        name = "Unnamed Abomination";
        difficulty = defaultDifficulty;
        dodge = dodgeChance;
    }

    public Monster(int h, int d, String n, int dif){
        health = h;
        damage = d;
        name = n;
        difficulty = dif;
        dodge = dodgeChance;
    }

    public Monster(Monster mon){
        health = mon.health;
        damage = mon.damage;
        name = mon.name;
        difficulty = mon.difficulty;
        dodge = dodgeChance;
    }

    public void setHealth(int h){
        health = h;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public double getDodge(){
        return dodgeChance;
    }

    public String toString(){
        return ("Monsters name: " + name + "\nDifficulty: " + difficulty + "\nHealth: " + health + "\nDamage: " + damage);
    }
}
