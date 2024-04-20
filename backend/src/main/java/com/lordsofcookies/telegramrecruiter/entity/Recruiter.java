package com.lordsofcookies.telegramrecruiter.entity;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "recruiter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recruiter {
    @Id
    @Column(name = "telegram_id")
    private String telegramId;

    @OneToOne(targetEntity = TelegramUser.class)
    @JoinColumn(name = "telegram_id", referencedColumnName = "telegram_id")
    @MapsId
    private TelegramUser telegramUser;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;
}
