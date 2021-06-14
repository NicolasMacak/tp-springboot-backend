package backend.tpservices.Models.General;

import org.springframework.http.HttpStatus;

public class SuccessObject {
    private int status;
    private String message;

    public SuccessObject(){}

    public SuccessObject(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
