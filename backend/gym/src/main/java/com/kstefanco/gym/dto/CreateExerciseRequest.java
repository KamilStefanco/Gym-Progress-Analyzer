package com.kstefanco.gym.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateExerciseRequest(

        @NotBlank
        String name,

        @NotBlank
        String muscleGroup

) {
}