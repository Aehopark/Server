package umc.aehopark.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.aehopark.domain.user.dto.request.AppleLoginRequest;
import umc.aehopark.domain.user.dto.request.KakaoLoginRequest;
import umc.aehopark.domain.user.dto.request.RegisterUserRequest;
import umc.aehopark.domain.user.dto.request.ReserveRequest;
import umc.aehopark.domain.user.dto.request.UserImageAlterRequest;
import umc.aehopark.domain.user.dto.request.UserNameAlterRequest;
import umc.aehopark.domain.user.dto.request.isMarketingRequest;
import umc.aehopark.domain.user.dto.response.AppleLoginResponse;
import umc.aehopark.domain.user.dto.response.KakaoLoginResponse;
import umc.aehopark.domain.user.dto.response.RegisterUserResponse;
import umc.aehopark.domain.user.dto.response.InfoUserResponse;
import umc.aehopark.domain.user.service.UserServiceImpl;
import umc.aehopark.global.common.ApiResponse;
import umc.aehopark.global.exception.ConflictException;
import umc.aehopark.global.exception.NotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserServiceImpl userService;
//    private final KakaoService kakaoService;
//    private final AppleService appleService;

    //  POST /reserve
    //  사전 예약 API
    @CrossOrigin(origins="*")
    @PostMapping("/reserve")
    public ResponseEntity<Void> reserve(@RequestBody ReserveRequest reserveRequest) {
        try {
            userService.reserve(reserveRequest);
            return ResponseEntity.ok().build(); //  200 OK
        } catch (IllegalArgumentException e) { //   400 Bad Request - 클라이언트 요청 오류
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ConflictException e) { //   409 Conflict - 이미 가입된 이메일/전화번호가 존재할 경우
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    //  GET /checkNick
    //  닉네임 중복 확인 API
    @GetMapping("/checkNick")
    public ResponseEntity<ApiResponse<Boolean>> checkNick(@RequestParam String nickname) {
        try {
            Boolean isAvailable = userService.checkNick(nickname);
            return ResponseEntity.ok(new ApiResponse<>("사용가능한 닉네임입니다.", isAvailable)); // 200 OK
        } catch (IllegalArgumentException e) { // 400 Bad Request - 클라이언트 요청 오류
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었습니다.", null));
        } catch (NotFoundException e) { // 404 Not Found - 조회되지 않을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("닉네임에는 영어와 숫자만 사용가능합니다.", null));
        } catch (ConflictException e) { // 409 Conflict - 이미 사용중인 닉네임
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("이미 사용중인 닉네임입니다.", null));
        }
    }

    //  POST /register
    //  회원가입 API
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> register(@RequestBody RegisterUserRequest registerRequest) {
        try {
            RegisterUserResponse registerUserResponse = userService.register(registerRequest);
            return ResponseEntity.ok().body(new ApiResponse<>("회원가입 성공", registerUserResponse)); // 200 OK
        } catch (IllegalArgumentException e) { //    400 Bad Request - 클라이언트 요청 오류
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
        } catch (ConflictException e) { //    409 Conflict - 이미 가입된 회원일 경우(providerId 중복)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("이미 가입된 회원입니다.", null));
        }
    }
//
//    //  POST /kakao
//    //  KAKAO 로그인 API
//    @PostMapping("/kakao")
//    public ResponseEntity<ApiResponse<KakaoLoginResponse>> loginKakao(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
//        try {
//            KakaoLoginResponse kakaoLoginResponse = userService.kakaoLogin(kakaoLoginRequest);
//            return ResponseEntity.ok().body(new ApiResponse<>("카카오 로그인 성공", kakaoLoginResponse)); // 200 OK
//        } catch (BadRequestException e) { //  400 Bad Request - 클라이언트 요청 오류
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
//        } catch (NotFoundException e) { //  404 Not Found - 가입되지 않은 회원일 경우 => 회원가입 페이지로 이동
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("가입되지 않은 회원입니다.", null));
//        }
//    }
//
//    //  POST /apple
//    //  APPLE 로그인 API
//    @PostMapping("/apple")
//    public ResponseEntity<ApiResponse<AppleLoginResponse>> loginApple(@RequestBody AppleLoginRequest appleLoginRequest) {
//        try {
//            AppleLoginResponse appleLoginResponse = userService.appleLogin(appleLoginRequest);
//            return ResponseEntity.ok().body(new ApiResponse<>("애플 로그인 성공", appleLoginResponse)); // 200 OK
//        } catch (BadRequestException e) { //  400 Bad Request - 클라이언트 요청 오류
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
//        } catch (NotFoundException e) { //  404 Not Found - 가입되지 않은 회원일 경우 => 회원가입 페이지로 이동
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("가입되지 않은 회원입니다.", null));
//        }
//    }
//
    //  DELETE /delete
    //  회원탈퇴 API
    //  Bearer Token 필요
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> delete(@RequestParam Long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.ok().body(new ApiResponse<>("계정 삭제 성공", null));
        } catch (IllegalArgumentException e) { // 400 Bad Request - ID 값이 없는 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("클라이언트 요청 오류", null));
        } catch (NotFoundException e) { //  404 Not Found - 유저를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("너 누구야?", null));
        }
    }

    //  GET /info
    //  내 회원정보 조회 API
    //  자기자신만 조회가능
    //  Bearer Token 필요
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<InfoUserResponse>> info(@RequestParam Long userId) {
        try {
            InfoUserResponse userInfoResponse = userService.infoUser(userId);
            return ResponseEntity.ok().body(new ApiResponse<>("회원정보 조회 성공", userInfoResponse)); // 200 OK
        }
//        catch (BadRequestException e) { //  400 Bad Request - 클라이언트 요청 오류
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
//        }
        catch (NotFoundException e) { //  404 Not Found - 조회되지 않을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("존재하지 않는 회원입니다.", null));
        }
    }

    //  PATCH /alterName
    //  닉네임 변경 API
    //  Bearer Token 필요
    @PostMapping("/alterName")
    public ResponseEntity<ApiResponse<Void>> alterName(@RequestBody UserNameAlterRequest request, @RequestParam Long userId) {
        try {
            userService.alterName(userId, request);
            return ResponseEntity.ok().body(new ApiResponse<>("이름 변경했따리", null)); // 200 OK
        } catch (IllegalArgumentException e) { // 400 Bad Request - 클라이언트 요청 오류
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("너 누구야?", null));
        }
    }

    //  PATCH /alterUserImage
    //  프로필 이미지 변경
    //  Bearer Token 필요
    @PostMapping("/alterUserImage")
    public ResponseEntity<ApiResponse<Void>> alterUserImage(@RequestBody UserImageAlterRequest imageUrl, @RequestParam Long userId) {
        try {
            userService.alterUserImage(userId, imageUrl);
            return ResponseEntity.ok().body(new ApiResponse<>("프로필 사진 변경 성공", null)); // 200 OK
        } catch (IllegalArgumentException e) { //  400 Bad Request - 클라이언트 요청 오류
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
        } catch (NotFoundException e) { //  404 Not Found - 조회되지 않을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("너 누구야?", null));
        }
    }

    //  POST /isMarketing
    //  마케팅 수신 동의 API
    //  Bearer Token 필요
    @PostMapping("/isMarketing")
    public ResponseEntity<ApiResponse<Void>> isMarketing(@RequestBody isMarketingRequest isMarketing, @RequestParam Long userId) {
        try {
            userService.isMarketing(userId, isMarketing);
            return ResponseEntity.ok().body(new ApiResponse<>("변경 성공", null)); // 200 OK
        } catch (IllegalArgumentException e) { //  400 Bad Request - 클라이언트 요청 오류
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었다 삐리리", null));
        } catch (NotFoundException e) { //  404 Not Found - 조회되지 않을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("너 누구야?", null));
        }
    }
}
