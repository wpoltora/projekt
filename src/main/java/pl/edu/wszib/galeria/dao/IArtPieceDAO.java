package pl.edu.wszib.galeria.dao;


import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.model.Order;

import java.util.List;

public interface IArtPieceDAO {
    ArtPiece getArtPieceById(int id);
    void updateArtPiece(ArtPiece artPiece);
    void deleteArtPiece(int id);
    void addArtPiece(ArtPiece artPiece);
    List<ArtPiece> getAllArtPieces();

}
