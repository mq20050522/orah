package com.cym.modules.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.Base64.Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cym.config.SessionContent;
import com.cym.modules.bean.ResultVo;
import com.cym.modules.bean.User;
import com.cym.modules.constants.GoogleConstans;
import com.cym.modules.constants.SysCodeConstans;
import com.cym.modules.mapper.UserMapper;
import com.cym.modules.service.ImgInfoService;
import com.cym.modules.utils.CommonUtil;
import com.cym.modules.utils.RedisUtil;
import com.cym.modules.utils.RestTemplateUtil;

@RestController
@RequestMapping("/app/auth")
public class GoogleAuthController {
	
	private static final Logger log = LoggerFactory.getLogger(ImgInfoController.class);

	@Value("${google_login}")
	private String googleAuthUrl;
	
	@Value("${google_get_token}")
	private String googleTokenUrl;
	
	@Value("${google_get_userinfo}")
	private String userInfoUrl;
	
	@Autowired
	private RestTemplateUtil client;
	
//	@Autowired
//	private GoogleOauthService authService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ImgInfoService imgInfoService;
	
	@Autowired
	private UserMapper userMapper;
	/**
	 * author: ymchen
	 * 功能描述：获取认证URL，并由前端完成引导用户到Goole登录页
	 * @return
	 */
	@RequestMapping("/sendLogin")
	public String sendLogin(HttpServletRequest req){
		//设置state放在重放攻击
		String state = UUID.randomUUID().toString().replaceAll("-", "");
		HttpSession session = req.getSession();
		session.setAttribute("state", state);
		SessionContent.getInstance().AddSession(session);
		
		Map<String, Object> params = new HashMap<>();
		params.put("client_id", GoogleConstans.clientId);
		params.put("redirect_uri", GoogleConstans.redirectUrl);
		params.put("response_type", GoogleConstans.responseType);
		params.put("scope", GoogleConstans.scopes);
		params.put("access_type", GoogleConstans.accessType);
		params.put("state", state);
		String url = googleAuthUrl;
		return getRedirectUrl(url,params);
	}
	
	public String getRedirectUrl(String url,Map<String,Object> params){
		StringBuffer result = new StringBuffer();
		result.append(url);
		result.append("?");
		Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Object> next = iterator.next();
			result.append(next.getKey()+"=");
			result.append(next.getValue()+"&");
		}
		result.delete(result.length()-1, result.length());
		return result.toString();
	}
	
	
	/**
	 * author: ymchen
	 * 功能描述：Google回调Url，用户登录成功，回调我们接口给用户授权
	 * params: code：用户授权码，state：防止重放攻击唯一随机码
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/callBack")
	public String toLogin(String code,String state,HttpServletRequest req,HttpServletResponse res) throws Exception{
		log.info("Google回调成功，入参[code=" + code + ",state=" + state + "]");
		
		//如果用户已登录，则不获取access_token直接返回
		Cookie[] cookies = req.getCookies();
		Map<String, Object> dataMap = CommonUtil.getDataMap(cookies);
		String sessionId = (String) dataMap.get("JSESSIONID");
		HttpSession session = SessionContent.getInstance().getSession(sessionId);
		User user = (User) session.getAttribute("user");
		if(user != null){
			res.sendRedirect("/h5/studentSearch.html");
			return null;
		}
		String sessionState = session.getAttribute("state").toString();
		if(StringUtils.isNotBlank(state) && state.equals(sessionState)){
			String token = null;
			
			//获取access_token
			String url = googleTokenUrl;
			Map<String, Object> body = new HashMap<>();
			body.put("client_id", GoogleConstans.clientId);
			body.put("client_secret", GoogleConstans.clientSecret);
			body.put("code", code);
			body.put("grant_type", "authorization_code");
			body.put("redirect_uri", GoogleConstans.redirectUrl);
			JSONObject result = client.clientRequest(url,HttpMethod.POST, body, new HashMap<>());
			log.info("获取access_token，结果="+result.toJSONString());
			token = result.getString("access_token");
			String token_type = result.getString("token_type");
			//保存用户的access_token和刷新token
			//保存刷新并设置过期时间，存储刷新token永久保存
			
			//通过access_token获取用户基本信息
			String userUrl = userInfoUrl;
			Map<String, String> header = new HashMap<>();
			header.put("Authorization", token_type+" "+token);
			JSONObject userInfoResult = client.clientRequest(userUrl,HttpMethod.GET, new HashMap<>(), header);
			log.info(userInfoResult.toJSONString());
			User user2 = new User();
			String name = userInfoResult.getString("name");
			String email = userInfoResult.getString("email");
			String id = userInfoResult.getString("id");
			user2.setEmail(email);
			user2.setUsername(name);
			user2.setPassword(id);
			byte[] uid = (user2.getId() + "-" + user2.getEmail()).getBytes();
			Encoder encoder = Base64.getEncoder();
			String encodeUid = encoder.encodeToString(uid);
			user2.setUid(encodeUid);
			
			ResultVo vo = imgInfoService.userRegister(user2);
			if("0".equals(vo.getCode()) || SysCodeConstans.MOBILE_EXIST.equals(vo.getCode())){
				User checkMobile = userMapper.checkMobile(email);
				HttpSession sessionUser = req.getSession();
				sessionUser.setAttribute("user", checkMobile);
				SessionContent.getInstance().AddSession(sessionUser);
				res.sendRedirect("/h5/studentSearch.html");
				return null;
			}
			res.sendRedirect("/h5/404.html");
			//注册用户
//			ResultVo r = authService.registerAndLogin(userInfoResult);
			
			//判断注册结果
			
			//注册成功后，设置登录状态
		}
		return null;
	}

	/*
	 * public static void main(String[] args) { C:\Users\JG\git\orah\data }
	 */
	
}
