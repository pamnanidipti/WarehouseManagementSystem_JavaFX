package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer_AvailablePOJOTable {
	
	public  SimpleIntegerProperty productID = new SimpleIntegerProperty();
	   public  SimpleIntegerProperty availQuant= new SimpleIntegerProperty();
	   public  SimpleStringProperty proName= new SimpleStringProperty();
	   public  SimpleStringProperty ProStatus= new SimpleStringProperty();
	   public  SimpleStringProperty ProDesc= new SimpleStringProperty();
	   
	   
	   
	   
	   public Customer_AvailablePOJOTable() {
	    }
	   
	   public int getProductID() {
		return productID.getValue();
	}

	

	public int getAvailQuant() {
		return availQuant.getValue();
	}
	
	
	public String getProName() {
		return proName.getValue();
	}
	
	public String getProStatus() {
		return ProStatus.getValue();
	}
	
	
	public String getProDesc() {
		return ProDesc.getValue();
	}
	
	public void setProductID(SimpleIntegerProperty productID) {
		this.productID = productID;
	}
	public void setAvailQuant(SimpleIntegerProperty availQuant) {
		this.availQuant = availQuant;
	}

	public void setProName(SimpleStringProperty proName) {
		this.proName = proName;
	}


	public void setProStatus(SimpleStringProperty proStatus) {
		ProStatus = proStatus;
	}

	

	public void setProDesc(SimpleStringProperty proDesc) {
		ProDesc = proDesc;
	}

}

	/*public int getCustomerID() {
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
	}*/

 

