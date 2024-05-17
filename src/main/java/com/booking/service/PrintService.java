package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr) {
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);
            num++;
        }
    }

    public static void showAllCustomer(List<Customer> customerList) {
        System.out.println();
        System.out.println("=============================== Tabel Customer ================================");
        System.out.printf("%-4s %-10s %-15s %-20s %-15s %-10s%n", "No", "ID", "Nama", "Alamat", "Membership", "Uang");

        int no = 1;
        for (Customer customer : customerList) {
            System.out.printf("%-4d %-10s %-15s %-20s %-15s Rp.%.0f%n",
                    no,
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getMembershipName(),
                    customer.getWallet());
            no++;
        }

        System.out.println("===============================================================================");
        System.out.println();
    }

    public static void showAllEmployee(List<Employee> employeeList) {
        System.out.println();
        System.out.println("======================= Tabel  Employee ========================");
        System.out.printf("%-4s %-10s %-15s %-20s %-15s%n", "No", "ID", "Nama", "Alamat",
                "Pengalaman");
        int no = 1;
        for (Employee employee : employeeList) {
            System.out.printf("%-4d %-10s %-15s %-20s %-15s%n",
                    no,
                    employee.getId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getExperience());
            no++;
        }

        System.out.println("================================================================");
        System.out.println();
    }

    public static void showAllService(List<Service> serviceList) {
        System.out.println();
        System.out.println("======================= Tabel  Service ========================");
        System.out.printf("%-4s %-15s %-25s %-20s%n", "No", "ID", "Nama", "Harga");
        int no = 1;
        for (Service service : serviceList) {
            System.out.printf("%-4d %-15s %-25s Rp.%.0f%n",
                    no,
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getPrice());
            no++;
        }

        System.out.println("================================================================");
        System.out.println();
    }

    public static void showRecentReservation(List<Reservation> reservationList) {
        System.out.println();
        System.out.println(
                "=============================================== Reservation Service ================================================");
        System.out.printf("%-4s %-15s %-25s %-25s %-20s %-20s%n", "No", "ID", "Nama Customer", "Service", "Total Biaya",
                "Workstage");
        int no = 1;
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("In Process")) {
                String serviceNames = reservation.getServices().stream()
                        .map(service -> service.getServiceName())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");

                System.out.printf("%-4d %-15s %-25s %-25s Rp.%-20.0f %-20s%n",
                        no,
                        reservation.getReservationId(),
                        reservation.getCustomer().getName(),
                        serviceNames,
                        reservation.getReservationPrice(),
                        reservation.getWorkstage());
                no++;
            }
        }
        System.out.println(
                "==================================================================================================================");
        System.out.println();
    }

    public static void showHistoryReservation(List<Reservation> reservationList) {
        System.out.println();
        System.out.println(
                "=============================================== Reservation History ================================================");
        System.out.printf("%-4s %-15s %-25s %-25s %-20s %-20s%n", "No", "ID", "Nama Customer", "Service", "Total Biaya",
                "Workstage");
        int no = 1;
        double totalProfit = 0;
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
                String serviceNames = reservation.getServices().stream()
                        .map(service -> service.getServiceName())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");

                System.out.printf("%-4d %-15s %-25s %-25s Rp.%-20.0f %-20s%n",
                        no,
                        reservation.getReservationId(),
                        reservation.getCustomer().getName(),
                        serviceNames,
                        reservation.getReservationPrice(),
                        reservation.getWorkstage());
                no++;

                totalProfit += reservation.getReservationPrice();
            }
        }
        System.out.println("Total Keuntungan: Rp." + totalProfit);
        System.out.println(
                "==================================================================================================================");
        System.out.println();
    }
}
