package umc.aehopark.domain.user.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.aehopark.domain.delivery.entity.Place;
import umc.aehopark.domain.payment.entity.OrderDetail;
import umc.aehopark.domain.payment.entity.TotalOrder;
import umc.aehopark.domain.product.entity.Basket;
import umc.aehopark.domain.product.entity.RecentlyViewedIngredient;
import umc.aehopark.domain.product.entity.Store;
import umc.aehopark.domain.product.entity.WishList;
import umc.aehopark.domain.user.entity.enums.OAuthProvider;
import umc.aehopark.domain.user.entity.enums.UserType;
import umc.aehopark.global.entity.BaseEntity;

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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Place> places = new ArrayList<>(); // 배송지

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WishList> wishLists = new ArrayList<>(); // 찜한 재료

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RecentlyViewedIngredient> recentlyViewedIngredients = new ArrayList<>(); // 최근 본 상품

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>(); // 상세주문

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TotalOrder> totalOrders = new ArrayList<>();  // 주문

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Store> stores = new ArrayList<>(); // 판매처

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Basket> baskets = new ArrayList<>(); // 장바구니

	public void setNickname(String nickname) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new IllegalArgumentException("닉네임이 비어있습니다.");
		}
		this.nickname = nickname;
	}

	public void setImageUrl(String imageUrl) {
		if (imageUrl == null || imageUrl.trim().isEmpty()) {
			throw new IllegalArgumentException("이미지 URL이 존재하지 않습니다.");
		}
		this.imageUrl = imageUrl;
	}

	public void setIsMarketing(Boolean isMarketing) {
		if (isMarketing == null) {
			throw new IllegalArgumentException("마케팅 여부가 비어있습니다.");
		}
		this.isMarketing = isMarketing;
	}

}
