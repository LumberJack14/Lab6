package Utils;

import java.io.Serializable;

public class Response implements Serializable {

    private ResponseStatus status;
    private String data;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Response(ResponseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status.toString();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
