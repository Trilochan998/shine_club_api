package com.shineclub.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shineclub.api.entity.Student;
@Repository
public interface StudentParticipatationRepo extends JpaRepository<Student, Long>{

	Optional<Student> getByPhone(String mobileNo);


}
