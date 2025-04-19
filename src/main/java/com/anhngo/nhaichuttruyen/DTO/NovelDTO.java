package com.anhngo.nhaichuttruyen.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class NovelDTO {

    private Integer id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    private OffsetDateTime createDate;

    @NotNull
    @JsonProperty("isDelete")
    private Boolean isDelete;

    @NotNull
    @Size(max = 100)
    private String status;

    @NotNull
    private OffsetDateTime updateDate;

    @NotNull
    private String summary;

    @NotNull
    private Integer viewCount;

    @NotNull
    @JsonProperty("isAvailable")
    private Boolean isAvailable;

    @NotNull
    @Size(max = 100)
    private String thumbnail;

    @NotNull
    private Integer likeCount;

    @NotNull
    @Size(max = 100)
    private String slug;

    @NotNull
    private UUID author;
    
    @NotNull
    private List<GenreDTO> genres;

}
