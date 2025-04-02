package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GenreNovelDTO {

    private Integer id;

    @NotNull
    private Integer genre;

    @NotNull
    private Integer novel;

}
