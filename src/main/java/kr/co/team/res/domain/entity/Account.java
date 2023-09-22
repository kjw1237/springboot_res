package kr.co.team.res.domain.entity;

import kr.co.team.res.domain.enums.UserRollType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.modelmapper.internal.asm.tree.LabelNode;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.management.LockInfo;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_MEMBER")
@DynamicUpdate
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mber_pid")
    private Long id;

    @Column(name = "store_pid")
    private Long storePid;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "nickname")
    private String nickName;

    @Column(name="name")
    private String name;

    @Column(name = "birthdate")
    private LocalDateTime birthDate;

    @Column(name = "join_platform")
    private String joinPlaform;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "del_yn")
    private String delYn;

    @Column(name = "del_date")
    private LocalDateTime delDate;
//    private LocalDateTime delDate;

    @Builder
    public Account(Long id , Long storePid , String loginId , String pwd , String nickName , LocalDateTime birthDate ,
                   String joinPlaform , LocalDateTime joinDate , String delYn , LocalDateTime delDate){
        this.id = id;
        this.storePid = storePid;
        this.loginId = loginId;
        this.pwd = pwd;
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.joinPlaform = joinPlaform;
        this.joinDate = joinDate;
        this.delYn = delYn;
        this.delDate = delDate;
//        this.delDate = delDate;
    }
}
