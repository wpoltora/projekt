package pl.edu.wszib.galeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.galeria.dao.IArtPieceDAO;
import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.services.IBasketService;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BasketServiceImpl implements IBasketService {

    @Autowired
    IArtPieceDAO artPieceDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public double calculateTotal() {
        double sum = 0;
        for (ArtPiece artPiece : this.sessionObject.getBasket()) {
            sum = sum + artPiece.getPrice();
        }
        return sum;
    }

    @Override
    public void addArtPieceByIDToBasket(int id) {
        ArtPiece artPiece = this.artPieceDAO.getArtPieceById(id);
        for(ArtPiece artPieceFromBasket : this.sessionObject.getBasket()){
            if(artPieceFromBasket.getId() == id){
                return;
            }
        }
        this.sessionObject.getBasket().add(artPiece);
    }

    @Override
    public void removeArtPieceByIdFromBasket(int id) {
        this.sessionObject.getBasket().removeIf(artPiece -> artPiece.getId() == id);
    }


}
