/* Joel Hervey - Choice.java
*	Creates choices for the player to interact with, and depending on what they choose, displays a different story.
*	If there is a reward, it will also give the player the reward depending on what action they chose
*/
import java.util.*;

public class  Choice extends Event {
	private String optionA;
	private String optionB;
	private String resolutionA;
	private String resolutionB;
	/**No param constructor
	*
	*/
	public Choice() {
		super();
		optionA = "N/A";
		optionB = "N/A";
	}
	/** Constructor that calls the super constructor as well as initializes the variables
	* This constructor does not includes the reward
	*
	*@param p The player attached to the choice
	*@param des The description of the choice
	*@param a The first option to choose
	*@param b The second option to choose
	*/
	public Choice(Player p,String des, String a, String b) {
		super(p,des);
		optionA = a;
		optionB = b;
	}
	/** Constructor that calls the super constructor as well as initializes the variables
	* This constructor includes the reward
	*
	*@param p The player attached to the choice
	*@param des The description of the choice
	*@param a The first option to choose
	*@param b The second option to choose
	*@param message The message to display when giving out the reward
	*@param r Reward attached to the object
	*/
	public Choice(Player p,String des, String a, String b, String message, Reward r) {
		super(p,des,r,message);
		optionA = a;
		optionB = b;
	}
	/** setResolution changes what story is told after the player inputs their decision
	*@param a The story for option A
	*@param b The story for option B
	*/
	public void setResolution(String a, String b) {
		resolutionA = a;
		resolutionB = b;
	}
	/** userChoice allows the player to input the choice between option A or B, and displays the resolution for that choice
	* It returns a boolean to be applied in the main class in certain situations. A and B will randomly be interchanged
	* according to the 'switcharoo' value.
	*
	*@return boolean
	*/
	public boolean userChoice() {
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		System.out.println(description);
		int switcharoo = r.nextInt(2);
		
		if (switcharoo == 0) {
			System.out.println("Do you choose to:\n\tA: "+optionA+"\n\t\tor\n\tB: "+optionB);
			String reply = s.nextLine();
			
			if(reply.equalsIgnoreCase("a")) {
				System.out.println(resolutionA);
				if(!reward.getType().equals("NONE")) {
					System.out.println(rewardMessage);
					player.addReward(reward);
				}
				return false;
			} else if(reply.equalsIgnoreCase("b")) {
				System.out.println(resolutionB);
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("Do you choose to:\n\tA: "+optionB+"\n\t\tor\n\tB: "+optionA);
			String reply = s.nextLine();
			
			if(reply.equalsIgnoreCase("b")) {
				System.out.println(resolutionA);
				if(!reward.getType().equals("NONE")) {
					System.out.println(rewardMessage);
					player.addReward(reward);
				}
				return false;
			} else if(reply.equalsIgnoreCase("a")) {
				System.out.println(resolutionB);
				return true;
			} else {
				return false;
			}
		}
	}
}