package com.kangbakso.spring_tutorial.repository.redis;

import com.kangbakso.spring_tutorial.entity.redis.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Object> {
}
