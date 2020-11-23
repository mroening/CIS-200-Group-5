import java.util.*;
public class Trap extends Event{
	private int damage;
	private String failMessage;
	private String successMessage;
	
	public Trap() {
		super();
		damage = 0;
		failMessage = "You failed";
		successMessage = "You succeded";
		
	}
	public Trap(Player p,String des, int dmg,String fail, String suc) {
		super(p,d);
		damage = a;
		failMessage = f;
		successMessage = s;
	}
	public Trap(Player p,String des, int dmg,String fail, String suc, Reward r) {
		super(p,des,r);
		damage = dmg;
		failMessage = fail;
		successMessage = suc;
	}
	//The higher the number the more likely you are to succede
	public void disarm(int chance) {
		System.out.println(description);
		Random rand = new Random();
		int a = rand.nextInt(100+1);
		int playerHealth = player.getHealth();
		if(a > chance) {
			System.out.println(failMessage);
			player.setHealth(playerHealth-damage);
		} else {
			System.out.println(successMessage);
			if(reward != null) {
			player.addReward(reward);
		}
		}
		
	}
}