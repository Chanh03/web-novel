package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
public class RatingDTO {

    private Integer id;

    @NotNull
    private Integer rating;

    @NotNull
    private String comment;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    private OffsetDateTime updateDate;

    @NotNull
    private Integer chapter;

}
