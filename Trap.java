import java.util.*;
import java.io.*;

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
		Scanner s = new Scanner (System.in);
		System.out.println(description);
		Random rand = new Random();
		int a = rand.nextInt(100+1);
		System.out.println("Your senses flare! Something is amiss. Do you choose to:\n\tA: Play it safe!\n\t\tor\n\tB: Try to avoid the threat!");
		String reply = s.nextLine();
		
		if(reply.equalsIgnoreCase("a")) {
			System.out.println(failMessage + "\nBut your turtle instinct was not for naught: The damage seems to have been lessend!");
			player.takeDamage(damage/2);
		} else {
			if(a > chance) {
				System.out.println(failMessage);
				player.takeDamage(damage);
			} else {
				System.out.print(successMessage+" ");
				if(!reward.getType().equals("NONE")) {
				System.out.println(rewardMessage+"\n");
				player.addReward(reward);
			} else {
				System.out.println("");
			}
			}
		}
		
	}
}