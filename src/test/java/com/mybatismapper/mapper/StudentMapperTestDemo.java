package com.mybatismapper.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.mapper.StudentMapper;
import com.mybatis.pojo.Student;

public class StudentMapperTestDemo {
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

	@Test
	public void testFindStudentById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象，代理对象可以看作是一个接口实现类
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		Student student = studentMapper.findStudentById(3);
		System.out.println(student);
	}

	@Test
	public void testFindAllStudent() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		List<Student> list = studentMapper.findAllStudent("studenttest");
		for (Student student : list) {
			System.out.println(student);
		}
	}

	@Test
	public void testInsertStudent() throws Exception {
		// 创建插入数据对象
		Student student = new Student();
		student.setName("中文测试1");
		student.setEmail("stumapper@163.com");
		student.setDob(new Date());
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

		studentMapper.insertStudent(student);
		// mapper代理方式，默认不自动提交，需手动打开提交
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testUpdateStudent() throws Exception {
		// 创建更新数据对象
		Student student = new Student();
		student.setStud_id(2);
		student.setName("change2");
		student.setEmail("change2@163.com");
		student.setDob(new Date());
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

		studentMapper.updateStudent(student);
		// mapper代理方式，默认不自动提交，需手动打开提交
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testDeleteStudentById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象，代理对象可以看作是一个接口实现类
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		studentMapper.deleteStudentById(5);
		// mapper代理方式，默认不自动提交，需手动打开提交
		sqlSession.commit();
		sqlSession.close();
	}
}
