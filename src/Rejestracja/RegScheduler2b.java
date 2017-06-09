package Rejestracja;

import madkit.action.KernelAction;
import madkit.gui.ConsoleAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;

/**
 * Created by Mefju on 09.06.2017.
 */
public class RegScheduler2b extends Scheduler {

    @Override
    protected void activate() {
        String community = "community";
        int numberOfAgents = 500;
        KernelAction.LAUNCH_NETWORK.getActionFor(this).actionPerformed(null);

        createGroup("community", "group", true);
        long startTime = System.currentTimeMillis();  //time of registering agents
        for (int i = 0; i < numberOfAgents; i++) {
            //createGroupIfAbsent("community", Integer.toString(i),true);
            launchAgent(new RegAgent(Integer.toString(i), community, "2"));
        }
        long endTime = System.currentTimeMillis();

        logger.info("TIME: " + Long.toString((endTime-startTime)));

        launchAgent(new FindAgent(Integer.toString(numberOfAgents/2), community));  //find 1 agent (other host)

        setDelay(0);
    }

    public static void main(String[] args) {
        new Madkit("--network","--launchAgents", RegScheduler.class.getName()+",true;"+ConsoleAgent.class.getName());
    }
}