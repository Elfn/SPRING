package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Elimane on Mar, 2018, at 04:10
 */
@Configuration
@EnableBatchProcessing
public class config {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;//entry point for building all kinds of steps

    @Autowired
    private JobBuilderFactory jobBuilderFactory;//for building jobs of various kinds

// Step is a domain object that encapsulates an independent, sequential phase of a batch job and contains all of the information necessary to define and control the actual batch processing
    @Bean
    public Step step1()
    {
        return stepBuilderFactory.get("step 1")
                //Tasklet is an interface to define processing strategy in a step
                            .tasklet(new Tasklet() {
                                //RepeatStatus an enum that define that processing can continue or is finished
                                @Override
                                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                    System.out.println("HELLO WORLD!!!!");
                                    return RepeatStatus.FINISHED;
                                }
                            }).build();
    }

    @Bean
    public Step step2()
    {
        return stepBuilderFactory.get("step 1")
                //Tasklet is an interface to define processing strategy in a step
                .tasklet(new Tasklet() {
                    //RepeatStatus an enum that define that processing can continue or is finished
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("HELLO WORLD STEP 2!!!!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("HEllo WORLd JOB!!!").start(step1()).next(step2()).build();
    }
}
