package umc.aehopark.domain.product.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListCheckResponseDto {
	private Long productId;
	private String name;
	private String sortName;
	private BigInteger price;
	private String category;
	private String imageUrl;
	private int wishlistStatus;
}
