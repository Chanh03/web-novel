package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@Setter
public class TransactionDTO {

    private Integer id;

    @NotNull
    private Integer amount;

    @NotNull
    @Size(max = 100)
    private String type;

    @NotNull
    @Size(max = 100)
    private String status;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    private UUID user;

}
