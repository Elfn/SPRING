package com.io.spring.batch.app.configuration;

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
 * Created by Elimane on Oct, 2018, at 20:30
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    /*
    *
    *> Step  is a domain object that encapsulates an independent, sequential phase of a batch job and contains all of the information necessary to define and control the actual batch processing.
    *> Job is simply a container for Steps
    *> Tasklet is an interface, which will be called to perform a single task only, like clean or set up resources before or after any step execution
    *
    * */

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step_1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This the first Step");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("This is first job").start(step1()).build();
    }

}
