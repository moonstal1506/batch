package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
public class TransitionConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("transition1")
                .start(step1())
                    .on("FAILED")
                    .to(step2())
                    .on("FAILED")
                    .stop()
                .from(step1())
                    .on("*")
                    .to(step3())
                    .next(step4())
                .from(step2())
                    .on("*")
                    .to(step5())
                    .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                            System.out.println("step1");
//                            stepContribution.setExitStatus(ExitStatus.FAILED);
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                            System.out.println("step2");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                            System.out.println("step3");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((stepContribution, chunkContext) -> {
                            System.out.println("step4");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet((stepContribution, chunkContext) -> {
                            System.out.println("step5");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

}
