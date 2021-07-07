package backend.tpservices.Modules.General.ResponseObjects;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

/*
    {
    "timestamp":"13-06-2021 06:31:36",
    "status":404,
    "error":"404 NOT FOUND",
    "message":"blabla",
    "localizedMessage":"nejaka strasne dlha message, ktora nikoho nezaujima"
    }

   dalsie mozne fieldy, ak by sme chceli:
   cause            // ex.getCause()
   stackTrace       // ex.getStackTrace()
*/

public class ErrorObject extends ResponseObject{

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
   private LocalDateTime timestamp;
   private int status;
   private String error;
   private String message;
   private String localizedMessage;

   public ErrorObject() {
       timestamp = LocalDateTime.now();
   }

   public ErrorObject(HttpStatus status) {
       this();
       this.status = status.value();
       this.error = status.toString();
       this.message = "No message available";
       this.localizedMessage = "No message available";
   }

    public ErrorObject(HttpStatus status, Throwable ex) {
       this();
       this.status = status.value();
       this.error = status.toString();
       this.message = ex.getMessage();
       this.localizedMessage = ex.getLocalizedMessage();
   }

   // -------------------------------------------------------------------------

    public int getStatus() {
        return status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public String getLocalizedMessage() {
        return localizedMessage;
    }
    public String getError() {
        return error;
    }
}