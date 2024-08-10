package umc.aehopark.domain.product.entity;

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
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.aehopark.domain.product.entity.enums.StoreStatus;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 판매처 식별자

    @Column(nullable = false)
    private String name; // 판매처 이름

    @Column(nullable = false)
    private String category; // 판매처 종류

    @Column(nullable = false)
    private String SellerName; // 판매자 이름

    @Column(nullable = false)
    private String phone; // 판매처 전화번호

    @Column(nullable = false)
    private String registraitonNumber; // 판매자 등록번호

    @Column(nullable = false)
    private String address; // 판매처 주소

    @Column(nullable = true)
    private String content; // 판매처 설명

    @Column(nullable = false)
    private StoreStatus status; // 판매처 상태

    @Column(nullable = false)
    private String email; // 판매자 이메일

    @Column(nullable = true)
    private String imageUri; // 판매처 이미지

    @Column(nullable = false)
    private Boolean isApproved; // 판매처 승인 여부



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자


    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>(); // 상품
}
