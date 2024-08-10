package umc.aehopark.domain.delivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DeliveryReqDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class addressAddDto {
        private String address;
        private String detail_address;
        private String area;
    }
}