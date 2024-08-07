package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNameAlterRequest {
    private String userName; // 변경할 유저 이름

    public UserNameAlterRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
