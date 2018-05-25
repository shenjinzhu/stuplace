package com.stu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stu.bean.Datas;

public interface DatasDao {

	void insertDatas(Datas data);

	void updateDatas(Datas data);

	void deleteById(@Param("file_id") String file_id);

	Datas selectDatasById(@Param("file_id") String file_id);

	List<Datas> selectDatasByData(Datas data);
}
