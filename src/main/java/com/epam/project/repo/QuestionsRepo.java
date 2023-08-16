package com.epam.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.project.entity.Question;

@Repository
public interface QuestionsRepo extends JpaRepository<Question, Integer> {

}
