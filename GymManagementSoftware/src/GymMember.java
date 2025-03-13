public abstract class GymMember {
	
    //instance variable declaration for all Gym members
    protected int id, attendance;
    protected String name, location, phone, email, gender, DOB, membershipStartDate;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
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
        
        // setting default attributes
        this.attendance = 0;
        this.loyaltyPoints = 0.0d;
        this.activeStatus = false;
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
		// only setting to true if activeStatus is false
		if(this.activeStatus==false) {
			this.activeStatus = true;
		}
	}
	
	//method that changes activeStatus to false if the membership needs to be deactivated or paused
	public void deactivateMembership() {
		// only setting to false if activeStatus is true
		if(this.activeStatus==true) {
			this.activeStatus = false;
		}
	}

	//method to reset a member's gym data
	public void resetMember() {
		this.activeStatus = false;
		this.attendance = 0;
		this.loyaltyPoints = 0.0d;
	}
	
	public void display() {
	    String activeStatus = this.activeStatus ? "Active" : "Inactive"; // converting boolean to string
	    
	    // updating only relevant JLabels
	    GymGUI.cardLabels[0].setText("Name: " + this.name);
	    GymGUI.cardLabels[1].setText("ID: " + this.id);
	    GymGUI.cardLabels[2].setText("Active Status: " + activeStatus);
	    GymGUI.cardLabels[3].setText("Start Date: " + this.membershipStartDate);
	    GymGUI.cardLabels[4].setText("Location: " + this.location);
	    GymGUI.cardLabels[5].setText("Phone no.: " + this.phone);
	    GymGUI.cardLabels[6].setText("Email: " + this.email);
	    GymGUI.cardLabels[7].setText("DOB: " + this.DOB);
	    GymGUI.cardLabels[8].setText("Attendance: " + this.attendance);
	    GymGUI.cardLabels[9].setText("Loyalty Points: " + String.format("%.1f", this.loyaltyPoints));
	}


}
