package com.ali.nurse_at_home.model.entity;

import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.enums.BidStatus;
import com.ali.nurse_at_home.model.enums.TimeIntervals;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
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
    List<Procedure> procedures;

    @CreationTimestamp
    LocalDateTime requestedTime;

    @Enumerated(STRING)
    TimeIntervals scheduledTime;  // Время, когда медсестра должна выполнить процедуру

    LocalDate scheduledDate;      // Дата, когда медсестра должна выполнить процедуру

    @Enumerated(STRING)
    BidStatus status;

    @ManyToOne
    Address address;

    @OneToOne(fetch = LAZY, mappedBy = "bid")
    Review review;
}
