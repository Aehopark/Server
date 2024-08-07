package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterUserRequest {
    private String nickname; // 사용자 닉네임
    private String address; // 사용자 주소
    private String detailAddress; // 사용자 상세주소
    private String imageUrl; // 사용자 프로필 이미지 URL
    private Boolean isMarketing; // 사용자 마켓팅 여부

    public RegisterUserRequest(String nickname, String address, String detailAddress, String imageUrl, Boolean isMarketing) {
        this.nickname = nickname;
        this.address = address;
        this.detailAddress = detailAddress;
        this.imageUrl = imageUrl;
        this.isMarketing = isMarketing;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getMarketing() {
        return isMarketing;
    }
}
