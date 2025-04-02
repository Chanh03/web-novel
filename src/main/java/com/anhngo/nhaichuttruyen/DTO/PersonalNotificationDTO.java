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
public class PersonalNotificationDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private String text;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    @JsonProperty("isRead")
    private Boolean isRead;

    @NotNull
    private UUID user;

}
