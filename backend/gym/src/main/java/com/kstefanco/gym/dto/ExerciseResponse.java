package com.kstefanco.gym.dto;

import java.util.UUID;

public record ExerciseResponse(

        UUID id,
        String name,
        String muscleGroup

) {
}