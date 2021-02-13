package pl.edu.wszib.galeria.services;

public interface IBasketService {
    double calculateTotal();
    void addArtPieceByIDToBasket(int id);
    void removeArtPieceByIdFromBasket(int id);
}
