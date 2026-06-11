package com.kstefanco.gym.repository;

import com.kstefanco.gym.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}