import madkit.kernel.Agent;

/**
 * Created by Mefju on 22.05.2017.
 */
public class Statistics extends Agent {

    private int numberOfMessages;
    private long stat = 0;
    private String community;

    public Statistics(int numberOfMessages, String community)
    {
        this.community = community;
        this.numberOfMessages = numberOfMessages;
    }
    @Override
    protected void activate() {
        createGroup(community, "stat");
        requestRole(community, "stat", "rec");
        logger.info("Statistics activate");
    }

    @Override
    protected void live() {
        int i = numberOfMessages;
        while(i > 0){
            StatMsg incommingMessage = (StatMsg) waitNextMessage();
            logger.info("Get stat");
            stat += incommingMessage.getTime();
            i--;
        }
        long average = stat/numberOfMessages;
        logger.info("AVERAGE: " + Long.toString(average));
    }

    @Override
    protected void end() {
    logger.info("END of statistics");
    }


}
