package com.idblife.batch.job.simple;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StepNextConditionalJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        return jobBuilderFactory.get("stepNextConditionalJob")
                .start(conditionJobStep1())
                    .on("FAILED")
                    .to(conditionJobStep3())
                    .on("*")
                    .end()
                .from(conditionJobStep1())
                    .on("*")
                    .to(conditionJobStep2())
                    .next(conditionJobStep3())
                    .on("*")
                    .end()
                .end().build();
    }

    private Step conditionJobStep2() {
        return stepBuilderFactory.get("conditionJobStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is conditionJobStep2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step conditionJobStep3() {
        return stepBuilderFactory.get("conditionJobStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is conditionJobStep3");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step conditionJobStep1() {
        return stepBuilderFactory.get("conditionJobStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is conditionalJopStep1");
                    /*
                      ExitStatus를 FAILED로 지정한다.
                      해당 status를 보고 flow가 진행된다.
                     */
                    //contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
