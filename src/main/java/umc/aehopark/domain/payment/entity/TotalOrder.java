package umc.aehopark.domain.payment.entity;

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
public class TotalOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 식별자

    @Column(nullable = false)
    private BigInteger totalPrice; // 주문 총 가격

    @Column(nullable = true)
    private BigInteger sailPrice; // 주문 할인 가격

    @Column(nullable = true)
    private String request; // 주문 요청사항

    @Column(nullable = false)
    private String recipient; // 주문 수령인

    @Column(nullable = false)
    private Boolean cancelStatus; // 주문 취소 여부

    @Column(nullable = false)
    private Boolean orderRefundStatus; // 주문 환불 여부
}
