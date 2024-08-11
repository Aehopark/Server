package umc.aehopark.domain.product.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasketAddRequest {
    private Long productId; // 상품 ID
    private Integer quantity; // 상품 수량
}
