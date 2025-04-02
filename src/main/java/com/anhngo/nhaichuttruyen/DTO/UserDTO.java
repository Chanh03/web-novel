package com.anhngo.nhaichuttruyen.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@Setter
public class UserDTO {

    private UUID id;

    @NotNull
    @Size(max = 100)
    private String username;

    @NotNull
    @Size(max = 255)
    private String fullName;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 100)
    private String avatar;

    @NotNull
    @JsonProperty("isEnabled")
    private Boolean isEnabled;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    private Integer walletBalance;

    @NotNull
    private OffsetDateTime updateDate;

}
