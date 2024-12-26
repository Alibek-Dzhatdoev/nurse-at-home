package com.ali.nurse_at_home.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "patients")
@FieldDefaults(level = PRIVATE)
public class Patient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String email;  // Электронная почта
    String firstName;  // Имя пациента
    String lastName;   // Фамилия пациента
    String phoneNumber;  // Номер телефона пациента
    LocalDate dateOfBirth;  // Дата рождения пациента (по желанию)

    @OneToMany(mappedBy = "patient")
    List<PatientAddress> addresses = new ArrayList<>();  // Адрес пациента (может быть несколько, но обычно один основной)

    @OneToMany(mappedBy = "patientId")
    List<Bid> bids = new ArrayList<>();  // Список заявок пациента

    @OneToMany(mappedBy = "patientId")
    List<Review> reviews = new ArrayList<>();  // Отзывы о медсестре (если есть)
}
