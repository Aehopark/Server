package umc.aehopark.global.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.aehopark.global.common.dto.ReasonDTO;
import umc.aehopark.global.type.BaseCode;;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    SUCCESS(HttpStatus.OK, "성공입니다.");

    private final HttpStatus httpStatus;
    private final String message;


    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }
}