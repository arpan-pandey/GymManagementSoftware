
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
		
		this.paidAmount += paidAmount;
		double remainingAmount = this.premiumCharge-this.paidAmount;
		
		// when paid amount is more than required
		if(this.paidAmount>this.premiumCharge) {
			
			// calculating excess amount
			double excessAmount = this.paidAmount-this.premiumCharge;		
			this.paidAmount= this.premiumCharge; // updating the paid amount
			this.isFullPayment= true; // since full payment is paid amount is equal to premium charge
			
			return String.format("Rs.%.2f has been accepted to settle the full payment. Excess amount Rs.%.2f has not been used.",this.premiumCharge, excessAmount);
		}
		// to check if full payment is complete
		else if(this.paidAmount==this.premiumCharge) {
			this.isFullPayment = true;
			
	        return String.format("Rs.%.2f has been successfully paid, and your plan is now fully paid for.", paidAmount, this.paidAmount);
		}
		// when paid amount is less than required
		else {
			return String.format("Rs.%.2f has been successfully paid, and your remaining amount is Rs.%.2f.", paidAmount, remainingAmount);			
		}
	}
	
	// method to calculate discount for premium members who have paid the full amount
	public String calculateDiscount() {
		if(this.isFullPayment) {
			this.discountAmount = 0.1 * this.premiumCharge;
			return String.format("Congratulations! A discount of Rs.%.2f has been applied to this membership.", this.discountAmount);
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
	
	// overridden method to display all the details of a premium member
	@Override
	public String display() {
	    // calling the superclass's display method and storing the returned string
	    String baseDetails = super.display();

	    // calculation of remaining amount
	    double remainingAmount = this.premiumCharge - this.paidAmount;
	    
	    // converting boolean to a readable format
	    String isFullPayment = this.isFullPayment ? "Yes" : "No";

	    // constructing the final string with the same delimiter '|'
	    return String.join("~",
	        baseDetails,
	        String.format("%.2f", this.paidAmount),
	        String.format("%.2f", remainingAmount),
	        isFullPayment,
	        (this.isFullPayment ? String.format("%.2f", this.discountAmount) : "Ineligible"), // adding only if fully paid
	        this.personalTrainer
	    );
	}		
}
