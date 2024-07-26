package ai.shreds.Application;

import java.util.List;
import lombok.Data;

@Data
public class ApplicationPreviousWorkflowStepDTO {
    private Integer stepNumber;
    private String stepName;
    private String stepState;
    private List<String> featureList;
}