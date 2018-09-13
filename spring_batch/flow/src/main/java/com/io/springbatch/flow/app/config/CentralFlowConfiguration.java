package com.io.springbatch.flow.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created by Elimane on May, 2018, at 08:43
 */
@Configuration
public class CentralFlowConfiguration {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 1 from inside flow central");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 2 from inside flow central");
                    return RepeatStatus.FINISHED;
                }).build();
    }


    @Bean
    public Job centralJobFlow(@Qualifier("flow1") Flow one,@Qualifier("flow2") Flow two)
    {

        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("split");

        //GROUP TWO FLOWS IN ONE JOB
        Flow flow = flowBuilder.split(new SimpleAsyncTaskExecutor())
                .add(one, two)
                .end();

        return jobBuilderFactory.get("splitJob")
                .start(step1())
                .next(step2())
                .on("COMPLETED")
                .to(flow)
                .from(flow)
                .end()
                .build();
    }



}
