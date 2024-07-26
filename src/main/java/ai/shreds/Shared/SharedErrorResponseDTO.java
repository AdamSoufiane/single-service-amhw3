package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for error responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedErrorResponseDTO {
    /**
     * Error code.
     */
    private String code;

    /**
     * Error message.
     */
    private String message;
}