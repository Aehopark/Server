package umc.aehopark.domain.product.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GoodProductResponseDto {
	private List<AdDto> ad;
	private List<IngredientDto> list;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AdDto {
		private Long id;
		private String imageUrl;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class IngredientDto {
		private Long id;
		private String category;
		private String ingredient;
		private String imageUrl;
		private int wishlistStatus;
	}

}
