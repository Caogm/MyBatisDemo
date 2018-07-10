package com.mybatis.mapper;

import java.util.List;

import com.mybatis.pojo.Student;

public interface StudentMapper {
	// 根据id查询用户信息
	public Student findStudentById(int id) throws Exception;

	// 根据用户名称返回用户列表
	public List<Student> findAllStudent(String name) throws Exception;

	// 插入用户
	public void insertStudent(Student student) throws Exception;

	// 更新用户
	public void updateStudent(Student student) throws Exception;

	// 删除用户
	public void deleteStudentById(int id) throws Exception;
}
