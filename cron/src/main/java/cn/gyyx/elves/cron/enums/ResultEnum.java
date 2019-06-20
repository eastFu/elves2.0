/**
 * @Title:  ResultEnum.java
 * @Package cn.gyyx.app.enums
 * @Description: Result 枚举类
 * @author: zhangguangxin@gyyx.cn
 * @date:   2017年12月4日 下午1:22:22
 * @version V1.0
 */
package cn.gyyx.elves.cron.enums;

public enum ResultEnum {

    SUCCESS(0, "成功"),
    ERROR_UNKONW(-1, "未知错误"),

    ERROR_USER_PASSWORD_ERROR(101, "密码不正确"),
    ERROR_USER_NONENTITY(102, "用户名不存在"),
    ERROR_USER_EXITED(103, "用户名已存在"),
    ERROR_USER_DELETE(104, "用户删除失败"),
    ERROR_USER_FIND(105, "用户获取失败"),
    ERROR_USER_UPDATE(106, "用户更新失败"),
    ERROR_USER_PASSWORD_UPDATE(107, "密码更新失败"),
    ERROR_USER_INFO_ERROR(108, "用户名或密码错误"),

    ERROR_TOKEN_ILLEGAL(201, "不合法的Authorization header"),
    ERROR_TOKEN_INVALID(202, "无效的token"),
    ERROR_TOKEN_TIMEOUT(203, "token已超时"),
    ERROR_NO_LOGIN(204, "未登陆"),
    ERROR_AUTH_VALID(301, "没有此资源访问权限"),
    
    ERROR_ROLENAME_EXITED(401, "角色名称已存在"),



    ERROR_CRON_EXISTED(501, "该定时任务已经存在"),
    ERROR_CRON_NOT_EXISTED(502, "该定时任务不存在"),
    ERROR_CRON_RESULT_ISEMPTY(503, "该定时任务结果不存在"),
    ERROR_CRON_PARAM_NO_ID(504, "参数异常,缺少id"),
    ERROR_CRON_PARAM_NO_IP(504, "参数异常,缺少ip")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
