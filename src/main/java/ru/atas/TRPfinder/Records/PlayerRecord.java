package ru.atas.TRPfinder.Records;

import jakarta.validation.constraints.NotNull;

public record PlayerRecord(
        @NotNull
        Long id,

        @NotNull
        String name

){
}
