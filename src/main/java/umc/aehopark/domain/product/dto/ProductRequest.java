package umc.aehopark.domain.product.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	private String category;
	private String ingredientName;
	private String name;
	private List<String> imageUrl;
	private BigInteger price;
	private String contents;
	
}