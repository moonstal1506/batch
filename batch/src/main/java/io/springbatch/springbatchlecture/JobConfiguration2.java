package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
public class JobConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
//    private final JobExecutionListener jobRepositoryListener;

    @Bean
    public Job job() { //SimpleJob(설계도)
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .next(step3())
                .validator(new DefaultJobParametersValidator(new String[]{"name", "date"}, new String[]{"count"})) //필수키만 있으면 검증성공
//                .validator(new CustomJobParametersValidator())
//                .incrementer(new RunIdIncrementer())
//                .validator(new JobParametersValidator() {
//                    @Override
//                    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
//
//                    }
//                })
//                .preventRestart()
//                .listener(new JobExecutionListener() {
//                    @Override
//                    public void beforeJob(JobExecution jobExecution) {
//
//                    }
//
//                    @Override
//                    public void afterJob(JobExecution jobExecution) {
//
//                    }
//                })
//                .listener(jobRepositoryListener)
                .build(); //job에 2개의 step을 저장 -> jobLuncher가 실행
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                            // 동기적 실행: 모든 것이 끝나고 job execution 객체 반환
                            // 비동기적 방식: 클라이언트가 잡을 수행 요청 -> 일단 반환해 -> 그 다음 내부적으로 로직 실행
//                            Thread.sleep(3000);
                            System.out.println("step1");
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
                })
                .build();
    }

//    @Bean
//    public Job job2() { //flowJob생성
//        return jobBuilderFactory.get("batchJob2")
//                .start(flow())
//                .next(step5())
//                .end()
//                .build();
//    }

    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(step3())
                .next(step4())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
//                    chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
//                    stepContribution.setExitStatus(ExitStatus.STOPPED);
                    System.out.println("step3");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step4");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step5");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
