package ch.noseryoung.uek_295_payment.Domain.Payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "payment_method")
    @NotBlank(message = "Payment method must contain a value")
    @Size(min = 3, max = 20, message = "Payment method must be between 3 and 20 characters")
    private String paymentMethod;

    @Column(name = "chf_exchange_rate")
    @NotNull(message = "CHF exchange rate must contain a value")
    private double chfExchangeRate;

    @Column(name = "currency")
    @NotBlank(message = "Currency must contain a value")
    @Size(min = 2, max = 20 , message = "Currency must be between 2 and 20 characters")
    private String currency;
}