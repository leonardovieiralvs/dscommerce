package com.lsouzadev.dscommerce.dto;

import com.lsouzadev.dscommerce.entities.Payment;

import java.time.Instant;

public class PaymentDto {

    private Long id;
    private Instant moment;

    public PaymentDto(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDto(Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }
}
