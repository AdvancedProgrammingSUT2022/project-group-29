package models;

public class Response {
    private int status_code;
    private String message;

    public Response(int status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
