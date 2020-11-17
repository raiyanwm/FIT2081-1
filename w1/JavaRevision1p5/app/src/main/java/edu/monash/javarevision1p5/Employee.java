package edu.monash.javarevision1p5;// Fig. 10.4: Employee.java
// Employee abstract superclass.

//abstract because we never intend to instantiate objects from it
//abstract because we want it to contain a abstract method earnings which will be implemented differently for each subclass
public abstract class Employee implements Payable{
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;

	// three-argument constructor
	public Employee(String first, String last, String ssn) {
		firstName = first;
		lastName = last;
		socialSecurityNumber = ssn;
	}

	// first name accessor and mutator
	public void setFirstName(String first) {
		firstName = first; // should validate
	}
	
	public String getFirstName() {
		return firstName;
	}

	// last name accessor and mutator
	public void setLastName(String last) {
		lastName = last; // should validate
	}

	public String getLastName() {
		return lastName;
	}

	// social security number accessor and mutator
	public void setSocialSecurityNumber(String ssn) {
		socialSecurityNumber = ssn; // should validate
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	
	
	// return String representation of Employee object
	//@overide is not required but if used the compiler will check its really overriding
	// i.e. you got the signature correct and are not accidentally overloading or adding a new method altogether - very useful
	@Override 
	public String toString() {
		return String.format("%s %s\nsocial security number: %s",
				getFirstName(), getLastName(), getSocialSecurityNumber());
	} // end method toString

	
	
	//1. WE WOULD WANT TO RENAME THE ABSTRACT earnings() METHOD TO getPaymentAmount() TO KEEP THE PAYABLE INTERFACE PROMISE
	//2. IT'S NOT NECESSARY BECAUSE Employee IS ABSTRACT THE COMPILER ALLOWS NO MENTION OF getPaymentAmount()
	//   KNOWING THAT THE INTERFACE PROMISE WILL BE INHERITED BY ANY SUBCLASSES (but not sub sub classes) OF Employee WHICH WILL THEREFORE HAVE TO KEEP
	//   THE INTERFACE PROMISE OF IMPLEMENTING getPaymentAmount 
	//   SEE DEITEL P747 LAST PARA
	
	// abstract method must be overridden by concrete subclasses
	//public abstract double earnings(); // no implementation here
	

}

