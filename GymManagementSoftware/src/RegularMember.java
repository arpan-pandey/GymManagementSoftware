
public class RegularMember extends GymMember{
	
    //unique instance variables declaration for Regular members
    private final int attendanceLimit;
    private String referralSource, plan, removalReason;
    private boolean isEligibleForUpgrade;
    private double price;
    
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource) {
        
        super(id, name, location, phone, email, gender, DOB, membershipStartDate); //calling super class constructor with appropriate arguments
        this.referralSource = referralSource; //initializing unique instance variable
        
        // setting default attributes:
        this.attendanceLimit = 30; // final
        this.plan = "Basic";
        this.removalReason = ""; // null
        this.isEligibleForUpgrade = false;
        this.price = 6500d;
    }
	
	
	//accessor methods for each unique attribute
	public int getAttendanceLimit() { return this.attendanceLimit; }	
	public String getReferralSource() { return this.referralSource; }
	public String getPlan() { return this.plan; }
	public String getRemovalReason() { return this.removalReason; }
	public double getPrice() { return this.price; }
	public boolean isEligibleForUpgrade() { return this.isEligibleForUpgrade; }
	

	//implementation of abstract method of parent class
	@Override
	public void markAttendance() {
		attendance++; //increasing attendance by 1
		loyaltyPoints+=5; //increasing legacy points by 5
		
		// updating isEligibleForUpgrade to true if (attendance of member >= attendance limit)
		this.isEligibleForUpgrade = super.getAttendance()>=this.attendanceLimit? true : false;
	}
	
	//method that returns plan price
	public double getPlanPrice(String plan) {
		
		//usage of switch case to return appropriate value
		
		switch(plan.toLowerCase()) { //parameter 'plan' is converted to lowercase for consistency 
		
		case "basic": return 6500d; 
			
		case "standard": return 12500d;
			
		case "deluxe": return 18500d;
			
		default: return -1d; //in case of invalid plan
			
		} // break statement isn't needed because return statement is used and a break statement will be unreachable anyways
	}
	
	// method to upgrade a member's plan
	public String upgradePlan(String plan) {
		 
		// to return error message when the member is not eligible to upgrade
		if(!this.isEligibleForUpgrade) {
			return "Not eligible for an upgrade yet!";
		}
		
		// to check if selected plan is the same as current plan [ equalsIgnoreCase() method is used for consistency ]
		else if(this.plan.equalsIgnoreCase(plan)) {
			return "Already subscribed to the same plan.";
		}
		
		// when member is eligible and valid plan is selected
		else {
			// to get price of new plan [ stored in a local variable ]
			double price = this.getPlanPrice(plan);
			
			// to check the validity of selected plan
			if(price == -1d) {
				return "Invalid plan selected!";
			}
			 
			// updating the plan and price if selected plan is valid, and the member is eligible for upgrade
			this.plan = plan;
			this.price = price;
			
			// success message
			return String.format("Your plan has been upgraded to %s for Rs.%.2f.",this.plan,this.price);
		}
	}

	// method to revert member back to regularMember, and reset all data
	public void revertRegularMember(String removalReason) {
		super.resetMember(); // calling super class's reset member method
		
		// resetting this sub class's unique attributes
		this.isEligibleForUpgrade = false;
		this.plan = "Basic";
		this.price = 6500d;
		this.removalReason = removalReason;
	}
	
	
	@Override
	public void display() {
	    super.display(); // calling parent class's display method to update common attributes

	    // updating regular member-specific attributes in remaining labels
	    GymGUI.cardLabels[10].setText("Current Plan: " + this.plan);
	    GymGUI.cardLabels[11].setText("Plan Price: " + String.format("%.2f", this.price));
	    GymGUI.cardLabels[12].setText("Can Upgrade? : " + (this.isEligibleForUpgrade() ? "Yes" : "No"));
	    GymGUI.cardLabels[13].setText("Referral Source: " + this.getReferralSource());
	    GymGUI.cardLabels[14].setText("Removal Reason: " + (!this.removalReason.isEmpty() ? this.removalReason : "N/A"));
	}


}
	
