package pl.edu.wszib.galeria.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.galeria.configuration.TestConfiguration;
import pl.edu.wszib.galeria.dao.IArtPieceDAO;
import pl.edu.wszib.galeria.dao.IOrderDAO;
import pl.edu.wszib.galeria.dao.IUserDAO;
import pl.edu.wszib.galeria.model.User;
import pl.edu.wszib.galeria.model.view.RegistrationModel;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {
    @Autowired
    IUserService userService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IArtPieceDAO artPieceDAO;

    @MockBean
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void registerTest(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("James123");
        registrationModel.setPass("jimmer123");
        registrationModel.setPass2("jimmer123");
        Mockito.when(this.userDAO.getUserByLogin("James123")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);

        boolean result = userService.register(registrationModel);
        Assert.assertTrue(result);
    }

    @Test
    public void registerFailedTest(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("Matt");
        registrationModel.setPass("matt123");
        registrationModel.setPass2("matt123");
        Mockito.when(this.userDAO.getUserByLogin("Matt")).thenReturn(new User());

        boolean result = userService.register(registrationModel);
        Assert.assertFalse(result);
    }

    @Test
    public void authenticationTest(){
        User user = new User();
        user.setLogin("Jim");
        user.setPass("Jim");
        Mockito.when(this.userDAO.getUserByLogin("Jim")).thenReturn(generateUser());
        this.userService.authenticate(user);
        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void authenticationFailedTest(){
        User user = new User();
        user.setLogin("james");
        user.setPass("james");
        Mockito.when(this.userDAO.getUserByLogin("james")).thenReturn(null);
        this.userService.authenticate(user);
        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    private User generateUser(){
        User user = new User();
        user.setId(2);
        user.setLogin("Jim");
        user.setPass("Jim");
        user.setRole(User.Role.USER);
        return user;
    }
}
