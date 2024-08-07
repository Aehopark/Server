package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppleLoginRequest {
    private String code; // 애플 로그인 코드
    private String idToken; // 애플 로그인 ID 토큰

    public AppleLoginRequest(String code, String idToken) {
        this.code = code;
        this.idToken = idToken;
    }

    public String getCode() {
        return code;
    }

    public String getIdToken() {
        return idToken;
    }
}
