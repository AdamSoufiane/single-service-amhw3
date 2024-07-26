package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import org.springframework.data.mongodb.core.mapping.Document; 
 import javax.validation.constraints.NotEmpty; 
 import javax.validation.constraints.NotNull; 
 import java.util.List; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Document(collection = "associations") 
 public class DomainAssociationEntity { 
     @NotNull 
     @NotEmpty 
     private String step; 
     @NotNull 
     @NotEmpty 
     private List<String> services; 
     @NotNull 
     private Boolean rights; 
 } 
 