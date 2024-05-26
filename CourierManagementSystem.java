package com.mycompany.couriermanagementsystem;

import com.mycompany.couriermanagementsystem.Interfaces.AdminManagerService;
import com.mycompany.couriermanagementsystem.Models.Admin;
import com.mycompany.couriermanagementsystem.Models.Branch;
import com.mycompany.couriermanagementsystem.Models.Parcel;
import com.mycompany.couriermanagementsystem.Models.Employee;
import com.mycompany.couriermanagementsystem.Models.Client;
import com.mycompany.couriermanagementsystem.Models.PaymentMethod;

import com.mycompany.couriermanagementsystem.Models.User;
import com.mycompany.couriermanagementsystem.Services.AdminManagerServiceImpl;

import com.mycompany.couriermanagementsystem.Services.BranchManagerServiceImpl;
import com.mycompany.couriermanagementsystem.Services.ClientServiceImpl;
import java.util.ArrayList;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CourierManagementSystem {

    public static List<Parcel> parcels = new ArrayList<>();
    public static List<Client> clients = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<Branch> branches = new ArrayList<>();
    public static List<PaymentMethod> paymentMethods = new ArrayList<>();

    private static AdminManagerService adminManagerService = new AdminManagerServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static boolean runAdminSystem(Admin loggedInUser) {
        System.out.println("Admin Management System");
        System.out.println("1. View Employee Details");
        System.out.println("2. Add New Employee");
        System.out.println("3. Edit Existing Employee");
        System.out.println("4. Delete/Remove Employee");
        System.out.println("5. View Branch Details");
        System.out.println("6. Add New Branch");
        System.out.println("7. Edit Existing Branch");
        System.out.println("8. Delete/Disable/Enable Branch");
        System.out.println("9. Add/Edit/Delete Payment Method");
        System.out.println("10. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                viewEmployees();
                break;
            case 2:
                addEmployee();
                break;
            case 3:
                editEmployee();
                break;
            case 4:
                deleteEmployee();
                break;
            case 5:
                viewBranches();
                break;
            case 6:
                addBranch();
                break;
            case 7:
                editBranch();
                break;
            case 8:
                deleteBranch();
                break;
            case 9:
                managePaymentMethods();
                break;
            case 10:
                System.out.println("Exiting...");
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    public static boolean runClientSystem(Client loggedInUser) {
        ClientServiceImpl service = new ClientServiceImpl();

        System.out.println("\nClient System");
        System.out.println("1. Register as new user");
        System.out.println("2. Add New Parcel Request");
        System.out.println("3. Cancel Parcel Request");
        System.out.println("4. Pickup Request");
        System.out.println("5. Update Parcel Information");
        System.out.println("6. Track Parcel");
        System.out.println("7. View Service History");
        System.out.println("8. Update own Profile Information");
        System.out.println("9. Schedule pickup time");
        System.out.println("10. View Parcel packaging guidelines");
        System.out.println("11. View delivery fee for different options");
        System.out.println("12. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter full name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter full address: ");
                String fullAddress = scanner.nextLine();
                System.out.print("Enter default sending branch ID: ");
                int defaultSendingBranchId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter default sending branch name: ");
                String defaultSendingBranchName = scanner.nextLine();

                Client newClient = new Client(0, fullName, username, password, fullAddress, false, false, defaultSendingBranchId, defaultSendingBranchName);
                service.registerClient(newClient);
                break;

            case 2:
                System.out.print("Enter parcel title: ");
                String title = scanner.nextLine();
                System.out.print("Enter parcel description: ");
                String parcelDescription = scanner.nextLine();
                System.out.print("Enter sending branch ID: ");
                int sendingBranchId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter sending branch name: ");
                String sendingBranchName = scanner.nextLine();
                System.out.print("Enter destination branch ID: ");
                int destinationBranchId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter destination branch name: ");
                String destinationBranchName = scanner.nextLine();

                Parcel newParcel = new Parcel(0, title, parcelDescription, new Date(), loggedInUser.getId(), loggedInUser.getFullName(), sendingBranchId, sendingBranchName, destinationBranchId, destinationBranchName, Parcel.ParcelStatusEnum.NewlyAdded, true, 0, "", 0, "");
                service.addParcelRequest(newParcel);
                break;

            case 3:
                System.out.print("Enter parcel ID to cancel: ");
                int parcelIdToCancel = scanner.nextInt();
                service.cancelParcelRequest(parcelIdToCancel);
                break;

            case 4:
                System.out.print("Enter parcel ID for pickup: ");
                int parcelIdForPickup = scanner.nextInt();
                service.pickupRequest(parcelIdForPickup);
                break;

            case 5:
                System.out.print("Enter parcel ID to update: ");
                int parcelIdToUpdate = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter updated title: ");
                String updatedTitle = scanner.nextLine();
                System.out.print("Enter updated description: ");
                String updatedDescription = scanner.nextLine();

                Parcel updatedParcel = new Parcel(parcelIdToUpdate, updatedTitle, updatedDescription, new Date(), 0, "", 0, "", 0, "", Parcel.ParcelStatusEnum.NewlyAdded, true, 0, "", 0, "");
                service.updateParcelInformation(updatedParcel);
                break;

            case 6:
                System.out.print("Enter parcel ID to track: ");
                int parcelIdToTrack = scanner.nextInt();
                Parcel trackedParcel = service.trackParcel(parcelIdToTrack);
                if (trackedParcel != null) {
                    System.out.println("Parcel Status: " + trackedParcel.getParcelStatus());
                }
                break;

            case 7:
                List<Parcel> history = service.viewServiceHistory(loggedInUser.getId());
                System.out.println("Service History: " + history.size() + " parcels");
                System.out.printf("%-5s %-20s %-30s %-20s %-20s %-20s %-20s%n", "ID", "Title", "Description", "Client Name", "Sending Branch", "Destination Branch", "Status");
                for (Parcel parcel : history) {
                    System.out.printf("%-5d %-20s %-30s %-20s %-20s %-20s %-20s%n",
                            parcel.getId(), parcel.getTitle(), parcel.getParcelDescription(),
                            parcel.getClientName(), parcel.getSendingBranchName(), parcel.getDestinationBranchName(),
                            parcel.getParcelStatus());
                }
                break;

            case 8:

                System.out.print("Enter updated full name: ");
                String updatedFullName = scanner.nextLine();
                System.out.print("Enter updated username: ");
                String updatedUsername = scanner.nextLine();
                System.out.print("Enter updated password: ");
                String updatedPassword = scanner.nextLine();
                System.out.print("Enter updated full address: ");
                String updatedFullAddress = scanner.nextLine();

                Client updatedClient = new Client(loggedInUser.getId(), updatedFullName, updatedUsername, updatedPassword, updatedFullAddress, false, false, 0, "");
                service.updateProfileInformation(updatedClient);
                break;

            case 9:
                System.out.print("Enter parcel ID to schedule pickup: ");
                int parcelIdForSchedulePickup = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter pickup time (YYYY-MM-DD HH:MM): ");
                String pickupTimeStr = scanner.nextLine();
                Date pickupTime = new Date(); // Placeholder for actual date parsing
                service.schedulePickupTime(parcelIdForSchedulePickup, pickupTime);
                break;

            case 10:
                String guidelines = service.viewParcelPackagingGuidelines();
                System.out.println(guidelines);
                break;

            case 11:
                System.out.print("Enter delivery option (standard/express/same-day): ");
                String deliveryOption = scanner.nextLine();
                double fee = service.viewDeliveryFee(deliveryOption);
                System.out.println("Delivery Fee: $" + fee);
                break;

            case 12:
                System.out.println("Exiting the system. Goodbye!");
                return true;
            default:
                System.out.println("Invalid option! Please choose a valid option.");
                break;
        }
        return false;
    }

    public static boolean runBranchManagerSystem(Employee loggedInUser) {
        BranchManagerServiceImpl service = new BranchManagerServiceImpl();
        System.out.println("Branch Manager Management System");
        System.out.println("1. Add New Parcel");
        System.out.println("2. Edit Parcel");
        System.out.println("3. View Parcel Details (List)");
        System.out.println("4. Cancel Parcel");
        System.out.println("5. View New Courier Requests");
        System.out.println("6. Assign Pickup Boy");
        System.out.println("7. Send Product to Destination");
        System.out.println("8. Receive Product from Source");
        System.out.println("9. Assign Delivery Boy");
        System.out.println("10. Update Product Status to Delivered");
        System.out.println("11. Track Parcel");
        System.out.println("12. Handle Returned or Cancelled Parcels");
        System.out.println("13. Generate and View Reports");
        System.out.println("14. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                // Add New Parcel
                System.out.println("Enter parcel title:");
                String title = scanner.nextLine();
                System.out.println("Enter parcel description:");
                String description = scanner.nextLine();
                System.out.println("Enter client ID:");
                int clientId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Enter client name:");
                String clientName = scanner.nextLine();

                System.out.println("Enter destination branch ID:");
                int destinationBranchId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Enter destination branch name:");
                String destinationBranchName = scanner.nextLine();

                Parcel newParcel = new Parcel();
                newParcel.setTitle(title);
                newParcel.setParcelDescription(description);
                newParcel.setClientId(clientId);
                newParcel.setClientName(clientName);

                newParcel.setDestinationBranchId(destinationBranchId);
                newParcel.setDestinationBranchName(destinationBranchName);

                service.addNewParcel(newParcel, loggedInUser);
                break;

            case 2:
                // Edit Parcel
                System.out.println("Enter parcel ID to update:");
                int updateParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel updatedParcel = new Parcel();
                System.out.println("Enter new title:");
                updatedParcel.setTitle(scanner.nextLine());
                System.out.println("Enter new description:");
                updatedParcel.setParcelDescription(scanner.nextLine());
                System.out.println("Enter new destination branch ID:");
                updatedParcel.setDestinationBranchId(scanner.nextInt());
                scanner.nextLine(); // Consume newline
                System.out.println("Enter new destination branch name:");
                updatedParcel.setDestinationBranchName(scanner.nextLine());
//                        System.out.println("Enter new status (number corresponding to ParcelStatusEnum):");
//                        updatedParcel.setParcelStatus(Parcel.ParcelStatusEnum.values()[scanner.nextInt()]);
                scanner.nextLine(); // Consume newline

                service.updateParcel(updateParcelId, updatedParcel);
                break;

            case 3:
                // View Parcel List
                List<Parcel> parcelList = service.viewParcelList();
                System.out.printf("%-5s %-20s %-30s %-20s %-20s %-20s %-20s%n", "ID", "Title", "Description", "Client Name", "Sending Branch", "Destination Branch", "Status");
                for (Parcel parcel : parcelList) {
                    System.out.printf("%-5d %-20s %-30s %-20s %-20s %-20s %-20s%n",
                            parcel.getId(), parcel.getTitle(), parcel.getParcelDescription(),
                            parcel.getClientName(), parcel.getSendingBranchName(), parcel.getDestinationBranchName(),
                            parcel.getParcelStatus());
                }
                break;
            case 4:
                // Example usage
                System.out.println("Enter parcel ID to cancel:");
                int cancelParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                service.cancelParcel(cancelParcelId);
                break;
            case 5:
                // View New Courier Requests
                List<Parcel> newCourierRequests = service.viewNewCourierRequests(loggedInUser);
                System.out.println("New Courier Requests:");
                for (Parcel newRequest : newCourierRequests) {
                    System.out.println(newRequest);
                }
                break;
            case 6:
                // Example usage
                System.out.println("Enter parcel ID to assign pickup boy:");
                int assignPickupParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel pickupParcel = service.viewParcelDetails(assignPickupParcelId);
                if (pickupParcel != null) {
                    System.out.println("Enter pickup boy UserID to assign pickup boy:");
                    int pickupboyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Employee pickupBoy = new Employee();

                    // Set properties for the pickup boy
                    service.assignPickupBoy(pickupParcel, pickupBoy);
                } else {
                    System.out.println("Parcel not found.");
                }
                break;
            case 7:
                // Example usage
                System.out.println("Enter parcel ID to send to destination:");
                int sendParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel sendParcel = service.viewParcelDetails(sendParcelId);
                if (sendParcel != null) {
                    service.sendProductToDestination(sendParcel);
                } else {
                    System.out.println("Parcel not found.");
                }
                break;
            case 8:
                // Example usage
                System.out.println("Enter parcel ID to receive from source:");
                int receiveParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel receiveParcel = service.viewParcelDetails(receiveParcelId);
                if (receiveParcel != null) {
                    service.receiveProductFromSource(receiveParcel);
                } else {
                    System.out.println("Parcel not found.");
                }
                break;
            case 9:
                // Example usage
                System.out.println("Enter parcel ID to assign delivery boy:");
                int assignDeliveryParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel deliveryParcel = service.viewParcelDetails(assignDeliveryParcelId);
                if (deliveryParcel != null) {
                    System.out.println("Enter dlivery boy UserID to assign pickup boy:");
                    int deliveryBoyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    // Assume you have a delivery boy instance
                    Employee deliveryBoy = new Employee();
                    // Set properties for the delivery boy
                    service.assignDeliveryBoy(deliveryParcel, deliveryBoy);
                } else {
                    System.out.println("Parcel not found.");
                }
                break;
            case 10:
                // Example usage
                System.out.println("Enter parcel ID to update status to delivered:");
                int deliverParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel deliverParcel = service.viewParcelDetails(deliverParcelId);
                if (deliverParcel != null) {
                    service.updateProductStatusToDelivered(deliverParcel);
                } else {
                    System.out.println("Parcel not found.");
                }
                break;
            case 11:
                // Example usage
                System.out.println("Enter parcel ID to track:");
                int trackParcelId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Parcel trackParcel = service.viewParcelDetails(trackParcelId);
                if (trackParcel != null) {
                    Parcel.ParcelStatusEnum status = service.trackParcel(trackParcel);
                    System.out.println("Parcel status: " + status);
                } else {
                    System.out.println("Parcel not found.");
                }
                break;
            case 12:
                // Example usage
                service.handleReturnedOrCancelledParcels();
                break;
            case 13:
                // Example usage
                service.generateAndViewReports(loggedInUser);
                break;
            case 14:
                System.out.println("Exiting...");
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        return false;
    }
    
    public static void setFixedUsers() {
        Employee employee = new Employee(1, "Test Branch Manager", "bm1", "123", User.UserTypeEnum.BranchManager, 1, "Test");
        employees.add(employee);

        Admin admin = new Admin(1, "Test Admin", "ad1", "123", User.UserTypeEnum.Admin, true, true);
        admins.add(admin);

        Client client = new Client(1, "Test Client", "cl1", "123", "Melbourne,Aus", false, false, 1, "Test");
        clients.add(client);
        
    }

    public static Admin findAdminById(String userName, String password) {
        for (Admin admin : admins) {
            if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public static Employee findBranchManagerById(String userName, String password) {
        for (Employee employee : employees) {
            if (employee.getUserName().equals(userName) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }

    public static Client findClientById(String userName, String password) {
        for (Client client : clients) {
            if (client.getUserName().equals(userName) && client.getPassword().equals(password)) {
                return client;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        setFixedUsers();

        while (true) {
            System.out.println("Select Login As");
            System.out.println("1. Admin");
            System.out.println("2. Branch Manger");
            System.out.println("3. Client");
            System.out.println("4. Exit");

            int loginType = scanner.nextInt();

            if (loginType == 4) {
                System.out.println("Thank you for using the software");
                return;
            }
            if (loginType > 3 || loginType < 0) {
                System.out.println("Select a valid login type.");
                continue;
            }
            scanner.nextLine();
            System.out.println("Provide UserName:");
            String userName = scanner.nextLine();

            System.out.println("Provide password:");
            String password = scanner.nextLine();

            Employee employee = null;
            Admin admin = null;
            Client client = null;

            if (loginType == 1) {
                admin = findAdminById(userName, password);
            } else if (loginType == 2) {
                employee = findBranchManagerById(userName, password);
            } else if (loginType == 3) {
                client = findClientById(userName, password);
            }

            if (employee == null && admin == null && client == null) {
                System.out.println("User Name or password is incorrect.");
                return;
            }

            while (true) {
                try {
                    boolean isLogout = false;
                    if (loginType == 2) {
                       isLogout = runBranchManagerSystem(employee);
                    } else if (loginType == 3) {
                        isLogout = runClientSystem(client);
                    } else if (loginType == 1) {
                        isLogout = runAdminSystem(admin);
                    } else {
                        System.out.println("Login Feature not enabled for this user type.");
                        return;
                    }
                    if(isLogout){
                        break;
                    }
                } catch (InputMismatchException e) {
                    // Handle input mismatch exception (e.g., user enters a string instead of an integer)
                    System.out.println("Invalid input. Please enter a valid option.");
                    scanner.nextLine(); // Consume newline
                } catch (Exception e) {
                    // Handle other exceptions
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        }

    }

    private static void viewEmployees() {
        for (Employee employee : adminManagerService.viewEmployees()) {
            System.out.println(employee.getId() + " " + employee.getFullName() + " " + employee.getBranchName());
        }
    }

    private static void addEmployee() {
        Employee employee = new Employee();
        System.out.println("Enter Employee Full Name: ");
        employee.setFullName(scanner.nextLine());
        System.out.println("Enter Employee Username: ");
        employee.setUserName(scanner.nextLine());
        System.out.println("Enter Employee Password: ");
        employee.setPassword(scanner.nextLine());
        System.out.println("Enter Branch ID: ");
        employee.setBranchId(scanner.nextInt());
        scanner.nextLine(); // consume newline
        System.out.println("Enter Branch Name: ");
        employee.setBranchName(scanner.nextLine());
        employee.setUserType(User.UserTypeEnum.BranchManager);

        try {
            adminManagerService.addEmployee(employee);
            System.out.println("Employee added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void editEmployee() {
        System.out.println("Enter Employee ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Employee employee = new Employee();
        employee.setId(id);
        System.out.println("Enter New Full Name: ");
        employee.setFullName(scanner.nextLine());
        System.out.println("Enter New Username: ");
        employee.setUserName(scanner.nextLine());
        System.out.println("Enter New Password: ");
        employee.setPassword(scanner.nextLine());
        System.out.println("Enter New Branch ID: ");
        employee.setBranchId(scanner.nextInt());
        scanner.nextLine(); // consume newline
        System.out.println("Enter New Branch Name: ");
        employee.setBranchName(scanner.nextLine());

        try {
            adminManagerService.editEmployee(employee);
            System.out.println("Employee edited successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteEmployee() {
        System.out.println("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        adminManagerService.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }

    private static void viewBranches() {
        for (Branch branch : adminManagerService.viewBranches()) {
            System.out.println(branch.getId() + " " + branch.getName() + " " + branch.getAddress());
        }
    }

    private static void addBranch() {
        Branch branch = new Branch();
        System.out.println("Enter Branch Name: ");
        branch.setName(scanner.nextLine());
        System.out.println("Enter Branch Short Name: ");
        branch.setShortName(scanner.nextLine());
        System.out.println("Enter Branch Address: ");
        branch.setAddress(scanner.nextLine());

        try {
            adminManagerService.addBranch(branch);
            System.out.println("Branch added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void editBranch() {
        System.out.println("Enter Branch ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Branch branch = new Branch();
        branch.setId(id);
        System.out.println("Enter New Branch Name: ");
        branch.setName(scanner.nextLine());
        System.out.println("Enter New Short Name: ");
        branch.setShortName(scanner.nextLine());
        System.out.println("Enter New Address: ");
        branch.setAddress(scanner.nextLine());

        try {
            adminManagerService.editBranch(branch);
            System.out.println("Branch edited successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteBranch() {
        System.out.println("Enter Branch ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        adminManagerService.deleteBranch(id);
        System.out.println("Branch deleted successfully.");
    }

    private static void managePaymentMethods() {
        System.out.println("1. Add Payment Method");
        System.out.println("2. Edit Payment Method");
        System.out.println("3. Delete Payment Method");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addPaymentMethod();
                break;
            case 2:
                editPaymentMethod();
                break;
            case 3:
                deletePaymentMethod();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addPaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod();
        System.out.println("Enter Payment Method Name: ");
        paymentMethod.setName(scanner.nextLine());

        try {
            adminManagerService.addPaymentMethod(paymentMethod);
            System.out.println("Payment Method added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void editPaymentMethod() {
        System.out.println("Enter Payment Method ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(id);
        System.out.println("Enter New Payment Method Name: ");
        paymentMethod.setName(scanner.nextLine());

        try {
            adminManagerService.editPaymentMethod(paymentMethod);
            System.out.println("Payment Method edited successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deletePaymentMethod() {
        System.out.println("Enter Payment Method ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        adminManagerService.deletePaymentMethod(id);
        System.out.println("Payment Method deleted successfully.");
    }

    private static void viewReports() {
        for (String report : adminManagerService.viewReports()) {
            System.out.println(report);
        }
    }
}
