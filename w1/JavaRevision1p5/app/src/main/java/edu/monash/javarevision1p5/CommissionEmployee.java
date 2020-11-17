package edu.monash.javarevision1p5;// Fig. 10.7: CommissionEmployee.java
// CommissionEmployee class extends Employee.

public class CommissionEmployee extends Employee {
	private double grossSales; // gross weekly sales
	private double commissionRate; // commission percentage

	// five-argument constructor
	public CommissionEmployee(String first, String last, String ssn, double sales, double rate) {
		super(first, last, ssn);
		setGrossSales(sales);
		setCommissionRate(rate);
	}

	// commissionRate accessor and mutator
	public void setCommissionRate(double rate) {
		if (rate > 0.0 && rate < 1.0)
			commissionRate = rate;
		else
			throw new IllegalArgumentException(
					"Commission rate must be > 0.0 and < 1.0");
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	// grossSales accessor and mutator 
	public void setGrossSales(double sales) {
		if (sales >= 0.0)
			grossSales = sales;
		else
			throw new IllegalArgumentException("Gross sales must be >= 0.0");
	}

	public double getGrossSales() {
		return grossSales;
	}

    // KEEP INHERITED INTERFACE PROMISE OF ABSTRACT CLASS (this is an implementation not an override but @override still works)
    @Override
	public double getPaymentAmount() {
		return getCommissionRate() * getGrossSales();
	}

	// return String representation of CommissionEmployee object
	@Override
	public String toString() {
		return String.format("%s: %s\n%s: $%,.2f; %s: %.2f", "commission employee", super.toString(), "gross sales",
				getGrossSales(), "commission rate", getCommissionRate());
	}
	
}

