package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationWorkflowServicePort; 
 import ai.shreds.shared.ApplicationCreateWorkflowRequestDTO; 
 import ai.shreds.shared.ApplicationCreateWorkflowResponseDTO; 
 import ai.shreds.shared.SharedErrorResponseDTO; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.validation.annotation.Validated; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
 import javax.validation.Valid; 
 import javax.validation.ConstraintViolationException; 
  
 @RestController 
 @RequestMapping("/api/v1/workflow") 
 @RequiredArgsConstructor 
 @Validated 
 public class AdapterWorkflowController { 
  
     private final ApplicationWorkflowServicePort workflowService; 
  
     @PostMapping 
     public ResponseEntity<ApplicationCreateWorkflowResponseDTO> createWorkflow(@Valid @RequestBody ApplicationCreateWorkflowRequestDTO request) { 
         ApplicationCreateWorkflowResponseDTO response = workflowService.createWorkflow(request); 
         return new ResponseEntity<>(response, HttpStatus.OK); 
     } 
  
     @ExceptionHandler(ConstraintViolationException.class) 
     public ResponseEntity<SharedErrorResponseDTO> handleValidationException(ConstraintViolationException exception) { 
         SharedErrorResponseDTO errorResponse = new SharedErrorResponseDTO(); 
         errorResponse.setCode("VALIDATION_ERROR"); 
         errorResponse.setMessage(exception.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<SharedErrorResponseDTO> handleException(Exception exception) { 
         SharedErrorResponseDTO errorResponse = new SharedErrorResponseDTO(); 
         errorResponse.setCode("INTERNAL_SERVER_ERROR"); 
         errorResponse.setMessage(exception.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 