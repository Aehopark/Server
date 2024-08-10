package umc.aehopark.domain.delivery.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import umc.aehopark.domain.delivery.dto.DeliveryReqDto;
import umc.aehopark.domain.delivery.dto.DeliveryResDto;

import java.util.List;

public interface DeliveryService {

    Page<DeliveryResDto.searchOrderDto> searchOrder(Long userId, Pageable adjustedPageable);
    DeliveryResDto.addressAddDto addAddress(Long userId, DeliveryReqDto.addressAddDto reqDto);
    List<DeliveryResDto.addressAddDto> searchAddress(Long userId);
    Boolean deleteAddress(Long placeId);
}
