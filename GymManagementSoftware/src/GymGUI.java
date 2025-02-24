/*
 * Color palette:
 * 
 * #0D1B2A (Dark Navy)
 * #1B263B (Midnight Blue)
 * #2E4057 (Gunmetal Blue)
 * #415A77 (Steel Blue)
 * #A3B7C8 (Pastel Blue)
 * #E0E1DD (Light Gray)
 * 
 * Extra Colors:
 * 
 * #FFFFFF (White)
 * #DDDEEE (Dark Gray)
 * #696969 (Gray (for placeholder))   
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class GymGUI{
	
	
	/*
	 * color variables
	 */
	
	// palette colors
	final Color DARKNAVY = new Color(0x0D1B2A);
    final Color MIDNIGHTBLUE = new Color(0x1B263B);
    final Color GUNMETALBLUE = new Color(0x2E4057);
    final Color STEELBLUE = new Color(0x415A77);
    final Color PASTELBLUE = new Color(0xA3B7C8);
    final Color LIGHTGRAY = new Color(0xF5F5F5);
    
    //extra colors
    final Color DARKGRAY = new Color(0xDDDEEE);
    final Color WHITE = new Color(0xFFFFFF);
    final Color PLACEHOLDERGRAY = new Color(0x696969); // input field placeholder color

    
    
    /*
	 * font variables
	 */
    
    // button fonts
    final Font BUTTON_FONT = new Font("Century Gothic", Font.PLAIN, 14);
    final Font BUTTON_FONT_ACTIVE = new Font("Century Gothic", Font.BOLD, 14);
    
    // body fonts
    final Font CONTENT_TITLE_FONT = new Font("Century Gothic", Font.BOLD, 20);
    final Font TITLE_SYMBOL_FONT = new Font("Century Gothic", Font.PLAIN, 20);
    final Font HEADING1_FONT = new Font("Century Gothic", Font.BOLD, 18);
    final Font PARAGRAPH_FONT = new Font("Century Gothic", Font.PLAIN, 16);
    
    // form font
    final Font INPUT_HEADING = new Font("Century Gothic", Font.BOLD, 15);
    final Font INPUT_FONT = new Font("Helvetica", Font.PLAIN, 14);
    final Font RADIO_FONT = new Font("Century Gothic", Font.BOLD, 14);
    
    // memberManagement input font
    final Font MANAGEMENT_INPUT_HEADING = new Font("Century Gothic", Font.BOLD, 13);
    final Font NON_EDITABLE_FIELD_FONT = new Font("Helvetica", Font.BOLD, 14);
    
    
    
    /*
     * Border, Margin & Padding Variables
     */
    
    final int horizontalMargin = 15;
    
    final int marginTopBottom = 38, marginLeftRight = 50; // margins for content sections
    final int buttonPaddingVertical = 10; // padding inside buttons
    final int formMarginTop = 45, formMarginBottom = 28; // margins for content sections
    final int planMarginTop = 14, planMarginLeft = 10; // margins for content sections
    
    final int cardMarginTop = 22, cardMarginBottom = 5, cardMarginHorizontal = 20; // margin for individual member card
    final int cardContentMarginVertical = 15, cardContentMarginHorizontal = 10;
    final int cardOddLabelMarginLeft = 11, cardUniqueLabelMarginLeft = 19;
    
    final int managementInputMarginTop = 8, managementInputMarginBottom = 18, managementInputMarginHorizontal = 18;

    // content margin
    final Border CONTENT_MARGIN = BorderFactory.createEmptyBorder(marginTopBottom, marginLeftRight, marginTopBottom, marginLeftRight);

    // borders for button states
    final Border DEFAULT_BUTTON_OUTLINE = BorderFactory.createLineBorder(DARKNAVY, 1);
    
    final Border ACTIVATE$DEACTIVATE_BUTTON_OUTLINE = BorderFactory.createLineBorder(STEELBLUE, 1);
    
    final Border ACTIVE_BUTTON_HIGHLIGHT = BorderFactory.createMatteBorder(0, 2, 0, 0, LIGHTGRAY);
    
    // borders for input field
    final Border FORM_INPUT_OUTLINE = BorderFactory.createMatteBorder(0, 0, 1, 0, MIDNIGHTBLUE);
    final Border FORM_INPUT_ACTIVE_OUTLINE = BorderFactory.createMatteBorder(0, 0, 2, 0, STEELBLUE);
    final Border FORM_INPUT_PADDING = BorderFactory.createEmptyBorder(0, horizontalMargin, 0, horizontalMargin);
    final Border FORM_INPUT_MARGIN = BorderFactory.createEmptyBorder(formMarginTop, horizontalMargin, formMarginBottom, horizontalMargin);
    
    // padding for member management card
    final Border CARD_MARGIN = BorderFactory.createEmptyBorder(cardMarginTop, cardMarginHorizontal, cardMarginBottom, cardMarginHorizontal);
    
    // margin for member management card labels
    final Border CARD_UNIQUE_LABEL_MARGIN_LEFT = BorderFactory.createEmptyBorder(0,cardUniqueLabelMarginLeft,0,0);
    final Border CARD_ODD_LABEL_MARGIN_LEFT = BorderFactory.createEmptyBorder(0,cardOddLabelMarginLeft,0,0);
    
    // margin for member management button panel
    final Border MANAGEMENT_BUTTON_PANEL_MARGIN = BorderFactory.createEmptyBorder(cardMarginTop,0,cardMarginBottom,0);
    
    // margin for member management input field
    final Border MEMBER_MANAGEMENT_INPUT_MARGIN = BorderFactory.createEmptyBorder(managementInputMarginTop,managementInputMarginHorizontal,managementInputMarginBottom,managementInputMarginHorizontal);
    
    // padding inside buttons
    final Border BUTTON_INNER_PADDING = BorderFactory.createEmptyBorder(buttonPaddingVertical, horizontalMargin, buttonPaddingVertical, horizontalMargin);

    // combined borders
    final Border DEFAULT_BUTTON_BORDER = BorderFactory.createCompoundBorder(DEFAULT_BUTTON_OUTLINE, BUTTON_INNER_PADDING);
    final Border ACTIVE_BUTTON_BORDER = BorderFactory.createCompoundBorder(ACTIVE_BUTTON_HIGHLIGHT, BUTTON_INNER_PADDING);
    
    final Border DEFAULT_INPUT_BORDER = BorderFactory.createCompoundBorder(FORM_INPUT_OUTLINE, FORM_INPUT_PADDING);
    final Border ACTIVE_INPUT_BORDER = BorderFactory.createCompoundBorder(FORM_INPUT_ACTIVE_OUTLINE, FORM_INPUT_PADDING);
    
    
	/*
	 * Frame variables
	 */
    
	JFrame frame = new JFrame("Gym Management Software"); // creating a new frame with the title "Gym GUI"
    	final int FRAME_HEIGHT = 650, FRAME_WIDTH = 1100; //height and width of frame
	
	
	/*
	 * MENUBAR VARIABLES
	 */
	
	JPanel menuBar, menuButtonPanel;
		final int MENU_BAR_WIDTH=225; // width of menuBar

	JLabel menuTitle;
	
	//array of menuBar buttons
	JButton[]
		menuButtons = {
				new JButton("Dashboard"),
				new JButton("Add a Member"),
				new JButton("Member Management")
		};
	
	int activeIndex; // variable to store index of active content
	int lastIndex; // variable to store index of last body content
	
	
	/*
	 * dashboard VARIABLES
	 */
	
	JPanel dashboardContent;
	
		JPanel dashboardTitle_P;
			JLabel dashboardTitle_L;
	
		JPanel dashboardTableWrapper_P;
			JPanel dashboardTable_P;
			
	/*
	 * addMember VARIABLES
	 */

	JPanel addMemberContent;
		JPanel addMemberTitle_P;
			JLabel addMemberTitle_L;
			
		JPanel memberTypeSelect_P;
			JLabel memberTypeSelect_L;
				JButton[]
					memberTypeButtons = {
						new JButton("Premium Member"),
						new JButton("Regular Member")
					};
				
				// boolean for if the current panel is membertype content or not (intially false)
				boolean[] isFormContent = {
						false, //premium
						false //regular
						}; 
				
				JPanel[]
				//array of form panels
				formPanels = {
					prem_form_P = new JPanel(),
					regular_form_P = new JPanel()
				};
				
				// member form panels
				JPanel prem_form_P = new JPanel();
				JPanel regular_form_P = new JPanel();
				
				// input panels
				JPanel input_id_P, input_name_P, input_location_P, input_phone_P, input_email_P, input_gender_P, input_DOB_P, input_membershipStartDate_P, input_personalTrainer_P, input_referralSource_P;
					
				// input labels
				JLabel input_id_L, input_name_L, input_location_L, input_phone_L, input_email_L, input_gender_L, input_DOB_L, input_membershipStartDate_L, input_personalTrainer_L, input_referralSource_L; 
				
				// input fields
				JTextField input_id_F, input_name_F, input_location_F, input_phone_F, input_email_F, input_DOB_F, input_membershipStartDate_F, input_personalTrainer_F, input_referralSource_F;
				
				// radio buttons
				JRadioButton input_genderMale, input_genderFemale;
				
						
				/*
				 * FORM ARRAYS
				 */
					
				//array of form panels + initialization
				JPanel[] inputPanels = {
						input_id_P = new JPanel(),
						input_name_P = new JPanel(),
						input_location_P = new JPanel(),
						input_phone_P = new JPanel(),
						input_email_P = new JPanel(),
						input_gender_P = new JPanel(),
						input_DOB_P = new JPanel(),
						input_membershipStartDate_P = new JPanel(),
						input_personalTrainer_P = new JPanel(),	
						input_referralSource_P = new JPanel(),
				};	
						
						
				// array of input labels + initialization
				JLabel[] inputLabels = {
						input_id_L = new JLabel("ID:"),
						input_name_L = new JLabel("Name:"),
						input_location_L = new JLabel("Location:"),
						input_phone_L = new JLabel("Phone:"),
						input_email_L = new JLabel("Email:"),
						input_gender_L = new JLabel("Gender:"),
						input_DOB_L = new JLabel("Date of Birth:"),
						input_membershipStartDate_L = new JLabel("Start Date:"),
						input_personalTrainer_L = new JLabel("Trainer Name:"),
						input_referralSource_L = new JLabel("Referral Source:")
				};
				
				// array of input fields + initialization
				JTextField[] inputFields = {
						input_id_F = new JTextField(),
						input_name_F = new JTextField(),
						input_location_F = new JTextField(),
						input_phone_F = new JTextField(),
						input_email_F = new JTextField(),
						input_DOB_F = new JTextField(),
						input_membershipStartDate_F = new JTextField(),
						input_personalTrainer_F = new JTextField(),	
						input_referralSource_F = new JTextField()	
				};
				
				final ButtonGroup GENDER = new ButtonGroup(); // button group for gender radio buttons
				boolean isGenderSelected = false;
				int indexOfGenderSelected = -1; // initially
				
				// array of gender radio buttons + initialization
				JRadioButton[] genderRadioButtons = {
						input_genderMale = new JRadioButton("Male"),
						input_genderFemale = new JRadioButton("Female")
				};
				
				String[]
						//array of common input placeholders
						commonPlaceholders = {
								"Enter a unique ID", 	// id
								"Enter your name", 		// name
								"Enter city",			// location
								"+977 9XXXXXXXX",		// phone no.
								"name@domain.com",		// email
								"YYYY-MM-DD",			// DOB
								"YYYY-MM-DD",			// Membership Start Date
							};
					
				String[][]
					//array of input field placeholders
					uniquePlaceholders = {
						{
							// premium form placeholder (Trainer name)
							"Enter trainer's name"
						},
						{
							// regular form placeholder (Referral source)
							"eg., Friend, Website, Ad"	
						}
				};
					
					
				/*
				 * FORM CONTROL BUTTONS VARIBALES
				 */
							
				JPanel formButtons_P = new JPanel();
				JPanel formControlButtonsPanel = new JPanel();
					JButton[]
						formButtons= {
								new JButton("Clear All"), // clear form button
								new JButton("Add Premium Member"),
								new JButton("Add Regular Member")
						};

	
	/*
	 * memberManagement VARIABLES
	 */
	
	JPanel memberManagementContent;
	
		JPanel memberManagementTitle_P;
				JLabel memberManagementTitle_L;
				
			JPanel memberManagementButton_P;
				JLabel memberManagementButton_L;
				JButton manageMemberButton = new JButton("Enter member ID");
				
			JPanel individualMemberManagement_P = new JPanel();
				JPanel memberCard_P = new JPanel();
				
				JPanel cardCommonAttributes_P = new JPanel();
				JPanel cardUniqueAttributes_P = new JPanel();
				
				JPanel[]
				//array of member card panels
				cardPanels = {
					cardCommonAttributes_P,
					cardUniqueAttributes_P
				};
				
				JLabel[]
					// card keyword labels
					cardLabels = {
							
						new JLabel("Name : "),
						new JLabel("ID : "),
						new JLabel("Active Status : "),
						new JLabel("Start Date : "),
						new JLabel("Location : "),
						new JLabel("Phone no. : "),
						new JLabel("Email : "),
						new JLabel("DOB : "),
						new JLabel("Attendance : "),
						new JLabel("Loyalty Points : "),
							
						// premium member unique attributes
						new JLabel("Paid Amount : "),
						new JLabel("Discount Amount : "),
						new JLabel("Full Payment? : "),
						new JLabel("Trainer Name : "),
							
						// regular member unique attributes
						new JLabel("Current Plan : "),
						new JLabel("Can Upgrade? : "),
						new JLabel("Referral Source : "),
						new JLabel("Removal Reason : ")

				};
				
				JPanel individualMemberButtonsWrapper_P = new JPanel();
			
					JPanel[]
						individualMemberButtons_P = {
							new JPanel(), // activate/deactivate membership
							new JPanel(), // mark attendance
							new JPanel(), // revert Premium/Regular member
						
							new JPanel(), // calculate discount || removal reason
							new JPanel(), // discount amount field || upgrade plan
							new JPanel(), // pay due amount || plan combo box
							new JPanel(), // pay amount field || plan price
							new JPanel()  // premium plan charge
					};
				
					JTextField[]
						individualMemberFields = {
							new JTextField(), // discount amount field || plan price
							new JTextField(), // paid amount || removal reason
							new JTextField()  // premium plan charge 
					};
					
					String[] plans = {"Basic","Standard","Deluxe"};
					JComboBox<String> plan_C = new JComboBox<String>(plans);
					
					JLabel[]
						individualMemberFieldTitle = {
							new JLabel("Discount Amount:"),
							new JLabel("Select A Plan:"),
							new JLabel("Plan Price:"),
							new JLabel("Removal Reason:"),
							new JLabel("Amount:"),
							new JLabel("Total Charge")
						};
				
					JButton[]
						individualMemberButtons = {
							new JButton("Activate Membership"),
							new JButton("Mark Attendance"),
							new JButton("Revert Premium Member"), // seperate button as required
							new JButton("Revert Regular Member"), // seperate button as required
							
							new JButton("Calculate Discount"), // OR Upgrade plan 
							new JButton("Pay Due Amount"),
					};
					
        // last member id and type of member
		int currentMemberIndex = 0;
		String lastMemberID = "-1";
		String memberInstanceOf = "";
		
		Runnable memberCardUpdate; // declaring a runnable
		
		// for member card buttons funcitonality
	    boolean showDialog = true;
	    
		String fullyPaidText = "Fully Paid❗"; // creating a String with a 'heavy exclamation' unicode character so that users cannot lock themselves out of the field accidentally

			
	/*
	 * ARRAYS
	 */
					
					
		String backSymbol, searchSymbol; // symbols
		// array of String icons
		String[] icons = {
			backSymbol = "◄",	
			searchSymbol = "⌕",
		};			
	
	JLabel[]
		//array of body titles
		contentTitles= {
			dashboardTitle_L = new JLabel("Dashboard"),		
			addMemberTitle_L = new JLabel("Add a Member"),
			memberManagementTitle_L = new JLabel("Member Management")
		},
		//array of utility buttons
		utilityButtons_L= {
			new JLabel(backSymbol), //back button for internal forms
			new JLabel(backSymbol)  //back button for member management
		},
		// array of central body panel titles
		centralPanelTitles = {
			memberTypeSelect_L = new JLabel("Choose a type of member to add:"),
			memberManagementButton_L = new JLabel("Click to re-open the member ID entry dialog:")
		};
	
	
	JPanel[] 
		//array of main body content panels
		bodyContent = {
			dashboardContent = new JPanel(),
			addMemberContent = new JPanel(),
			memberManagementContent = new JPanel()
		},
		
		//array of main body title panels
		contentTitlePanel = {
			dashboardTitle_P = new JPanel(),	
			addMemberTitle_P = new JPanel(),
			memberManagementTitle_P = new JPanel()
		},
		//array of utility buttons
		utilityButtons_P = { 
			new JPanel(), //back button	for add a member forms
			new JPanel(), // back button for member management
		},
		// array of center body panels
		centralBodyPanels = {
			dashboardTableWrapper_P = new JPanel(),
			memberTypeSelect_P = new JPanel(),
			memberManagementButton_P = new JPanel()
		};
	
	JButton[]
			//array of all buttons
			Buttons= {
				menuButtons[0],
				menuButtons[1],
						formButtons[0],
						formButtons[1],
						formButtons[2],
				menuButtons[2],
						individualMemberButtons[0],
						individualMemberButtons[1],
						individualMemberButtons[2],
						individualMemberButtons[3],
						individualMemberButtons[4],
						individualMemberButtons[5],
			},
			// array of body buttons
			centralPanelButtons = {
					memberTypeButtons[0],
					memberTypeButtons[1],
					manageMemberButton,
			};
	
	
	/*
	 * ARRAYLIST
	 */
	
	 // array list of GymMember objects
	private ArrayList<GymMember> members = new ArrayList<GymMember>(); // private for data hiding 
	
	// initializing test objects
	GymMember testPremiumMember = new PremiumMember(1,"Ram Sapkota","Kathmandu","+977 9812345678","test_member1@gmail.com","Male","2004-12-12","2025-02-03","Alex Smith");
	GymMember testRegularMember = new RegularMember(2,"Sita Phuyal","Pokhara","+977 9876543210","test_member2@gmail.com","Female","2002-04-29","2025-01-13","Instagram Ad");
	
	
	
	//constructor
	public GymGUI() {
		
		// adding test objects to the arraylist
		members.add(testPremiumMember);
		members.add(testRegularMember);
		
		/*
		 * MENU BAR SECTION
		 */
		
		//setting menu title's attributes
		menuTitle = new JLabel();
		menuTitle.setText("QUICK ACCESS");
		menuTitle.setForeground(WHITE); // setting text color to White
		menuTitle.setFont(new Font("Century Gothic",Font.BOLD,18));
		

		// Styling buttons stored in the menuButtons array using a for-each loop
		for (JButton button : Buttons) {
		    button.setBackground(DARKNAVY);
		    button.setFocusable(false);
		    button.setForeground(LIGHTGRAY);
		    button.setFont(BUTTON_FONT);
		    button.setHorizontalAlignment(SwingConstants.LEADING);
		    button.setBorder(DEFAULT_BUTTON_BORDER);
		    
		    //use of an anonymous class MouseAdapter to customize the behavior of the mouse on these buttons 
		    button.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseEntered(MouseEvent e) {
		            button.setBackground(PASTELBLUE);
		            button.setForeground(DARKNAVY);
		        }

		        @Override
		        public void mouseExited(MouseEvent e) {
		            button.setBackground(DARKNAVY);
		            button.setForeground(LIGHTGRAY);
		        }
		        
		        @Override
		        public void mousePressed(MouseEvent e) {
		        	button.setForeground(DARKNAVY);
		        }
		        
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	button.setBackground(DARKNAVY);
		        }
		        
		        @Override
		        public void mouseReleased(MouseEvent e) {
		        	button.setForeground(LIGHTGRAY);
		        	button.setBackground(DARKNAVY);
		        }
		    });
		}
		
		
		// styling buttons of the body content panels using a for-each loop on the bodyButtons array
		for (JButton button : centralPanelButtons) {
		    button.setFocusable(false);
		    button.setForeground(LIGHTGRAY);
		    button.setBorder(DEFAULT_BUTTON_BORDER);
		    button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setPreferredSize(new Dimension(200,50));
			button.setFont(PARAGRAPH_FONT);
			button.setBackground(MIDNIGHTBLUE);
			
		    //use of an anonymous class MouseAdapter to customize the behavior of the mouse on these buttons 
		    button.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseEntered(MouseEvent e) {
		            button.setBackground(PASTELBLUE);
		            button.setForeground(MIDNIGHTBLUE);
		        }

		        @Override
		        public void mouseExited(MouseEvent e) {
		            button.setBackground(MIDNIGHTBLUE);
		            button.setForeground(LIGHTGRAY);
		        }
		        
		        @Override
		        public void mousePressed(MouseEvent e) {
		        	button.setForeground(MIDNIGHTBLUE);
		        }
		        
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	button.setBackground(MIDNIGHTBLUE);
		        }
		        
		        @Override
		        public void mouseReleased(MouseEvent e) {
		        	button.setForeground(LIGHTGRAY);
		        	button.setBackground(MIDNIGHTBLUE);
		        }
		    });
		}
		
		// setting 'menu buttons' panel's attribute
		menuButtonPanel = new JPanel();
		menuButtonPanel.setPreferredSize(new Dimension(MENU_BAR_WIDTH, 200));
		menuButtonPanel.setBackground(DARKNAVY);
		menuButtonPanel.setBorder(DEFAULT_BUTTON_OUTLINE);
		menuButtonPanel.setLayout(new GridLayout(0,1,0,0));
		
		//adding each button to the menuButtonPane using a for each loop
		for(JButton button: menuButtons) {
			menuButtonPanel.add(button);
		}
		
		// setting menuBar attributes
		menuBar = new JPanel();
		menuBar.setPreferredSize(new Dimension(MENU_BAR_WIDTH, FRAME_HEIGHT));
		menuBar.setBackground(DARKNAVY); // setting menu bar color to Dark Navy
		menuBar.setLayout(new FlowLayout(FlowLayout.CENTER,0,40));	
		menuBar.add(menuTitle); //adding the menu title
		menuBar.add(menuButtonPanel); //adding the button panel
		
		
		/*
		 * BODY CONTENT SECTION (COMMON ATTRIBUTES)
		 */

		// setting title attributes using a for each loop
		for(JLabel titles : contentTitles) {
			titles.setForeground(DARKNAVY);
			titles.setFont(CONTENT_TITLE_FONT);
		}

		// setting title panel attributes using a for loop
		for(int i=0; i<contentTitlePanel.length;i++) {
			JPanel titlePanel = contentTitlePanel[i];
			titlePanel.setVisible(true);
			titlePanel.setPreferredSize(new Dimension(1,27));
			titlePanel.setLayout(new BorderLayout());
			titlePanel.add(contentTitles[i],BorderLayout.CENTER);
			titlePanel.setBackground(LIGHTGRAY);
		}
		
		// setting content attributes using a for loop
		for(int i=0; i<bodyContent.length;i++) {
			JPanel content = bodyContent[i];
			content.setPreferredSize(new Dimension((FRAME_WIDTH - MENU_BAR_WIDTH), FRAME_HEIGHT));
		    content.setBackground(LIGHTGRAY); // setting main content color to Light Gray
			content.setLayout(new BorderLayout());
			content.add(contentTitlePanel[i],BorderLayout.NORTH);
			content.setBorder(CONTENT_MARGIN);
		}
		
		for(int i = 0 ; i < bodyContent.length; i++) {
			
			
			//styling the central panel
			centralBodyPanels[i].setPreferredSize(new Dimension(1,1)); 
			centralBodyPanels[i].setBackground(LIGHTGRAY);
			centralBodyPanels[i].setBorder(CONTENT_MARGIN);
			
			if(i!=0) { 
			// styling the central panel titles
			centralPanelTitles[i-1].setFont(HEADING1_FONT);
			centralPanelTitles[i-1].setForeground(MIDNIGHTBLUE);
			centralPanelTitles[i-1].setPreferredSize(new Dimension(600,150));
			centralPanelTitles[i-1].setBorder(CONTENT_MARGIN); // setting margin
			centralPanelTitles[i-1].setHorizontalAlignment(SwingConstants.CENTER);
			centralPanelTitles[i-1].setVerticalAlignment(SwingConstants.BOTTOM);

			centralBodyPanels[i].add(centralPanelTitles[i-1]);
			}
			
			if(i==0) {continue;} // doing nothing when index is of dashboard
			
			else if(i==1) {
				// adding one extra button to the select member type panel
				centralBodyPanels[i].add(centralPanelButtons[i-1]);
			}
			
			// adding corresponding buttons to the panels
			centralBodyPanels[i].add(centralPanelButtons[i]);
			
			bodyContent[i].add(centralBodyPanels[i], BorderLayout.CENTER);
		}
		
		// setting attributes of utilityButton (back button) text using a for each loop
		for(JLabel utilityButton_L : utilityButtons_L) {
			utilityButton_L.setForeground(DARKNAVY);
			utilityButton_L.setFont(TITLE_SYMBOL_FONT);
			utilityButton_L.setHorizontalAlignment(SwingConstants.CENTER);
			
			// overriding the methods of anonymous class MouseAdapter(), to change behaviour of mouse when interacting with this button
			utilityButton_L.addMouseListener(new MouseAdapter() {
				
				@Override
		        public void mouseEntered(MouseEvent e) {
					utilityButton_L.setForeground(STEELBLUE);
		        }
				
				@Override
		        public void mouseExited(MouseEvent e) {
		        	utilityButton_L.setForeground(DARKNAVY);
		        }
		        
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	utilityButton_L.setForeground(STEELBLUE);
		        }
		        
		        @Override
		        public void mousePressed(MouseEvent e) {
		        	utilityButton_L.setForeground(DARKNAVY);
		        }
		        
		        @Override
		        public void mouseReleased(MouseEvent e) {
		        	utilityButton_L.setForeground(DARKNAVY);
		        }
			});
			
		}		
		
		// setting attributes of the utilityButton panel with a for loop
		for(int i=0 ; i<utilityButtons_P.length ; i++) {
			JPanel utilityButton_P = utilityButtons_P[i];
			utilityButton_P.setBackground(LIGHTGRAY);
			utilityButton_P.setPreferredSize(new Dimension(55,1));
			utilityButton_P.setLayout(new BorderLayout());
			utilityButton_P.add(utilityButtons_L[i], BorderLayout.CENTER);
			utilityButton_P.setVisible(true);
		}
		
		
		/*
		 * DASHBOARD SECTION
		 */
			int marker;
		
			
		

		/*
		 * ADD A MEMBER SECTION
		 */
		
		// form styling, put into a runnable 
		Runnable showFormContent = new Runnable() {
		
			@Override
			public void run() { 
					int formIndex = isFormContent[0] ? 0 : 1;
					
				    formPanels[formIndex].setBackground(LIGHTGRAY);
				    formPanels[formIndex].setLayout(new GridLayout(3, 3, 0, 0));
				    
				    for (int j = 0; j < inputPanels.length - 1; j++) {

				    	int l = j < 8 ? j : j+formIndex;
				    	
				        inputLabels[l].setForeground(MIDNIGHTBLUE);
				        inputLabels[l].setFont(INPUT_HEADING);

				        
				        // initializing panels
				        JPanel inputPanel = inputPanels[j]; //putting the current panel into a variable for easy future use

				        inputPanel.removeAll();
				        inputPanel.setBorder(FORM_INPUT_MARGIN);
				        inputPanel.setBackground(LIGHTGRAY);
				        inputPanel.setLayout(new BorderLayout());
				        inputPanel.add(inputLabels[l], BorderLayout.NORTH); // adding label at the top

				        if (j == 5) { 
				        	//adding gender radio buttons instead of a text field for panel 5
				        	
				        	inputPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0)); // setting different layout
				            inputLabels[j].setPreferredSize(new Dimension(200, 15)); // increasing the width of label to make it so that gender radio buttons appaer on next line


				            // to set attributes of radio buttons
				            
				            for (int k = 0; k < 2; k++) {

				                JRadioButton genderRadio = genderRadioButtons[k]; //putting the current radio button into a variable for easy future use
				                
				                GENDER.add(genderRadio);
				                genderRadio.setForeground(MIDNIGHTBLUE);
				                genderRadio.setBackground(LIGHTGRAY);
				                genderRadio.setFont(RADIO_FONT);
				                genderRadio.setFocusable(false);
				                genderRadio.setPreferredSize(new Dimension(80, 60));
				                
				                // to set isGenderSelected to true when a gender radio is clicked
				                genderRadio.addMouseListener(new MouseAdapter() {
				                	
				                	@Override
				                	public void mousePressed(MouseEvent e) {		                		
				                		for(int j = 0 ; j < inputFields.length ; j++) {
						        			inputFields[j].setFocusable(false);
						        			
						        			indexOfGenderSelected = e.getSource()==genderRadioButtons[0] ? 0 : 1;
						        			
						        		}
				                	}
				                	
				                	@Override
				                	public void mouseReleased(MouseEvent e) {		                		
				                		for(int j = 0 ; j < inputFields.length ; j++) {
						        			inputFields[j].setFocusable(true);
						        		}
				                	}
				                	
				                	@Override
				                	public void mouseClicked(MouseEvent e) {		                		
				                		isGenderSelected = true;
				                	}
				                });

				                inputPanel.add(genderRadio);

				            }
				            
				        }
				        
				        else {
				        	
				        	int k = j < 5 ? j : (j==inputFields.length - 1 && isFormContent[1]) ? j : j-1; // to use/add [j-1] fields to the panels after panel 5
				        		
				        	// setting the placeholders for input variables
				        	String inputPlaceholder = k < (inputFields.length - 2) ? commonPlaceholders[k] : uniquePlaceholders[formIndex][0];
				        	
				        	JTextField inputField = inputFields[k]; // assigning required/corresponding field to a common variable
				        	
			                inputPanel.add(inputField, BorderLayout.SOUTH);
			                inputField.setText(inputPlaceholder); //setting corresponding placeholder
				            
				            //styling the text fields
				            inputField.setPreferredSize(new Dimension(1,35));
				            inputField.setBorder(DEFAULT_INPUT_BORDER); // setting default border initially
				            inputField.setFont(INPUT_FONT);
				            inputField.setForeground(PLACEHOLDERGRAY);
				            inputField.setBackground(LIGHTGRAY);
				            
				            // using the anonymous focusAdapter class to override focus methods for textFields
				            inputField.addFocusListener(new FocusAdapter() {
				                @Override
				                public void focusGained(FocusEvent e) {
				                    if (inputField.getText().equals(inputPlaceholder)) {
				                        inputField.setText(""); // Clear placeholder instantly
				                        inputField.setForeground(MIDNIGHTBLUE); // Set active text color
				                        inputField.setBorder(ACTIVE_INPUT_BORDER); // setting different border when field gains focus
				                    }
				                }

				                @Override
				                public void focusLost(FocusEvent e) {
				                    if (inputField.getText().isEmpty()) {
				                        inputField.setText(inputPlaceholder); // Restore placeholder
				                        inputField.setForeground(PLACEHOLDERGRAY); // Set placeholder color   
				                    }
				                    inputField.setBorder(DEFAULT_INPUT_BORDER); // setting default border when field loses focus
				                }
				            });
				            
				            // to make sure that caret in the text fields dont appear when focus is lost, using the anonymous class MouseAdapter and overriding it's click methods
							bodyContent[1].addMouseListener(new MouseAdapter() {
								
								@Override
								public void mousePressed(MouseEvent e) {
									inputField.setFocusable(false);
									
								}
								
								@Override
								public void mouseReleased(MouseEvent e) {
									inputField.setFocusable(true);
								}
							});
				            
				        }
				        formPanels[formIndex].add(inputPanel);
				    }

				    // setting attributes and adding buttons to the panel below the form
				    
				    formButtons_P.setBackground(LIGHTGRAY);
				    formButtons_P.setPreferredSize(new Dimension(1, 100));
				    formButtons_P.setLayout(new BorderLayout());

					
					formControlButtonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,10,30));
					formControlButtonsPanel.setPreferredSize(new Dimension(400,1));
					formControlButtonsPanel.setBackground(LIGHTGRAY);
			}
		};
		
		// for form control buttons functionality
		for(int k = 0 ; k < formButtons.length ; k++) {
			JButton formButton = formButtons[k];
			
			formButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					// event listener for clear all button of each form
					if(e.getSource()==formButtons[0]) {
						
                        int currentFormIndex = isFormContent[0] ? 0 : 1; // determining index of the current panel
						
						String options[]= {"Yes","No"}; // to explicitly define options in the following dialog box and select no as the focused button
						
						for(int i = 0 ; i < inputFields.length - 1; i++) {
			        		
				        	// setting the corresponding placeholders for input fields into a common variable
				        	String inputPlaceholder = i < (inputFields.length - 2) ? commonPlaceholders[i] : uniquePlaceholders[currentFormIndex][0];
				        	
				        	// seetting the corresponding input fields into a common variable
				        	JTextField inputField = i < (inputFields.length - 2) ? inputFields[i] : inputFields[i+currentFormIndex];
				        	
							// checking if input fields dont have their corresponding placeholders or empty value, and only allowing form clearing when that's the case
							if( ! (inputField.getText().equals(inputPlaceholder)) && ! (inputField.getText().equals(""))) {
							
								// showing a confirmation dialog to make sure that all fields are meant to be reset (byte to save memory)
								byte clearWish = (byte) JOptionPane.showOptionDialog(frame, "Are you sure you want to clear all fields?", "Caution: Fields will be reset", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						
								// only resetting the form if yes is clicked
								if(clearWish==0) {
									for(int j=0 ; j < inputFields.length ; j++ ) {
										
										// setting the placeholders for input variables
										String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
										
										inputFields[j].setForeground(PLACEHOLDERGRAY);
										inputFields[j].setText(placeholder);
										inputFields[j].setFocusable(false);
										inputFields[j].setBorder(DEFAULT_INPUT_BORDER);
									}
									
									GENDER.clearSelection(); // deselecting radio buttons
									isGenderSelected = false; // since gender is deselected
									
									JOptionPane.showMessageDialog(frame, "Form successfully cleared!", "Success", JOptionPane.INFORMATION_MESSAGE); // success message
								}
								else {
									break; //exiting out of the loop and doing nothing else when either 'no' or close button is pressed
								}
							}
							
							// removing caret and adding placeholder silently when atlest one field is null, and the clear all button is clicked
							if((inputField.getText().equals(""))) {
								
								for(int j=0 ; j < inputFields.length ; j++ ) {
									
									// setting the placeholders for input variables
									String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
									
									inputFields[j].setForeground(PLACEHOLDERGRAY);
									inputFields[j].setText(placeholder);
									inputFields[j].setFocusable(false);
									inputFields[j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
								}
							}
						}
						
						GENDER.clearSelection(); // deselecting radio buttons
						isGenderSelected = false; // since gender is deselected
					}
					
					// event listener for add premium member/regular member buttons of respective forms
					if(e.getSource()==formButtons[1] || e.getSource()==formButtons[2]) {
						
						boolean allFieldsFilled = true; // assuming all fields are filled initially
						
						int currentFormIndex = e.getSource()==formButtons[1] ? 0 : 1;
						
						String memberType = isFormContent[0] ? "premium" : "regular";

						for (int k = 0; k < inputFields.length - 1; k++) {
						    
				        	// setting the corresponding placeholders for input fields into a common variable
				        	String inputPlaceholder = k < (inputFields.length - 2) ? commonPlaceholders[k] : uniquePlaceholders[currentFormIndex][0];
				        	
				        	// seetting the corresponding input fields into a common variable
				        	JTextField inputField = k < (inputFields.length - 2) ? inputFields[k] : inputFields[k+currentFormIndex];
				        	
				        	String text = inputField.getText(); // getting text from input fields

				        	// setting allFieldsFilled to false, if atleast one input field is "empty"
						    if (text.equals(inputPlaceholder) || text.equals("")) {
						        allFieldsFilled = false;
						    }
						    
						}

						// LOGIC TO DISPLAY ERROR MESSAGES AND CREATE OBJECTS
						
						// checking if all fields are filled
						if (!allFieldsFilled) {
						    JOptionPane.showMessageDialog(frame, "All fields must be filled before adding a "+memberType+" member.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
						} 
						
						 // checking if gender is selected after confirming all fields are filled 
						
						else if (!isGenderSelected) {	
							// remving caret from the fields
							for (int k = 0; k < inputFields.length; k++) {
								inputFields[k].setFocusable(false);
							}
							
						    JOptionPane.showMessageDialog(frame, "Please select a gender.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
						} 
						
						// proceeding with adding a member if all conditions are met
						else {  

							boolean isUniqueId = true; // boolean to check if id is unique (set to true by default)
							
							// using try/catch blocks to handle String -> int conversion issues
						    try {
						        int id = Integer.parseInt(inputFields[0].getText()); // Convert input to int
						        
						        for (GymMember member : members) {
						            if (member.id == id) {
						                isUniqueId = false;
						                break; // since there is no need to check for any more matches
						            }
						        }
						        
						        if(isUniqueId) {
						        	
						        	// declaring variables to pass into contructor of respective classes
						        	String 
					        		name = inputFields[1].getText(),
					        		location = inputFields[2].getText(),
					        		phone = inputFields[3].getText(),
					        		email = inputFields[4].getText(),
					        		gender = indexOfGenderSelected==0 ? "Male" : "Female", // use of ternary operator to dynamically assign value
					        		DOB = inputFields[5].getText(),
					        		membershipStartDate = inputFields[6].getText(),
					        		personalTrainer = inputFields[7].getText(), // unique field
					        		referralSource = inputFields[8].getText(); // unique field
						        	
						        	
									 // creating premium member object, based on the form on display
									if (isFormContent[0]) { 
								        
										GymMember premiumMember = new PremiumMember(id, name, location, phone, email, gender, DOB, membershipStartDate, personalTrainer);
										members.add(premiumMember);
								    } 
									
									// creating regular member object, based on the form on display
									else {
										
										GymMember regularMember = new RegularMember(id, name, location, phone, email, gender, DOB, membershipStartDate, referralSource);
										members.add(regularMember);
								    }
									
									
									// success dialog
									JOptionPane.showMessageDialog(frame, "You have successfully added a " + memberType + " member!", "Member Added", JOptionPane.INFORMATION_MESSAGE);
									
									// clearing the forms after object creation
									for(int j=0 ; j < inputFields.length ; j++ ) {
										
										// setting the placeholders for input variables
										String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
										
										inputFields[j].setForeground(PLACEHOLDERGRAY);
										inputFields[j].setText(placeholder);
										inputFields[j].setFocusable(false);
										inputFields[j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
									}
									
									GENDER.clearSelection(); // deselecting radio buttons
									isGenderSelected = false; // since gender is deselected
						        
						        }
						        else {
						        	JOptionPane.showMessageDialog(frame, "Member with ID "+inputFields[0].getText()+" already exists! Please enter a new ID.", "Duplicate input", JOptionPane.ERROR_MESSAGE);
						        }
						    } 
						    
						    catch (NumberFormatException e1) {
						    	
						    	// error message
						    	JOptionPane.showMessageDialog(frame, "Invalid ID! Please enter a valid number.", "Invalid input", JOptionPane.ERROR_MESSAGE);
						    	
						    }	


						}
					}
				}
				
				// making sure that caret doesnt appear after clearing the form
				@Override
				public void mouseExited(MouseEvent e) {
					for(int j=0 ; j < inputFields.length ; j++ ) {
						inputFields[j].setFocusable(true);
					}
				}
			});
		}
		
		
		/*
		 * MEMBER MANAGEMENT SECTION
		 */
		
		bodyContent[2].remove(memberManagementButton_P);
		bodyContent[2].add(individualMemberManagement_P);
		
		individualMemberManagement_P.setLayout(new BorderLayout());
		individualMemberManagement_P.add(memberCard_P, BorderLayout.NORTH);
		individualMemberManagement_P.add(individualMemberButtonsWrapper_P, BorderLayout.CENTER);
		
		// making it so that the caret from the editable form field gets remove when pressed elsewhere
		individualMemberManagement_P.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				individualMemberFields[1].setEnabled(false);
				individualMemberFields[1].setFocusable(false);
				individualMemberFields[1].setEnabled(true);
			}
		});
		
		// card styling
		memberCard_P.setBorder(CARD_MARGIN);
		memberCard_P.setBackground(LIGHTGRAY);
		memberCard_P.setPreferredSize(new Dimension(1,245));
		memberCard_P.setLayout(new BorderLayout());
		memberCard_P.add(cardCommonAttributes_P, BorderLayout.WEST);
		memberCard_P.add(cardUniqueAttributes_P, BorderLayout.CENTER);
		
		// first card panel styling
		cardCommonAttributes_P.setBackground(LIGHTGRAY);
		cardCommonAttributes_P.setPreferredSize(new Dimension(455,1));
		cardCommonAttributes_P.setLayout(new GridLayout(0,2));
		
		// second card panel styling
		cardUniqueAttributes_P.setBackground(DARKGRAY);
		cardUniqueAttributes_P.setLayout(new GridLayout(0,1));
		
		// individual member management buttons panel styling 
		individualMemberButtonsWrapper_P.setBackground(LIGHTGRAY);
		individualMemberButtonsWrapper_P.setLayout(new GridLayout(3,3));
		individualMemberButtonsWrapper_P.setBorder(MANAGEMENT_BUTTON_PANEL_MARGIN);
		
		for(JPanel buttonPanel : individualMemberButtons_P) {
			buttonPanel.setBackground(LIGHTGRAY);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		}
		
		for(JButton managementButtons : individualMemberButtons) {
			managementButtons.setPreferredSize(new Dimension(220,45));
			managementButtons.setHorizontalAlignment(SwingConstants.CENTER);
			
			// to remove caret from form field
			managementButtons.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					individualMemberFields[1].setEnabled(false);
					individualMemberFields[1].setFocusable(false);
					individualMemberFields[1].setEnabled(true);
				}
			});
		}
		
		for(JTextField inputField : individualMemberFields) {
            inputField.setPreferredSize(new Dimension(1,30));
            inputField.setBorder(DEFAULT_INPUT_BORDER); // setting default border initially
            inputField.setFont(INPUT_FONT);
            inputField.setForeground(MIDNIGHTBLUE);
            inputField.setBackground(LIGHTGRAY);
            inputField.setFocusable(false);
		}
		
    	// setting font for non-editable fields
    	for(int i = 0; i < individualMemberFields.length ; i+=2) { 
    		individualMemberFields[i].setForeground(GUNMETALBLUE);
    		individualMemberFields[i].setFont(NON_EDITABLE_FIELD_FONT); // setting font of non focusable field
    	}
		
		for(JLabel inputHeading : individualMemberFieldTitle) {
			inputHeading.setForeground(MIDNIGHTBLUE);
			inputHeading.setFont(MANAGEMENT_INPUT_HEADING);
		}
		
		
		/*
		 * FRAME SECTION
		 */
		
		// setting frame attributes
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); // setting the dimensions of the frame
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close the window on exit
		frame.setLocationRelativeTo(null); // to center the frame on the screen
		frame.setLayout(new BorderLayout());

		frame.add(menuBar, BorderLayout.WEST);
		
		activeIndex = 0; // setting the lastContent to addMemberContent, initially
		lastIndex = activeIndex;
		menuButtons[activeIndex].setFont(BUTTON_FONT_ACTIVE);
		menuButtons[activeIndex].setBorder(ACTIVE_BUTTON_BORDER);
		frame.add(bodyContent[activeIndex], BorderLayout.CENTER); // initially showing dashboard
		
		

		/*
		 * BUTTON FUNCTIONALITIES
		 */
		
		// for menu buttons functionality
		for (JButton menuButton : menuButtons) {
		    menuButton.addMouseListener(new MouseAdapter() {
		    	
		        @Override
		        public void mousePressed(MouseEvent e) {
		        	
	                // looping through the buttons to find the clicked button and change attributes
		            for (int i = 0; i < menuButtons.length; i++) {

		                if (e.getSource() == menuButtons[i]) {
		                	
		                    // checking if an add member form is open
		                    if (isFormContent[0] || isFormContent[1]) {
		                    	
		                        int currentFormIndex = isFormContent[0] ? 0 : 1; // determining index of the current panel
		                        boolean isFormFilled = false;

		                        for (int j = 0; j < inputFields.length - 1; j++) {
		                        	
						        	// setting the corresponding placeholders for input fields into a common variable
						        	String inputPlaceholder = j < (inputFields.length - 2) ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
						        	
						        	// seetting the corresponding input fields into a common variable
						        	JTextField inputField = j < (inputFields.length - 2) ? inputFields[j] : inputFields[j+currentFormIndex];

		                            // checking if any field has user input
		                            if (!inputField.getText().equals(inputPlaceholder) && !inputField.getText().isEmpty()) {
		                                isFormFilled = true;
		                                break;
		                            }
		                        }

		                        if (isFormFilled) {
		                            String[] options = {"Yes", "No"};
		                            byte menuWish = (byte) JOptionPane.showOptionDialog(frame, 
		                                    "Doing this will reset all fields. Proceed to \"" + menuButtons[i].getText() + "\" panel?",
		                                    "Caution: Fields will be reset", 
		                                    JOptionPane.YES_NO_OPTION, 
		                                    JOptionPane.WARNING_MESSAGE, 
		                                    null, options, options[1]);

		                            if (menuWish != 0) {
		                            	continue; // skipping reset if "No" is selected
		                            }
		                        }

		                        // resetting form fields
		                        for (int j = 0; j < inputFields.length; j++) {
		                            String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
		                            inputFields[j].setForeground(PLACEHOLDERGRAY);
		                            inputFields[j].setText(placeholder);
		                            inputFields[j].setFocusable(false);
		                            inputFields[j].setBorder(DEFAULT_INPUT_BORDER);
		                        }

		                        GENDER.clearSelection();
		                        isGenderSelected = false;

		                        // reverting title and remove back button
		                        addMemberTitle_L.setText("Add a Member");
		                        addMemberTitle_P.remove(utilityButtons_P[0]);

		                        // removing current form panel and form buttons
		                        bodyContent[1].remove(formPanels[currentFormIndex]);
		                        bodyContent[1].remove(formButtons_P);
		                        
		                        // showing the memberTypeSelect panel
		                        bodyContent[1].add(memberTypeSelect_P, BorderLayout.CENTER);
		                        
		                        // making the fields be focusable after the form panel has been removed
		                        for(int j = 0 ; j < inputFields.length ; j++) {
		                        	inputFields[j].setFocusable(true);
		                        }
		                    }

		                    // updating active menu button styling and switch content
		                    lastIndex = activeIndex;
		                    activeIndex = i;

		                    menuButtons[lastIndex].setFont(BUTTON_FONT);
		                    menuButtons[lastIndex].setBorder(DEFAULT_BUTTON_BORDER);

		                    menuButtons[activeIndex].setFont(BUTTON_FONT_ACTIVE);
		                    menuButtons[activeIndex].setBorder(ACTIVE_BUTTON_BORDER);

		                    frame.remove(bodyContent[lastIndex]);
		                    frame.add(bodyContent[i]);

		                    // reseting form content booleans
		                    for (int k = 0; k < isFormContent.length; k++) {
		                        isFormContent[k] = false;
		                    }		                    
		                }
		            }
		            
		            // refreshing the frame layout to reflect the changes
		            frame.revalidate(); // recalculating layout
		            frame.repaint();    // redrawing the frame
		            manageMemberButton.setEnabled(true);
   
		            
		         // showing input dialog box if the member management panel is clicked and the current panel isn't member management panel
		            if (e.getSource() == menuButtons[2] && lastIndex != 2) {
		            	
		            	showDialog=true;

		                memberManagementTitle_L.setText("Member Management"); // reverting to original title
		                memberManagementTitle_P.remove(utilityButtons_P[1]); // removing back button
		                
		                bodyContent[2].remove(memberManagementButton_P);
		                bodyContent[2].remove(individualMemberManagement_P);
		                
		                // initializing the Runnable interface to declare a function to handle the dialog and input processing
		                memberCardUpdate = new Runnable() {
		                	
		                	// initializing memberId with a placeholder value
		                	int memberId = 0x696969;
		                    @Override
		                    public void run() {
		                    			                    	
		                        // looping until input is valid
		                        while (true) {
		                        	String input;
		                        	
		                        	if(showDialog==true) {
			                            // dialog to input member ID
			                             input = JOptionPane.showInputDialog(frame, "Enter a member ID:", "Manage membership", JOptionPane.INFORMATION_MESSAGE);
			                            // handling the case where no input was provided or dialog was closed
			    			            if (input == null) {
			    			                bodyContent[2].add(memberManagementButton_P, BorderLayout.CENTER);
			    			                memberManagementButton_P.revalidate();
			    			                memberManagementButton_P.repaint();
			    			                break; // exitting the loop
			    			            }
			    			            else {
			    			            	lastMemberID = input;			    			            	
			    			            }
		                        	}
		                        	else {
		                        		input = lastMemberID;
		                        	}
		                            
		                            try {
		                                memberId = Integer.parseInt(input);
		                                if (memberId != 0x696969) {
		                                	
		                                    boolean isExistingId = false; // checking if ID exists
		                                    
		                                    String[]
		                                    	memberDetails = {
		                                    		"", // name
		                                    		"", // id
		                                    		"", // activeStatus
		                                    		"", // membershipStartDate
		                                    		"", // location
		                                    		"", // phone no
		                                    		"", // email
		                                    		"", // DOB
		                                    		"", // attendance
		                                    		"", // loyalty points
		                                    		
		                                    		"", // paid amount || current plan
		                                    		"", // discount || isEligibleForUpgrade
		                                    		"", // isFullPayment || referral source
		                                    		""  // trainer name || removal reason
		                                    	};

		                                    // checking if the ID exists using a for loop of GymMember objects
		                                    for (int i = 0; i<members.size(); i++) {
		                                    	GymMember member = members.get(i);
		                                    	
		                                        if (member.id == memberId) {
		                                            isExistingId = true;
		                                            
		                                            currentMemberIndex = i;
		                                            memberInstanceOf = member instanceof PremiumMember ? "Premium" : "Regular";
		                                            
		                                            // setting values of the array above, using getter methods of the object
		                                            memberDetails[0] = member.getName();
		                                            memberDetails[1] = Integer.toString(member.getId()); // converting int to string
		                                            memberDetails[2] = member.isActiveStatus() == true ? "Active" : "Inactive"; // getting boolean and using corresponding string for readability
		                                            memberDetails[3] = member.getMembershipStartDate();
		                                            memberDetails[4] = member.getLocation();
		                                            memberDetails[5] = member.getPhone();
		                                            memberDetails[6] = member.getEmail();
		                                            memberDetails[7] = member.getDOB();
		                                            memberDetails[8] = Integer.toString(member.getAttendance()); // converting int to string
		                                            memberDetails[9] = Double.toString(member.getLoyaltyPoints()); // converting double to string
		                                            
		                                            // premium member unique getters
		                                            if(memberInstanceOf.equals("Premium")) { 
		                                            	
		                                            	PremiumMember premiumMember = (PremiumMember) member; // downcasting to access child methods
		                                            	
		                                            	memberDetails[10] = Double.toString(premiumMember.getPaidAmount()); // converting double to string
			                                            memberDetails[11] = Double.toString(premiumMember.getDiscountAmount()); // converting double to string 
			                                            memberDetails[12] = premiumMember.isFullPayment() == true ? "Yes" : "No"; // getting boolean and using corresponding string for readability
			                                            memberDetails[13] = !premiumMember.getPersonalTrainer().equals("")? premiumMember.getPersonalTrainer() : "N/A"; // showing N/A when trainer name is empty
			                                            
			                                            // setting text of 1st non-editable field
	                                					individualMemberFields[0].setText(premiumMember.isFullPayment() == true ? (premiumMember.getDiscountAmount()!=0.0d? "Rs. "+Double.toString(premiumMember.getDiscountAmount()) : "Uncalculated") : "Ineligible");
	                                					
	                                					// setting text of editable field
	                                					individualMemberFields[1].setText(premiumMember.isFullPayment() == true ? fullyPaidText : "");
	                                					
	                                					if(premiumMember.isFullPayment()) {
	                                						individualMemberFields[1].setFont(NON_EDITABLE_FIELD_FONT);
	                                						individualMemberFields[1].setForeground(STEELBLUE);
	                                					}
	                                					
			                                            // setting text of 2nd non-editable field
			                            				individualMemberFields[2].setText("Rs. "+Double.toString(premiumMember.getPremiumCharge()));
		                                            }
		                                            // regular member unique getters
		                                            else {

		                                            	RegularMember regularMember = (RegularMember) member; // downcasting to access child methods
		                                            	
		                                            	memberDetails[10] = regularMember.getPlan();
		                                            	memberDetails[11] = member.getAttendance()>=regularMember.getAttendanceLimit() ? "Yes" : "No"; // getting boolean using given logic and using corresponding string for readability
		                                            	memberDetails[12] = regularMember.getReferralSource();
			                                            memberDetails[13] = regularMember.getRemovalReason().equals("")? "N/A" : regularMember.getRemovalReason(); // showing N/A when removal reason is empty
		                                            }
		                                            
		                                            
		                                            /*
		                                             * STYLING THE ACTIVATE/DEACTIVATE BUTTON
		                                             */
		                            		    	individualMemberButtons[0].setText(member.isActiveStatus()?"Deactivate Membership":"Activate Membership"); // setting corresponding text of first management button for each member
		                            				individualMemberButtons[0].setBackground(STEELBLUE); // setting green or red for activate or deactivate button
		                            				individualMemberButtons[0].setBorder(ACTIVATE$DEACTIVATE_BUTTON_OUTLINE);
		                            				
		                            				
		                            				// setting mouse interaction effects for activate/deactivate member button
		                            				individualMemberButtons[0].addMouseListener(new MouseAdapter() { 
		                            					@Override
		                            					public void mouseEntered(MouseEvent e) {
		                            						individualMemberButtons[0].setForeground(LIGHTGRAY);
		                            						individualMemberButtons[0].setBackground(GUNMETALBLUE);
		                            					}
		                            					@Override
		                            					public void mouseExited(MouseEvent e) {
		                            						individualMemberButtons[0].setBackground(STEELBLUE);
		                            					}
		                            					@Override
		                            					public void mouseClicked(MouseEvent e) {
		                            						individualMemberButtons[0].setBackground(STEELBLUE);
		                            					}
		                            					@Override
		                            					public void mouseReleased(MouseEvent e) {
		                            						individualMemberButtons[0].setBackground(STEELBLUE);
		                            					}
		                            				});
		                            		    	
		                            		    	
		                                            break; // no need to check further
		                                        }
		                                    }

		                                    if (isExistingId) {
		                                    	
		                                    	memberManagementContent.revalidate();
		                                        memberManagementContent.repaint();
		                                    	
		                                        // updating the title
		                                        memberManagementTitle_L.setText(memberInstanceOf + " Member | " + memberDetails[0] + " (ID: " + memberId + ")");
		                                        memberManagementTitle_P.add(utilityButtons_P[1], BorderLayout.WEST);
		                                        
		                                        // removing previous content
		                                        memberManagementContent.remove(memberManagementButton_P);
		                                        memberManagementContent.remove(individualMemberManagement_P);
		                                        
		                                        memberManagementContent.add(individualMemberManagement_P, BorderLayout.CENTER); 
		                                        
		                                        // removing all previous button panels, and it's contents
		                                        individualMemberButtonsWrapper_P.removeAll();
		                                        
		        		                    	individualMemberFields[1].setFocusable(false); // making sure the field isn't focusable at first
		                                        
		                                     // adding button panels in corresponding places
		                                		for(int i = 0 ; i < individualMemberButtons_P.length - 1 ; i++ ) {
		                                			
			                                        individualMemberButtons_P[i].removeAll();
			                                        individualMemberButtons_P[i].setBorder(null);
			                                        individualMemberButtons_P[i].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		                                			
		                                			// adding corresponding panel to manage member button wrapper
		                                			individualMemberButtonsWrapper_P.add(individualMemberButtons_P[i]);
                                					
                                					// adding empty panels to premium member button wrapper
		                                			if(memberInstanceOf.equals("Premium")){
			                                			if(i==4) {
			                                				JPanel emptyPanel = new JPanel();
			                                				emptyPanel.setBackground(LIGHTGRAY);
			                                				
	                                						individualMemberButtonsWrapper_P.add(emptyPanel);
			                                			}
			                                			
			                                			else if(i==6) {
			                                				individualMemberButtonsWrapper_P.add(individualMemberButtons_P[i+1]);
			                                			}
		                                			}
		                                			
		                                			// adding empty panel to regular member button wrapper and resetting form field
		                                			else if(memberInstanceOf.equals("Regular")) {
		                                				
	                                					individualMemberFields[0].setText(""); // setting text for unfcusable input field to reset everytime this runnable is called
		                                				
	                                					// adding empty panels
	                                					if (i==1 || i==3) {
			                                				JPanel emptyPanel = new JPanel();
			                                				emptyPanel.setBackground(LIGHTGRAY);
			                                				
	                                						individualMemberButtonsWrapper_P.add(emptyPanel);
		    											}
		                                			}
		                                			
		                                			// setting corresponding buttons using a switch statement on i
		                                			switch (i) {
		                                			
		                                				case 0:
		                                				case 1:
		                                					individualMemberButtons_P[i].add(individualMemberButtons[i]);
		                                					break;
		                                			
		                                				case 2:
		                                					int buttonIndex = memberInstanceOf.equals("Premium")? 0 : 1; 
		                                					
		                                					individualMemberButtons_P[i].add(individualMemberButtons[i+buttonIndex]);
		                                			        break;

		                                				case 3:
		                                					if(memberInstanceOf.equals("Premium")) {
		                                						individualMemberButtons[i+1].setText("Calculate Discount");
		                                						individualMemberButtons_P[i].add(individualMemberButtons[i+1]);		                                						
		                                					}
		                                					else {
		                                						individualMemberButtons_P[i].removeAll();
		                                						individualMemberButtons_P[i].setBorder(MEMBER_MANAGEMENT_INPUT_MARGIN);
		                                						individualMemberButtons_P[i].setBackground(LIGHTGRAY);
		                                						individualMemberButtons_P[i].setLayout(new BorderLayout());
		                                						individualMemberButtons_P[i].add(individualMemberFieldTitle[3], BorderLayout.NORTH); // adding label at the top
		                                						individualMemberButtons_P[i].add(individualMemberFields[1], BorderLayout.SOUTH); // adding field at the bottom
		                                						individualMemberFields[1].setText(""); // resetting the field when the runnable is called
		                                					}
		                                			        break;
		                                			        
		                                				case 4:
		                                					if(memberInstanceOf.equals("Premium")){
			                                					individualMemberButtons_P[i].removeAll();
			                                					individualMemberButtons_P[i].setBorder(MEMBER_MANAGEMENT_INPUT_MARGIN);
			                                					individualMemberButtons_P[i].setBackground(LIGHTGRAY);
			                                					individualMemberButtons_P[i].setLayout(new BorderLayout());
			                                					
			                                					individualMemberFields[0].setForeground(GUNMETALBLUE);
		                                						individualMemberButtons_P[i].add(individualMemberFieldTitle[0], BorderLayout.NORTH); // adding label at the top
		                                						individualMemberButtons_P[i].add(individualMemberFields[0], BorderLayout.SOUTH); // adding field at the bottom	
		                                					}
		                                					else{
		                                						individualMemberButtons[i].setText("Upgrade Plan");
		                                						individualMemberButtons_P[i].add(individualMemberButtons[i]);
		                                					}
		                                					break;

		                                				case 5: 
		                                					if (memberInstanceOf.equals("Regular")) {
			                                					individualMemberButtons_P[i].removeAll();
			                                					individualMemberButtons_P[i].setBorder(MEMBER_MANAGEMENT_INPUT_MARGIN);
			                                					individualMemberButtons_P[i].setBackground(LIGHTGRAY);
			                                					individualMemberButtons_P[i].setLayout(new BorderLayout());
			                                					
		                                						individualMemberButtons_P[i].add(individualMemberFieldTitle[1], BorderLayout.NORTH); // adding label at the top
		                                						individualMemberButtons_P[i].add(plan_C, BorderLayout.SOUTH); // adding combobox at the bottom
		                                						
		                                				        plan_C.setSelectedIndex(-1); // setting null value by default
		                                				        plan_C.setBackground(LIGHTGRAY);
		                                				        plan_C.setPreferredSize(new Dimension(1,29));
		                                				        plan_C.setForeground(MIDNIGHTBLUE);
		                                				        plan_C.setFont(INPUT_FONT);
		                                				        plan_C.setFocusable(false); // to make it so that the annoying highlight doesn't appear
		                                					}
		                                					else {
	                                							individualMemberButtons_P[i].add(individualMemberButtons[i]); // adding the button to the corresponding panel
		                                					}
		                                					break;

		                                				case 6: 
		                                					for(int k = 0 ; k < 2 ; k++) {
			                                					individualMemberButtons_P[6+k].removeAll();
		                                						individualMemberButtons_P[6+k].setBorder(MEMBER_MANAGEMENT_INPUT_MARGIN);
		                                						individualMemberButtons_P[6+k].setBackground(LIGHTGRAY);
		                                						individualMemberButtons_P[6+k].setLayout(new BorderLayout());
		                                					}
	                                						
	                                						if(memberInstanceOf.equals("Premium")) {
	                                							
	                                							// pay amount field
		                                						individualMemberButtons_P[i].add(individualMemberFieldTitle[4], BorderLayout.NORTH); // adding label at the top
		                                						individualMemberButtons_P[i].add(individualMemberFields[1], BorderLayout.SOUTH); // adding field at the bottom
		                                						
		                                						// total amount field
		                                						individualMemberButtons_P[i+1].add(individualMemberFieldTitle[5], BorderLayout.NORTH); // adding label at the top
		                                						individualMemberButtons_P[i+1].add(individualMemberFields[2], BorderLayout.SOUTH); // adding field at the bottom
	                                						}
	                                						else {
		                                						individualMemberButtons_P[6].add(individualMemberFieldTitle[2], BorderLayout.NORTH); // adding label at the top
		                                						individualMemberButtons_P[6].add(individualMemberFields[0], BorderLayout.SOUTH); // adding field at the bottom
	                                						}
	                                						
		                                					break;
		                                			}
		                                		}
		                                        
		                                        for(JPanel cardPanel : cardPanels) {
		                                        	cardPanel.removeAll(); // removing all previous labels completely
		                                        }
		                                        
		                                        // Looping through the updated member details and creating new labels dynamically  
		                                        for (int i = 0; i < cardLabels.length-4 ; i++) {  
		                                        	
		                                            // declaring a variable to store text color for active status / current plan (regularMember)
		                                            String customDetailAttribute = "";
		                                            String bold = "font-weight: bold; "; // making the text bold, by storing the CSS attribute in a variable and using it
		                                            
		                                            // for active status
		                                            if(i==2) {
		                                            	customDetailAttribute = (memberDetails[2].equals("Active")) ? "color: green" : "color: red"; // red for inactive, green for active
		                                            }
		                                            // for current plan (regularMember)
		                                            else if(memberInstanceOf.equals("Regular")) {
		                                            	
		                                            	// for setting colors for different plan types
			                                            if (i==10) {
			                                            	
			                                            	switch(memberDetails[i]) {
			                                            	
			                                            		case "Basic" : customDetailAttribute = bold + "color: #16A085"; break; // dark turquoise
			                                            		case "Standard" : customDetailAttribute = bold + "color: #9B59B6"; break; // dark pastel purple
			                                            		case "Deluxe" : customDetailAttribute = bold + "color: #27AE60"; break; // dark emerald green
			                                            		
			                                            		default : System.out.println("this statement will never print"); // impossible case
			                                            	}
			                                            }
			                                            
			                                            // for upgrade eligibility when eligible
			                                            else if(i==11 && !memberDetails[i].equals("No")) {
			                                            	customDetailAttribute = bold+"color: green";
			                                            }
			                                            
			                                            // for removal reason when it isn't empty
			                                            else if(i==13 && !memberDetails[i].equals("N/A")) {
			                                            	customDetailAttribute = "color: red";
			                                            }
		                                            }
		                                            else if(memberInstanceOf.equals("Premium")) {
		                                            	if(i==11 && !memberDetails[i].equals("0.0")) {
		                                            		customDetailAttribute = bold+"color: green"; 
		                                            	}
		                                            	else if(i==12 && !memberDetails[i].equals("No")) {
		                                            		customDetailAttribute = bold+"color: green";
		                                            	}
		                                            }
		                                            
		                                            // using i+4 card titles when i is greater than 9 and the member is a regular member
		                                            String detailTitle = (i<10) ? cardLabels[i].getText() : cardLabels[i+(memberInstanceOf.equals("Premium")? 0 : 4)].getText();
		                                            
		                                            // to add unique labels to second panel
		                                            JPanel cardPanel = (i<10) ? cardCommonAttributes_P : cardUniqueAttributes_P;
		                                            
		                                            JLabel label = new JLabel(String.format(
			                                                "<html>"
			                                                    + "<p style=\"font-size: 11px; color: %s\">"
			                                                        + "<span style=\"font-weight: bold; font-family: 'Century Gothic'\">"
			                                                        	+ "%s"
			                                                        + "</span>"
			                                                        + "<span style=\"font-weight: 100; font-family: 'Helvetica'; %s\">"
			                                                        	+ "%s%s"
			                                                        + "</span>"
			                                                    + "</p>"
			                                                + "</html>", 
			                                                
			                                                "#1B263B", //MIDNIGHTBLUE
			                                                detailTitle,
			                                                customDetailAttribute,
			                                                (i == 2) ? "⦿ " : ((memberInstanceOf.equals("Premium")&&(i==10 || i==11)) ? "Rs." : ""), // adding ⦿ symbol to active status, and adding Rs. prefix to premium member discount amount and paid amount value using ternary operator
			                                                memberDetails[i] 
			                                            ));  
			                                            
			                                            // adding margin for unique labels and odd common labels using nested ternary operator
			                                            label.setBorder((i>9)?CARD_UNIQUE_LABEL_MARGIN_LEFT : ( (i%2==1)? CARD_ODD_LABEL_MARGIN_LEFT : null));
			                                            
			                                            // adding the new labels to the card  
			                                            cardPanel.add(label);
		                                            }
	                                            
		                                		// refreshing the main content
		                                        memberManagementContent.revalidate();
		                                        memberManagementContent.repaint();
		                                        
		                                        break; // exit loop after valid member ID is found
		                                    } 
		                                    
		                                    else {
		                                        JOptionPane.showConfirmDialog(frame, "Invalid ID! This ID doesn't exist.", "Invalid input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		                                    }
		                                }
		                            } catch (NumberFormatException e1) {
		                                // error message
		                                byte errorWish = (byte) JOptionPane.showConfirmDialog(frame, "Invalid ID! Please enter a valid number.", "Invalid input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

		                                if (errorWish != 0) {
		                                	memberManagementContent.remove(memberManagementButton_P);
	                                        memberManagementContent.remove(individualMemberManagement_P);
		                                	
		                                    // adding button when the dialog is closed
	                                        memberManagementContent.add(memberManagementButton_P, BorderLayout.CENTER); // add statement
		                                    memberManagementButton_P.revalidate();
		                                    memberManagementButton_P.repaint();
		                                    break; // exiting loop if user closes the dialog
		                                }
		                            }
		                        }
		                    	
		                        // to make sure that the button doesn't keep it's pressed state when the JOptionPane blocks the EDT
		                    	menuButtons[2].getModel().setPressed(false);
		                    	
		                    	// refreshing menubutton panel
		                    	menuButtonPanel.revalidate();
		                    	menuButtonPanel.repaint();
		                    }
		                };
		                
		                // calling the run function initalized above, initially
		                memberCardUpdate.run();
		                
		                // adding an action listener to the button (when it's re-added to the panel)
		                manageMemberButton.addMouseListener(new MouseAdapter() {
		                    @Override
		                    public void mousePressed(MouseEvent e) {
		                    	manageMemberButton.setForeground(MIDNIGHTBLUE);

		                    	// triggering the same dialog input handling (run() function) when the button is clicked
		                    	memberCardUpdate.run();
		                    }
		                    @Override
		    		        public void mouseEntered(MouseEvent e) {
		    		            manageMemberButton.setBackground(PASTELBLUE);
		    		            manageMemberButton.setForeground(MIDNIGHTBLUE);
		    		        }

		    		        @Override
		    		        public void mouseExited(MouseEvent e) {
		    		            manageMemberButton.setBackground(MIDNIGHTBLUE);
		    		            manageMemberButton.setForeground(LIGHTGRAY);
		    		        }
		    		        
		    		        @Override
		    		        public void mouseClicked(MouseEvent e) {
		    		        	manageMemberButton.setBackground(MIDNIGHTBLUE);
		    		        }
		    		        
		    		        @Override
		    		        public void mouseReleased(MouseEvent e) {
		    		        	manageMemberButton.setForeground(LIGHTGRAY);
		    		        	manageMemberButton.setBackground(MIDNIGHTBLUE);
		    		        }
		                });

		                // TO FIX AN ISSUE WHERE THE BUTTON KEEPS THE PRESSED STATE COLOR EVEN AFTER CHANGING PANELS
		                manageMemberButton.setBackground(MIDNIGHTBLUE); // Resets color
		                manageMemberButton.setForeground(LIGHTGRAY);
		            }

		        }
		    });
		}

		
		// updating the add member screen when a type of member is selected
		for(JButton memberTypeButton : memberTypeButtons) {
			
			// overriding the overridden method: mousePressed(MouseEvent e) of Button[] array, to match with the current button styling
			memberTypeButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					
					JButton sourceButton = (JButton) e.getSource();
					
					sourceButton.setEnabled(false); // disabling the source button after it is pressed
					
					// removing previous content
					bodyContent[1].remove(memberTypeSelect_P);
					
					// looping through the buttons to get to required form
					for (int i = 0; i < memberTypeButtons.length; i++) {
						if (sourceButton == memberTypeButtons[i]) {
							
							bodyContent[1].add(formPanels[i], BorderLayout.CENTER); // showing corresponding content
							
							isFormContent[i] = true; // since current panel is not member type
							showFormContent.run();
						}
			        	
			        	// setting the form title accroding to the button pressed
			        	int formType = sourceButton == memberTypeButtons[0] ? 0 : 1;
			        	
			        	String formTitle = formType == 0 ? "Add a Premium Member" : "Add a Regular Member";
			        	addMemberTitle_L.setText(formTitle);
			        	
			    		// adding back buttons to premium and regular member content titles
			        	addMemberTitle_P.add(utilityButtons_P[0],BorderLayout.WEST);
			        	
			        	// removing previous buttons from button panel
			        	for(JButton controlButton : formButtons) {
			        		formControlButtonsPanel.remove(controlButton);
			        	}
			        	
			        	// adding buttons to buttons panel
			        		formControlButtonsPanel.add(formButtons[0]);
							formControlButtonsPanel.add(formButtons[1+formType]); // adding "add premium member" OR "add regular member" based on what form was clicked
			        	
						// adding control button panel to bottom panel and add that panel to the main panel
						formButtons_P.add(formControlButtonsPanel,BorderLayout.EAST);
						bodyContent[1].add(formButtons_P, BorderLayout.SOUTH);
						
					}
					
					// refreshing the frame layout to reflect the changes
					bodyContent[1].revalidate(); // recalculating layout
					bodyContent[1].repaint();    // redrawing the frame
					
					
					// TO FIX AN ISSUE WHERE THE BUTTON KEEPS THE PRESSED STATE COLOR EVEN AFTER CHANGING PANELS
					sourceButton.setBackground(MIDNIGHTBLUE); // Resets color
					sourceButton.setForeground(LIGHTGRAY);
					sourceButton.setEnabled(true);
				}
			});
		}
		
		// adding functionality of utility buttons
		for(JLabel utilityButton : utilityButtons_L) {
			utilityButton.addMouseListener(new MouseAdapter() {
				    	
				@Override
				public void mousePressed(MouseEvent e) {

					JLabel sourceLabel = (JLabel) e.getSource();
					
						// add member form back button
						if (sourceLabel == utilityButtons_L[0]) {
							
							int currentFormIndex = isFormContent[0] == true ? 0 : 1; //determining index of current panel
							
							boolean isFormFilled = false; // since form is "empty" by default
							
							for(int j = 0 ; j < inputFields.length - 1; j++) {
								
					        	// setting the corresponding placeholders for input fields into a common variable
					        	String inputPlaceholder = j < (inputFields.length - 2) ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
					        	
					        	// seetting the corresponding input fields into a common variable
					        	JTextField inputField = j < (inputFields.length - 2) ? inputFields[j] : inputFields[j+currentFormIndex];
								
								// checking if atleast one input field doesn't have it's corresponding placeholder or empty value, and setting emptyForm to false when that's the case
								if( ! (inputField.getText().equals(inputPlaceholder)) && ! (inputField.getText().equals("")) ) {
									isFormFilled = true;
									break; // to exit out of this loop when it finds the first user inputted field
								}
							}
							
							if(isFormFilled==true) {
								String options[]= {"Yes","No"}; // to explicitly define options in the following dialog box and select no as the focused button
								
								// showing a confirmation dialog to make sure that all fields are meant to be reset (byte to save memory)
								byte backWish = (byte) JOptionPane.showOptionDialog(frame, "Are you sure you want to clear all fields and go back?", "Caution: Fields will be reset", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
								
			                    GENDER.clearSelection(); // deselecting radio buttons
			                    isGenderSelected = false; // since gender is deselected
			                    
					        	for(int m = 0 ; m < inputFields.length ; m++) {
								
								// only resetting the form if yes is clicked
								if(backWish==0) {
									for(int j=0 ; j < inputFields.length - 1 ; j++ ) {
										
							        	// setting the corresponding placeholders for input fields into a common variable
							        	String inputPlaceholder = j < (inputFields.length - 2) ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];

										inputFields[j].setForeground(PLACEHOLDERGRAY);
										inputFields[j].setText(inputPlaceholder);
										inputFields[j].setFocusable(false);
										inputFields[j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
									}
									
									// reverting title and removing back button
				                    addMemberTitle_L.setText("Add a Member");
						        	addMemberTitle_P.remove(utilityButtons_P[0]);
									
									// removing the current form panel & form buttons
									bodyContent[1].remove(formPanels[currentFormIndex]);
									bodyContent[1].remove(formButtons_P);
									
									//showing the memberTypeSelect panel
									bodyContent[1].add(memberTypeSelect_P, BorderLayout.CENTER); // showing corresponding content

									for(int k = 0 ; k < isFormContent.length ; k++) {
				                    	isFormContent[k] = false; // since current panel is not member type content
				                    }
									
								}
					        	}
							}
							
							else {
								// backing out when the if statement above never executes (when all fields are empty anyways)
								
			                    GENDER.clearSelection(); // deselecting radio buttons
			                    isGenderSelected = false; // since gender is deselected
    
			                    // reverting title and removing back button
			                    addMemberTitle_L.setText("Add a Member");
					        	addMemberTitle_P.remove(utilityButtons_P[0]);
			                    
					        	// removing the current form panel & form buttons
								bodyContent[1].remove(formPanels[currentFormIndex]);
								bodyContent[1].remove(formButtons_P);
								
								//showing the memberTypeSelect panel
								bodyContent[1].add(memberTypeSelect_P, BorderLayout.CENTER); // showing corresponding content
								
								for(int k = 0 ; k < isFormContent.length ; k++) {
			                    	isFormContent[k] = false; // since current panel is not member type content
			                    }
								
							}
							
							for(int j=0 ; j < inputFields.length ; j++ ) {
								inputFields[j].setFocusable(true);
							}
						}
						
						// member management back button
						else if (sourceLabel == utilityButtons_L[1]) {
							
							// so that button doesn't keep it's pressed state when backbutton is clicked
							manageMemberButton.getModel().setPressed(false);
							manageMemberButton.setBackground(MIDNIGHTBLUE);
							manageMemberButton.setForeground(LIGHTGRAY);
							
							// removing individual member management panel and adding default central panel that lets you enter id when button is clicked
							memberManagementContent.remove(individualMemberManagement_P);
							memberManagementContent.add(memberManagementButton_P, BorderLayout.CENTER);							
							
							// removing back button from title
							memberManagementTitle_P.remove(utilityButtons_P[1]);
							
							// reverting title
			                memberManagementTitle_L.setText("Member Management");
						}
						
						
						
						// refreshing the frame layout to reflect the changes
						frame.revalidate(); // recalculating layout
						frame.repaint(); // redrawing the frame
						
				}
			});
		}
		
		
		
		/*
		 *  ACTIVATE/DEACTIVATE MEMBERSHIP BUTTON  
		 */
		
		individualMemberButtons[0].addMouseListener(new MouseAdapter() {
			
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	GymMember member = members.get(currentMemberIndex); // putting the current member into a common variable
		    	showDialog = false; // setting to false so dialog doesn't appear again when we a re already in the management screen

		    	String wishKeyword = member.isActiveStatus()? "Deactivate":"Activate";
                int activateWish = JOptionPane.showOptionDialog(frame, wishKeyword+" "+member.getName()+"'s Membership?", "Confirm "+wishKeyword.substring(0,wishKeyword.length()-1)+"ion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
                
                if(activateWish==0) {
                	
                    if(member.isActiveStatus()) {
                    	// deactivating membership  
                    	member.deactivateMembership();
                    }
                    else {
                    	// activating membership  
                    	member.activateMembership();
                    }
                    
                    // refreshing ui  
                    memberManagementContent.revalidate();
                    memberManagementContent.repaint();
                    
                    SwingUtilities.invokeLater(() -> {
                    	memberCardUpdate.run(); // executing the card text getting/setting runnable  
                    	showDialog = true; // reverting to true after text update is finished
                    	
                    	// success dialog
                    	JOptionPane.showOptionDialog(frame, member.getName()+"'s membership has been successfully "+wishKeyword.toLowerCase()+"d!" , wishKeyword.substring(0,wishKeyword.length()-1)+"ion Successful!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 0);
                    });                 
                } 
                
            	// since the JOptionPane is a modal dialog (meaning it blocks the EDT until it is closed), it can make the button state be stuck
            	// that is why the following statement forces the button's pressed state to be false
                // we do this only for this differently colored button since, for other buttons, pressed state is really identical to PASTELBLUE (mouseEntered color)
            	individualMemberButtons[0].getModel().setPressed(false);
            }
		});
		
		
		/*
		 *  MARK ATTENDANCE BUTTON  
		 */
		
		individualMemberButtons[1].addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	GymMember member = members.get(currentMemberIndex); // putting the current member into a common variable
		    	showDialog = false; // setting to false so dialog doesn't appear again when we a re already in the management screen
		    	
                
		    	if(member.isActiveStatus()) {
			    	int markWish = JOptionPane.showOptionDialog(frame, "Mark "+member.getName()+"'s attendance?", "Confirm Attendance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
			    	
			    	if(markWish==0) {
			    		member.markAttendance();
			    		
	                    SwingUtilities.invokeLater(() -> {
	                    	memberCardUpdate.run(); // executing the card text getting/setting runnable  
	                    	showDialog = true; // reverting to true after text update is finished
	                    	
	                    	// success dialog
	                    	JOptionPane.showOptionDialog(frame, member.getName()+"'s attendance has been successfully marked.", "Attendance Successful!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 0);
	                    });
			    	}
		    	}
		    	else {
		    		JOptionPane.showOptionDialog(frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.", "Inactive Member", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
		    	}
            }
		});
		
		/*
		 *  REVERT MEMBER BUTTON  
		 */
		
		MouseListener revertMember = new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	GymMember member = members.get(currentMemberIndex); // putting the current member into a common variable
		    	showDialog = false; // setting to false so dialog doesn't appear again when we a re already in the management screen
		    	
		    	boolean proceedUpdate = true; // boolean to make the card update or not
		    	
		    	String options[]= {"Yes","No"}; // to explicitly define options in the following dialog box and select no as the focused button
		    	int revertWish = JOptionPane.showOptionDialog(frame, "Proceeding will reset "+member.getName()+"'s data. Are you sure?", "Confirm Reset", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
		    	
		    	if(revertWish==0) {
		    		
		    		if(memberInstanceOf.equals("Regular")) {
		    			
		    			String removalReason = individualMemberFields[1].getText();
		    			RegularMember regularMember = (RegularMember) member; // downcasting to GymMember RegularMember
		    			
		    			// showing error if removal reason field is empty
		    			if(removalReason.equals("")) {
		    				proceedUpdate = false;
		    				JOptionPane.showOptionDialog(frame, "Please enter removal reason first.", "Invalid Reason", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
		    			}
		    			// reverting regular member by passing the non-empty removal reason String
		    			else {
		    				regularMember.revertRegularMember(removalReason);
		    			}
		    		}
		    		
		    		// reverting premium member without any confirmation
		    		else {
		    			PremiumMember premiumMember = (PremiumMember) member; // downcasting to GymMember PremiumMember
		    			
		    			premiumMember.revertPremiumMember();	
		    		}
		    		
		    		if(proceedUpdate) {
	                    SwingUtilities.invokeLater(() -> {
	                    	memberCardUpdate.run(); // executing the card text getting/setting runnable  
	                    	showDialog = true; // reverting to true after text update is finished
	                    	
	                    	// success dialog
	                    	JOptionPane.showOptionDialog(frame, member.getName()+"'s data has been successfully reset.", "Reset Successful!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 0);
	                    });
		    		}
		    	}
            }
		};
		
		// adding the mouse listener above to both member reset buttons
		individualMemberButtons[2].addMouseListener(revertMember);
		individualMemberButtons[3].addMouseListener(revertMember);
		
		
		/*
		 * CALCULATE DISCOUNT/UPGRADE PLAN BUTTON
		 */
		
		individualMemberButtons[4].addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	GymMember member = members.get(currentMemberIndex); // putting the current member into a common variable
		    	showDialog = false; // setting to false so dialog doesn't appear again when we a re already in the management screen
		    	
		    	boolean proceedUpdate = true; // boolean to make the card update or not		
		    	
	    		// making local variables to store data
    			String message="";
    			String title="";
    			int messageType=0x696969;
    			
		    	if(member.isActiveStatus()) {
			    	
			    	/*
			    	 * UPGRADE PLAN
			    	 */
			    	if(memberInstanceOf.equals("Regular")) {
		    			RegularMember regularMember = (RegularMember) member; // downcasting to GymMember RegularMember
		    			
		    			// putting selected plan into a String || initializing with random value if nothing is selected
			    		String selectedPlan = plan_C.getSelectedIndex()==-1? "none" : (String) plan_C.getSelectedItem();
			    		String previousPlan = regularMember.getPlan(); // putting te previous plan in a String before upgradeing to new plan
			    		boolean isDowngrade = regularMember.getPlanPrice(regularMember.getPlan()) > regularMember.getPlanPrice(selectedPlan); // checking if user is downgrading 
			    		
		    			// storing return message in the local variable
		    			message = regularMember.upgradePlan(selectedPlan);

		    			// if member is eligible
		    			if(regularMember.isEligibleForUpgrade()) {
			    			
		    				// if no plan is selected
		    				if(selectedPlan.equals("none")) {
			    			    title = "Ineligible for Upgrade";
			    			    messageType = JOptionPane.ERROR_MESSAGE; // setting dialog type to error
			    			    proceedUpdate = false;
		    				}
		    				// if plan is downgraded
		    				else if(isDowngrade) {
			    			    title = "Downgrade Successful";
			    			    messageType = JOptionPane.INFORMATION_MESSAGE; // setting dialog type to information
			    				message = "Your plan has been downgraded to "+selectedPlan+" for Rs."+regularMember.getPlanPrice(selectedPlan)+"."; // seperate message for downgrade
		    				}
		    				// if same plan as currentPlan is selected
		    				else if(previousPlan.equals(selectedPlan)) {
			    			    title = "Upgrade Unsuccessful";
			    			    messageType = JOptionPane.ERROR_MESSAGE; // setting dialog type to information
		    				}
		    				// if plan is upgraded
		    				else {
			    			    title = "Upgrade Successful";
			    			    messageType = JOptionPane.INFORMATION_MESSAGE; // setting dialog type to information			    					
		    				}
		    			}
		    			else if(!regularMember.isEligibleForUpgrade()){
		    			    title = "Ineligible for Upgrade";
		    			    messageType = JOptionPane.ERROR_MESSAGE; // setting dialog type to error
		    			    proceedUpdate = false;
		    			}
			    			
			    		JOptionPane.showOptionDialog(frame, message, title, JOptionPane.DEFAULT_OPTION, messageType, null, null, 0);
		    		}
			    	
			    	
		    		/*
		    		 * CALCULATE DISCOUNT
		    		 */
		    		else {
		    			PremiumMember premiumMember = (PremiumMember) member; // downcasting to GymMember PremiumMember

		    			
		    			if(!premiumMember.isFullPayment) {
		    			    title = "Ineligible for Discount";
		    			    messageType = JOptionPane.ERROR_MESSAGE; // setting dialog type to error
		    			    proceedUpdate = false;
		    			}
		    			else {
		    			    title = "Discount Application Successful";
		    			    messageType = JOptionPane.INFORMATION_MESSAGE; // setting dialog type to error
		    			    
		    			    // updating non-focusable field text (converting double to String)
		    			    individualMemberFields[0].setText("Rs. "+Double.toString(premiumMember.getDiscountAmount()));
		    			}
		    			
		    			// storing return message in the local variable
		    			message = premiumMember.calculateDiscount();
		    			
		    			JOptionPane.showOptionDialog(frame, message, title, JOptionPane.DEFAULT_OPTION, messageType, null, null, 0);
		    		}
			    	
		    		if(proceedUpdate) {
	                    SwingUtilities.invokeLater(() -> {
	                    	memberCardUpdate.run(); // executing the card text getting/setting runnable  
	                    	showDialog = true; // reverting to true after text update is finished
	                    });
		    		}
		    	}
		    	else {
		    		JOptionPane.showOptionDialog(frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.", "Inactive Member", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
		    	}

            }
		});
		
		
		/*
		 *  REGULAR MEMBERMANGEMENT PLAN COMBO BOX
		 */
		
		plan_C.addItemListener(new ItemListener() {
			
			@Override
		    public void itemStateChanged(ItemEvent e) {
				GymMember member = members.get(currentMemberIndex); // putting current member into a common variable
				RegularMember regularMember = (RegularMember) member; // downcasting member to RegularMember
		    	
		    	individualMemberFields[1].setFocusable(false);
		    	
		    	// checking if an option was selected
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	
		        	String selectedPlan = (String) plan_C.getSelectedItem();
		        	individualMemberFields[0].setForeground(STEELBLUE);
		        	individualMemberFields[0].setText("Rs. "+Double.toString(regularMember.getPlanPrice(selectedPlan)));
		        }
		    }
		});
		
		plan_C.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				GymMember member = members.get(currentMemberIndex); // putting current member into a common variable
		    	if(!member.isActiveStatus()) {
		    		plan_C.setEnabled(false);
		    	}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				plan_C.setEnabled(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				GymMember member = members.get(currentMemberIndex); // putting current member into a common variable

		    	if(!member.isActiveStatus()) {
		    		JOptionPane.showOptionDialog(frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.", "Inactive Member", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
		    	}
			}
		});
		
		/*
		 * PAY DUE AMOUNT BUTTON
		 */
		
		individualMemberButtons[5].addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	
		    	GymMember member = members.get(currentMemberIndex); // putting the current member into a common variable
		    	PremiumMember premiumMember = (PremiumMember) member; // downcasting to GymMember PremiumMember
		    	
		    	if(member.isActiveStatus()) {
					/*
					 * PAY DUE AMOUNT FIELD
					 */

			    	showDialog = false; // setting to false so dialog doesn't appear again when we a re already in the management screen
			    	boolean proceedUpdate = true; // boolean to make the card update or not

			    	String payingAmount = individualMemberFields[1].getText();
			    	
		    		// making local variables to store data
	    			String message="";
	    			String title="";
	    			int messageType=0x696969;
			    	
	    			// when field is not filled
			    	if(payingAmount.equals("") && !premiumMember.isFullPayment()) {
			    		message = "Please enter amount first.";
			    		title = "Invalid Amount";
			    		messageType = JOptionPane.ERROR_MESSAGE;
			    		
			    		proceedUpdate = false; // no need for update
			    	}
			    	else {
			    		
			    		title = "Payment Successful";
			    		messageType = JOptionPane.INFORMATION_MESSAGE;
			    		
			    		try {
			    			message = premiumMember.payDueAmount(Double.parseDouble(payingAmount)); // parsing string to double		    			
			    		}
			    		catch(NumberFormatException e2) {
			    			if(premiumMember.isFullPayment) {
				    			// when the user tries to pay "Fully Paid" text
				    			message = "Your plan has already been paid fully.";
					    		title = "Already Paid";
					    		messageType = JOptionPane.INFORMATION_MESSAGE;
					    		proceedUpdate = false;
			    			}
			    			else {
				    			// when the user tries to pay some text
				    			message = "Please enter a valid amount.";
					    		title = "Invalid Amount";
					    		messageType = JOptionPane.ERROR_MESSAGE;
					    		proceedUpdate = false;
					    		individualMemberFields[1].setText(""); // resetting the field
					    		
			    			}
			    		}
			    	}

			    	JOptionPane.showOptionDialog(frame, message, title, JOptionPane.DEFAULT_OPTION, messageType, null, null, 0);
		    		
		    		if(proceedUpdate) {
	                    SwingUtilities.invokeLater(() -> {
	                    	memberCardUpdate.run(); // executing the card text getting/setting runnable  
	                    	showDialog = true; // reverting to true after text update is finished
	                    });
		    		}
		    	}
		    	else {
		    		JOptionPane.showOptionDialog(frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.", "Inactive Member", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
		    	}
		    }
		});
		
		
		// mouse listetner for input fields
		individualMemberFields[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(memberInstanceOf.equals("Premium")) {
					GymMember member = members.get(currentMemberIndex); // putting the current member into a common variable
					PremiumMember premiumMember = (PremiumMember) member; // downcasting member to premium member
					
					// showing inactive error dialog only when premiumMember.isFullPayment() is false
					if(!individualMemberFields[1].getText().equals(fullyPaidText)) {
						if(memberInstanceOf.equals("Premium") && !premiumMember.isActiveStatus()) {	
				    		individualMemberFields[1].setFocusable(false);
				    		JOptionPane.showOptionDialog(frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.", "Inactive Member", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
				    	}
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
					// making the field editable only when premiumMember.isFullPayment() is false
					if(!individualMemberFields[1].getText().equals(fullyPaidText)) {
						individualMemberFields[1].setFocusable(true);	
				}
			}
		});
	}

	// main metchod that calls constructor of this class
	public static void main(String[] args) {
		new GymGUI();
	}
}