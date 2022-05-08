package com.cym.modules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cym.modules.bean.StudentInfo;

public interface StudentSearchMapper {
	public List<StudentInfo> search(@Param("studentName") String studentName);
	
}
