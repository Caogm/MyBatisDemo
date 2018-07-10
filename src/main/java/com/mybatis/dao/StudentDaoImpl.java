package com.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.pojo.Student;

public class StudentDaoImpl implements StudentDao {

	private SqlSessionFactory sqlSessionFactory;

	// 将SqlSessionFactory注入
	public StudentDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 根据id查询用户信息
	@Override
	public Student findStudentById(int id) {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 根据id查询用户
		Student student = sqlSession.selectOne("test.findStudentById", id);

		sqlSession.close();
		return student;
	}

	// 根据用户名称返回用户列表
	@Override
	public List<Student> findAllStudent(String name) throws Exception {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 根据用户名称返回用户列表
		List<Student> students = sqlSession.selectList("test.findAllStudent", name);
		sqlSession.close();
		return students;
	}

	// 插入用户
	@Override
	public void insertStudent(Student student) throws Exception {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 插入用户
		sqlSession.insert("test.insertStudent", student);
		sqlSession.commit();
		sqlSession.close();
	}

}
