package kr.co.team.res.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_employee")
@DynamicUpdate
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name ="employee_sex")
    private String employeeSex;

    @Column(name ="employee_address")
    private String employeeAddress;

    @Column(name ="employee_pay")
    private String employeePay;

    @Column(name ="employee_phone")
    private String employeePhone;

    @Column(name ="employee_img")
    private String employeeImg;

    @Column(name ="hire_date")
    private Date hireDate;

    @Column(name ="fire_date")
    private Date fireDate;

    @Column(name ="fire_yn")
    private String fireYn;

    @Column(name ="pay_date")
    private Date payDate;

    @Column(name ="employee_etc")
    private String employeeEtc;

    @Column(name ="contract_img")
    private String contractImg;

    @Column(name ="reg_date")
    private Date regDate;

    @Column(name ="del_date")
    private Date delDate;

    @Column(name ="del_yn")
    private String delYn;
}
