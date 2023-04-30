package com.shop.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipcode;
    private String streetAdr;
    private String detailAdr;

    protected Address() {
    }

    public Address(String zipcode, String streetAdr, String detailAdr) {
        this.zipcode = zipcode;
        this.streetAdr = streetAdr;
        this.detailAdr = detailAdr;
    }
}
