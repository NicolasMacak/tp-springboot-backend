package backend.tpservices.Controllers;
import backend.tpservices.Models.General.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.rmi.UnexpectedException;

//Ak bude vyhodena niektora Exception, automaticky sa k nej priradi handler

@RestControllerAdvice
public class GlobalErrorsController {

  //NoSuchObjectException => 404 not found
  @ExceptionHandler
  public ResponseEntity<ErrorObject> objectNotFoundHandler(NoSuchObjectException ex) {
    ErrorObject error = new ErrorObject(HttpStatus.NOT_FOUND, ex);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  //NoResultException => 204 no content
  @ExceptionHandler
  public ResponseEntity<ErrorObject> noDataFoundHandler(NoResultException ex) {
    ErrorObject error = new ErrorObject(HttpStatus.NO_CONTENT, ex);
    return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
  }

  //UnexpectedException => 500 internal server error
  @ExceptionHandler
  public ResponseEntity<ErrorObject> internalServerErrorHandler(UnexpectedException ex) {
    ErrorObject error = new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  //InvalidObjectException => 400 bad request
  @ExceptionHandler
  public ResponseEntity<ErrorObject> badRequestHandler(InvalidObjectException ex){
    ErrorObject error = new ErrorObject(HttpStatus.BAD_REQUEST, ex);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  //NullPointerException => 500 internal server error
  @ExceptionHandler
  public ResponseEntity<ErrorObject> niecoSaPosraloHandler(NullPointerException ex) {
    ErrorObject error = new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}