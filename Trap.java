/* Joel Hervey - Trap.java
*	Creates traps that the player either disarms or gets hit by. If the trap contains a reward, it will also give the 
*	reward to the player. The player can also choose to play it safe and just take half the damage from the trap
*/
import java.util.*;
import java.io.*;

public class Trap extends Event{
	private int damage;
	private String failMessage;
	private String successMessage;
	/**No param constructor
	*
	*/
	public Trap() {
		super();
		damage = 0;
		failMessage = "You failed";
		successMessage = "You succeded";
		
	}
	/** Constructor that calls the super constructor as well as initializes the variables
	* This constructor does not include the reward
	*
	*@param p The player attached to the trap
	*@param des The description of the trap
	*@param dmg The damage of the trap
	*@param fail The message to display if you fail to disarm the trap
	*@param suc The message to display if you disarm the trap
	*/
	public Trap(Player p,String des, int dmg,String fail, String suc) {
		super(p,des);
		damage = dmg;
		failMessage = fail;
		successMessage = suc;
	}
	/** Constructor that calls the super constructor as well as initializes the variables
	* This constructor includes the reward
	*
	*@param p The player attached to the trap
	*@param des The description of the trap
	*@param dmg The damage of the trap
	*@param fail The message to display if you fail to disarm the trap
	*@param suc The message to display if you disarm the trap
	*@param r Reward attached to the object
	*@param message The message to display when giving out the reward
	*/
	public Trap(Player p,String des, int dmg,String fail, String suc, Reward r,String message) {
		super(p,des,r,message);
		damage = dmg;
		failMessage = fail;
		successMessage = suc;
	}
	/** disarm allows the player to either play it safe and take half damage, or to try and disarm the trap. 
	* The player disarms the trap based on random chance. If the player disarms the trap, it displays the success message, and hands out a reward if
	* one exists. Otherwise it displays the failure message
	*
	*@param chance The chance that the player disarms the trap
	*/
	public void disarm(int chance) {
		Scanner s = new Scanner (System.in);
		System.out.println(description);
		Random rand = new Random();
		int a = rand.nextInt(100+1);
		System.out.println("Your senses flare! Something is amiss. Do you choose to:\n\tA: Play it safe!\n\t\tor\n\tB: Go for it!");
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