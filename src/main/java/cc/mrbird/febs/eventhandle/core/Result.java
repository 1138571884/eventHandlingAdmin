package cc.mrbird.febs.eventhandle.core;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一API响应结果封装
 *
 * @author wx
 * @date 2019/01/15
 */
public class Result {

    @ApiModelProperty("响应code 100 成功 99失败")
    private int code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
