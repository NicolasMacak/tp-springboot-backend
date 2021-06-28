package backend.tpservices.Modules.General.Exceptions.Declarations;

public class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String model, String fields) {
        super("Model: "+model+" fields: "+fields);
    }
}
