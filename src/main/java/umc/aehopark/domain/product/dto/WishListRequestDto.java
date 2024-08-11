package umc.aehopark.domain.product.dto;

import lombok.Data;

@Data
public class WishListRequestDto {
	private long id;
	private long ingredientId; // 식재료 ID
}