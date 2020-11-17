package edu.monash.javarevision1p5;// Fig. 10.5: SalariedEmployee.java
// SalariedEmployee concrete class extends abstract class Employee.

public class SalariedEmployee extends Employee {
	private double weeklySalary;

	// four-argument constructor
	// note call to super class's Constructor which is efficient since we can't access private inst vars in subclass
	public SalariedEmployee(String first, String last, String ssn, double salary) {
		super(first, last, ssn); // pass to Employee constructor
		setWeeklySalary(salary); // validate and store salary
	} // end four-argument SalariedEmployee constructor

	// weeklySalary accessor and mutator
	public void setWeeklySalary(double salary) {
		if (salary >= 0.0)
			weeklySalary = salary;
		else
			throw new IllegalArgumentException("Weekly salary must be >= 0.0");
	}

	public double getWeeklySalary() {
		return weeklySalary;
	}

    // KEEP INHERITED INTERFACE PROMISE OF ABSTRACT CLASS (this is an implementation not an override but @override still works)
    @Override
	public double getPaymentAmount() {
		return getWeeklySalary();
	}

	// return String representation of SalariedEmployee object
	// note call to super class's toString which is efficient since we can't access private inst vars in subclass
	@Override
	public String toString() {
		return String.format("salaried employee: %s\n%s: $%,.2f", super.toString(), "weekly salary", getWeeklySalary());
	}
} 

