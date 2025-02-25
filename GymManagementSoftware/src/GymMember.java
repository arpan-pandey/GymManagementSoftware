public abstract class GymMember {
	
	//instance variable declaration and initialization of default values for all Gym members
	protected int id, attendance= 0;
	protected String name, location, phone, email, gender, DOB, membershipStartDate;
	protected double loyaltyPoints= 0.0d;
	protected boolean activeStatus= false;
	
	public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate) {
		//parent class constructor, to initialize the instance variables
		
		this.id = id;
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.DOB = DOB;
		this.membershipStartDate = membershipStartDate;
	}
	
	
	//accessor methods for each attribute
	public int getId() { return this.id; }
	public int getAttendance() { return this.attendance; }
	public String getName() { return this.name; }
	public String getLocation() { return this.location; }
	public String getPhone() { return this.phone; }
	public String getEmail() { return this.email; }
	public String getGender() { return this.gender; }
	public String getDOB() { return this.DOB; }
	public String getMembershipStartDate() { return this.membershipStartDate; }
	public double getLoyaltyPoints() { return this.loyaltyPoints; }
	public boolean isActiveStatus() { return this.activeStatus; }
	
	
	//abstract method for marking attendance
	public abstract void markAttendance();
	
	//method that changes activeStatus to true if the membership needs to be activated or renewed
	public void activateMembership() {
		this.activeStatus = true;
	}
	
	//method that changes activeStatus to false if the membership needs to be deactivated or paused
	public void deactivateMembership() {
		this.activeStatus = false;
	}

	//method to reset a member's gym data
	public void resetMember() {
		this.activeStatus = false;
		this.attendance = 0;
		this.loyaltyPoints = 0.0d;
	}
	
	// method to display all details of a member
	public String display() {
	    String activeStatus = this.activeStatus ? "Active" : "Inactive"; // simplification of boolean value for user readability
	    
	    // constructing a single string with '|' as the delimiter
	    return String.join("~",
		    this.name,
	    	String.valueOf(this.id), // int to String
	        activeStatus,
	        this.membershipStartDate,
	        this.location,
	        this.phone,
	        this.email,
	        this.DOB,
	        String.valueOf(this.attendance),
	        String.format("%.1f", this.loyaltyPoints),
	        this.gender
	    );
	}

}
