package com.rookies3.MySpringbootLab.repository;

import com.rookies3.MySpringbootLab.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateBook() {
        Book book = new Book("스프링 부트 입문", "홍길동", "9788956746425", 30000, LocalDate.of(2025, 5, 7));
        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test
    void testFindByIsbn() {
        Book book = new Book("JPA 프로그래밍", "박둘리", "9788956746432", 35000, LocalDate.of(2025, 4, 30));
        bookRepository.save(book);

        Optional<Book> found = bookRepository.findByIsbn("9788956746432");
        assertThat(found).isPresent();
        assertThat(found.get().getAuthor()).isEqualTo("박둘리");
    }

    @Test
    void testFindByAuthor() {
        Book book1 = new Book("스프링 부트 입문", "홍길동", "9788956746425", 30000, LocalDate.of(2025, 5, 7));
        Book book2 = new Book("JPA 프로그래밍", "홍길동", "9788956746449", 35000, LocalDate.of(2025, 4, 30));
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findByAuthor("홍길동");
        assertThat(books).hasSize(2);
    }

    @Test
    void testUpdateBook() {
        Book book = new Book("스프링 부트 입문", "홍길동", "9788956746425", 30000, LocalDate.of(2025, 5, 7));
        Book saved = bookRepository.save(book);

        saved.setPrice(32000);
        Book updated = bookRepository.save(saved);

        assertThat(updated.getPrice()).isEqualTo(32000);
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("JPA 프로그래밍", "박둘리", "9788956746432", 35000, LocalDate.of(2025, 4, 30));
        Book saved = bookRepository.save(book);

        bookRepository.delete(saved);

        Optional<Book> deleted = bookRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();
    }
}
