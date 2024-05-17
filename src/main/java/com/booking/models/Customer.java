package com.booking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)

public class Customer extends Person {
    private double wallet;
    private Membership member;

    public String getMembershipName() {
        return member != null ? member.getMembershipName() : "No Membership";
    }
}
