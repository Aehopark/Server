package umc.aehopark.global.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class reissueTokenRequest {
    private String AccessToken; // 액세스 토큰
    private String refreshToken; // 리프레시 토큰

    public reissueTokenRequest(String accessToken, String refreshToken) {
        this.AccessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
