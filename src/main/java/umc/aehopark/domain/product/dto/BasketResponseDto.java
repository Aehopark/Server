package umc.aehopark.domain.product.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Data
public class BasketResponseDto {

	@Getter
	@Setter
	@AllArgsConstructor
	public static class ProductDto {
		private Long basketId;
		private Long productId;
		private String name;
		private BigInteger price;
		private String category;
		private String imageUrl;
		private int wishlistStatus;
	}
}
