package co.simplon.csrf_exercise.csrf_exercise.dtos;

import jakarta.validation.constraints.NotBlank;

public record Login(@NotBlank String username, @NotBlank String password) {

}
