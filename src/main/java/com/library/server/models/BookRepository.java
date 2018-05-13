package com.library.server.models;

import org.springframework.data.repository.CrudRepository;

import com.library.server.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
