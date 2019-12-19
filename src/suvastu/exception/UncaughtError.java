package suvastu.exception;

public class UncaughtError extends RuntimeException {

    private final String msg;

    public UncaughtError(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
