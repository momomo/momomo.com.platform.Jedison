package m.exceptions;

public class $JedisonNotAvailableException extends $JedisonException {
    public $JedisonNotAvailableException() {
        super("No jedison instance is registered to the current executing thread.");
    }
}
