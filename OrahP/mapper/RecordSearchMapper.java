
package com.cym.modules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cym.modules.bean.Record;
import com.cym.modules.bean.StudentInfo;

public interface RecordSearchMapper {
	public List<Record> rsearch(@Param("studentName") String studentName); // 返还整数看是否存成功？这啥阿 public

	public int addRecord(Record record);

}
