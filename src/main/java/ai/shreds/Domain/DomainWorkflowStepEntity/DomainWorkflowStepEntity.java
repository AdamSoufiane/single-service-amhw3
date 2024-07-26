package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.io.Serializable;

/**
 * Represents a workflow step in the domain layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainWorkflowStepEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The number of the step.
     */
    private Integer stepNumber;

    /**
     * The name of the step.
     */
    private String stepName;

    /**
     * The state of the step.
     */
    private String stepState;

    /**
     * The list of features associated with the step.
     */
    private List<String> featureList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainWorkflowStepEntity that = (DomainWorkflowStepEntity) o;
        return Objects.equals(stepNumber, that.stepNumber) &&
                Objects.equals(stepName, that.stepName) &&
                Objects.equals(stepState, that.stepState) &&
                Objects.equals(featureList, that.featureList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepNumber, stepName, stepState, featureList);
    }
}