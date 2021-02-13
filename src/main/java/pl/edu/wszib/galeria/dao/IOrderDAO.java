package pl.edu.wszib.galeria.dao;

import pl.edu.wszib.galeria.model.Order;

public interface IOrderDAO {
    void saveOrder(Order order);
    Order getOrderById(int id);
}
