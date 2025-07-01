package m.exceptions;

public class $JedisonSeveralInstancesCreatedException extends $JedisonException {
    public $JedisonSeveralInstancesCreatedException() {
        super("More than one jedison instance has been created, and need to call jedison.withConnection(...) prior to select the instance to perform the desired action.");
    }
}
