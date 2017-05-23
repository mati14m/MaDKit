import madkit.action.KernelAction;
import madkit.gui.ConsoleAgent;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;

@SuppressWarnings("serial")
public class MyScheduler2 extends Scheduler {

    private GenericBehaviorActivator<AbstractAgent> activator1;

    @Override
    protected void activate() {
        String role1 = "1";
        String role2 = "2";
        String community = "mycomm";
        int numberOfMessages = 1000;
        int numberOfAgents = 50;
        String text = "test";
        KernelAction.LAUNCH_NETWORK.getActionFor(this).actionPerformed(null);


        for(int i=0; i<5; i++){    //length of message
            text += text;
        }
        logger.info(text);
        byte[] messageText = text.getBytes();

        for (int i = 0; i < numberOfAgents; i++) {    //number of agents - first of pair
            createGroupIfAbsent(community, Integer.toString(i), true);
            launchAgent(new MyAgent(role1, role2, Integer.toString(i), community, numberOfMessages, messageText));
            activator1 = new GenericBehaviorActivator<AbstractAgent>(community, Integer.toString(i), "1", "doIt");
            addActivator(activator1);
        }

        launchAgent(new Statistics(numberOfAgents, community));  //agent od statystyk
        logger.info("Scheduler!!!");
        setDelay(0);
    }


    public static void main(String[] args) {
        new Madkit("--network", "--launchAgents", MyScheduler2.class.getName()+",true;"+ConsoleAgent.class.getName());
    }

}