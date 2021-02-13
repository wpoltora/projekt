package pl.edu.wszib.galeria.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.galeria.dao.IArtPieceDAO;
import pl.edu.wszib.galeria.model.ArtPiece;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateArtPieceDAOImpl implements IArtPieceDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ArtPiece getArtPieceById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<ArtPiece> query = session.createQuery("FROM pl.edu.wszib.galeria.model.ArtPiece WHERE id = :id");
        query.setParameter("id", id);
        ArtPiece artPiece = null;
        try {
            artPiece = query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("ArtPiece not found");
        }
        session.close();
        return artPiece;
    }

    @Override
    public void updateArtPiece(ArtPiece artPiece) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(artPiece);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteArtPiece(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        ArtPiece artPiece = getArtPieceById(id);
        try{
            tx = session.beginTransaction();
            session.delete(artPiece);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public void addArtPiece(ArtPiece artPiece) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(artPiece);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<ArtPiece> getAllArtPieces() {
        Session session = this.sessionFactory.openSession();
        Query<ArtPiece> query = session.createQuery("FROM pl.edu.wszib.galeria.model.ArtPiece");
        List<ArtPiece> artPieces = query.getResultList();
        session.close();
        return artPieces;
    }
}
