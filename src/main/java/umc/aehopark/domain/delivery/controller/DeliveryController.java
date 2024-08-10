package umc.aehopark.domain.delivery.controller;

import com.google.protobuf.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import umc.aehopark.domain.delivery.dto.DeliveryReqDto;
import umc.aehopark.domain.delivery.dto.DeliveryResDto;
import umc.aehopark.domain.delivery.entity.Place;
import umc.aehopark.domain.delivery.service.DeliveryService;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.global.common.ApiResponse;
import umc.aehopark.global.common.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeliveryController {

    private final DeliveryService deliveryService;


    //배송목록 및 주문목록 조회 +페이징
    @GetMapping("/order/{userId}")
    public ApiResponse<Page<DeliveryResDto.searchOrderDto>> searchOrder(@PathVariable("userId") Long userId,
                                                                            @RequestParam(defaultValue = "1") int page,
                                                                            @PageableDefault(page = 1, size = 10) Pageable pageable,
                                                                            @RequestHeader("Authorization") String authorizationHeader){
//        String token = author

        //페이지 세팅
        Pageable adjustedPageable = PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());

        return ApiResponse.onSuccess(deliveryService.searchOrder(userId, adjustedPageable));
    }
    //배송지 추가
    @PostMapping("/myshop/{userId}/address/register")
    public ApiResponse<DeliveryResDto.addressAddDto> addAddress(@PathVariable("userId") Long userId,
                                    @RequestBody @Valid DeliveryReqDto.addressAddDto reqDto){

        return ApiResponse.onSuccess(deliveryService.addAddress(userId, reqDto));

    }

    //배송지 조회
    @GetMapping("/myshop/{userId}/address")
    public ApiResponse<List<DeliveryResDto.addressAddDto>> searchAddress(@PathVariable("userId") Long userId){

        return ApiResponse.onSuccess(deliveryService.searchAddress(userId));
    }
     //배송지 삭제
    @DeleteMapping("/myshop/{placeId}/address/delete")
    public ApiResponse deleteAddress(@PathVariable("placeId") Long placeId){

        if(deliveryService.deleteAddress(placeId)) {
            return ApiResponse.onSuccess("삭제에 성공했습니다.");
        }else{
            return ApiResponse.onFailure("삭제에 실패했습니다.", placeId);
        }
    }


}
