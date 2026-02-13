package com.sandrew.publish.core.result;


import lombok.Data;

/**
 * Created by summer on 2019/12/18.
 */
@Data
public class JsonResult<T>
{
    private boolean result;

    private String msg;

    private Object data;

    public JsonResult<T> requestSuccess(T data)
    {
        this.result = true;
        this.msg = "";
        this.data = data;
        return this;
    }

    public JsonResult<T> requestSuccess()
    {
        this.result = true;
        this.msg = "";
        return this;
    }

    public JsonResult<T> requestFailure(String errMsg)
    {
        this.result = false;
        this.msg = errMsg;
        return this;
    }
}
