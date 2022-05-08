package com.cym.modules.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.Base64.Encoder;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cym.config.SessionContent;
import com.cym.modules.bean.Record;
import com.cym.modules.bean.ResultVo;
import com.cym.modules.bean.StudentInfo;
import com.cym.modules.bean.User;
import com.cym.modules.constants.GoogleConstans;
import com.cym.modules.constants.SysCodeConstans;
import com.cym.modules.mapper.RecordSearchMapper;
import com.cym.modules.mapper.StudentSearchMapper;
import com.cym.modules.mapper.UserMapper;
import com.cym.modules.service.ImgInfoService;
import com.cym.modules.utils.CommonUtil;
import com.cym.modules.utils.RedisUtil;
import com.cym.modules.utils.RestTemplateUtil;

@RestController
@RequestMapping("/app/student")
public class StudentSearchController {
	
	private static final Logger log = LoggerFactory.getLogger(ImgInfoController.class);


	@Autowired
	private StudentSearchMapper studentMapper;
	@Autowired
	private RecordSearchMapper recordMapper;
	
	
	@RequestMapping("/getStudentDataByName")
	public ResultVo search(String name) {
		List<StudentInfo> result = studentMapper.search(name);
		return ResultVo.success(result);	
	}
	@RequestMapping("/addStudentRecord")
	public ResultVo addRecord(@RequestBody JSONObject param, HttpServletRequest req, HttpServletResponse res) throws IOException {
		String sname = param.getString("student_name");
		String rtype = param.getString("record_type");
		String rcontent = param.getString("rcontent");
		Cookie[] cookies = req.getCookies();
		Map<String, Object> dataMap = CommonUtil.getDataMap(cookies);
		String sessionId = (String) dataMap.get("JSESSIONID");
		HttpSession session = SessionContent.getInstance().getSession(sessionId);
		User user;
		try {
			user = (User)session.getAttribute("user");
			if(user == null){
				res.sendRedirect("/h5/anneslogin.html");
				return null;
			}
		} catch (Exception e) {
			res.sendRedirect("/h5/anneslogin.html");
			return null;
		}
		String tname = user.getUsername();
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = s.format(date);
		Record record = new Record(null, sname, rtype, rcontent, tname, format, format);
		recordMapper.addRecord(record);
		return ResultVo.success();
	}
	
}
