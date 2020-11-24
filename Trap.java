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
		super(p,des);
		damage = dmg;
		failMessage = fail;
		successMessage = suc;
	}
	public Trap(Player p,String des, int dmg,String fail, String suc, Reward r,String message) {
		super(p,des,r,message);
		damage = dmg;
		failMessage = fail;
		successMessage = suc;
	}
	/*Activates the trap with a random chance to succeed, then displays the proper messages*/
	public void disarm(int chance) {
		System.out.println(description);
		Random rand = new Random();
		int a = rand.nextInt(100+1);
		if(a > chance) {
			System.out.println(failMessage);
			player.takeDamage(damage);
		} else {
			System.out.print(successMessage+" ");
			if(reward != null) {
			System.out.println(rewardMessage+"\n");
			player.addReward(reward);
		} else {
			System.out.println("");
		}
		}
		
	}
}