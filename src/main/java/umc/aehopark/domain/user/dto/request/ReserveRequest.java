package umc.aehopark.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReserveRequest {
    @NotNull
    private String name; // 사전 예약자 이름
    @NotNull
    private String email; // 사전 예약자 이메일
    @NotNull
    private String phone; // 사전 예약자 전화번호
}
