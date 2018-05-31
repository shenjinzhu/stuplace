package com.stu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.bean.Datas;
import com.stu.dao.DatasDao;

@Service
public class DatasServiceimpl implements DatasService {

	@Autowired
	private DatasDao dataDao;

	@Override
	public void save(Datas data) {
		dataDao.insertDatas(data);
	}

	@Override
	public void update(Datas data) {
		dataDao.updateDatas(data);
	}

	@Override
	public Datas findOne(String file_id) {
		return dataDao.selectDatasById(file_id);
	}

	@Override
	public List<Datas> find(Datas data) {
		return dataDao.selectDatasByData(data);
	}

	@Override
	public void deleteById(String file_id) {
		dataDao.deleteById(file_id);
	}

}
