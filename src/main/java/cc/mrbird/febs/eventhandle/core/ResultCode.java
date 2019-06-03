package cc.mrbird.febs.eventhandle.core;


/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author wx
 * @date 2019/01/15
 */
public enum ResultCode {
    //成功
    SUCCESS(100),
    //失败
    FAIL(99);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
