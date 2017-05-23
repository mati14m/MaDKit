import madkit.kernel.Message;

/**
 * Created by Mefju on 22.05.2017.
 */
public class StatMsg extends Message {
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
