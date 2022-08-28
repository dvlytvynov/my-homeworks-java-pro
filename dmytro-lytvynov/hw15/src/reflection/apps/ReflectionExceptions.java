package reflection.apps;

public class ReflectionExceptions extends Exception{
    public ReflectionExceptions(String message) {
        super(message);
    }

    public ReflectionExceptions(Throwable cause) {
        super(cause);
    }

    public ReflectionExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
