package com.sametruzgar.librarySystem.service;

import com.sametruzgar.librarySystem.dto.BookRequest;
import com.sametruzgar.librarySystem.dto.BookResponse;
import org.springframework.context.annotation.Bean;

import java.util.List;
public interface BookService {
    List <BookResponse> getAllBooks();



    BookResponse getBookById(Long id);
    BookResponse addBook(BookRequest bookRequest);
    BookResponse updateBook(Long id,BookRequest bookRequest);
    void deleteBook(Long id);

    BookResponse borrowBook(Long bookId, Long memberId);

    BookResponse returnBook(Long BookId);

}
