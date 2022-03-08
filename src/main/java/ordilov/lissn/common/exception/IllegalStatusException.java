package ordilov.lissn.common.exception;


public class IllegalStatusException extends BaseException {

    public IllegalStatusException() {
        super(ErrorCode.BAD_REQUEST);
    }

}
