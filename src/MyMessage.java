import madkit.kernel.Message;

/**
 * Created by Mefju on 14.05.2017.
 */
public class MyMessage extends Message {
    private byte[] msg;

    public byte[] getMsg() {
        return msg;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }
}
