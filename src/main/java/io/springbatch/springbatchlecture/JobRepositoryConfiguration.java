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
public class JobRepositoryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
//    private final JobExecutionListener jobRepositoryListener;

    @Bean
    public Job job() { //SimpleJob(설계도)
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
//                .listener(jobRepositoryListener)
                .build(); //job에 2개의 step을 저장 -> jobLuncher가 실행
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                            // 동기적 실행: 모든 것이 끝나고 job execution 객체 반환
                            // 비동기적 방식: 클라이언트가 잡을 수행 요청 -> 일단 반환해 -> 그 다음 내부적으로 로직 실행
                            Thread.sleep(3000);
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> RepeatStatus.FINISHED)
                .build();
    }

}
