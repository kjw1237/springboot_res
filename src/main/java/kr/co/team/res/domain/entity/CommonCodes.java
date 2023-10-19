package kr.co.team.res.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_common_code")
public class CommonCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_pid")
    private Long id;

    @Column(name = "mber_pid")
    private Long mberPid;

    @Column(name = "code_key")
    private String codeKey;

    @Column(name = "code_value")
    private String codeValue;

    @Column(name = "code_table")
    private String codeTable;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "del_yn")
    private String delYn;

    @Column(name = "del_date")
    private LocalDateTime delDate;
}
