package com.REST_Project.API.Service;

import com.REST_Project.API.Models.Book;
import com.REST_Project.API.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookrepo;

    public List<Book> getAllBooks(){
        return bookrepo.findAll();
    }

    public Book getBook(Long id){
        return bookrepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> getBookByAuthor(String author){
        return bookrepo.findByAuthor(author);
    }

    public List<Book> getBookByGenre(String genre){
        return bookrepo.findByGenre(genre);
    }

    public List<Book> getBooksByAuthorAndGenre(String author, String genre){
        return bookrepo.findByAuthorAndGenre(author, genre);
    }

    public void postBook(Book newBook){
        bookrepo.save(newBook);
    }

    public Book updateBook(Long id, Book updated_book){
        Book existingBook = bookrepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
        existingBook.setTitle(updated_book.getTitle());
        existingBook.setAuthor(updated_book.getAuthor());
        existingBook.setGenre(updated_book.getGenre());
        existingBook.setPrice(updated_book.getPrice());
        bookrepo.save(existingBook);

        return existingBook;
    }

    public void deleteBookById(Long id){
        Book existingBook = bookrepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
        bookrepo.delete(existingBook);
    }
}
