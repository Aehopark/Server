package umc.aehopark.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
}
