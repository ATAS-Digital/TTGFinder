package ru.atas.TRPfinder.Records;

import jakarta.validation.constraints.NotNull;
import ru.atas.TRPfinder.Entities.Role;

public record EventRegistrationRecord (
        @NotNull
        Long playerId,

        @NotNull
        Long gameId,

        String role
){
}
