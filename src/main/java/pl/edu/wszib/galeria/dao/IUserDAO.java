package pl.edu.wszib.galeria.dao;

import pl.edu.wszib.galeria.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
