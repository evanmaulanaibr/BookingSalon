package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class ReservationService {
    private static List<Customer> customerList = PersonRepository.getAllCustomer();
    private static List<Employee> employeeList = PersonRepository.getAllEmployee();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    // private static List<Reservation> reservationList = new ArrayList<>();

    public static void createReservation() {
        Scanner scanner = new Scanner(System.in);

        PrintService.showAllCustomer(customerList);
        System.out.print("Masukan Customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = getCustomerByCustomerId(customerId);

        PrintService.showAllEmployee(employeeList);
        System.out.print("Masukan Employee ID: ");
        String employeeId = scanner.nextLine();
        Employee employee = getEmployeeByEmployeeId(employeeId);

        List<Service> selectedServices = new ArrayList<>();
        boolean addMoreServices = true;

        while (addMoreServices) {
            PrintService.showAllService(serviceList);
            System.out.print("Masukan Service ID: ");
            String serviceId = scanner.nextLine();
            Service service = getServiceById(serviceId);
            if (service != null) {
                selectedServices.add(service);
            } else {
                System.out.println("Service ID tidak ditemukan.");
            }

            System.out.print("Ingin Pilih Service Lain (Y/T): ");
            String tambahan = scanner.nextLine();
            addMoreServices = tambahan.equalsIgnoreCase("Y");
        }

        double totalPrice = calculateTotalPrice(selectedServices);

        Reservation reservation = Reservation.builder()
                .reservationId(generateReservationId())
                .customer(customer)
                .employee(employee)
                .services(selectedServices)
                .reservationPrice(totalPrice)
                .workstage("In Process")
                .build();

        ServiceRepository.addReservation(reservation);

        System.out.println("\nBooking Berhasil");
        System.out.println("Total Biaya Booking: Rp." + totalPrice);

        // PrintService.showRecentReservation(reservationList);
    }

    private static Service getServiceById(String serviceId) {
        for (Service service : serviceList) {
            if (service.getServiceId().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    public static Customer getCustomerByCustomerId(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private static Employee getEmployeeByEmployeeId(String employeeId) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    private static double calculateTotalPrice(List<Service> services) {
        double total = 0;
        for (Service service : services) {
            total += service.getPrice();
        }
        return total;
    }

    private static int reservationCounter = 1;

    private static String generateReservationId() {
        String reservationIdPrefix = "Rsv-";
        String reservationId = reservationIdPrefix + String.format("%02d", reservationCounter);
        reservationCounter++;
        return reservationId;
    }

    public static void editReservationWorkstage(Reservation reservation, String newWorkstage) {
        switch (newWorkstage.toLowerCase()) {
            case "finish":
                reservation.setWorkstage("Finish");
                System.out.println("Workstage updated to: Finish");
                break;
            case "cancel":
                reservation.setWorkstage("Cancel");
                System.out.println("Workstage updated to: Cancel");
                break;
            default:
                System.out.println("Invalid workstage input.");
                break;
        }
    }

    public static void updateReservation(List<Reservation> reservationList) {
        Scanner scanner = new Scanner(System.in);

        PrintService.showRecentReservation(reservationList);

        System.out.print("Masukan Reservasion ID: ");
        String reservationId = scanner.nextLine();

        Reservation selectedReservation = null;
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)) {
                selectedReservation = reservation;
                break;
            }
        }

        if (selectedReservation == null) {
            System.out.println("Reservation ID " + reservationId + " tidak ditemukan.");
            return;
        }

        System.out.print("Konfirmasi (finish/cancel): ");
        String newWorkstage = scanner.nextLine();

        editReservationWorkstage(selectedReservation, newWorkstage);
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
