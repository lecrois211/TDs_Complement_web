package edu.spring.btp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class IndexController {


    @RequestMapping("/","/index")
    fun indexFunction():String{
        return "index"
    }

    

}