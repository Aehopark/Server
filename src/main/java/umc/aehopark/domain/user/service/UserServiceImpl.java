package umc.aehopark.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.aehopark.domain.user.dto.request.AppleLoginRequest;
import umc.aehopark.domain.user.dto.request.KakaoLoginRequest;
import umc.aehopark.domain.user.dto.request.RegisterUserRequest;
import umc.aehopark.domain.user.dto.request.ReserveRequest;
import umc.aehopark.domain.user.dto.request.UserImageAlterRequest;
import umc.aehopark.domain.user.dto.request.UserNameAlterRequest;
import umc.aehopark.domain.user.dto.request.isMarketingRequest;
import umc.aehopark.domain.user.dto.response.AppleLoginResponse;
import umc.aehopark.domain.user.dto.response.InfoUserResponse;
import umc.aehopark.domain.user.dto.response.KakaoLoginResponse;
import umc.aehopark.domain.user.dto.response.RegisterUserResponse;
import umc.aehopark.domain.user.entity.Reservation;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.domain.user.repository.ReservationRepository;
import umc.aehopark.domain.user.repository.UserRepository;
import umc.aehopark.global.exception.ConflictException;
import umc.aehopark.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
//    @Transactional(readOnly = true)
    public void reserve(ReserveRequest reserveRequest) {
        // 요청 유효성 검사
        if (reserveRequest.getEmail() == null || reserveRequest.getPhone() == null) {
            throw new IllegalArgumentException("잘못된 요청 오류");
        }

        // 동일한 이메일 또는 전화번호를 가진 예약자가 있는지 췤크
        boolean emailExists = reservationRepository.existsByEmail(reserveRequest.getEmail());
        boolean phoneExists = reservationRepository.existsByPhone(reserveRequest.getPhone());

        if (emailExists || phoneExists) {
            throw new ConflictException("이미 가입된 이메일/전화번호가 존재합니다.");
        }

        // 새로운 예약 생성 및 저장
        Reservation reservation = Reservation.builder()
                .name(reserveRequest.getName())
                .email(reserveRequest.getEmail())
                .phone(reserveRequest.getPhone())
                .build();

        reservationRepository.save(reservation);
    }

    @Override
    public Boolean checkNick(String nickname) {
        // 닉네임 유효성 검사
        if (nickname == null) {
            throw new IllegalArgumentException("닉네임이 null입니다.");
        }

        // 닉네임이 영어와 숫자로만 구성되어 있는지 확인
        if (!nickname.matches("^[a-zA-Z0-9]+$")) {
            throw new NotFoundException("유효하지 않은 닉네임입니다.");
        }

        // 닉네임 중복 확인
        boolean exists = userRepository.existsByNickname(nickname);
        if (exists) {
            throw new ConflictException("이미 사용중인 닉네임입니다.");
        }
        return true;
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerRequest) {
        return null;
    }

    @Override
    public KakaoLoginResponse kakaoLogin(KakaoLoginRequest kakaoLoginRequest) {
        return null;
    }

    @Override
    public AppleLoginResponse appleLogin(AppleLoginRequest appleLoginRequest) {
        return null;
    }

    @Override
    public void delete(Long userId) {
        // userId 유효성 검사
        if (userId == null) {
            throw new IllegalArgumentException("User ID가 비어있습니다.");
        }

        // 사용자 존재 여부 확인
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new NotFoundException("User Id값을 찾지 못하였습니다. ID: " + userId);
        }

        // 사용자 삭제
        userRepository.deleteById(userId);
        // Redis에 저장된 사용자 정보 삭제
    }

    @Override
    public InfoUserResponse infoUser(Long userId) {
        // userId 유효성 검사
        if (userId == null) {
            throw new IllegalArgumentException("User ID가 비어있습니다.");
        }

        // 사용자 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Id값을 찾지 못하였습니다. ID: " + userId));

        // 사용자 정보 반환
        return InfoUserResponse.builder()
                .userId(String.valueOf(user.getId()))
                .type(user.getType().name())
                .nickname(user.getNickname())
                .address(user.getAddress())
                .phone(Integer.valueOf(user.getPhone()))
                .isMarketing(user.getIsMarketing())
                .provider(user.getProvider().name())
                .build();
    }

    @Override
    public void alterName(Long userId, UserNameAlterRequest request) {
        // userId와 nickname 유효성 검사
        if (userId == null) {
            throw new IllegalArgumentException("User ID가 비어있습니다.");
        }
        if (request == null || request.getNickname() == null) {
            throw new IllegalArgumentException("닉네임이 비어있습니다.");
        }

        // 사용자의 닉네임 업데이트
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Id값을 찾지 못하였습니다. ID: " + userId));
        user.setNickname(request.getNickname());
        userRepository.save(user);
    }

    @Override
    public void alterUserImage(Long userId, UserImageAlterRequest imageUrl) {
        // userId와 imageUrl 유효성 검사
        if (userId == null) {
            throw new IllegalArgumentException("User ID가 비어있습니다.");
        }
        if (imageUrl == null || imageUrl.getUserImage() == null) {
            throw new IllegalArgumentException("이미지가 비어있습니다.");
        }

        // 사용자 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Id값을 찾지 못하였습니다. ID:" + userId));

        // 사용자의 프로필 이미지 업데이트
        user.setImageUrl(imageUrl.getUserImage());
        userRepository.save(user);
    }

    @Override
    public void isMarketing(Long userId, isMarketingRequest isMarketing) {
        // userId와 isMarketing 유효성 검사
        if (userId == null) {
            throw new IllegalArgumentException("User ID가 비어있습니다.");
        }
        if (isMarketing == null) {
            throw new IllegalArgumentException("마케팅 여부가 비어있습니다.");
        }

        // 사용자 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Id값을 찾지 못하였습니다. ID:" + userId));

        // 사용자의 마케팅 여부 업데이트
        user.setIsMarketing(isMarketing.getIsMarketing());
        userRepository.save(user);
    }
}
