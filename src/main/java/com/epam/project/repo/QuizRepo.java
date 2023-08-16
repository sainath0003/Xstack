package com.epam.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.project.entity.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
