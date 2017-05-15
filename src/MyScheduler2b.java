import madkit.action.KernelAction;
import madkit.gui.ConsoleAgent;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;

@SuppressWarnings("serial")
public class MyScheduler2b extends Scheduler {

    private GenericBehaviorActivator<AbstractAgent> activator1;

    @Override
    protected void activate() {
        String role1 = "1";
        String role2 = "2";
        String community = "local";
        int numberOfMessages = 1000;
        String text = "test";
        KernelAction.LAUNCH_NETWORK.getActionFor(this).actionPerformed(null);


        for(int i=0; i<5; i++){    //length of message
            text += text;
        }
        logger.info(text);
        byte[] messageText = text.getBytes();

        for (int i = 0; i < 50; i++) {    //number of agents * 2 (pair)
            createGroupIfAbsent(community, Integer.toString(i), true);
            launchAgent(new MyAgent(role2, role1, Integer.toString(i), community, numberOfMessages, messageText));
        }

        logger.info("Scheduler!!!");
        setDelay(300);
    }


    public static void main(String[] args) {
        new Madkit("--network", "--launchAgents", MyScheduler2b.class.getName()+",true;"+ConsoleAgent.class.getName());
    }

}