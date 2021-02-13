package pl.edu.wszib.galeria.services.impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.galeria.dao.IArtPieceDAO;
import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.services.IArtPieceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtPieceServiceImpl implements IArtPieceService {
    private String sortMethod="Name";

    @Autowired
    IArtPieceDAO artPieceDAO;

    @Override
    public ArtPiece getArtPieceById(int id) {
        return this.artPieceDAO.getArtPieceById(id);
    }

    @Override
    public void updateArtPiece(ArtPiece artPiece) {
        ArtPiece DBArtPiece = this.artPieceDAO.getArtPieceById(artPiece.getId());
        DBArtPiece.setId(artPiece.getId());
        DBArtPiece.setName(artPiece.getName());
        DBArtPiece.setCreator(artPiece.getCreator());
        DBArtPiece.setCategory(artPiece.getCategory());
        DBArtPiece.setStyle(artPiece.getStyle());
        DBArtPiece.setPrice(artPiece.getPrice());
        this.artPieceDAO.updateArtPiece(DBArtPiece);
    }

    @Override
    public void deleteArtPiece(int id){
        this.artPieceDAO.deleteArtPiece(id);
    }

    @Override
    public void addArtPiece(ArtPiece artPiece){
        this.artPieceDAO.addArtPiece(artPiece);
    }

    @Override
    public List<ArtPiece> getAllArtPieces() {
        List<ArtPiece> artPieces = this.artPieceDAO.getAllArtPieces();
        sort(artPieces, sortMethod);
        return artPieces;
    }

    public String getSortMethod() {
        return sortMethod;
    }

    @Override
    public void setSortMethod(String sortMethod) {
        this.sortMethod = sortMethod;
    }

    private void sort(List<ArtPiece> artPieces, String sortMethod) {
        switch (sortMethod) {
            case "Name":
                artPieces.sort(Comparator.comparing(ArtPiece::getName, String.CASE_INSENSITIVE_ORDER));
                return;
            case "Creator":
                artPieces.sort(Comparator.comparing(ArtPiece::getCreator, String.CASE_INSENSITIVE_ORDER));
                return;
            case "Category":
                artPieces.sort(Comparator.comparing(ArtPiece::getCategory, String.CASE_INSENSITIVE_ORDER));
                return;
            case "Style":
                artPieces.sort(Comparator.comparing(ArtPiece::getStyle, String.CASE_INSENSITIVE_ORDER));
                return;
            case "Price":
                artPieces.sort(Comparator.comparing(ArtPiece::getPrice));
                return;
        }
    }
}
