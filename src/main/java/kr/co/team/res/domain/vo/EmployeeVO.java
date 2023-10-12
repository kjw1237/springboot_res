package kr.co.team.res.domain.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.Date;

@Getter
@Setter
public class EmployeeVO {

    private Long id;
    private Long employeePid;
    private String employeeSex;
    private String employeeAddress;
    private String employeePay;
    private String employeePhone;
    private String employeeImg;
    private Date hireDate;
    private Date fireDate;
    private String fireYn;
    private Date payDate;
    private String employeeEtc;
    private String contractImg;
    private Date regDate;
    private Date delDate;
    private String delYn;
}
