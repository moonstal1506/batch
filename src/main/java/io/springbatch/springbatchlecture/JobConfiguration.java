package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() { //SimpleJob(설계도)
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build(); //job에 2개의 step을 저장 -> jobLuncher가 실행
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("======================");
                    System.out.println(">> Job");
                    System.out.println("======================");
                    return RepeatStatus.FINISHED; //한번 실행 후 종료
                })
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("======================");
                    System.out.println(">> Job2");
                    System.out.println("======================");
                    return RepeatStatus.FINISHED; //한번 실행 후 종료
                })
                .build();
    }
}