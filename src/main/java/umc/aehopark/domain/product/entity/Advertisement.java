package umc.aehopark.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.aehopark.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Advertisement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 광고 식별자

    @Column(nullable = false)
    private String title; // 광고 제목

    @Column(nullable = true)
    private String content; // 광고 내용

    @Column(nullable = true)
    private String imageUrl; // 광고 이미지 URL

    @Column(nullable = true)
    private String productId; // 광고 상품 식별자
}
