package com.cym.modules.constants;

public class SysCodeConstans {

	//操作成功
	public static final String SYS_SUCCESS_CODE = "0";
	
	//系统异常
	public static final String SYS_ERROR_CODE = "-100001";
	
	//参数不能为空
	public static final String PARAM_NOT_NULL = "-100002";

	//图片保存失败
	public static final String SAVE_PICTURE_ERROR = "-100003"; 
	
	//图片格式不支持
	public static final String IMG_SUFFIX_ERROR = "-100004"; 
	
	//图片过大
	public static final String IMG_SIZE_ERROR = "-100005"; 
	
	//注册失败
	public static final String REGISTER_ERROR = "-100006"; 
		
	//注册失败: 电话号已存在
	public static final String MOBILE_EXIST = "-100007"; 
	
	//登陆失败：电话号或密码不能为空
	public static final String BLANK_ERROR = "-100008"; 
	
	//电话号或密码有误
	public static final String LOGIN_ERROR = "-100009";
	
	//用户未登录
	public static final String UNLOGIN = "-1000010";
	
	//用户已点赞
	public static final String ALREADY_LIKED = "-1000011";
	
	//点赞失败
	public static final String  LIKE_ERROR= "-1000012";
	
	 //用户未点赞
	 public static final String LIKE_RECORD_UNEXIST = "-1000013";
	 
	//用户登录已过期
	public static final String LOGIN_EXPIRED = "-1000014";
	
	//系统繁忙，请重试
	public static final String SYS_TIMEOUT = "-1000015";
}


