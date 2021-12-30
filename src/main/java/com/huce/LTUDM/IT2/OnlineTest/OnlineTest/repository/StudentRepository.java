package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {

}
