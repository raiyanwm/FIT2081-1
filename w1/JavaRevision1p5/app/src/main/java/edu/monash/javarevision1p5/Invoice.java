package edu.monash.javarevision1p5;// Fig. 10.12: Invoice.java
// Invoice class implements Payable.

public class Invoice implements Payable { //INTERFACE PROMISE (see last method)
	private String partNumber;
	private String partDescription;
	private int quantity;
	private double pricePerItem;

	// four-argument constructor
	public Invoice(String part, String description, int count, double price) {
		partNumber = part; //bad style
		partDescription = description; //bad style
		setQuantity(count); // validate and store quantity
		setPricePerItem(price); // validate and store price per item
	}

	// partNumber accessor and mutator
	public void setPartNumber(String part) {
		partNumber = part;
	}

	public String getPartNumber() {
		return partNumber;
	}

	// description accessor and mutator
	public void setPartDescription(String description) {
		partDescription = description;
	}

	public String getPartDescription() {
		return partDescription;
	}

	// quantity accessor and mutator
	public void setQuantity(int count) {
		quantity = (count < 0) ? 0 : count; // quantity cannot be negative
	}

	public int getQuantity() {
		return quantity;
	}

	// pricePerItem accessor and mutator
	public void setPricePerItem(double price) {
		pricePerItem = (price < 0.0) ? 0.0 : price; // validate price
	}

	public double getPricePerItem() {
		return pricePerItem;
	}

	
	// return String representation of Invoice object
	public String toString() {
		return String.format("%s: \n%s: %s (%s) \n%s: %d \n%s: $%,.2f",
				"invoice", "part number", getPartNumber(), getPartDescription(), "quantity", getQuantity(), "price per item", getPricePerItem());
	}

	
	
	// method REQUIRED to carry out contract with interface Payable
	// PROMISE KEPT
	public double getPaymentAmount() {
		return getQuantity() * getPricePerItem(); // calculate total cost
	}
	
}
