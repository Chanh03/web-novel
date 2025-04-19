package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
public class GlobalNotificationDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private String text;

    @NotNull
    private OffsetDateTime createDate;

}
