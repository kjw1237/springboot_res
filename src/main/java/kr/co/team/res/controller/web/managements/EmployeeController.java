package kr.co.team.res.controller.web.managements;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    @GetMapping("/managements/employPage")
    public String employPage(){
        return "/pages/managements/store/employee";
    }
}
