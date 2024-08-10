package umc.aehopark.domain.payment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;
import umc.aehopark.domain.delivery.entity.Place;
import umc.aehopark.domain.user.entity.User;
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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place; // 배송지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment; // 배송지


    @OneToMany(mappedBy = "totalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>(); // 주문상세
}
