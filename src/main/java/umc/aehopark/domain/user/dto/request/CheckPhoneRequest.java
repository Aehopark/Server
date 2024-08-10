package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckPhoneRequest {
    private String code; // 인증번호

    public CheckPhoneRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
