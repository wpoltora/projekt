package pl.edu.wszib.galeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.wszib.galeria.dao.IUserDAO;
import pl.edu.wszib.galeria.model.User;
import pl.edu.wszib.galeria.model.view.RegistrationModel;
import pl.edu.wszib.galeria.services.IUserService;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO UserDAO;

    @Override
    public void authenticate(User user) {
        User DBUser = this.UserDAO.getUserByLogin(user.getLogin());
        if(DBUser == null){
            return;
        }

        if(user.getPass().equals(DBUser.getPass()) ){
            this.sessionObject.setLoggedUser(DBUser);
        };
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {
        if(this.UserDAO.getUserByLogin(registrationModel.getLogin()) !=null){
            return false;
        }
        User newUser = new User(0, registrationModel.getLogin(), registrationModel.getPass(), User.Role.USER);
        return this.UserDAO.persistUser(newUser);
    }
}
