package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidatePhoneRequest {
    private String phone; // 인증할 전화번호

    public ValidatePhoneRequest(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
