package testJsonFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import top.idalin.entity.T_MALL_CLASS_1;
import top.idalin.mapper.T_MALL_CLASS_1_mapper;
/**
 * 生成静态文件
 * 将java文件转换成js静态文件
 */
class TestClass1 {

	@Test
	void test() throws Exception {
		// 1. 获取SQLSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 2. 获取session
		SqlSession session = sqlSessionFactory.openSession();
		// 3. 获取mapper
		T_MALL_CLASS_1_mapper mapper = session.getMapper(T_MALL_CLASS_1_mapper.class);
		// 4. 操作获取数据
		List<T_MALL_CLASS_1> list = mapper.getList();
		System.out.println(list.size());
		// 5. 创建gson对象
		Gson gson = new Gson();
		
		// 6. 转json
		String jsonStr = gson.toJson(list);
		// 7. 生成静态文件
		FileOutputStream out = new FileOutputStream("e:/class1.js");
		out.write(jsonStr.getBytes());
		out.close();
	}

}
