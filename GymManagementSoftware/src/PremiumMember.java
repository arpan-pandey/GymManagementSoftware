
public class PremiumMember extends GymMember{
	
    //unique instance variable declaration for Premium members
    private final double premiumCharge;
    private double paidAmount, discountAmount;
    private boolean isFullPayment;
    private String personalTrainer;
    
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer){
        
        super(id, name, location, phone, email, gender, DOB, membershipStartDate); //calling super class constructor with appropriate arguments
        this.personalTrainer = personalTrainer; //initializing unique instance variable
    
        // setting default attributes:
        premiumCharge = 50000d; // final
        this.paidAmount = 0.0d;
        this.discountAmount = 0.0d;
        this.isFullPayment = false;
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
		loyaltyPoints+=10; //increasing legacy points by 10
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
			
			return String.format("Rs.%.2f has been accepted to settle the full payment. Excess amount Rs.%.2f has not been used.",paidAmount-excessAmount, excessAmount);
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
	
	@Override
	public void display() {
	    super.display(); // calling parent class's display method to update common attributes

	    double remainingAmount = this.premiumCharge - this.paidAmount;
	    String isFullPayment = this.isFullPayment ? "Yes" : "No";
	    String discountDisplay = this.isFullPayment ? (this.getDiscountAmount()==0.0d? "Uncalculated" : String.format("%.2f", this.discountAmount)) : "Ineligible";
	    String trainerDisplay = !this.personalTrainer.isEmpty() ? this.personalTrainer : "N/A";

	    // updating premium member-specific attributes in remaining labels
	    GymGUI.cardLabels[10].setText("Paid Amount: " + String.format("%.2f", this.paidAmount));
	    GymGUI.cardLabels[11].setText("Remaining Amount: " + String.format("%.2f", remainingAmount));
	    GymGUI.cardLabels[12].setText("Full Payment? : " + isFullPayment);
	    GymGUI.cardLabels[13].setText("Discount Amount: " + discountDisplay);
	    GymGUI.cardLabels[14].setText("Trainer Name: " + trainerDisplay);
	}
		
}
