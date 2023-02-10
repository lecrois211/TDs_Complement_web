package edu.spring.td2.Controllers;

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(path=["", "/index"])
    fun controllerIndex():String{
        return "index"
    }
}
