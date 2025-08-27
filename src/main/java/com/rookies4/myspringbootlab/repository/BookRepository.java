package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringbootlab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

/** Spring Data JPA repository bound to Book entity. */
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);

    boolean existsByIsbn(String isbn);

    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("select b from Book b left join fetch b.detail where b.id = :id")
    Optional<Book> findByIdWithBookDetail(@Param("id") Long id);

    @Query("select b from Book b left join fetch b.detail where b.isbn = :isbn")
    Optional<Book> findByIsbnWithBookDetail(@Param("isbn") String isbn);
}