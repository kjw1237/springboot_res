package kr.co.team.res.controller.web.managements;

import kr.co.team.res.common.utill.MessageHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    MessageHandler messageHandler;
    @GetMapping("/managements/employ")
    public String employPage(HttpServletResponse response){

        messageHandler.alert(response , "test");

        return "/pages/managements/store/employee";
    }
}
