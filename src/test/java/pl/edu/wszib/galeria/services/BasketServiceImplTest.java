package pl.edu.wszib.galeria.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.galeria.configuration.TestConfiguration;
import pl.edu.wszib.galeria.dao.IArtPieceDAO;
import pl.edu.wszib.galeria.dao.IOrderDAO;
import pl.edu.wszib.galeria.dao.IUserDAO;
import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class BasketServiceImplTest {
    @Autowired
    IBasketService basketService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IArtPieceDAO artPieceDAO;

    @MockBean
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void calculateTotalTest(){
        Assert.assertNotNull(this.basketService);
        sessionObject.getBasket().add(new ArtPiece(1, "Obrazek", "Jack", "Obraz","Contemporary" ,12.99));
        sessionObject.getBasket().add(new ArtPiece(1, "Obraz2", "Jackson", "Obraz","Modern" ,15.99));
        double expectedTotal = 28.98;
        Assert.assertEquals(expectedTotal, basketService.calculateTotal(), 0.01);
    }
}
