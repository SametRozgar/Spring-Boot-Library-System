package com.sametruzgar.librarySystem.service.impl;

import com.sametruzgar.librarySystem.dto.BookRequest;
import com.sametruzgar.librarySystem.dto.BookResponse;
import com.sametruzgar.librarySystem.model.Book;
import com.sametruzgar.librarySystem.model.Member;
import com.sametruzgar.librarySystem.service.BookService;
import com.sametruzgar.librarySystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class BookServiceImpl implements BookService {

    private final List<Book> books=new ArrayList<>();

    private final AtomicLong idCounter = new AtomicLong(1);

    @Autowired
    private MemberService memberService;


    @Override
    public List<BookResponse> getAllBooks() {
        return books.stream()
                .map(this::mapToBookResponse)
                .toList();
    }
    @Override
    public BookResponse getBookById(Long id) {
        Book book = findBookById(id);
        return mapToBookResponse(book);
    }

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setId(idCounter.getAndIncrement());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setAvailable(true);

        books.add(book);
        return mapToBookResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = findBookById(id);
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());

        return mapToBookResponse(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = findBookById(id);
        books.remove(book);
    }

    @Override
    public BookResponse borrowBook(Long bookId, Long memberId) {
        Book book = findBookById(bookId);
        Member member = memberService.findMemberById(memberId);

        if (!book.isAvaible()) {
            throw new IllegalStateException("Kitap zaten ödünç alınmış");
        }

        book.setAvailable(false);
        member.getBorrowedBooks().add(bookId);

        return mapToBookResponse(book);
    }

    @Override
    public BookResponse returnBook(Long bookId) {
        Book book = findBookById(bookId);

        if (book.isAvaible()) {
            throw new IllegalStateException("Kitap zaten kütüphanede");
        }

        book.setAvailable(true);
        memberService.findMembersWithBook(bookId).forEach(member ->
                member.getBorrowedBooks().remove(bookId));

        return mapToBookResponse(book);
    }


    public Book findBookById(long id){
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElseThrow();
    }

    public BookResponse mapToBookResponse(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.isAvaible()

        );
    }

}
