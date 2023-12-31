package kr.co.team.res.domain.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
public class StoreVO {

    private Long id;
    private Long mber_pid;

    @NotNull(message = "매장명은 필수 값입니다.")
    @NotBlank(message = "매장명은 필수 값입니다.")
    private String storeName;

    @NotNull(message = "매장주소는 필수값 입니다.")
    @NotBlank(message = "매장주소는 필수값 입니다.")
    private String storeAddres;

    private String storeCategory;

    private String openTime;
    private String closeTime;
    private String storeDescription;

    private String delYn;
    private LocalDateTime delDate;
}
