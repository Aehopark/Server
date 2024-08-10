package umc.aehopark.domain.product.dto;

import lombok.Getter;
import lombok.Setter;
import umc.aehopark.domain.product.entity.Ingredient;

@Getter
@Setter
public class IngredientSearchResponse {
	private String category;
	private String ingredient;
	private String imageUrl;
	private int wishlistStatus;

	public IngredientSearchResponse(Ingredient ingredient) {
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