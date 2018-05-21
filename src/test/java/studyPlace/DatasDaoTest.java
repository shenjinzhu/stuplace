package studyPlace;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stu.bean.Datas;
import com.stu.dao.DatasDao;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:applicationContext.xml" }) // 加载配置文件
public class DatasDaoTest {

	@Autowired
	private DatasDao dao;

	@Test
	public void testSave() {
		Datas data = new Datas();
		data.setTitle("12");
		data.setAuthor("12");
		data.setCai(11);
		data.setUrl("1232");
		data.setCan_use(1000);
		data.setDisplay("22");
		data.setDown_time(22);
		data.setSize(12);
		data.setFile_id("1441");
		data.setUp_time(new Date(System.currentTimeMillis()));
		data.setUseful(true);
		data.setZan(10);
		dao.insertDatas(data);
	}

	// @Test
	public void testDelete() {
		// dao.delete("1441");
	}

}
