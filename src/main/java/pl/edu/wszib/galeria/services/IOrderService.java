package pl.edu.wszib.galeria.services;


import pl.edu.wszib.galeria.model.Order;

public interface IOrderService {
    void saveOrder(Order order);
    Order getOrderById(int id);
}
