package pl.edu.wszib.galeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.galeria.model.User;
import pl.edu.wszib.galeria.services.IBasketService;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("artPieces", this.sessionObject.getBasket());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("sum", this.basketService.calculateTotal());
        return "basket";
    }

    @RequestMapping(value = "/addToBasket/{id}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable int id) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.basketService.addArtPieceByIDToBasket(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteFromBasket/{id}", method = RequestMethod.GET)
    public String removeFromBasket(@PathVariable int id) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.basketService.removeArtPieceByIdFromBasket(id);
        return "redirect:/basket";
    }
}
