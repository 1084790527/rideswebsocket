
package com.example.rideswebsocket.bean;
import java.io.Serializable;

/**
 * 
 * 登陆用户信息
 * 
 * @author
 *
 */
public class LoginUserInfo implements Serializable {

	/** 序列版本号 */
	private static final long serialVersionUID = 27422145269547314L;

	private int id;
	private String name;
	private int roleType;
	private String loginName;

	public LoginUserInfo(PUserData pUserData) {
		this.id = pUserData.getId();
		this.name = pUserData.getName();
		this.roleType = pUserData.getRoleType();
		this.loginName = pUserData.getLoginName();
	}

	@Override
	public String toString() {
		return "LoginUserInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", roleType=" + roleType +
				", loginName='" + loginName + '\'' +
				'}';
	}


	public static Integer getId(){
		LoginUserInfo userInfo = threadLocal.get();
		if (null != userInfo) {
			return userInfo.getIds();
		}
		return null;
	}

	public static String getName(){
		LoginUserInfo userInfo = threadLocal.get();
		if (null != userInfo && userInfo.getNames() != null) {
			return userInfo.getNames();
		}
		return null;
	}

	public static Integer getRoleType(){
		LoginUserInfo userInfo = threadLocal.get();
		if (null != userInfo && userInfo.getRoleTypes() != 0) {
			return userInfo.getRoleTypes();
		}
		return null;
	}

	public static String getLoginName(){
		LoginUserInfo userInfo = threadLocal.get();
		if (null != userInfo && userInfo.getLoginNames() != null) {
			return userInfo.getLoginNames();
		}
		return null;
	}

	public int getIds() {
		return id;
	}

	public String getNames() {
		return name;
	}

	public int getRoleTypes() {
		return roleType;
	}

	public String getLoginNames() {
		return loginName;
	}

	private static ThreadLocal<LoginUserInfo> threadLocal = new ThreadLocal<LoginUserInfo>() {
		protected LoginUserInfo initialValue() {
			return null;
		}
	};

	public static LoginUserInfo getLoginUserInfo() {
		return threadLocal.get();
	}

	/**
	 * 设定登陆用户信息
	 *
	 * @param userInfo
	 */
	public static void setLoginUserInfo(LoginUserInfo userInfo) {
		threadLocal.set(userInfo);
	}


	/**
	 * 清除线程
	 */
	public static void destroy() {
		threadLocal.remove();
	}


}
