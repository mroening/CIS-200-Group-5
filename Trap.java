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
	public Trap(Player p,String d, int a,String f, String s) {
		super(p,d);
		damage = a;
		failMessage = f;
		successMessage = s;
	}
	public void disarm(int bound) {
		if(bound % 2 != 0) {
			bound++;
		}
		System.out.println(description);
		Random rand = new Random();
		int a = rand.nextInt(bound+1);
		if(a >= bound/2) {
			System.out.println(failMessage);
			playerInfo.Hurt(damage);
		} else {
			System.out.println(successMessage);
		}
	}
}