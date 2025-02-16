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
	
	//method to display all details of a member
	public void display() {
		String activeStatus = this.activeStatus == true? "Active" : "Inactive"; //simplification of boolean value for user readability
		
		//left justifying each attribute name so that it is more readable
		System.out.printf("%-30s: %s%n", "Active Status", activeStatus); //the local string variable is used
		System.out.printf("%-30s: %d%n", "Id", this.id);
		System.out.printf("%-30s: %s%n", "Name", this.name);
		System.out.printf("%-30s: %s%n", "Gender", this.gender);
		System.out.printf("%-30s: %d%n", "Attendance", this.attendance);
		System.out.printf("%-30s: %.1f%n", "Loyalty Points", this.loyaltyPoints);
		System.out.printf("%-30s: %s%n", "Membership Start Date", this.membershipStartDate);
		System.out.printf("%-30s: %s%n", "DOB", this.DOB);
		System.out.printf("%-30s: %s%n", "Phone", this.phone);
		System.out.printf("%-30s: %s%n", "Email", this.email);
		System.out.printf("%-30s: %s%n", "Location", this.location);
	}
}
