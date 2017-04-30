package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AdminPOJOTable {
	
	public  SimpleIntegerProperty adminID = new SimpleIntegerProperty();
	   public  SimpleStringProperty FName= new SimpleStringProperty();
	   public  SimpleStringProperty LName= new SimpleStringProperty();
	   public  SimpleStringProperty city= new SimpleStringProperty();

    public AdminPOJOTable() {
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
//    }
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
    }
    

    	}

