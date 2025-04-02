package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@Setter
public class ReceiveNotificationDTO {

    private Integer id;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    private UUID user;

    @NotNull
    private Integer novel;

}
