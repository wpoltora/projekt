package pl.edu.wszib.galeria.services;

import pl.edu.wszib.galeria.model.ArtPiece;

import java.util.List;

public interface IArtPieceService {
    ArtPiece getArtPieceById(int id);
    void updateArtPiece(ArtPiece artPiece);
    void deleteArtPiece(int id);
    void addArtPiece(ArtPiece artPiece);
    List<ArtPiece> getAllArtPieces();
    void setSortMethod(String method);
}
