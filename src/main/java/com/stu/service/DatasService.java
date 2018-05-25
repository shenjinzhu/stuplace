package com.stu.service;

import java.util.List;

import com.stu.bean.Datas;

public interface DatasService {

	void save(Datas data);

	void update(Datas data);

	Datas findOne(String file_id);

	List<Datas> find(Datas data);
}
