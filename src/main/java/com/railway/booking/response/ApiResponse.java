package com.railway.booking.response;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private Object error;
    private Object meta;


    public ApiResponse() {

    }
    public ApiResponse(boolean success, T data, Object error, Object meta) {
        this.success=success;
        this.data=data;
        this.error=error;
        this.meta=meta;
    }
}
