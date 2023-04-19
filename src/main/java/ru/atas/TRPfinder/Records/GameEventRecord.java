package ru.atas.TRPfinder.Records;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record GameEventRecord (
        @NotNull
        ZonedDateTime date,

        @NotNull
        String name,

        String description
){
}
