package com.anhngo.nhaichuttruyen.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Username không được để trống")
    @Size(max = 100, message = "Username tối đa 100 ký tự")
    private String username;

    @NotBlank(message = "Full name không được để trống")
    private String fullName;

    @NotBlank(message = "Password không được để trống")
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Avatar không được để trống")
    @Size(min = 1, message = "Avatar phải có ít nhất 1 ký tự")
    @Pattern(regexp = ".*\\S.*", message = "Avatar không được chỉ chứa khoảng trắng")
    private String avatar;

    private Boolean isEnabled = false;

    @NotNull(message = "Create date không được để trống")
    private LocalDateTime createDate;

    @NotNull(message = "Wallet balance không được để trống")
    @Min(value = 0, message = "Wallet balance không thể nhỏ hơn 0")
    private Integer walletBalance;

    @NotNull(message = "Update date không được để trống")
    private LocalDateTime updateDate;
}
