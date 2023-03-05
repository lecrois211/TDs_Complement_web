package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.DogRepository
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@Controller
class MainController {
    @Autowired
    lateinit var mrep: MasterRepository

    @Autowired
    lateinit var drep: DogRepository

    @RequestMapping("/", "")
    fun index(modelMap: ModelMap):String{
        modelMap["AdopteChien"]=drep.findByMasterIsNull()
        modelMap["proprietaires"]=mrep.findAll()
        return "index"
    }

    @PostMapping("/master/add")
    fun AjouteMaitre(
            @ModelAttribute master: Master,
            @RequestParam("addButton") action: String,
    ): RedirectView {
        mrep.save(master)

        return RedirectView("/")
    }

    @PostMapping("/master/{id}/dog")
    fun manageDogsByMasterId(
            @PathVariable id:Int,
            @ModelAttribute dog: Dog,
            @RequestParam("dog-action") action: String
    ): RedirectView {
        val master=mrep.findById(id).orElse(null)
        mrep.save(master)
        if(master!=null){
            when (action){
                "add" -> {

                        val aDog: Dog = Dog(dog.name)

                        aDog.master = master

                        drep.save(aDog)

                }
                "give-up" -> {
                        val remDog = drep.findByNameAndMasterId(dog.name, id)
                        if(remDog != null){
                            master.giveUpDog(remDog)
                            drep.save(remDog)

                    }
                }
            }
        }
        return RedirectView("/")
    }
    @GetMapping("master/{id}/delete")
    fun deleteMaster(@PathVariable id:Int): RedirectView {
        val master = mrep.findById(id).orElse(null)
        if (master != null) {
            for(dog in drep.findAll()){
                if(dog.master?.id == id) dog.master = null
            }
            //master.preRemove()
            mrep.delete(master)
        }
        return RedirectView("/")
    }
    @PostMapping("/dog/{id}/action")
    fun manageDogById(
            @PathVariable id:Int,
            @RequestParam("dog-action") action: String,
            @RequestParam("choixMaitre") idMaitre: Int
    ): RedirectView {
        val dog=drep.findById(id).orElse(null)
        val master=mrep.findById(idMaitre).orElse(null)
        mrep.save(master)
        if(master!=null){
            when (action){
                "remove" -> {
                    drep.delete(dog)
                }
                "adopt" -> {
                    dog.master = master
                    master.addDog(dog)
                    drep.save(dog)
                }
            }
        }
        return RedirectView("/")
    }
}