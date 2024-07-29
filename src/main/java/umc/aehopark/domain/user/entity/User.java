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
    private Long id;

    @Column(nullable = false)
    private UserType type;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String detailAddress;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean isMarket;

    @Column(nullable = false)
    private OAuthProvider provider;

    @Column(nullable = false, unique = true)
    private String providerId;
}
