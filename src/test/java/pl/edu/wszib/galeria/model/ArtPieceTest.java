package pl.edu.wszib.galeria.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

public class ArtPieceTest{
    @Test
    public void cloneTest(){
        ArtPiece artPiece = new ArtPiece();
        artPiece.setId(2);
        artPiece.setCreator("John Williams");
        artPiece.setStyle("Modern");
        artPiece.setCategory("Painting");
        artPiece.setName("Morning");
        artPiece.setPrice(22.99);

        ArtPiece clone = artPiece.clone();

        Assert.assertEquals(artPiece.getId(), clone.getId());
        Assert.assertEquals(artPiece.getCreator(), clone.getCreator());
        Assert.assertEquals(artPiece.getStyle(), clone.getStyle());
        Assert.assertEquals(artPiece.getCategory(), clone.getCategory());
        Assert.assertEquals(artPiece.getName(), clone.getName());
        Assert.assertEquals(artPiece.getPrice(), clone.getPrice(), 0.0001);

        Assert.assertNotSame(artPiece, clone);
    }
}