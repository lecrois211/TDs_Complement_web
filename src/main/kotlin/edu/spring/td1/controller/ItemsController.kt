package edu.spring.td1.controller

import edu.spring.td1.models.Item
import edu.spring.td1.Service.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.*
import org.springframework.web.servlet.mvc.support.*



@Controller
@SessionAttributes("items")
class ItemsController {

    @get:ModelAttribute("items")
    val item: Set<Item>
        get(){
            var items = HashSet<Item>()
            items.add(Item("Foo"))
            items.add(Item("aaa"))
            return items
        }

    @RequestMapping("/")
    fun indexAction(@RequestAttribute("msg") msg:UIMessage.Message?):String{
        return "index"
    }

    @RequestMapping("/new")
    fun newAction():String{
        return "newForm"
    }

    @PostMapping("/addNew")
    fun addNewAction(@ModelAttribute("nom") nom:String, @SessionAttribute("items") items:HashSet<Item>, attrs:RedirectAttributes):RedirectView{
       if (items.add(Item(nom))){
        attrs.addFlashAttribute("msg", edu.spring.td1.Service.UIMessage.Companion.message("Ajout item", "$nom a été ajouté avec succès :)"))
        return RedirectView("/")
           }
        else{
           attrs.addFlashAttribute("msg", edu.spring.td1.Service.UIMessage.Companion.message("Echec ajout", "$nom déjà existant"))
           return RedirectView("/")
       }
    }

}