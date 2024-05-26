package com.mycompany.couriermanagementsystem.Interfaces;

import com.mycompany.couriermanagementsystem.Models.Client;
import com.mycompany.couriermanagementsystem.Models.Parcel;
import java.util.Date;
import java.util.List;

public interface ClientService {
    void registerClient(Client client);
    void addParcelRequest(Parcel parcel);
    void cancelParcelRequest(int parcelId);
    void updateParcelInformation(Parcel updatedParcel);
    Parcel trackParcel(int parcelId);
    List<Parcel> viewServiceHistory(int clientId);
    void updateProfileInformation(Client updatedClient);
    void schedulePickupTime(int parcelId, Date pickupTime);
    String viewParcelPackagingGuidelines();
    double viewDeliveryFee(String option);
    void pickupRequest(int parcelId);
}

