package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserRoleDTO {

    private Integer id;

    @NotNull
    private String role;

    @NotNull
    private UUID user;

}
