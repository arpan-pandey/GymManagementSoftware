
public class PremiumMember extends GymMember{
	
	//unique instance variable declaration and initialization of default values for Premium members
	final double premiumCharge=50000d;
	double paidAmount=0d, discountAmount=0d;
	boolean isFullPayment=false;
	String personalTrainer;
	
	public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer){
		
		super(id, name, location, phone, email, gender, DOB, membershipStartDate); //calling super class constructor with appropriate arguments
		this.personalTrainer = personalTrainer; //initializing unique instance variable
	}
	
	
	//accessor methods for each unique attribute
	public double getPremiumCharge() { return this.premiumCharge; }
	public double getPaidAmount() { return this.paidAmount; }
	public double getDiscountAmount() { return this.discountAmount; }
	public boolean isFullPayment() { return this.isFullPayment; }
	public String getPersonalTrainer() { return this.personalTrainer; }
	
	
	//implementation of abstract method of parent class
	@Override
	public void markAttendance() {
		attendance++; //increasing attendance by 1
		loyaltyPoints+=5; //increasing legacy points by 5
	}
		
	// method to update due amount
	public String payDueAmount(double paidAmount) {
		
		// to check if full payment has already been done
		if(this.isFullPayment==true) {
			return "Your plan has already been paid fully.";
		}
		
		double remainingAmount = this.premiumCharge-this.paidAmount;
		
		// to check if paid amount is more than required
		if((this.paidAmount+paidAmount)>this.premiumCharge) {
			this.paidAmount= this.premiumCharge; // updating the paid amount
			this.isFullPayment= true; // since full payment is paid amount is equal to premium charge
			return String.format("Rs.%.2f has been accepted to settle the full payment. Excess amount Rs.%.2f has not been used.",remainingAmount, paidAmount-remainingAmount);
		}
		
		this.paidAmount += paidAmount;
		
		// to check if full payment is complete
		if(this.paidAmount==this.premiumCharge) {
			this.isFullPayment = true;
	        return String.format("Rs.%.2f has been successfully paid, and your total paid amount is Rs.%.2f. Your plan is now fully paid.", paidAmount, this.paidAmount);
		}
		
		return String.format("Rs.%.2f has been successfully paid, and your remaining amount is Rs.%.2f.", paidAmount,this.paidAmount);
	}
	
	// method to calculate discount for premium members who have paid the full amount
	public String calculateDiscount() {
		if(this.isFullPayment) {
			this.discountAmount = 0.1 * this.premiumCharge;
			return String.format("Congratulations! A discount of Rs.%.2f has been applied to your account.", this.discountAmount);
		}
		else {
			return "Sorry! Discount isn't applicable since payment hasn't been paid fully yet.";
		}
	}
	
	// method to revert member back to regularMember, and reset all data
	public void revertPremiumMember() {
		super.resetMember(); // calling super class's reset member method
		
		//resetting this sub class's unique attributes
		this.personalTrainer="";
		this.isFullPayment=false;
		this.paidAmount = this.discountAmount = 0d;
	}
	
	// overrided method to display all the details of a premium member
	@Override
	public void display() {
		
		super.display(); // calling super class's display method
		
		// displaying the plan and price of every regular member in addition to other details
		System.out.printf("%-30s: %s%n", "Personal Trainer", this.personalTrainer);
		System.out.printf("%-30s: Rs.%.2f%n", "Paid Amount", this.paidAmount);
		
		// calculation of remaining amount 
		double remainingAmount = this.premiumCharge-this.paidAmount;
		
		// displaying remaining amount only if there is payment remaining
		if(remainingAmount!=0d) {
			System.out.printf("%-30s: Rs.%.2f%n", "Remaining Amount", remainingAmount);
		}
		
		String isFullPayment = this.isFullPayment==true? "Yes":"No"; //overriding this sub class's isFullPayment attribute for better understandability
		System.out.printf("%-30s: Rs.%s%n", "Fully paid?", isFullPayment); //use of overrided attribute
		
		// displaying  of a regular member, if there is one
		if(this.isFullPayment==true) {
			System.out.printf("%-30s: Rs.%.2f%n", "Discount Amount", this.discountAmount);
		}
	}
		
		
		
}
