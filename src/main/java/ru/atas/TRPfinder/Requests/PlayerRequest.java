package ru.atas.TRPfinder.Requests;

import jakarta.validation.constraints.NotNull;

public record PlayerRequest (
        @NotNull
        String name,

        @NotNull
        String login

){
}
