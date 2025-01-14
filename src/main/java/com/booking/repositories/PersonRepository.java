package com.booking.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Membership;
import com.booking.models.Person;

public class PersonRepository {
        public static List<Person> getAllPerson() {
                List<Person> listPerson = new ArrayList<>();

                // Membership member1 = new Membership("Mem-01", "none");
                // Membership member2 = new Membership("Mem-02", "Silver");
                // Membership member3 = new Membership("Mem-03", "Gold");

                Membership member1 = Membership.builder()
                                .memberId("Mem-01")
                                .membershipName("None")
                                .build();

                Membership member2 = Membership.builder()
                                .memberId("Mem-02")
                                .membershipName("Silver")
                                .build();

                Membership member3 = Membership.builder()
                                .memberId("Mem-03")
                                .membershipName("Gold")
                                .build();

                Person customer1 = Customer.builder()
                                .id("Cust-01")
                                .name("Budi")
                                .address("Bandung")
                                .member(member1)
                                .wallet(1_000_000)
                                .build();

                Person customer2 = Customer.builder()
                                .id("Cust-02")
                                .name("Aceng")
                                .address("Cimahi")
                                .member(member2)
                                .wallet(1_000_000)
                                .build();

                Person customer3 = Customer.builder()
                                .id("Cust-03")
                                .name("Nur")
                                .address("Garut")
                                .member(member3)
                                .wallet(1_000_000)
                                .build();

                Person customer4 = Customer.builder()
                                .id("Cust-04")
                                .name("Iwan")
                                .address("Sukabumi")
                                .member(member2)
                                .wallet(1_000_000)
                                .build();

                Person employee1 = Employee.builder()
                                .id("Emp-01")
                                .name("Jono")
                                .address("Bandung")
                                .experience(3)
                                .build();

                Person employee2 = Employee.builder()
                                .id("Emp-02")
                                .name("Joni")
                                .address("Cimahi")
                                .experience(1)
                                .build();

                Person employee3 = Employee.builder()
                                .id("Emp-03")
                                .name("Hana")
                                .address("Garut")
                                .experience(5)
                                .build();

                listPerson.addAll(Arrays.asList(customer1, customer2, customer3, customer4, employee1, employee2,
                                employee3));

                return listPerson;
        }

        public static List<Employee> getAllEmployee() {
                List<Person> allPersons = getAllPerson();
                return allPersons.stream()
                                .filter(person -> person instanceof Employee)
                                .map(person -> (Employee) person)
                                .collect(Collectors.toList());
        }

        public static List<Customer> getAllCustomer() {
                List<Person> allPersons = getAllPerson();
                return allPersons.stream()
                                .filter(person -> person instanceof Customer)
                                .map(person -> (Customer) person)
                                .collect(Collectors.toList());
        }
}
