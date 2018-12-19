package com.interestgroup.microservicepaper.Repository;

import com.interestgroup.microservicepaper.model.ExaminationPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationPaperRepository extends JpaRepository<ExaminationPaper, Long> {
}
