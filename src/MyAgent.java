import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;
import java.util.logging.Level;


@SuppressWarnings("serial")
public class MyAgent extends Agent {

    private String myRole;
    private String partnerRole;
    private String group;
    private String community;
    private int numberOfMessages;
    private Boolean notStart = true;
    private long startTime;
    private long endTime;
    private byte[] messageText;


    public  MyAgent(String myRole, String partnerRole, String group, String community, int numberOfMessages, byte[] messageText){
        this.myRole = myRole;
        this.partnerRole = partnerRole;
        this.group = group;
        this.community = community;
        this.numberOfMessages = numberOfMessages;
        this.messageText = messageText;
    }

    @Override
    protected void activate() {
        requestRole(community, group, myRole);
        logger.info("activate");
    }

    @Override
    protected void live() {
        AgentAddress other = null;
        while(other == null){
            other = getAgentWithRole(community, group, partnerRole);
        }

        if(myRole.equals("1")){
            while(notStart){
                pause(500);
            }
            startTime = System.currentTimeMillis();
            sendMessage(other, new MyMessage());
            numberOfMessages--;
        }
        while(numberOfMessages > 0) {
            MyMessage incommingMessage = (MyMessage) waitNextMessage();
            numberOfMessages--;
            MyMessage msg = new MyMessage();
            msg.setMsg(messageText);
            sendMessage(other, msg);
        }
        endTime = System.currentTimeMillis();
        if(myRole.equals("1")) {
            MyMessage incommingMessage = (MyMessage) waitNextMessage();
        }
            pause(5000);
    }

    @SuppressWarnings("unused")
    private void doIt() {
        notStart = false;
    }

    @Override
    protected void end() {
        if(myRole.equals("1")){
            logger.log(Level.INFO, Long.toString(endTime - startTime));
        }
        pause(5000);
    }
}
