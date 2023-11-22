package test.tools.selenium.extensions;

public class SeleniumJupiterException extends RuntimeException {

    private static final long serialVersionUID = -7026228903533825338L;

    public SeleniumJupiterException(String message) {
        super(message);
    }

    public SeleniumJupiterException(Throwable cause) {
        super(cause);
    }

    public SeleniumJupiterException(String message, Throwable cause) {
        super(message, cause);
    }

}
