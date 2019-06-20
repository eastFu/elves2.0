package cn.gyyx.elves.console.utils;


import cn.gyyx.elves.console.domain.Page;
import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.enums.ResultEnum;

import java.util.Map;

public class ResultUtil {

    public static Result<Object> successPage(Object object, Page page) {
        Result<Object> result = new Result<Object>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        result.setPage(page);
        return result;
    }

    public static Result<Object> success(Object object) {
        Result<Object> result = new Result<Object>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    
    
    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> success(Integer code,Object object) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result<Object> error(ResultEnum e) {
        Result<Object> result = new Result<Object>();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }

    public static Result<Object> error(String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }

    public static Result<Object> successForPage(Object object,Map<String,Object> map) {
        Result<Object> result = new Result<Object>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);

        if(map != null){
            Object pageObj = map.get("page");
            if(pageObj != null && pageObj instanceof  Page){
                result.setPage((Page)pageObj);

            }
        }
        return result;
    }
}
