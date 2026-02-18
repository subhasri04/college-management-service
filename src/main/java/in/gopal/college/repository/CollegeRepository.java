package in.gopal.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gopal.college.entity.College;

public interface CollegeRepository extends JpaRepository<College, Long>{


}
