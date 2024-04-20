package com.lordsofcookies.telegramrecruiter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = TelegramUser.class)
    @JoinColumn(name = "created_by_telegram_user")
    private TelegramUser createdBy;
}
