package pl.edu.wszib.galeria.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import pl.edu.wszib.galeria.model.ArtPiece;
import pl.edu.wszib.galeria.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class ArtPieceServiceImplTest {

    @Autowired
    IArtPieceService artPieceService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IArtPieceDAO artPieceDAO;

    @MockBean
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void getAllArtPiecesTest(){
        Mockito.when(this.artPieceDAO.getAllArtPieces()).thenReturn(generateList());
        List<ArtPiece> list = this.artPieceService.getAllArtPieces();
        Assert.assertNotNull(list);
        ArtPiece artPiece1 = new ArtPiece(0, "Lake", "James", "Painting","Modern", 19.99);
        Assert.assertEquals(list.get(0).getId(), artPiece1.getId());
        Assert.assertEquals(list.get(0).getName(), artPiece1.getName());
        Assert.assertEquals(list.get(0).getCreator(), artPiece1.getCreator());
        Assert.assertEquals(list.get(0).getCategory(), artPiece1.getCategory());
        Assert.assertEquals(list.get(0).getStyle(), artPiece1.getStyle());
        Assert.assertEquals(list.get(0).getPrice(), artPiece1.getPrice(), 0.01);
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void sortTest(){
        this.artPieceService.setSortMethod("Price");
        Mockito.when(this.artPieceDAO.getAllArtPieces()).thenReturn(generateList());
        List<ArtPiece> list = this.artPieceService.getAllArtPieces();
        Assert.assertEquals(list.get(0).getId(), 1);
        Assert.assertEquals(list.get(1).getId(), 0);
    }

    private List<ArtPiece> generateList(){
        List<ArtPiece> list = new ArrayList<>();
        ArtPiece artPiece1 = new ArtPiece(0, "Lake", "James", "Painting","Modern", 19.99);
        ArtPiece artPiece2 = new ArtPiece(1, "Sea", "John", "Painting","Retro", 12.99);
        list.add(artPiece1);
        list.add(artPiece2);
        return list;
    }

}
