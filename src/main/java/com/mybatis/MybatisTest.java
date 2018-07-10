package com.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.pojo.Student;

public class MybatisTest {

	private SqlSessionFactory sqlSessionFactory;

	// 创建工厂
	@Before
	public void init() throws IOException {

		// 配置文件 mybatis-config.xml
		String resource = "mybatis-config.xml";
		// 加载配置文件到输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	// 测试根据id查询用户（单条）
	@Test
	public void findStudentById() {
		// 通过SqlSessionFactory创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过sqlSession操作数据库
		Student student = null;
		try {
			student = sqlSession.selectOne("com.mybatis.mappers.StudentMapper.findStudentById", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭sqlsession
			sqlSession.close();
		}

		System.out.println(student);
	}

}
