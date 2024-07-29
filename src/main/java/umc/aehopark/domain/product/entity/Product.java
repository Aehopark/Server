package umc.aehopark.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigInteger;
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
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 식별자

    @Column(nullable = false)
    private String name; // 상품 이름

    @Column(nullable = true)
    private String content; // 상품 설명

    @Column(nullable = false)
    private BigInteger price; // 상품 가격

    @Column(nullable = false)
    private Integer stock; // 상품 재고

    @Column(nullable = false)
    private Integer deliveryFee; // 배송비
}
