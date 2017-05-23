import madkit.gui.ConsoleAgent;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;

@SuppressWarnings("serial")
public class MyScheduler extends Scheduler {

	private GenericBehaviorActivator<AbstractAgent> activator1;

	@Override
	protected void activate() {
		String role1 = "1";
		String role2 = "2";
		String community = "community";
		int numberOfMessages = 1000;
		int numberOfAgents = 100;
		String text = "test";

		for(int i=0; i<5; i++){    //length of message
			text += text;
		}
		logger.info(text);
		byte[] messageText = text.getBytes();

		for (int i = 0; i < numberOfAgents; i++) {    //number of agents * 2 (pair)
			createGroup("community", Integer.toString(i));
			launchAgent(new MyAgent(role1, role2, Integer.toString(i), community, numberOfMessages, messageText));
			launchAgent(new MyAgent(role2, role1, Integer.toString(i), community, numberOfMessages, messageText));
			activator1 = new GenericBehaviorActivator<AbstractAgent>("community", Integer.toString(i), "1", "doIt");
			addActivator(activator1);
		}

		launchAgent(new Statistics(numberOfAgents, community));
		logger.info("Scheduler!!!");
		setDelay(0);
	}


	public static void main(String[] args) {
		new Madkit("--launchAgents", MyScheduler.class.getName()+",true;"+ConsoleAgent.class.getName());
	}

}