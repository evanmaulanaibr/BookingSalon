package com.booking.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Person;
import com.booking.models.Reservation;
// import com.booking.models.Membership;
import com.booking.models.Service;

public class ServiceRepository {
        public static List<Service> getAllService() {
                List<Service> serviceList = new ArrayList<>();

                // Service service1 = new Service("Serv-01", "Potong Rambut", 75000);
                // Service service2 = new Service("Serv-02", "Styling Rambut", 125000);
                // Service service3 = new Service("Serv-03", "Pewarnaan Rambut", 500000);
                // Service service4 = new Service("Serv-04", "Rebonding", 60000);
                // Service service5 = new Service("Serv-05", "Mengeriting Rambut", 70000);

                Service service1 = Service.builder()
                                .serviceId("Serv-01")
                                .serviceName("Potong Rambut")
                                .price(75000)
                                .build();

                Service service2 = Service.builder()
                                .serviceId("Serv-02")
                                .serviceName("Styling Rambut")
                                .price(125000)
                                .build();

                Service service3 = Service.builder()
                                .serviceId("Serv-03")
                                .serviceName("Pewarnaan Rambut")
                                .price(500000)
                                .build();

                Service service4 = Service.builder()
                                .serviceId("Serv-04")
                                .serviceName("Rebonding")
                                .price(60000)
                                .build();

                Service service5 = Service.builder()
                                .serviceId("Serv-05")
                                .serviceName("Mengeriting Rambut")
                                .price(70000)
                                .build();

                serviceList.addAll(Arrays.asList(service1, service2, service3, service4, service5));

                return serviceList;

                // return serviceList.stream()
                // .filter(service -> service instanceof Service)
                // .map(service -> (Service) service)
                // .collect(Collectors.toList());
        }

        private static List<Reservation> reservationList = new ArrayList<>();

        public static List<Reservation> getAllReservations() {
                return reservationList;
        }

        public static void addReservation(Reservation reservation) {
                reservationList.add(reservation);
        }
}
