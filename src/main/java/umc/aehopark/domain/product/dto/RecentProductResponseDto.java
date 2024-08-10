package umc.aehopark.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.aehopark.domain.product.entity.Ingredient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecentProductResponseDto {
	private String ingredientId;
	private String category;
	private String ingredient;
	private String imageUrl;
	private int wishlistStatus;

	public RecentProductResponseDto(Ingredient ingredient) {
		this.category = ingredient.getCategory().getName();
		this.ingredient = ingredient.getName();
		this.imageUrl =
			ingredient.getIngredientImages() == null || ingredient.getIngredientImages().isEmpty() ? null :
				ingredient.getIngredientImages()
					.get(0)
					.getImageUrl();
		this.wishlistStatus = ingredient.getWishLists() == null ? 0 :
			ingredient.getWishLists().size();

	}

}
