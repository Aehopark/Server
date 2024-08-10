package umc.aehopark.domain.delivery.converter;

import umc.aehopark.domain.delivery.dto.DeliveryReqDto;
import umc.aehopark.domain.delivery.dto.DeliveryResDto;
import umc.aehopark.domain.delivery.entity.Place;
import umc.aehopark.domain.payment.entity.OrderDetail;
import umc.aehopark.domain.payment.entity.TotalOrder;
import umc.aehopark.domain.product.entity.ProductImage;
import umc.aehopark.domain.user.entity.User;

import static umc.aehopark.domain.delivery.function.DateCalc.formatDate2;

public class DeliveryConverter {

    public static DeliveryResDto.searchOrderDto toSearchDto(OrderDetail orderDetail){
        return DeliveryResDto.searchOrderDto.builder()
                .orderDetailId(orderDetail.getId())
                .orderId(orderDetail.getTotalOrder().getId())
                .product(orderDetail.getProduct().getName())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .productPic(orderDetail.getProduct().getProductImages().stream()
                        .map(ProductImage::getUrl)
                        .toList())
                .createAt(formatDate2(orderDetail.getCreatedAt()))
                .buy("구매")
                .build();


    }
    public static Place toPlaceEntity(DeliveryReqDto.addressAddDto reqDto, User user){

        return Place.builder()
                .address(reqDto.getAddress())
                .detailAddress(reqDto.getDetail_address())
                .request("")
                .build();
    }

    public static DeliveryResDto.addressAddDto addressAddDto(User user,  Place place, String area){

        return DeliveryResDto.addressAddDto.builder()
                .placeId(place.getId())
                .address(place.getAddress())
                .detail_address(place.getDetailAddress())
                .phone(user.getPhone())
                .area(area)
                .build();
    }

//
//    public static DeliveryResDto.addressAddDto addressAddDto(User user,  Place place, String area){
//
//        return DeliveryResDto.addressAddDto.builder()
//                .placeId(place.getId())
//                .address(place.getAddress())
//                .detail_address(place.getDetailAddress())
//                .phone(user.getPhone())
//                .area(area)
//                .build();
//    }




}
