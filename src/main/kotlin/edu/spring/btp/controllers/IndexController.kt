package edu.spring.btp.controllers;

import edu.spring.btp.entities.Complaint
import edu.spring.btp.entities.Domain
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.ProviderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Provider
import java.security.Security

@Controller
class IndexController {

    @Autowired
    lateinit var domainRepository: DomainRepository

    @Autowired
    lateinit var complaintRepository: ComplaintRepository

    @Autowired
    lateinit var providerRepository: ProviderRepository

    @RequestMapping("/", "/index")
    fun indexFunction(model: ModelMap): String {
        val domains = domainRepository.findByParentName("Root")
        val count = domains.count()
        model["count"] = count
        model["CurrentDomain"] = "Root"
        model["domains"] = domains
        return "index"
    }

    @GetMapping("/domain/{name}")
    fun domainFunction(model: ModelMap, @PathVariable name: String): String {
        val domains = domainRepository.findByParentName(name)
        val count = domains.count()
        model["count"] = count
        model["CurrentDomain"] = name
        model["domains"] = domains
        val domainP = domainRepository.findByName(name)
        model["domainParent"] = domainP.parent?.name
        return "index"
    }

    @GetMapping("/complaints/{domain}")
    fun complaintsFunction(model: ModelMap, @PathVariable domain: String): String {
        val domaine = domainRepository.findByName(domain)
        val complaints = domaine.complaints
        model["CurrentDomain"] = domain
        model["complaints"] = complaints
        return "complaints"
    }

    @GetMapping("/complaints/{domain}/sub")
    fun SubcomplaintsFunction(model: ModelMap, @PathVariable domain: String): String {
        val domaine = domainRepository.findByParentName(domain)
        val complaints: MutableList<Complaint> = domainRepository.findByName(domain).complaints
        for (dom: Domain in domaine) {
            complaints += dom.complaints
        }
        model["CurrentDomain"] = domain
        model["complaints"] = complaints
        return "complaints"
    }

    @GetMapping("/complaints/{domain}/new")
    fun NewComplaintsFunction(model: ModelMap, @PathVariable domain: String): String {
        model["domain"]=domain
        var thisDomain = domainRepository.findByName(domain)
        model["username"]="Anonymous"
        var providers = mutableListOf<edu.spring.btp.entities.Provider>()
        for(aProvider in providerRepository.findAll()){
            if(aProvider.domains.contains(thisDomain)){
                providers.add(aProvider)
            }
        }
        model["providers"]=providers
        return "forms/complaint"
    }

    @PostMapping("/complaints/{domain}/new")
    fun AddComplaintsFunction(model: ModelMap, @PathVariable domain: String): String {
    return "forms/complaint"
    }
}