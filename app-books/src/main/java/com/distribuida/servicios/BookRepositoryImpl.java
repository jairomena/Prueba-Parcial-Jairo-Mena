package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext(unitName = "appBooks")
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager.createNamedQuery("Book.findAll",Book.class).getResultList();
    }

    @Override
    public Book findById(Integer id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }
    @Override
    @Transactional
    public void insert(Book book) {
        entityManager.persist(book);
    }
    @Override
    @Transactional
    public void update(Book book) {
        Book bookAux = entityManager.find(Book.class, book.getId());
        if (bookAux != null){
            entityManager.merge(book);
        }
    }
    @Override
    @Transactional
    public void delete(Integer id) {
        Book bookDel = entityManager.find(Book.class, id);
        if (bookDel != null){
            entityManager.remove(bookDel);
        }
    }
}
