package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImageAlterRequest {
    private String userImage; // 변경할 유저 이미지

    public UserImageAlterRequest(String userImage) {
        this.userImage = userImage;
    }

    public String getUserImage() {
        return userImage;
    }
}
