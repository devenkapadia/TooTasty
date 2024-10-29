package com.foodisgood.tootasty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Invalid Credentials")

        @JsonProperty("email")
        String email,

        @JsonProperty("password")
        String password
)
{
}