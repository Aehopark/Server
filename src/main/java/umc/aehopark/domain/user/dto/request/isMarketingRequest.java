package umc.aehopark.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class isMarketingRequest {
    private boolean isMarketing; // 마케팅 수신 동의 여부

    public isMarketingRequest(boolean isMarketing) {
        this.isMarketing = isMarketing;
    }

    public boolean getIsMarketing() {
        return isMarketing;
    }
}
