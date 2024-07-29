package umc.aehopark.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.aehopark.domain.user.entity.enums.OAuthProvider;
import umc.aehopark.domain.user.entity.enums.UserType;
import umc.aehopark.global.entity.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 식별자

    @Column(nullable = false)
    private UserType type; // 사용자 타입

    @Column(nullable = false, unique = true)
    private String nickname; // 사용자 닉네임

    @Column(nullable = true)
    private String address; // 사용자 주소

    @Column(nullable = true)
    private String detailAddress; // 사용자 상세주소

    @Column(nullable = true)
    private String phone; // 사용자 전화번호

    @Column(nullable = true)
    private String imageUrl; // 사용자 프로필 이미지 URL

    @Column(nullable = false)
    private Boolean isMarketing; // 사용자 마켓팅 여부

    @Column(nullable = false)
    private OAuthProvider provider; // 사용자 OAuth 제공자

    @Column(nullable = false, unique = true)
    private String providerId; // 사용자 OAuth 제공자 식별자
}
