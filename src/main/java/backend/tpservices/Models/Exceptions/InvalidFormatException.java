package backend.tpservices.Models.Exceptions;

public class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String model, String fields) {
        super("Model: "+model+" fields: "+fields);
    }
}
