package com.spring5.spring5webapp.repositories;

import com.spring5.spring5webapp.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
