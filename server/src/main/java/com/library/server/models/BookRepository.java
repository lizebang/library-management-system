package com.library.server.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.library.server.models.Book;

public interface BookRepository extends CrudRepository<Book, String> {

    Book findByIsbn(String isbn);

    Page<Book> findByTag(String tag, Pageable page);

    Page<Book> findByAuthor(String author, Pageable page);

    @Query(value = "SELECT * FROM book WHERE isbn = ?1 OR name = ?1 OR author = ?1",
            nativeQuery = true)
    Page<Book> findByKeyword(String keyword, Pageable page);

    @Query(value = "SELECT * FROM book WHERE isbn LIKE %?1% OR name LIKE %?1% OR author LIKE %?1% OR tag LIKE %?1%",
            nativeQuery = true)
    Page<Book> findFuzzy(String keyword, Pageable page);

    @Transactional
    void deleteBookByIsbn(String isbn);

    Page<Book> findAll(Pageable page);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.inventory = ?1 WHERE b.isbn = ?2")
    int updateInventory(Integer inventory, String isbn);
}
