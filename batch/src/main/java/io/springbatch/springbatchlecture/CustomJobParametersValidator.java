package io.springbatch.springbatchlecture;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        if (jobParameters.getString("name") == null) {
            //--job.name=batchJob name2=user1 실패
            throw new JobParametersInvalidException("name parameters is not found");
        }
    }
}
