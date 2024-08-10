package umc.aehopark.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class InfoUserResponse {
    private String userId;
    private String type; // UserType
    private String nickname;
    private String address;
    private Integer phone;
    private String email;
    private Boolean isMarketing;
    private String provider; // OAuthProvider
}
