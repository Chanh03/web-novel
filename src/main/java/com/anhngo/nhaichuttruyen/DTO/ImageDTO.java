package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
public class ImageDTO {

    private Integer id;

    @NotNull
    @Size(max = 100)
    private String url;

    @NotNull
    private Integer orderNum;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    private Integer chapter;

}
