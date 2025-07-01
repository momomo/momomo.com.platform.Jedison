package m.jedison;

import m.jedison.spaces.JSpaceProcess;
import m.jedison.spaces.JSpaceQueue;

/**
 * Same as JQueue but enforces the message type for proper tracking
 */
public abstract class JQueueMessage<M extends JMessage> extends JQueue<M> {

    @Override
    protected void onReadsFail(Throwable e) {
        super.onReadsFail(e);
    }

    @Override
    protected void onReadFail(Throwable e) {
        super.onReadFail(e);
    }

    public JQueueMessage(JSpaceProcess space) {
        super(new JSpaceQueue(space));
    }

    @Override
    public void queue(M message) {
        message.registerQueued();

        super.queue(message);
    }

    @Override
    protected void $process$(M message, int attempt) {
        message.registerProcess();

        super.$process$(message, attempt);
    }

    @Override
    protected void onProcessFailed(M message, Throwable e, int attempt) {
        message.registerProcessFailed();

        super.onProcessFailed(message, e, attempt);
    }

    @Override
    protected void onProcessSuccess(M message) {
        message.registerProcessSuccess();

        super.onProcessSuccess(message);
    }

    @Override
    protected void process(M message) throws Exception {
        message.process();
    }
}
