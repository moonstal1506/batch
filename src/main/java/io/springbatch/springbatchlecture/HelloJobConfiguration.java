package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() { // 일
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1()) //일의 순서
                .next(helloStep2())
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("======================");
                    System.out.println(">> 헬로 스프링 배치");
                    System.out.println("======================");
                    return RepeatStatus.FINISHED; //한번 실행 후 종료
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("======================");
                    System.out.println(">> 헬로 스프링 배치2");
                    System.out.println("======================");
                    return RepeatStatus.FINISHED; //한번 실행 후 종료
                })
                .build();
    }
}
