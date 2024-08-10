package umc.aehopark.domain.product.handler;

import umc.aehopark.global.exception.GeneralException;
import umc.aehopark.global.type.BaseErrorCode;

public class ProductHandler extends GeneralException {
	public ProductHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
