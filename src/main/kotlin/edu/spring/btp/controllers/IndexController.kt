package edu.spring.btp.controllers;

import edu.spring.btp.entities.Domain
import edu.spring.btp.repositories.DomainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class IndexController {

    @Autowired
    lateinit var domainRepository: DomainRepository

    @RequestMapping("/","/index")
    fun indexFunction(model: ModelMap):String{
        val domains = domainRepository.findByParentName("Root")
        model["CurrentDomain"] = "Root"
        model["domains"] = domains
        return "index"
    }

    @GetMapping("/domain/{name}")
    fun domainFunction(model: ModelMap, @PathVariable name: String):String{
        val domains = domainRepository.findByName(name)
        model["domains"] = domains
        return "index"
    }
}