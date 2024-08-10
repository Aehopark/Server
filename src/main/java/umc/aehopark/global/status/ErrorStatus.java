package umc.aehopark.global.status;

<<<<<<< HEAD

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
=======
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
>>>>>>> c87bb972cc4558dad03ba25f39de87f259b87e38
import umc.aehopark.global.common.dto.ErrorReasonDTO;
import umc.aehopark.global.type.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

<<<<<<< HEAD
    // 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "금지된 요청입니다."),

    // User 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자가 없습니다."),
    NICKNAME_INPUT_NOT_EXIST(HttpStatus.BAD_REQUEST, "닉네임을 입력해주세요"),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다."),

    FAIL_PARSE_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, "Apple Identity Token 헤더 파싱을 실패하였습니다."),
    FAIL_PARSE_CLAIM(HttpStatus.BAD_REQUEST, "Claim을 파싱하는데 실패하였습니다."),
    INVALID_APPLE_OAUTH_CLAIMS(HttpStatus.BAD_REQUEST, "유효하지 않은 Apple OAuth Claim입니다.");


    private final HttpStatus httpStatus;
    private final String message;

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
=======
	// 일반적인 응답
	_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러, 관리자에게 문의 바랍니다."),
	_BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
	_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
	_FORBIDDEN(HttpStatus.FORBIDDEN, "금지된 요청입니다."),

	// User 관련 에러
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자가 없습니다."),
	NICKNAME_INPUT_NOT_EXIST(HttpStatus.BAD_REQUEST, "닉네임을 입력해주세요"),
	DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다."),

	// Product 관련 에러
	DUPLICATE_PRODUCT(HttpStatus.BAD_REQUEST, "중복된 상품입니다."),
	PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "상품이 없습니다."),
	INGREDIENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "검색한 식재료가 없습니다."),
	CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "검색한 카테고리가 없습니다."),

	FAIL_PARSE_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, "Apple Identity Token 헤더 파싱을 실패하였습니다."),
	FAIL_PARSE_CLAIM(HttpStatus.BAD_REQUEST, "Claim을 파싱하는데 실패하였습니다."),
	INVALID_APPLE_OAUTH_CLAIMS(HttpStatus.BAD_REQUEST, "유효하지 않은 Apple OAuth Claim입니다.");

	private final HttpStatus httpStatus;
	private final String message;

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
>>>>>>> c87bb972cc4558dad03ba25f39de87f259b87e38

}