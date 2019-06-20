package cn.gyyx.elves.console.exception;


import cn.gyyx.elves.console.enums.ResultEnum;

public class AppException extends RuntimeException{

    private static final long serialVersionUID = -2147107150574486659L;
    private Integer code;

    public AppException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
    public AppException(String msg) {
        super(msg);
        this.code = -1;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
