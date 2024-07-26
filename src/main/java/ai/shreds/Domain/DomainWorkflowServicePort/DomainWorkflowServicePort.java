package ai.shreds.domain;

import ai.shreds.shared.ApplicationCreateWorkflowRequestDTO;
import java.util.List;

/**
 * Interface for Domain Workflow Service.
 * This interface defines the contract for the DomainWorkflowService which handles
 * the core business logic for generating workflow steps based on input data and predefined business rules.
 */
public interface DomainWorkflowServicePort {

    /**
     * Fetches all association records from the database.
     *
     * @return List of DomainAssociationEntity containing all associations.
     */
    List<DomainAssociationEntity> fetchAssociations();

    /**
     * Generates workflow steps based on the input request parameters.
     * Applies the necessary business rules to create the workflow steps.
     *
     * @param requestParams The input request parameters containing the details for workflow generation.
     * @return List of DomainWorkflowStepEntity containing the generated workflow steps.
     */
    List<DomainWorkflowStepEntity> generateWorkflowSteps(ApplicationCreateWorkflowRequestDTO requestParams);
}