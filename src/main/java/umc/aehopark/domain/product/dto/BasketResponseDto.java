package umc.aehopark.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketResponseDto {

	private Long id; // Basket ID
	private Long userId; // User ID for the basket
	private Long productId; // Product ID in the basket
	private Integer quantity; // Quantity of the product in the basket

}
