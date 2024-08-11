package umc.aehopark.domain.product.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasketDeleteRequest {
    private Long basketId; // 장바구니 ID

    public Long getProductId() {
        return basketId;
    }
}
