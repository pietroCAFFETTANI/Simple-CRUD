package com.REST_Project.API.Repo;

import com.REST_Project.API.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepo extends JpaRepository<Book, Long> {
    @Override
    Optional<Book> findById(Long id);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByAuthorAndGenre(String author, String genre);
}


