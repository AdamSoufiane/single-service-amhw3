package shared;

import java.util.List;
import lombok.Data;

@Data
public class ApplicationCreateWorkflowRequestDTO {
    private String requestId;
    private List<String> featureList;
    private Boolean elementaryService;
    private Boolean rightsProvisioning;
    private List<ApplicationPreviousWorkflowStepDTO> activationWorkflow;
    private List<ApplicationPreviousWorkflowStepDTO> renewalWorkflow;
    private String type;
}