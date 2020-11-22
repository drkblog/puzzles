package ar.com.drk.scratch;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

public class Lombok {

    @EqualsAndHashCode
    @RequiredArgsConstructor
    static class Customer {
        private final String fullName;
        private final String username;
        private final String mail;
        private final ZonedDateTime birthDate;
        @EqualsAndHashCode.Exclude
        private final boolean active;
        @EqualsAndHashCode.Exclude
        private byte[] password;
        @EqualsAndHashCode.Exclude
        private ZonedDateTime lastLogin;
    }

    public static void main(String[] args) {
        Customer customer_v1 = new Customer("Leandro Fernandez",
                "leandro",
                "fakeAddress@drk.com.ar",
                ZonedDateTime.parse("1976-07-23T00:00:00Z"),
                true);
        Customer customer_v2 = new Customer("Leandro Fernandez",
                "leandro",
                "fakeAddress@drk.com.ar",
                ZonedDateTime.parse("1976-07-23T00:00:00Z"),
                false);
        System.out.println("customer_v1.equals(customer_v2) = " + customer_v1.equals(customer_v2));
        System.out.println("customer_v1.hashCode() = " + customer_v1.hashCode());
        System.out.println("customer_v2.hashCode() = " + customer_v2.hashCode());
    }
}
