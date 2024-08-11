package umc.aehopark.domain.product.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailSearchResponseDto {
	private List<PriceResponseDto> prices;
	private List<ProductResponseDto> products;
	private List<PlatFormResponseDto> platforms;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ProductResponseDto {
		private long productId;
		private String brand;
		private String name;
		private BigInteger price;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PriceResponseDto {
		private BigInteger price;
		private BigInteger monthTopPrice;
		private List<PriceDetailResponseDto> totals;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PriceDetailResponseDto {
		private BigInteger price;
		private LocalDateTime date;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PlatFormResponseDto {
		private long id;
		private String name;
		private BigInteger price;
	}

}

