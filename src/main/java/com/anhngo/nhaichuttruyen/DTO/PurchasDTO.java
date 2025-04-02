package com.anhngo.nhaichuttruyen.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@Setter
public class PurchasDTO {

    private Integer id;

    @NotNull
    private Integer price;

    @NotNull
    private OffsetDateTime purchaseDate;

    @NotNull
    @Size(max = 100)
    private String paymentStatus;

    @NotNull
    private UUID user;

    @NotNull
    private Integer chapter;

}
