package umc.aehopark.domain.user.service;

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

public interface UserService {
	void reserve(ReserveRequest reserveRequest);

	Boolean checkNick(String nickname);

	RegisterUserResponse register(RegisterUserRequest registerRequest);

	KakaoLoginResponse kakaoLogin(KakaoLoginRequest kakaoLoginRequest);

	AppleLoginResponse appleLogin(AppleLoginRequest appleLoginRequest);

	InfoUserResponse infoUser(Long userId);

	void delete(Long userId);

	void alterName(Long userId, UserNameAlterRequest nickname);

	void alterUserImage(Long userId, UserImageAlterRequest imageUrl);

	void isMarketing(Long userId, isMarketingRequest isMarketing);
}

