package com.tefal.requirement.exception.handler;


import com.tefal.requirement.dto.ErrorResponseDto;
import com.tefal.requirement.exception.ProjectNotFoundException;
import com.tefal.requirement.exception.RequirementNotFoundException;
import com.tefal.requirement.exception.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProjectNotFound(ProjectNotFoundException ex , HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneral(Exception ex , HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RequirementNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRequirementNotFound(RequirementNotFoundException ex , HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleUnAuthorizedAccessException(UnauthorizedAccessException ex , HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
