package com.mybatis.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.pojo.Student;

public class StudentDaoImplTest {

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

	// 根据id查询用户信息
	@Test
	public void testFindStudentById() throws Exception {

		StudentDao studentDao = new StudentDaoImpl(sqlSessionFactory);
		Student student = studentDao.findStudentById(1);
		System.out.println(student);
	}

	// 根据用户名称返回用户列表
	@Test
	public void testFindAllStudent() throws Exception {
		StudentDao studentDao = new StudentDaoImpl(sqlSessionFactory);
		List<Student> students = studentDao.findAllStudent("studenttest");
		for (Student stus : students) {
			System.out.println(stus);
		}
	}

	// 插入用户
	@Test
	public void insertStudent() throws Exception {
		// 创建插入数据对象
		Student student = new Student();
		student.setName("stu1");
		student.setEmail("1514@163.com");
		student.setDob(new Date());

		StudentDao studentDao = new StudentDaoImpl(sqlSessionFactory);
		studentDao.insertStudent(student);
	}
}
