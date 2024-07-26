package ai.shreds.Adapter;

import ai.shreds.Shared.SharedErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class AdapterExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SharedErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        SharedErrorResponseDTO errorResponse = new SharedErrorResponseDTO();
        errorResponse.setCode("400");
        errorResponse.setMessage("Bad Request: " + exception.getMessage());
        logger.error("Bad Request: ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<SharedErrorResponseDTO> handleNullPointerException(NullPointerException exception) {
        SharedErrorResponseDTO errorResponse = new SharedErrorResponseDTO();
        errorResponse.setCode("500");
        errorResponse.setMessage("Internal Server Error: Null Pointer Exception");
        logger.error("Internal Server Error: ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SharedErrorResponseDTO> handleException(Exception exception) {
        SharedErrorResponseDTO errorResponse = new SharedErrorResponseDTO();
        errorResponse.setCode("500");
        errorResponse.setMessage("Internal Server Error: " + exception.getMessage());
        logger.error("Internal Server Error: ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}