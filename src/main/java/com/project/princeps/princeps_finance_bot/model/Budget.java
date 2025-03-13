package com.project.princeps.princeps_finance_bot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id", nullable = false)
    private Long budget_id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private TgUser user_id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category_id;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ColumnDefault("0")
    @Column(name = "limit_amount")
    private BigDecimal limitAmount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "period_begin")
    private Instant periodBegin;

    @Column(name = "period_end")
    private Instant periodEnd;

}