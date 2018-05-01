package com.spring5.spring5webapp.repositories;

import com.spring5.spring5webapp.entities.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
}
