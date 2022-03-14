package ordilov.lissn.common.domain.exception;


public class IllegalStatusException extends BaseException {

    public IllegalStatusException() {
        super(ErrorCode.BAD_REQUEST);
    }

}
