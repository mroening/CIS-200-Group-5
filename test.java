/* Trent Powell- Group 5 Final Proj. (test)
* CIS 200/ Scholars Section
*/

import java.util.*;
import java.io.*;

public class test {
	public static void main (String [ ] args) {
		Player player = new Player();
		System.out.println(player);
		Story story = new Story(player.getName());
		story.event1();
		story.event2();
		story.event3(player);
		story.event4(player);
		story.event5(player);
		story.event6(player);
		story.event7();
		
	}
}