package com.cym.modules.constants;

public class GoogleConstans {
	// 客户端 ID
	public static final String clientId = "185335662427-r8bd307n8nr7jg6e2mfp0okb8hmalgsv.apps.googleusercontent.com";
	
	public static final String clientSecret = "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";
	public static final String applicationName = "nope";
	//回调接口，说明：该值必须与您在客户端的 API 控制台凭据页面中配置的 OAuth 2.0 客户端的授权重定向 URI 之一完全匹配 
	public static final String redirectUrl = "http://localhost:8080/app/auth/callBack";
	public static final String scopes = "openid%20profile%20email";
	//确定 Google OAuth 2.0 端点是否返回授权代码
	public static final String responseType = "code";
	//用户设置为此值时;
	public static final String accessType = "offline";
}
