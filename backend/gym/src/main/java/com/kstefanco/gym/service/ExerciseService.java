package com.kstefanco.gym.service;

import com.kstefanco.gym.dto.CreateExerciseRequest;
import com.kstefanco.gym.dto.ExerciseResponse;
import com.kstefanco.gym.entity.Exercise;
import com.kstefanco.gym.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseResponse createExercise(CreateExerciseRequest request){
        Exercise exercise = Exercise.builder().name(request.name()).muscleGroup(request.muscleGroup()).build();

        Exercise saved = exerciseRepository.save(exercise);

        return new ExerciseResponse(saved.getId(), saved.getName(), saved.getMuscleGroup());
    }

    public List<ExerciseResponse> getExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(exercise ->
                        new ExerciseResponse(
                                exercise.getId(),
                                exercise.getName(),
                                exercise.getMuscleGroup()
                        )
                )
                .toList();
    }



}
