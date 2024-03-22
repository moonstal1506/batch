package io.springbatch.springbatchlecture.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ApiStartTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println(" >> ApiService is started");
        return RepeatStatus.FINISHED;
    }
}
