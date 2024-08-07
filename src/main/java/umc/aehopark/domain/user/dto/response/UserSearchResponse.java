package umc.aehopark.domain.user.dto.response;

public class UserSearchResponse {
    private String userId;
    private String type; // UserType
    private String nickname;
    private String address;
    private Integer phone;
    private String email;
    private Boolean isMarketing;
    private String provider; // OAuthProvider
}
