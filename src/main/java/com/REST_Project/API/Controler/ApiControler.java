package com.REST_Project.API.Controler;


import com.REST_Project.API.Models.Book;
import com.REST_Project.API.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControler {

    @Autowired
    private BookService bookService;

    @GetMapping(value="/")
    public String wecolme(){
        return "WELCOME!";
    }



    @GetMapping(value = "/books/{id}")
    public Book getBookId(@PathVariable Long id){
        try {
            return bookService.getBook(id);
        }catch(RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre) {

        if (author != null && genre != null) {
            return bookService.getBooksByAuthorAndGenre(author, genre);
        } else if (author != null) {
            return bookService.getBookByAuthor(author);
        } else if (genre != null) {
            return bookService.getBookByGenre(genre);
        } else {
            return bookService.getAllBooks();
        }
    }

    @PostMapping(value = "/add")
    public String addBook(@RequestBody Book book){
        bookService.postBook(book);
        return "Saved...";
    }

    @PutMapping(value = "/{id}/update")
    public String updateBook(@PathVariable Long id, @RequestBody Book updated_book){
        try{
            bookService.updateBook(id, updated_book);
            return "Book: " + updated_book.getTitle() + " was updated.";
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping(value = "/{id}/delete")
    public String deleteBook(@PathVariable Long id){
        try{
            bookService.deleteBookById(id);
            return "Livro Deletado com Sucesso!";
        } catch(RuntimeException e){
            return "Livro com id de "+ id + " não foi encontrado.";
        }
    }
}
