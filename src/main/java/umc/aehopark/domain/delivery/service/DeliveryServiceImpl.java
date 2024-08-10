package umc.aehopark.domain.delivery.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.aehopark.domain.delivery.converter.DeliveryConverter;
import umc.aehopark.domain.delivery.dto.DeliveryReqDto;
import umc.aehopark.domain.delivery.dto.DeliveryResDto;
import umc.aehopark.domain.delivery.entity.Place;
import umc.aehopark.domain.delivery.repository.OrderDetailRepository;
import umc.aehopark.domain.delivery.repository.PlaceRepository;
import umc.aehopark.domain.delivery.repository.UserRepository;
import umc.aehopark.domain.payment.entity.OrderDetail;
import umc.aehopark.domain.user.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryServiceImpl implements DeliveryService {


    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public Page<DeliveryResDto.searchOrderDto> searchOrder(Long userId, Pageable adjustedPageable){
        Page<OrderDetail> OrderDetails = orderDetailRepository.findAllByUserIdOrderByCreatedAtDesc(userId, adjustedPageable);

        List<DeliveryResDto.searchOrderDto> orderDetailDtos = OrderDetails.stream()
                        .map(DeliveryConverter::toSearchDto)
                        .toList();

        return new PageImpl<>(orderDetailDtos, adjustedPageable, orderDetailDtos.size());
    }

    public DeliveryResDto.addressAddDto addAddress(Long userId, DeliveryReqDto.addressAddDto reqDto){
        User user = userRepository.findAllById(userId);

        Long id = placeRepository.save(DeliveryConverter.toPlaceEntity(reqDto,user)).getId();

        Place place = placeRepository.findAllById(id);

        // 이후에 area 컬럼이 추가될 경우 place에서 area를 넣으면 됨
        return DeliveryConverter.addressAddDto(user, place, reqDto.getArea());


    }

    public List<DeliveryResDto.addressAddDto> searchAddress(Long userId){
        //유저를 찾아오는 메소드
        User user = userRepository.findAllById(userId);

        List<Place> places = placeRepository.findAllByUserId(user.getId());

        List<DeliveryResDto.addressAddDto> dtos =  places.stream()
                    .map(place -> {
                        return DeliveryConverter.addressAddDto(user, place, "집");
                        })
                    .toList();

        return dtos;
    }

    public Boolean deleteAddress(Long placeId){
        placeRepository.deleteById(placeId);

        Place place = placeRepository.findAllById(placeId);

        if(place !=null){
            return false;
        }else{
            return true;
        }
    }

}


