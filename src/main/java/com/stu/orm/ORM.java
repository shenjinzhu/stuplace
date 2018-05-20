package com.stu.orm;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * @ClassName: ORM 
 * @Description: 根据字段生成POJO对象
 * @author Jin 
 * @date 2016年3月4日 下午1:48:13  
 */
public class ORM {
	private static List<Class<?>> classes = null;
	static {
		if(classes == null) {
			classes = new ArrayList<Class<?>>();
		}
		register(Boolean.class);
		register(Long.class);
		register(Date.class);
		register(Integer.class);
		register(Short.class);
		register(Byte.class);
		register(Double.class);
		register(String.class);
		register(Timestamp.class);
		register(BigDecimal.class);
		register(Boolean.TYPE);
		register(Byte.TYPE);
		register(Short.TYPE);
		register(Integer.TYPE);
		register(Long.TYPE);
		register(Float.TYPE);
		register(Double.TYPE);
	}
	
	
	/** 
	 * 构造方法
	 */
	private ORM() {}
	
	/**
	 * 通过反射生成POJO对象
	 * @Title: generatePOJO 
	 * @param clazz
	 * @param fields
	 * @param objs
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> generatePOJO(Class<T> clazz, String fields, List<?> list) throws Exception {
		List<T> result = new ArrayList<T>();
		
		for(Object obj: list) {
			Object[] objs = (Object[]) obj;
//			Object instance = clazz.newInstance();
			Object instance = BeanFactory.getBean(clazz);
			String[] attrs = fields.replaceAll(" ", "").split(",");
			
			for(int i = 0; i < attrs.length; i ++) {
				Method method = getMethod(clazz, "set" + captureName(attrs[i]));
				Class<?>[] clz = method.getParameterTypes();
				boolean isPadding = false;
				if(objs[i] == null) {
					isPadding = true;
					objs[i] = getPaddingValue(clz[0]);
					if(objs[i] == null)
						continue;
				}
				if(!isPadding) {
					try {
						if(clz[0] == Byte.class) objs[i] = ((BigDecimal) objs[i]).byteValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Short.class) objs[i] = ((BigDecimal) objs[i]).shortValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Integer.class) objs[i] = ((BigDecimal) objs[i]).intValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Long.class) objs[i] = ((BigDecimal) objs[i]).longValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Float.class) objs[i] = ((BigDecimal) objs[i]).floatValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Double.class) objs[i] = ((BigDecimal) objs[i]).doubleValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Byte.TYPE) objs[i] = ((BigDecimal) objs[i]).byteValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Short.TYPE) objs[i] = ((BigDecimal) objs[i]).shortValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Integer.TYPE) objs[i] = ((BigDecimal) objs[i]).intValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Long.TYPE) objs[i] = ((BigDecimal) objs[i]).longValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Float.TYPE) objs[i] = ((BigDecimal) objs[i]).floatValue();
					} catch(Exception e) {}
					try {
						if(clz[0] == Double.TYPE) objs[i] = ((BigDecimal) objs[i]).doubleValue();
					} catch(Exception e) {}
					
					if(objs[i] instanceof Clob) objs[i] = ClobToString((Clob) objs[i]);
				}
//				log.info(method.getName() + "\t" + objs[i] + "\t" + (objs[i] instanceof Byte) + "\t" + clz[0]);
				method.invoke(instance, objs[i]);
			}
			result.add((T) instance);
		}
		return result;
	}
	
	/** 
	 * 数据库字段转换为类对象字段
	 * @param str
	 * @return String
	 */
	public static String captureName(String str) {
		char[] cs = str.toLowerCase().toCharArray();
		for(int i = 0; i < cs.length; i ++) {
			if(cs[i] == '_') {
				//排除_1这种情况
				if(cs[i + 1] > '9') {
					cs[i + 1] -= 32;
				}
			}
		}
	    cs[0] -= 32;
	    return String.valueOf(cs).replaceAll("_", "");
	}
	
	/**
	 * 注册要反射生成的对象类
	 * @param clazz
	 * @return void
	 */
	public static void register(Class<?> clazz) {
		if(clazz != null) {
			if(!classes.contains(clazz)) {
				classes.add(clazz);
			}
		}
	}
	
	//探测参数
	public static Method getMethod(Class<?> clazz, String methodName) throws Exception {
		Method method = null;
		try {
			method = clazz.getMethod(methodName);
		} catch(Exception e) {
			for(Class<?> claz: classes) {
				//探测对象类型
				try {
					method = clazz.getMethod(methodName, claz);
					break;
				} catch(Exception e1) {
//					//探测九中基础类型
					if(classes.indexOf(claz) == classes.size() - 1)
						throw e1;
				}
			}
		}
		return method;
	}
	
	/** 
	 * 数据库 Clob转字符串
	 * @param clob
	 * @return String
	 */
	private static String ClobToString(Clob clob) {
		String result = "";
		if(clob != null) {
			try {
				result = clob.getSubString(1, (int) clob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/** 
	 * 获取不同类型的填充值
	 * @param clazz
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getPaddingValue(Class<T> clazz) {
		T result = null;
		Object obj = null;
		if(clazz == Byte.class) obj = new Byte((byte) -128);
		else if(clazz == Short.class) obj = new Short((short) -128);
		else if(clazz == Integer.class) obj = new Integer(-128);
		else if(clazz == Long.class) obj = new Long(-128);
		else if(clazz == Float.class) obj = new Float(-128);
		else if(clazz == Double.class) obj = new Double(-128);
		else if(clazz == Boolean.class) obj = new Boolean(false);
		else if(clazz == BigDecimal.class) obj = new BigDecimal(-128);
		else if(clazz == String.class) obj = new String("--");
		else if(clazz == Date.class) obj = new Date(0);
		else if(clazz == Timestamp.class) obj = new Timestamp(0);
		else if(clazz == Byte.TYPE) obj = -128;
		else if(clazz == Short.TYPE) obj = -128;
		else if(clazz == Integer.TYPE) obj = -128;
		else if(clazz == Long.TYPE) obj = -128;
		else if(clazz == Float.TYPE) obj = -128;
		else if(clazz == Double.TYPE) obj = -128;
		else if(clazz == Boolean.TYPE) obj = false;
		result = (T) obj;
		return result;
	}
}
