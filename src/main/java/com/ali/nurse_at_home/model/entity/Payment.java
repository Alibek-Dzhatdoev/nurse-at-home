package com.ali.nurse_at_home.model.entity;

import com.ali.nurse_at_home.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Table(name = "payments")
@FieldDefaults(level = PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Long patientId;  // Пациент, который заплатил

    @OneToOne
    Bid bid;  // Заявка, за которую был совершен платеж

    Double amount;  // Сумма платежа

    @CreationTimestamp
    LocalDateTime paymentDate;  // Дата платежа

    PaymentMethod paymentMethod;  // Метод оплаты (например, карта, наличные)

}
