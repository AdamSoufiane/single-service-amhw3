package ai.shreds.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "associations")
public class DomainAssociationEntity {
    @NotNull
    @Size(min = 1, message = "Step must be at least one character long")
    private String step;
    
    @NotNull
    @Size(min = 1, message = "Services list must not be empty")
    private List<String> services;
    
    @NotNull
    private Boolean rights;
}