package pl.edu.wszib.galeria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArtPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String creator;
    private String category;
    private String style;
    private double price;

    public ArtPiece(int id, String name, String creator, String category, String style, double price) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.category = category;
        this.style = style;
        this.price = price;
    }

    public ArtPiece() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public ArtPiece clone() {
        return new ArtPiece(this.id, this.name, this.creator, this.category, this.style, this.price);
    }

    @Override
    public String toString() {
        return "ArtPiece{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", catefory='" + category + '\'' +
                ", style ='" + style + '\'' +
                ", price=" + price +
                '}';
    }
}
