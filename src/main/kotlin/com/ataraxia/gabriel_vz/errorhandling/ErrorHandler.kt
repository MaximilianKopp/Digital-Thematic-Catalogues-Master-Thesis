package com.ataraxia.gabriel_vz.errorhandling

import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun workNotFoundException(servletRequest: HttpServletRequest, exception: Exception):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("Work not found", exception.message!!), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(JsonParseException::class)
    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("JSON Error", exception.message ?: "invalid JSON"),
                HttpStatus.BAD_REQUEST)
    }
}