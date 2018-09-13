package com.io.springbatch.flow.app.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Elimane on May, 2018, at 08:41
 */
@Configuration
public class FlowSecondConfiguration {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1Flow2() {
        return stepBuilderFactory.get("step1Flow2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 1 from inside flow two");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2Flow2() {
        return stepBuilderFactory.get("step2Flow2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 2 from inside flow two");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Flow flow2()
    {

        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow two");

        flowBuilder.start(step1Flow2()).next(step2Flow2()).end();

        return flowBuilder.build();


    }


}
