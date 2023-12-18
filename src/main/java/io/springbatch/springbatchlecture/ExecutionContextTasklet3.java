package io.springbatch.springbatchlecture;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {

    //여기서 예외 발생하면 스텝4로 넘어가지 않음 -> 잡 실패 -> 재시작 가능 -> 데이터 가져올 수 있는지..
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("ExecutionContextTasklet3");
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");

        if (name == null) {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
//            throw new RuntimeException("ExecutionContextTasklet3 실패");
        }
        return RepeatStatus.FINISHED;
    }
}
