package com.interestgroup.microservicequestion.Repository;

import com.interestgroup.microservicequestion.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
