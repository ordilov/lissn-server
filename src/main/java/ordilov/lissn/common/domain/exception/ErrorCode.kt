package ordilov.lissn.common.domain.exception;


enum class ErrorCode(val message: String) {
    BAD_REQUEST("잘못된 입력으로 요청했습니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 있습니다."),
    UNAUTHORIZED("인증되지 않은 사용자입니다."),
    EXPIRED_TOKEN("토큰이 만료되었습니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다.");
}