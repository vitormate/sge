package com.rokaly.sge.repository;

import com.rokaly.sge.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
