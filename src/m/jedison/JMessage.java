package m.jedison;

import lombok.Getter;
import m.instance.Nanotime;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

public abstract class JMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    protected final long version;

    protected JMessage() {
        this(1L);
    }

    protected JMessage(long version) {
        this.version = version;
    }

    protected final OffsetDateTime          created = Nanotime.$().offset();
    protected       Status                  status  = Status.CREATED;
    protected final ArrayList<QueuedRecord> queued  = new ArrayList<>();

    public void registerQueued() {
        queued.add( new QueuedRecord() );
    }

    public void registerProcess() {
        last().registerProcess();
    }
    public void registerProcessSuccess() {
        this.status = Status.SUCCESS;

        last().registerProcessEnd();
    }
    public void registerProcessFailed() {
        this.status = Status.FAILED;
        last().registerProcessEnd();
    }

    private QueuedRecord last() {
        return queued.get(queued.size()-1);
    }

    public abstract <E extends Exception> void process() throws E;

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public static final class QueuedRecord implements Serializable {
        private static final long serialVersionUID = 1L;

        @Getter final OffsetDateTime           queued     = Nanotime.$().offset();
        @Getter final ArrayList<ProcessRecord> processing = new ArrayList<>();

        public void registerProcess() {
            processing.add( new ProcessRecord() );
        }

        public void registerProcessEnd() {
            last().registerEnd();
        }

        private ProcessRecord last() {
            return processing.get(processing.size()-1);
        }

    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public static final class ProcessRecord implements Serializable{
        private static final long serialVersionUID = 1L;

        @Getter final OffsetDateTime start = Nanotime.$().offset();
        @Getter       OffsetDateTime end;
        @Getter       String    error;                // In case there is an error, the error message will be attached here

        public void registerEnd() {
            this.end = Nanotime.$().offset();
        }
    }
    
    

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    enum Status {
        CREATED, QUEUED, REQUEUED, SUCCESS, FAILED
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

}
