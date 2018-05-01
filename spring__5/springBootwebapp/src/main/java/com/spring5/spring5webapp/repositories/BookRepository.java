package com.spring5.spring5webapp.repositories;

import com.spring5.spring5webapp.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}
