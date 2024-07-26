package ai.shreds.Application;

import lombok.Data;
import java.util.List;

@Data
public class ApplicationWorkflowStepDTO {
    private Integer stepNumber;
    private String stepName;
    private String stepState;
    private List<String> featureList;
}