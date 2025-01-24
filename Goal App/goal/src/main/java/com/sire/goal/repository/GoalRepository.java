package com.sire.goal.repository;

import com.sire.goal.models.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findByUserId(Integer userId);
}
