package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoLoginRequest {
    private String accessToken; // 카카오 로그인 토큰

    public KakaoLoginRequest(String accessToken) {
        this.accessToken = accessToken;
    }

}
