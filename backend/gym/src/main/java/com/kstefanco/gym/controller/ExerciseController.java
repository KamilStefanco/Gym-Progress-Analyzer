package com.kstefanco.gym.controller;

import com.kstefanco.gym.dto.CreateExerciseRequest;
import com.kstefanco.gym.dto.ExerciseResponse;
import com.kstefanco.gym.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ExerciseResponse createExercise(@Valid @RequestBody CreateExerciseRequest request){
        return exerciseService.createExercise(request);
    }

    @GetMapping
    public List<ExerciseResponse> getExercises() {
        return exerciseService.getExercises();
    }

}
