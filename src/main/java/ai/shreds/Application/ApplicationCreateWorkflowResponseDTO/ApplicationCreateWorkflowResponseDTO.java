import java.util.List;
import lombok.Data;

@Data
public class ApplicationCreateWorkflowResponseDTO {
    private List<ApplicationWorkflowStepDTO> workflow;
}