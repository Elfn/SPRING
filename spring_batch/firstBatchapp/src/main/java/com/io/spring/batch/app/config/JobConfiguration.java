package com.io.spring.batch.app.config;

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
 * Created by Elimane on May, 2018, at 03:44
 */

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;//entry point for building all kinds of steps

    @Autowired
    private StepBuilderFactory stepBuilderFactory;//for building jobs of various kinds


    //a Step is a domain object that encapsulates an independent,
    // sequential phase of a batch job and contains all of the
    // information necessary to define and control the actual
    // batch processing

    //Tasklet is a Strategy for processing in a step.


    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                System.out.println("Hello world!!!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                System.out.println("Hello world!!! STEP 2");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Job job()
    {
        return jobBuilderFactory.get("first job!!!").start(step1()).next(step2()).build();
    }


}
