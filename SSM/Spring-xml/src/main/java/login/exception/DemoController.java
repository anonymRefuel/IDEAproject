package login.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

/**
 * @author 无名氏
 * @date 2021/9/15
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

//    http://localhost:8080/login/show?name=lisa
    @RequestMapping("/show")
    public String show(@RequestParam(value = "name",required = false) String username) throws FileNotFoundException, MyException {
        System.out.println("show running...");
//        demoService.show1();
//        demoService.show2();
//        demoService.show3();
//        demoService.show4();
        demoService.show5();
        return "index";
    }
}
