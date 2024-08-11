package umc.aehopark.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.aehopark.global.status.SuccessStatus;
import umc.aehopark.global.type.BaseCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"message", "result"})
public class ApiResponse<T> {

	@JsonProperty("message")
	private final String message;
	@JsonInclude(Include.NON_NULL)
	private T result;

	// 성공한 경우 응답 생성
	public static <T> ApiResponse<T> onSuccess(T result) {
		return new ApiResponse<>(SuccessStatus.SUCCESS.getMessage(), result);
	}

	public static <T> ApiResponse<T> of(BaseCode code, T result) {
		return new ApiResponse<>(code.getReasonHttpStatus().getMessage(), result);
	}

	// 실패한 경우 응답 생성
	public static <T> ApiResponse<T> onFailure(String message, T data) {
		return new ApiResponse<>(message, data);
	}
}