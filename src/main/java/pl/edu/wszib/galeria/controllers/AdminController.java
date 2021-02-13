package pl.edu.wszib.galeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.model.User;
import pl.edu.wszib.galeria.services.IArtPieceService;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    IArtPieceService artPieceService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        ArtPiece artPiece = this.artPieceService.getArtPieceById(id);
        model.addAttribute("artPiece", artPiece);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute ArtPiece artPiece) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        this.artPieceService.updateArtPiece(artPiece);

        return "redirect:/main";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newForm(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        ArtPiece artPiece = new ArtPiece();
        model.addAttribute("artPiece", artPiece);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newArtPiece(@ModelAttribute ArtPiece artPiece) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        this.artPieceService.addArtPiece(artPiece);

        return "redirect:/main";
    }

    @RequestMapping(value = "/delete_artPiece/{id}", method = RequestMethod.GET)
    public String handleDeleteArtPiece(@PathVariable int id) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        this.artPieceService.deleteArtPiece(id);
        return "redirect:/main";
    }
}
