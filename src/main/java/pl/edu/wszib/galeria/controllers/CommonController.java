package pl.edu.wszib.galeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.services.IArtPieceService;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    IArtPieceService artPieceService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        model.addAttribute("artPieces", this.artPieceService.getAllArtPieces());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "contact";
    }

    @RequestMapping(value = "/sort_name", method = RequestMethod.GET)
    public String sortName(){
        this.artPieceService.setSortMethod("Name");
        return "redirect:/main";
    }

    @RequestMapping(value = "/sort_creator", method = RequestMethod.GET)
    public String sortCreator(){
        this.artPieceService.setSortMethod("Creator");
        return "redirect:/main";
    }

    @RequestMapping(value = "/sort_category", method = RequestMethod.GET)
    public String sortCategory(){
        this.artPieceService.setSortMethod("Category");
        return "redirect:/main";
    }

    @RequestMapping(value = "/sort_style", method = RequestMethod.GET)
    public String sortStyle(){
        this.artPieceService.setSortMethod("Style");
        return "redirect:/main";
    }

    @RequestMapping(value = "/sort_price", method = RequestMethod.GET)
    public String sortPrice(){
        this.artPieceService.setSortMethod("Price");
        return "redirect:/main";
    }
}
