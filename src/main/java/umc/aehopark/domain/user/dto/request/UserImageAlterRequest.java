package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImageAlterRequest {
    private String imageUrl; // 변경할 유저 이미지

    public String getUserImage() {
        return imageUrl;
    }
}
