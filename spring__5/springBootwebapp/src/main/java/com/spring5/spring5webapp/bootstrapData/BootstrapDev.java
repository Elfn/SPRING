package com.spring5.spring5webapp.bootstrapData;

import com.spring5.spring5webapp.entities.Author;
import com.spring5.spring5webapp.entities.Book;
import com.spring5.spring5webapp.entities.Publisher;
import com.spring5.spring5webapp.repositories.AuthorRepository;
import com.spring5.spring5webapp.repositories.BookRepository;
import com.spring5.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootstrapDev implements ApplicationListener<ContextRefreshedEvent>{



    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    //DI
    public BootstrapDev(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
         initData();
    }
    //Data initialization
    public void initData()
    {
        Author a = new Author("M","E");

        Publisher p = new Publisher("L","B");
        publisherRepository.save(p);

        Book b = new Book("ANGULAR","11223",p);

        a.getBooks().add(b);
        authorRepository.save(a);
        b.getAuthors().add(a);
        bookRepository.save(b);



        Author a2 = new Author("K","L");
        Publisher p2 = new Publisher("M","C");
        publisherRepository.save(p2);

        Book b2 = new Book("SPRING","16566",p2);

        a2.getBooks().add(b);
        authorRepository.save(a2);
        b2.getAuthors().add(a);
        bookRepository.save(b2);



    }
}
