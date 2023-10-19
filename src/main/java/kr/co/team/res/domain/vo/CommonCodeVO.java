package kr.co.team.res.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonCodeVO {
    private Long id;
    private Long mberPid;
    private String codeKey;
    private String codeValue;
    private String codeTable;
    private LocalDateTime regDate;
    private String delYn;
    private LocalDateTime delDate;
}
