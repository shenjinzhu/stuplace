package studyPlace;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stu.bean.Datas;
import com.stu.bean.PageHelp;
import com.stu.dao.DatasDao;

@RunWith(SpringJUnit4ClassRunner.class) // ʹ��junit4���в���
@ContextConfiguration(locations = { "classpath:applicationContext.xml" }) // ���������ļ�
public class DatasDaoTest {

	@Autowired
	private DatasDao dao;

	// @Test
	public void testSave() {
		int i = 0;
		while (true) {
			Datas data = new Datas();
			data.setTitle("12" + i);
			data.setAuthor("12" + i);
			data.setCai(11 * i);
			data.setUrl("1232" + i);
			data.setCan_use(1000 * i);
			data.setDisplay("22" + i);
			data.setDown_time(22 * i);
			data.setSize(12 * i);
			data.setFile_id("1441" + i);
			data.setUp_time(new Date(System.currentTimeMillis()));
			data.setUseful(true);
			data.setZan(10);
			dao.insertDatas(data);
			if (i++ > 30) {
				break;
			}
		}
	}

	// @Test
	public void testup() {
		Datas data = new Datas();
		data.setTitle("12改");
		data.setAuthor("12改");
		data.setCai(11);
		data.setUrl("1232改");
		data.setCan_use(1000);
		data.setDisplay("22改");
		data.setDown_time(22);
		data.setSize(12);
		data.setFile_id("1441");
		data.setUp_time(new Date(System.currentTimeMillis()));
		data.setUseful(true);
		data.setZan(10);
		dao.updateDatas(data);
	}

	@Test
	public void findTest() {
		PageHelp p = new PageHelp();
		p.setPageNo(1);
		p.setPageSize(10);
		p.setFrom(10);
		p.setSort("desc");
		p.setSortField("title");
		Datas d = new Datas();
		d.setPage(p);
		List<Datas> list = dao.selectDatasByData(d);
		for (Datas l : list) {
			System.out.println(l);
		}
	}

}
