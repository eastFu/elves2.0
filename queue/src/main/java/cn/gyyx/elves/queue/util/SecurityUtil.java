package cn.gyyx.elves.queue.util;

import java.util.UUID;

public class SecurityUtil {

	// 用于生成密钥的常量字符串
	private static final String KEY = "GYYX"; 

	/**
	 * @Title: getOpenId
	 * @Description: 获取openId,应用唯一
	 * @return String 返回类型
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String openId = uuid.toString().toUpperCase();
		return openId.replace("-", "");
	}

	/**
	 * @Title: getUniqueKey
	 * @Description: 通过应用的openId,生成应用的密钥
	 *               <p>原则：（KEY+openId） md5加密字符串 </p>
	 * @return String    返回类型
	 */
	public static String getUniqueKey() {
		String plain = SecurityUtil.KEY + getUUID();
		String key = MD5Utils.MD5(plain);
		return key.substring(8, 24);
	}
	
}
