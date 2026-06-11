package com.kstefanco.gym.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "exercise_sets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Integer reps;

    @Column(nullable = false)
    private Integer setOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;
}