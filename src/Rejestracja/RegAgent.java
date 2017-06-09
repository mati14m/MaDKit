package Rejestracja;

import madkit.kernel.Agent;

/**
 * Created by Mefju on 09.06.2017.
 */
public class RegAgent extends Agent {

    private String group;
    private String community;
    private String role;
    private Boolean notStart = true;

    public RegAgent(String group, String community, String role){
        this.group = group;
        this.community = community;
        this.role = role;
    }

    @Override
    protected void activate() {
        requestRole(community, group, role);
    }

    @Override
    protected void live() {
        while(notStart){
            pause(500);
        }
    }

    @SuppressWarnings("unused")
    private void doIt() {
        notStart = false;
    }
}
