package kr.co.team.res.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "TEST_TABLE")
public class Test {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private  String pw;

}
