package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.dto.BookDTO;
import com.rookies4.myspringbootlab.repository.BookRepository;
import com.rookies4.myspringbootlab.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest req) {
        Book book = new Book();
        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setIsbn(req.getIsbn());
        book.setPrice(req.getPrice());
        book.setPublishDate(req.getPublishDate());
        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    public BookDTO.BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다: id=" + id));
        return toResponse(book);
    }

    public BookDTO.BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("해당 ISBN의 도서를 찾을 수 없습니다: " + isbn));
        return toResponse(book);
    }

    public List<BookDTO.BookResponse> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BookDTO.BookResponse updateBook(Long id, BookDTO.BookUpdateRequest req) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("수정할 도서를 찾을 수 없습니다: id=" + id));
        // 변경이 필요한 필드만 부분적으로 업데이트
        if (req.getTitle() != null) existBook.setTitle(req.getTitle());
        if (req.getAuthor() != null) existBook.setAuthor(req.getAuthor());
        if (req.getIsbn() != null) existBook.setIsbn(req.getIsbn());
        if (req.getPrice() != null) existBook.setPrice(req.getPrice());
        if (req.getPublishDate() != null) existBook.setPublishDate(req.getPublishDate());
        Book updated = bookRepository.save(existBook);
        return toResponse(updated);
    }

    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id))
            throw new BusinessException("삭제할 도서를 찾을 수 없습니다: id=" + id);
        bookRepository.deleteById(id);
    }

    private BookDTO.BookResponse toResponse(Book book) {
        return new BookDTO.BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getPublishDate());
    }
}
