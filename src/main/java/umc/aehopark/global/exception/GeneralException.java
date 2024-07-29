package umc.aehopark.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.aehopark.global.common.dto.ErrorReasonDTO;
import umc.aehopark.global.type.BaseErrorCode;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}