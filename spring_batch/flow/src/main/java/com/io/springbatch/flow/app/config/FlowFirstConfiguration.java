package com.io.springbatch.flow.app.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Elimane on May, 2018, at 08:36
 */
@Configuration
public class FlowFirstConfiguration {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1Flow1() {
        return stepBuilderFactory.get("step1Flow1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 1 from inside flow one");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2Flow1() {
        return stepBuilderFactory.get("step2Flow1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 2 from inside flow one");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Flow flow1()
    {

        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow one");

        flowBuilder.start(step1Flow1()).next(step2Flow1()).end();

        return flowBuilder.build();


    }



}
