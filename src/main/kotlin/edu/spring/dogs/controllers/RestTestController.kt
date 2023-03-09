package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/rest")
class RestTestController {

    @Autowired
    lateinit var masterRepository : MasterRepository

    @GetMapping("/masters")
    fun getMasters() = masterRepository.findAll()

    @PostMapping("/masters", consumes = ["applications.json"])
    fun addMaster(@ModelAttribute master: Master) :ResponseEntity<Master>{
        if(masterRepository.save(master)!= null){
            return ResponseEntity.ok(master)
        }
        return ResponseEntity.badRequest().build()
    }
}