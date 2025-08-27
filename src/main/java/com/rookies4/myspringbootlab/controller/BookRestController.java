package com.rookies4.myspringbootlab.controller;

import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> ResponseEntity.ok(book)) // Optional.map() 활용
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("해당 ISBN의 도서를 찾을 수 없습니다: " + isbn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setIsbn(newBook.getIsbn());
                    book.setPrice(newBook.getPrice());
                    book.setPublishDate(newBook.getPublishDate());
                    Book updatedBook = bookRepository.save(book);
                    return ResponseEntity.ok(updatedBook);
                })
                .orElseThrow(() -> new BusinessException("수정할 도서를 찾을 수 없습니다: id=" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseThrow(() -> new BusinessException("삭제할 도서를 찾을 수 없습니다: id=" + id));
    }
}
