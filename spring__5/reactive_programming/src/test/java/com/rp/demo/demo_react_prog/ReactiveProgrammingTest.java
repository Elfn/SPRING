package com.rp.demo.demo_react_prog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Elimane on May, 2018, at 20:00
 */

@Slf4j
public class ReactiveProgrammingTest {


    Student elimane = new Student("Elimane","Fofana",27);
    Student fiona = new Student("Fiona", "Glenanne",20);
    Student sam = new Student("Sam", "Axe",37);
    Student jesse = new Student("Jesse", "Porter",38);




    @Test
    public void monoTests() throws Exception {

        //MONO is an inteface of Java 8 to handle 0 or 1 event
        //create new person mono
        Mono<Student> studentMono = Mono.just(elimane);

        //BLOCK is to get person object from mono publisher
        Student student = studentMono.block();

        // output name
        log.info(student.studentInfos());
    }

    @Test
    public void monoTransform() throws Exception {
        //create new person mono
        Mono<Student> studentMono = Mono.just(fiona);

        StudentCommand command = studentMono
                .map(person -> { //type transformation
                    return new StudentCommand(person);
                }).block();

        log.info(command.studentInfos());
    }

    @Test(expected = NullPointerException.class)
    public void monoFilter() throws Exception {
        Mono<Student> studentMono = Mono.just(sam);

        //filter example
        Student samAxe = studentMono
                .filter(student -> student.getFirstname().equalsIgnoreCase("foo"))
                .block();


        log.info(samAxe.studentInfos()); //throws NPE
    }

    @Test
    public void fluxTest() throws Exception {


        //FLUX is an inteface of Java 8 to handle 1 or many events

        Flux<Student> people = Flux.just(elimane, fiona, sam, jesse);

        people.subscribe(person -> log.info(person.studentInfos()));

    }

    @Test
    public void fluxTestFilter() throws Exception {

        Flux<Student> studentFlux = Flux.just(elimane, fiona, sam, jesse);

        studentFlux.filter(student -> student.getFirstname().equals(fiona.getFirstname()))
                .subscribe(person -> log.info(person.studentInfos()));

    }

    @Test
    public void fluxTestDelayNoOutput() throws Exception {

        Flux<Student> studentFlux = Flux.just(elimane, fiona, sam, jesse);

        studentFlux.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> log.info(person.studentInfos()));

    }

    @Test
    public void fluxTestDelay() throws Exception {

        //COUNTDOWNLATCH allows one or more threads to wait
        // until a set of operations being performed
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Student> studentFlux = Flux.just(elimane, fiona, sam, jesse);

        studentFlux.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.studentInfos()));

        countDownLatch.await();

    }

    @Test
    public void fluxTestFilterDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Student> people = Flux.just(elimane, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .filter(student -> student.getFirstname().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.studentInfos()));

        countDownLatch.await();
    }
}
