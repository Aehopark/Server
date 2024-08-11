package umc.aehopark.domain.product.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.aehopark.global.entity.BaseEntity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "recommend_list")
public class RecommendList extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recommendlist_id")
	private Long recommendlistId; // 금주의 추천 목록 식별자

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient; // 식재료

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt; // 생성일자

	@Column(name = "updated_at")
	private LocalDateTime updatedAt; // 수정일자

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	// 생성자, 게터, 세터는 Lombok이 처리
}
