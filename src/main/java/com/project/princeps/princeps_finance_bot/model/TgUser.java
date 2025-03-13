package com.project.princeps.princeps_finance_bot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tg_user")
public class TgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Size(max = 50)
    @NotNull
    @Unique
    @Column(name = "tg_user_id", nullable = false, length = 50)
    private Long tg_user_id;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "time_registration", nullable = false)
    private Timestamp timeRegistration;

}