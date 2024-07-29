package umc.aehopark.global.status;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.aehopark.global.common.dto.ErrorReasonDTO;
import umc.aehopark.global.type.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // User 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자가 없습니다."),
    NICKNAME_INPUT_NOT_EXIST(HttpStatus.BAD_REQUEST, "닉네임을 입력해주세요"),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다."),

    FAIL_PARSE_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, "Apple Identity Token 헤더 파싱을 실패하였습니다."),
    FAIL_PARSE_CLAIM(HttpStatus.BAD_REQUEST, "Claim을 파싱하는데 실패하였습니다."),
    INVALID_APPLE_OAUTH_CLAIMS(HttpStatus.BAD_REQUEST, "유효하지 않은 Apple OAuth Claim입니다.");


    private final HttpStatus httpStatus;
    private final String message;
`
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }

}