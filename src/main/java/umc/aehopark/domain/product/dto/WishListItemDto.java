package umc.aehopark.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishListItemDto { // Regular class declaration
	private Long userId;
	private Long ingredientId;
}