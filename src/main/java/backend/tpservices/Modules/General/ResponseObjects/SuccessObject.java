package backend.tpservices.Modules.General.ResponseObjects;

import org.springframework.http.HttpStatus;

public class SuccessObject extends ResponseObject {
    private int status;
    private String message;
    private Object data;

    public SuccessObject(){}

    public SuccessObject(String message) {
        this.status = HttpStatus.OK.value();
        this.message = message;
    }

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
    public Object getObject() { return data; }
}
