package de.gast.activityrecord.controller.exceptionHandler;

import de.gast.activityrecord.controller.ActivityRecordController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mateusz-warzyc.
 */
@ControllerAdvice(basePackageClasses = ActivityRecordController.class)
public class ActivityRecordExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityRecordExceptionHandler.class);
    private static final String VALIDATION_MESSAGE_TEMPLATE = "Error field: {0} - message: {1}";
    private static final String UNEXPECTED_ERROR_MESSAGE_TEMPLATE = "Unexpected error: {0}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationErrors(final MethodArgumentNotValidException ex) {
        LOGGER.error("one or some of the parameters is missing, or empty");
        List<String>  errors = ex.getBindingResult().getFieldErrors().stream()
                .map( e -> MessageFormat.format(VALIDATION_MESSAGE_TEMPLATE,e.getField(),e.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleRequestParamValidationErrors(final ConstraintViolationException ex) {
        LOGGER.error("one or some of the parameters is missing, or empty");
        List<String> errors = ex.getConstraintViolations().stream()
                .map( e -> MessageFormat.format(VALIDATION_MESSAGE_TEMPLATE, e.getPropertyPath(),e.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity handleEncodingExceptionErrors(final UnsupportedEncodingException ex) {
        LOGGER.error("Wrong encoding for request parameters..");
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(final Exception ex) {
        LOGGER.error("Unexpected error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageFormat.format(UNEXPECTED_ERROR_MESSAGE_TEMPLATE, ex.getMessage()));
    }
}
