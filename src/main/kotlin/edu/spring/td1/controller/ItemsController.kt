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

    @PostMapping("/suppress")
    fun suppItem(@ModelAttribute("nom") nom:String, @SessionAttribute("items") items:HashSet<Item>, attrs: RedirectAttributes):RedirectView {
        if (items.remove(Item(nom))) {
            attrs.addFlashAttribute(
                "msg",
                edu.spring.td1.Service.UIMessage.Companion.message(
                    "Suppression item",
                    "$nom a été supprimé avec succès :)"
                )
            )
            return RedirectView("/")
        } else {
            attrs.addFlashAttribute(
                "msg",
                edu.spring.td1.Service.UIMessage.Companion.message(
                    "Echec suppression",
                    "$nom n'est pas dans la liste d'objets"
                )
            )
            return RedirectView("/")
        }
    }

    @RequestMapping("/supp")
    fun suppAction():String{
        return "SuppForm"
    }

    @RequestMapping("/modif")
    fun modifAction():String{
        return "ModifForm"
    }

    @PostMapping("/modification")
    fun modifItem(@ModelAttribute("ancienNom") ancienNom:String, @ModelAttribute("nouveauNom") nouveauNom:String, @SessionAttribute("items") items:HashSet<Item>, attrs: RedirectAttributes):RedirectView{
        if(items.remove(Item(ancienNom)) && items.add(Item(nouveauNom))) {
                attrs.addFlashAttribute(
                    "msg",
                    edu.spring.td1.Service.UIMessage.Companion.message(
                        "Succès Modification :)",
                        "modification apportée avec succès ($ancienNom est devenu $nouveauNom)"
                    )
                )
                return RedirectView("/")
            }
        else {
            attrs.addFlashAttribute(
                "msg",
                edu.spring.td1.Service.UIMessage.Companion.message(
                    "Echec modification",
                    "$ancienNom n'est pas dans la liste d'objets / $nouveauNom existe déja"
                )
            )
            return RedirectView("/")
        }
    }


}