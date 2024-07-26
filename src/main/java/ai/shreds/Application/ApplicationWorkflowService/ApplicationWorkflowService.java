package ai.shreds.application;

import ai.shreds.domain.DomainAssociationEntity;
import ai.shreds.domain.DomainWorkflowServicePort;
import ai.shreds.shared.ApplicationCreateWorkflowRequestDTO;
import ai.shreds.shared.ApplicationCreateWorkflowResponseDTO;
import ai.shreds.shared.ApplicationPreviousWorkflowStepDTO;
import ai.shreds.shared.ApplicationWorkflowStepDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationWorkflowService implements ApplicationWorkflowServicePort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationWorkflowService.class);

    private final DomainWorkflowServicePort domainService;

    @Override
    public ApplicationCreateWorkflowResponseDTO createWorkflow(ApplicationCreateWorkflowRequestDTO requestParams) {
        logger.info("Creating workflow for request: {}", requestParams.getRequestId());
        List<ApplicationWorkflowStepDTO> workflowSteps = applyBusinessRules(requestParams);
        return new ApplicationCreateWorkflowResponseDTO(workflowSteps);
    }

    @Override
    public List<ApplicationWorkflowStepDTO> applyBusinessRules(ApplicationCreateWorkflowRequestDTO requestParams) {
        logger.info("Applying business rules for request: {}", requestParams.getRequestId());
        List<DomainAssociationEntity> associations = domainService.fetchAssociations();
        List<ApplicationWorkflowStepDTO> workflowSteps = associations.stream()
                .filter(association -> filterAssociations(association, requestParams))
                .map(this::mapToWorkflowStep)
                .collect(Collectors.toList());

        if (requestParams.getActivationWorkflow() != null) {
            addConditionalSteps(requestParams.getActivationWorkflow(), workflowSteps);
        }

        if (requestParams.getRenewalWorkflow() != null) {
            addConditionalSteps(requestParams.getRenewalWorkflow(), workflowSteps);
        }

        return workflowSteps;
    }

    private boolean filterAssociations(DomainAssociationEntity association, ApplicationCreateWorkflowRequestDTO requestParams) {
        boolean matchesFeatureList = requestParams.getFeatureList().stream().anyMatch(feature -> association.getServices().contains(feature));
        return requestParams.getRightsProvisioning() ? association.getRights() && matchesFeatureList : !association.getRights() && matchesFeatureList;
    }

    private ApplicationWorkflowStepDTO mapToWorkflowStep(DomainAssociationEntity association) {
        return new ApplicationWorkflowStepDTO(0, association.getStep(), "INITIALIZED", association.getServices());
    }

    private void addConditionalSteps(List<ApplicationPreviousWorkflowStepDTO> previousWorkflow, List<ApplicationWorkflowStepDTO> workflowSteps) {
        for (ApplicationPreviousWorkflowStepDTO step : previousWorkflow) {
            if (step.getStepName().equals("CVS_ALLOCATION") && step.getStepState().equals("SUCCESS")) {
                workflowSteps.add(new ApplicationWorkflowStepDTO(0, "CVS_DEALLOCATION", "INITIALIZED", step.getFeatureList()));
            }
            if (step.getStepName().equals("CVS_UPDATE") && step.getStepState().equals("SUCCESS")) {
                workflowSteps.add(new ApplicationWorkflowStepDTO(0, "CVS_DEALLOCATION", "INITIALIZED", step.getFeatureList()));
            }
        }
    }
}