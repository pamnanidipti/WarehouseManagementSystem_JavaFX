package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AdminPOJOTable {
	
	public SimpleIntegerProperty adminID = new SimpleIntegerProperty();
	   public SimpleStringProperty FName = new SimpleStringProperty(); 
	   public SimpleStringProperty LName = new SimpleStringProperty();
	   public SimpleStringProperty city = new SimpleStringProperty();
	  
	   public Integer getadminID() {
	      return adminID.get();
	   }

	   public String getFName() {
	      return FName.get();
	   }

	   public String getLName() {
	      return LName.get();
	   }

	   public String getUserType() {
	      return city.get();
	   }

	}

