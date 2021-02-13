package pl.edu.wszib.galeria.services;


import pl.edu.wszib.galeria.model.User;
import pl.edu.wszib.galeria.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
}
