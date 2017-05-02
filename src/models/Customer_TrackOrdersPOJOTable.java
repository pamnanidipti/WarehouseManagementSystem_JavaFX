package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer_TrackOrdersPOJOTable {
	
	public  SimpleIntegerProperty ordID = new SimpleIntegerProperty();
	   public  SimpleStringProperty cusaddress= new SimpleStringProperty();
	   public  SimpleIntegerProperty pordID = new SimpleIntegerProperty();
	   public  SimpleStringProperty ProdDescp= new SimpleStringProperty();
	   public  SimpleStringProperty prodName= new SimpleStringProperty();
	   public  SimpleIntegerProperty pordQuant = new SimpleIntegerProperty();
	   
	   
	   
	   
	   public Customer_TrackOrdersPOJOTable() {
	    }
	

	public int getOrdID() {
		return ordID.getValue();
	}

	public String getCusaddress() {
		return cusaddress.getValue();
	}

	public int getPordID() {
		return pordID.getValue();
	}

	public String getProdDescp() {
		return ProdDescp.getValue();
	}

	public String getProdName() {
		return prodName.getValue();
	}

	public int getPordQuant() {
		return pordQuant.getValue();
	}

	/*public void setOrdID(SimpleIntegerProperty ordID) {
		this.ordID = ordID;
	}

	public void setCusaddress(SimpleIntegerProperty cusaddress) {
		this.cusaddress = cusaddress;
	}

	public void setPordID(SimpleIntegerProperty pordID) {
		this.pordID = pordID;
	}

	public void setProdDescp(SimpleStringProperty prodDescp) {
		ProdDescp = prodDescp;
	}

	public void setProdName(SimpleStringProperty prodName) {
		this.prodName = prodName;
	}

	public void setPordQuant(SimpleIntegerProperty pordQuant) {
		this.pordQuant = pordQuant;
	}
	*/
	
	
	
}

	
 

