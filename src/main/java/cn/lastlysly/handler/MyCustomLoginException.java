package cn.lastlysly.handler;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-26 15:50
 **/
public class MyCustomLoginException extends Exception {
    public MyCustomLoginException() {
        super();
    }

    public MyCustomLoginException(String message) {
        super(message);
    }

    public MyCustomLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCustomLoginException(Throwable cause) {
        super(cause);
    }

    protected MyCustomLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
