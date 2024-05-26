package com.mycompany.couriermanagementsystem.Interfaces;

import com.mycompany.couriermanagementsystem.Models.Parcel;
import com.mycompany.couriermanagementsystem.Models.Employee;

import java.util.List;

public interface BranchManagerService {
    void addNewParcel(Parcel parcel, Employee user);
    void updateParcel(int id, Parcel updatedParcel);
    Parcel viewParcelDetails(int id);
    List<Parcel> viewParcelList();
    void cancelParcel(int parcelId);
    List<Parcel> viewNewCourierRequests(Employee loggedInUser);
    void assignPickupBoy(Parcel parcel, Employee pickupBoy);
    void sendProductToDestination(Parcel parcel);
    void receiveProductFromSource(Parcel parcel);
    void assignDeliveryBoy(Parcel parcel, Employee deliveryBoy);
    void updateProductStatusToDelivered(Parcel parcel);
    Parcel.ParcelStatusEnum trackParcel(Parcel parcel);
    void handleReturnedOrCancelledParcels();
    void generateAndViewReports(Employee user);
}

