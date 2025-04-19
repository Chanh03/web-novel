package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookmarkDTO {
    private Integer id;

    @NotNull
    private UUID userId;

    @NotNull
    private Integer chapterId;
}