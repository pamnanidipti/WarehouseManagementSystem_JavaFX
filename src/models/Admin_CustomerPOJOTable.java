package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Admin_CustomerPOJOTable {
	
	public  SimpleIntegerProperty customerID = new SimpleIntegerProperty();
	   public  SimpleStringProperty customerFName= new SimpleStringProperty();
	   public  SimpleStringProperty customerLName= new SimpleStringProperty();
	   public  SimpleStringProperty customerAddress= new SimpleStringProperty();

    public Admin_CustomerPOJOTable() {
    }

	public int getCustomerID() {
		return customerID.getValue();
	}

	public void setCustomerID(SimpleIntegerProperty customerID) {
		this.customerID = customerID;
	}

	public String getCustomerFName() {
		return customerFName.getValue();
	}

	public void setCustomerFName(SimpleStringProperty customerFName) {
		this.customerFName = customerFName;
	}

	public String getCustomerLName() {
		return customerLName.getValue();
	}

	public void setCustomerLName(SimpleStringProperty customerLName) {
		this.customerLName = customerLName;
	}

	public String getCustomerAddress() {
		return customerAddress.getValue();
	}

	public void setCustomerAddress(SimpleStringProperty customerAddress) {
		this.customerAddress = customerAddress;
	}

           
//    public AdminPOJOTable(int adminID, String FName,String LName,String city ) {
//        this.adminID = new SimpleIntegerProperty(adminID);
//        this.FName = new SimpleStringProperty(FName);
//        this.LName = new SimpleStringProperty(LName);
//        this.city = new SimpleStringProperty(city);
//        
//    }
    
//    public void setAdminId(Integer adminID) {
//        this.adminID.set(adminID);
//    }
//    public void setAdminFName(String adminFname) {
//        this.FName.set(adminFname);
//    }
//    public void setAdminLName(String adminLname) {
//        this.LName.set(adminLname);
//    }
//    public void setAdminCity(String city) {
//        this.city.set(city);
/*//    }
    public int getAdminID() {
        return adminID.getValue();
    }

    public String getFName() {
        return FName.getValue();
    }

    public String getLName() {
        return LName.getValue();
    }

    public String getCity() {
        return city.getValue();
    }*/
    
    

    	}

