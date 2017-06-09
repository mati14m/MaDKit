package Rejestracja;

import madkit.gui.ConsoleAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;

/**
 * Created by Mefju on 09.06.2017.
 */
public class RegScheduler extends Scheduler {

    @Override
    protected void activate() {
        String community = "community";
        int numberOfAgents = 500;

        createGroup("community", "group");
        long startTime = System.currentTimeMillis();  //time of registering agents
        for (int i = 0; i < numberOfAgents; i++) {
            createGroup("community", Integer.toString(i));
            launchAgent(new RegAgent(Integer.toString(i), community, "1"));
        }
        long endTime = System.currentTimeMillis();

        logger.info("TIME: " + Long.toString((endTime-startTime)));

        launchAgent(new FindAgent(Integer.toString(numberOfAgents/2), community));  //find 1 agent

        setDelay(0);
    }

    public static void main(String[] args) {
        new Madkit("--launchAgents", RegScheduler.class.getName()+",true;"+ConsoleAgent.class.getName());
    }
}
