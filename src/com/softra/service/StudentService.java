package com.softra.service;

import com.softra.dao.StudentDao;
import com.softra.model.Student;

public class StudentService {
	StudentDao dao;
	
	public StudentService() {
		dao = new StudentDao();
	}
	
	public void saveStud(Student stu) {
		System.out.println("Inside Service Class");
		dao.create(stu);
	}
}
