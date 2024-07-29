package umc.aehopark.global.common.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {
    private HttpStatus httpStatus;

    private final String message;
}