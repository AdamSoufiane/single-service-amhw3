package ai.shreds.domain; 
  
 import ai.shreds.application.ApplicationCreateWorkflowRequestDTO; 
 import ai.shreds.infrastructure.InfrastructureAssociationRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Service; 
  
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 @Service 
 @RequiredArgsConstructor 
 public class DomainWorkflowService implements DomainWorkflowServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(DomainWorkflowService.class); 
     private final InfrastructureAssociationRepositoryPort associationRepository; 
  
     @Override 
     public List<DomainAssociationEntity> fetchAssociations() { 
         try { 
             return associationRepository.findAll(); 
         } catch (Exception e) { 
             logger.error("Error fetching associations", e); 
             throw new FetchAssociationsException("Error fetching associations", e); 
         } 
     } 
  
     @Override 
     public List<DomainWorkflowStepEntity> generateWorkflowSteps(ApplicationCreateWorkflowRequestDTO requestParams) { 
         List<DomainAssociationEntity> associations = fetchAssociations(); 
  
         // Apply business rules to filter associations 
         List<DomainAssociationEntity> filteredAssociations = associations.stream() 
                 .filter(association -> requestParams.getFeatureList().containsAll(association.getServices())) 
                 .filter(association -> !requestParams.getRightsProvisioning() || association.getRights()) 
                 .collect(Collectors.toList()); 
  
         // Convert filtered associations to workflow steps 
         List<DomainWorkflowStepEntity> workflowSteps = filteredAssociations.stream() 
                 .map(association -> new DomainWorkflowStepEntity( 
                         determineStepNumber(association), 
                         association.getStep(), 
                         "INITIALIZED", 
                         association.getServices() 
                 )) 
                 .collect(Collectors.toList()); 
  
         // Additional business rules for optional workflows 
         if (requestParams.getActivationWorkflow() != null) { 
             requestParams.getActivationWorkflow().stream() 
                     .filter(step -> "CVS_ALLOCATION".equals(step.getStepName()) && "SUCCESS".equals(step.getStepState())) 
                     .findFirst() 
                     .ifPresent(step -> workflowSteps.add(new DomainWorkflowStepEntity(determineStepNumber(null), "CVS_DEALLOCATION", "INITIALIZED", List.of()))); 
         } 
  
         if (requestParams.getRenewalWorkflow() != null) { 
             requestParams.getRenewalWorkflow().stream() 
                     .filter(step -> "CVS_UPDATE".equals(step.getStepName()) && "SUCCESS".equals(step.getStepState())) 
                     .findFirst() 
                     .ifPresent(step -> workflowSteps.add(new DomainWorkflowStepEntity(determineStepNumber(null), "CVS_DEALLOCATION", "INITIALIZED", List.of()))); 
         } 
  
         return workflowSteps; 
     } 
  
     private int determineStepNumber(DomainAssociationEntity association) { 
         // Implement logic to determine the step number 
         // Placeholder logic: return a random number or based on some attribute of association 
         return association != null ? association.hashCode() % 100 : 0; 
     } 
  
     // Custom exception class for better clarity 
     public static class FetchAssociationsException extends RuntimeException { 
         public FetchAssociationsException(String message, Throwable cause) { 
             super(message, cause); 
         } 
     } 
 } 
 