
package com.mycompany.couriermanagementsystem.Interfaces;

import com.mycompany.couriermanagementsystem.Models.Branch;
import com.mycompany.couriermanagementsystem.Models.Employee;
import com.mycompany.couriermanagementsystem.Models.PaymentMethod;
import java.util.List;

public interface AdminManagerService {
    void addEmployee(Employee employee);
    void editEmployee(Employee employee);
    void deleteEmployee(int employeeId);
    List<Employee> viewEmployees();

    void addBranch(Branch branch);
    void editBranch(Branch branch);
    void deleteBranch(int branchId);
    List<Branch> viewBranches();

    void addPaymentMethod(PaymentMethod paymentMethod);
    void editPaymentMethod(PaymentMethod paymentMethod);
    void deletePaymentMethod(int paymentMethodId);
    List<PaymentMethod> viewPaymentMethods();

    List<String> viewReports();
}
