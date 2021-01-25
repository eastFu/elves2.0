package cn.gyyx.elves.api.vo;

import cn.gyyx.elves.core.enums.Errorcode;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author East.F
 * @ClassName: ElvesResponse
 * @Description: The value object for elves-api response
 * @date 2019/4/28 17:16
 */
public class ElvesResponse implements Serializable {

    private static final String SUCCESS_FLAG = "true";

    private static final String ERROR_FLAG = "false";

    /**
     * false/true
     */
    private String flag;
    private String error;
    private Object result;

    private ElvesResponse(){
    }

    public static ElvesResponse newErrorInstance(Errorcode code){
        return  new ElvesResponse().setFlag(ElvesResponse.ERROR_FLAG).setError(code.getValue());
    }

    public static ElvesResponse newSuccessInstance(Object data){
        return  new ElvesResponse().setFlag(ElvesResponse.SUCCESS_FLAG).setError("").setResult(data);
    }

    public String getFlag() {
        return flag;
    }

    public ElvesResponse setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public String getError() {
        return error;
    }

    public ElvesResponse setError(String error) {
        this.error = error;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ElvesResponse setResult(Object result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
