package cn.edu.scu.exception;

/**
 * @author TZQ
 * @Description TODO
 */
public class ProjectNotFoundException extends RuntimeException {
    private static final String MSG = "Project not found!";

    public ProjectNotFoundException() {
        super(MSG);
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return MSG;
    }
}
