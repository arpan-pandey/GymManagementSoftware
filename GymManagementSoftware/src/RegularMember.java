
public class RegularMember extends GymMember{
	
	//unique instance variable declaration and initialization of default values for Regular members
	private final int attendanceLimit = 30;
	private String referralSource, plan = "Basic", removalReason="";
	private boolean isEligibleForUpgrade = false;
	private double price = 6500d;
	
	public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource) {
		
		super(id, name, location, phone, email, gender, DOB, membershipStartDate); //calling super class constructor with appropriate arguments
		this.referralSource = referralSource; //initializing unique instance variable
		
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
		
		// updating isEligibleForUpgrade to true if (attendance of member >= attendance limit)
		this.isEligibleForUpgrade = super.getAttendance()>=this.attendanceLimit? true : false;
		 
		// to return error message when the member is not eligible to upgrade
		if(!this.isEligibleForUpgrade) {
			return "Not eligible for an upgrade yet!";
		}
		
		// to check if selected plan is the same as current plan [ equalsIgnoreCase() method is used for consistency ]
		if(this.plan.equalsIgnoreCase(plan)) {
			return "Already subscribed to the same plan.";
		}
		
		// to get price of new plan [ stored in a local variable ]
		double price = getPlanPrice(plan);
		 
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

	// method to revert member back to regularMember, and reset all data
	public void revertRegularMember(String removalReason) {
		super.resetMember(); // calling super class's reset member method
		
		// resetting this sub class's unique attributes
		this.isEligibleForUpgrade = false;
		this.plan = "Basic";
		this.price = 6500d;
		this.removalReason = removalReason;
	}
	
	
	// overridden method to display all the details of a regular member
	@Override
	public String display() {
	    // calling the superclass's display method and storing the returned string
	    String baseDetails = super.display();

	    // constructing the final string with the same delimiter '|'
	    return String.join("~",
	        baseDetails,
	        this.plan,
	        String.format("%.2f", this.price),
	        this.isEligibleForUpgrade()?"Yes":"No",
	        this.getReferralSource(),
	        (!this.removalReason.isEmpty() ? this.removalReason : "") // adding only if non-empty
	    );
	}

}
	
