package com.stu.orm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.stu.util.Tools;


/** 
 * @ClassName: BeanFactory 
 * @Description: Spring托管的Bean对象的创建工厂
 * @author 0xFF 
 * @date 2017年3月18日 上午10:01:17  
 */
public class BeanFactory {
	private static final Logger log = LoggerFactory.getLogger(BeanFactory.class);
	private static WebApplicationContext springContext;
	
	
	/** 
	 * 设置Spring上下文
	 * @param content
	 * @return void
	 */
	public static void setSpringContent(WebApplicationContext content) {
		springContext = content;
	}
	
	/** 
	 * 根据类获取Bean对象
	 * @param clazz
	 * @return T
	 */
	public static <T> T getBean(Class<T> clazz) {
		T result = null;
		if(clazz != null) {
			waitUntilInitialized();
			result = springContext.getBean(clazz);
		}
		return result;
	}
	
	/** 
	 * 根据bean名字获取Bean对象
	 * @param beanName
	 * @return Object
	 */
	public static Object getBean(String beanName) {
		Object result = null;
		if(beanName != null) {
			waitUntilInitialized();
			result = springContext.getBean(beanName);
		}
		return result;
	}
	
	/** 
	 * 等待Spring上下文初始化
	 * @Title waitUntilInitialized
	 * @return void
	 */
	private static void waitUntilInitialized() {
		if(springContext == null) {
			boolean print = false;
			while(springContext == null) {
				if(!print) {
					log.info("等待BeanFactory初始化...");
					print = true;
				}
				Tools.sleep(1);
			}
			if(print) {
				log.info("BeanFactory初始化完毕，退出等待");
			}
		}
	}
}
