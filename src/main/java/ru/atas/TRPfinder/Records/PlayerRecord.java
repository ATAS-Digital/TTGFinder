package ru.atas.TRPfinder.Records;

import jakarta.validation.constraints.NotNull;

public record PlayerRecord(
        @NotNull
        String name,

        @NotNull
        String login

){
}
