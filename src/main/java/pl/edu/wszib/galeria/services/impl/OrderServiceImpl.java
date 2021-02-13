package pl.edu.wszib.galeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.galeria.dao.IOrderDAO;
import pl.edu.wszib.galeria.services.IOrderService;
import pl.edu.wszib.galeria.model.Order;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void saveOrder(Order order) {
        this.orderDAO.saveOrder(order);
    }

    @Override
    public Order getOrderById(int id) {
        return this.orderDAO.getOrderById(id);
    }
}
