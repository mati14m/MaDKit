package Rejestracja;

import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;

/**
 * Created by Mefju on 09.06.2017.
 */
public class FindAgent extends Agent {

    private String group;
    private String community;
    private Boolean notStart = true;

    public FindAgent(String group, String community){
        this.group = group;
        this.community = community;
    }

    @Override
    protected void activate() {
        requestRole(community, group, "2");
    }

    @Override
    protected void live() {
        long startTime = System.currentTimeMillis();
        AgentAddress regAgent = getAgentWithRole(community, group, "10");
        long endTime = System.currentTimeMillis();
        logger.info("SEARCH TIME: "+Long.toString(endTime-startTime)+" "+regAgent.getAgentNetworkID());
    }

    @SuppressWarnings("unused")
    private void doIt() {
        notStart = false;
    }
}
