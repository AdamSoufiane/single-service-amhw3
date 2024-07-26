package ai.shreds.Application;

import ai.shreds.shared.ApplicationCreateWorkflowRequestDTO;
import ai.shreds.shared.ApplicationCreateWorkflowResponseDTO;
import ai.shreds.shared.ApplicationWorkflowStepDTO;
import java.util.List;

/**
 * Interface for the workflow service in the application layer.
 * Defines methods for creating workflows and applying business rules.
 */
public interface ApplicationWorkflowServicePort {

    /**
     * Creates a workflow based on the provided request parameters.
     *
     * @param requestParams the request parameters for creating the workflow
     * @return the response containing the generated workflow
     */
    ApplicationCreateWorkflowResponseDTO createWorkflow(ApplicationCreateWorkflowRequestDTO requestParams);

    /**
     * Applies business rules to the provided request parameters to generate workflow steps.
     *
     * @param requestParams the request parameters for applying business rules
     * @return the list of generated workflow steps
     */
    List<ApplicationWorkflowStepDTO> applyBusinessRules(ApplicationCreateWorkflowRequestDTO requestParams);
}