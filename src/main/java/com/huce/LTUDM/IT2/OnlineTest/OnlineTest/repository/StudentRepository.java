package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;

public interface StudentRepository extends CrudRepository<Student, String> {

}
