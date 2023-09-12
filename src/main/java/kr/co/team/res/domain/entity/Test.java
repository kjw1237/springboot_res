package kr.co.team.res.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "pid")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TEST_TABLE")
@DynamicUpdate
public class Test {


    @Id
    @Column(name ="pid")
    private int pid;

    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private  String pw;

}
