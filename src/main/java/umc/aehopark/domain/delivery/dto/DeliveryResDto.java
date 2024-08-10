package umc.aehopark.domain.delivery.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryResDto {


    @Getter
    @Setter
    @Builder
    public static class searchOrderDto{
        long orderDetailId; //주문상세 ID
        long orderId; //주문 ID
        String product; //제품이름
        Integer quantity; //제품개수
        BigInteger price; //제품가격
        List<String> productPic;
        String createAt;
        String buy; //구매여부
        //배송준비 여부 추가해야함!!
    }

    @Getter
    @Setter
    @Builder
    public static class addressAddDto{
        private long placeId;
        private String address;
        private String detail_address;
        private String phone;
        private String area;
    }


//    @Getter
//    @Setter
//    @Builder
//    public static class deleteAdressDto{
//        long placeId;
//        String address;
//        String detail_address;
//        String phone;
//        String area;
//    }





//    public static class searchOrderDto2{
//        String address;
//        String datail_address;
//    }

}
