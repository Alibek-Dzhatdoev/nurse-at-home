package com.ali.nurse_at_home.model.entity;

import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.enums.BidStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Table(name = "bids")
@FieldDefaults(level = PRIVATE)
public class Bid {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Long patientId;

    Long nurseId;  // Медсестра, которая может принять заявку (может быть null, до принятия)

    @ManyToMany
    List<Service> services;  // Услуга, которую хочет получить пациент

    @CreationTimestamp
    LocalDateTime requestedTime;  // Время запроса (когда пациент подал заявку)

    LocalDateTime scheduledTime;  // Время, когда медсестра должна выполнить услугу

    @Enumerated(STRING)
    BidStatus status;  // Статус заявки (например, "Ожидает", "В процессе", "Завершена", "Отклонена")

    @ManyToOne
    Address address;  // Адрес оказания услуги
}
