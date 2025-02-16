/*
 * Color palette:
 * 
 * #0D1B2A (Dark Navy)
 * #1B263B (Midnight Blue)
 * #415A77 (Steel Blue)
 * #778DA9 (Slate Blue)
 * #A3B7C8 (Pastel Blue)
 * #E0E1DD (Light Gray)
 * 
 * Extra Colors:
 * 
 * #FFFFFF (White)
 * #DDDEEE (Dark Gray)
 * #696969 (Gray (for placeholder))
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
    final Color STEELBLUE = new Color(0x415A77);
    final Color SLATEBLUE = new Color(0x778DA9);
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

    // content margin
    final Border CONTENT_MARGIN = BorderFactory.createEmptyBorder(marginTopBottom, marginLeftRight, marginTopBottom, marginLeftRight);

    // borders for button states
    final Border DEFAULT_BUTTON_OUTLINE = BorderFactory.createLineBorder(DARKNAVY, 1);
    final Border ACTIVE_BUTTON_HIGHLIGHT = BorderFactory.createMatteBorder(0, 2, 0, 0, LIGHTGRAY);
    
    // borders for input field
    final Border FORM_INPUT_OUTLINE = BorderFactory.createMatteBorder(0, 0, 1, 0, MIDNIGHTBLUE);
    final Border FORM_INPUT_ACTIVE_OUTLINE = BorderFactory.createMatteBorder(0, 0, 2, 0, STEELBLUE);
    final Border FORM_INPUT_PADDING = BorderFactory.createEmptyBorder(0, horizontalMargin, 0, horizontalMargin);
    final Border FORM_INPUT_MARGIN = BorderFactory.createEmptyBorder(formMarginTop, horizontalMargin, formMarginBottom, horizontalMargin);
    
    // border for plan selection
    final Border PLAN_SELECTION_MARGIN = BorderFactory.createEmptyBorder((formMarginTop - planMarginTop), (horizontalMargin - planMarginLeft), formMarginBottom, horizontalMargin);
    
    // padding for member management card
    final Border CARD_MARGIN = BorderFactory.createEmptyBorder(cardMarginTop, cardMarginHorizontal, cardMarginBottom, cardMarginHorizontal);
    final Border CARD_CONTENT_MARGIN = BorderFactory.createEmptyBorder(cardContentMarginVertical, cardContentMarginHorizontal, cardContentMarginVertical, cardContentMarginHorizontal);
    
    // margin for member management card labels
    final Border CARD_UNIQUE_LABEL_MARGIN_LEFT = BorderFactory.createEmptyBorder(0,cardUniqueLabelMarginLeft,0,0);
    final Border CARD_ODD_LABEL_MARGIN_LEFT = BorderFactory.createEmptyBorder(0,cardOddLabelMarginLeft,0,0);
    
    // padding inside buttons
    final Border BUTTON_INNER_PADDING = BorderFactory.createEmptyBorder(buttonPaddingVertical, horizontalMargin, buttonPaddingVertical, horizontalMargin);

    // combined borders
    final Border DEFAULT_BUTTON_BORDER = BorderFactory.createCompoundBorder(DEFAULT_BUTTON_OUTLINE, BUTTON_INNER_PADDING);
    final Border ACTIVE_BUTTON_BORDER = BorderFactory.createCompoundBorder(ACTIVE_BUTTON_HIGHLIGHT, BUTTON_INNER_PADDING);
    
    final Border DEFAULT_INPUT_BORDER = BorderFactory.createCompoundBorder(FORM_INPUT_OUTLINE, FORM_INPUT_PADDING);
    final Border ACTIVE_INPUT_BORDER = BorderFactory.createCompoundBorder(FORM_INPUT_ACTIVE_OUTLINE, FORM_INPUT_PADDING);


    /*
     * extra variables
     */
    
	int activeIndex; // variable to store index of active content
	int lastIndex; // variable to store index of last body content
	String backSymbol = "◄"; // arrow symbol for backButton
	
	final ButtonGroup GENDER = new ButtonGroup(); // button group for gender radio buttons
	boolean isGenderSelected = false;
	int indexOfGenderSelected = -1; // initially
	
	// boolean for if the current panel is membertype content or not (intially false)
	boolean[] isFormContent = {
			false, //premium
			false //regular
			}; 
	
	// for member card buttons funcitonality
    boolean showDialog = true;
    String lastMemberID = "-1";
    Runnable inputDialogHandler; // declaring a runnable
	
    
	/*
	 * Frame variables
	 */
    
	JFrame frame = new JFrame("Gym Software"); // creating a new frame with the title "Gym GUI"
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
	
	
	/*
	 * dashboard VARIABLES
	 */
	
	JPanel dashboardContent;
	
		JPanel dashboardTitle_P;
			JLabel dashboardTitle_L;
	
	
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
				
				
				// premium member form
				JPanel prem_form_P = new JPanel();
				JPanel prem_id_P, prem_name_P, prem_location_P, prem_phone_P, prem_email_P, prem_gender_P, prem_DOB_P, prem_membershipStartDate_P, prem_personalTrainer_P;
					
				JLabel prem_id_L = new JLabel("ID:"),
					   prem_name_L = new JLabel("Name:"),
					   prem_location_L = new JLabel("Location:"),
					   prem_phone_L = new JLabel("Phone:"),
					   prem_email_L = new JLabel("Email:"),
					   prem_gender_L = new JLabel("Gender:"),
					   prem_DOB_L = new JLabel("Date of Birth:"),
					   prem_membershipStartDate_L = new JLabel("Start Date:"),
					   prem_personalTrainer_L = new JLabel("Trainer Name:"); 
					
				JTextField prem_id_F, prem_name_F, prem_location_F, prem_phone_F, prem_email_F, prem_DOB_F, prem_membershipStartDate_F, prem_personalTrainer_F;
				
				JRadioButton
					prem_genderMale = new JRadioButton("Male"),
					prem_genderFemale = new JRadioButton("Female");
				
					
				// regular member form
				JPanel regular_form_P = new JPanel();
				JPanel regular_id_P, regular_name_P, regular_location_P, regular_phone_P, regular_email_P, regular_gender_P, regular_membershipStartDate_P, regular_DOB_P, regular_referralSource_P;
					
				JLabel regular_id_L = new JLabel("ID:"),
					   regular_name_L = new JLabel("Name:"),
					   regular_location_L = new JLabel("Location:"),
					   regular_phone_L = new JLabel("Phone:"),
					   regular_email_L = new JLabel("Email:"),
					   regular_gender_L = new JLabel("Gender:"),
					   regular_DOB_L = new JLabel("Date of Birth:"),
					   regular_membershipStartDate_L = new JLabel("Start Date:"),
					   regular_referralSource_L = new JLabel("Referral Source:"); 
					
				JTextField regular_id_F, regular_name_F, regular_location_F, regular_phone_F, regular_email_F, regular_DOB_F, regular_membershipStartDate_F, regular_referralSource_F;
				
				JRadioButton
					regular_genderMale = new JRadioButton("Male"),
					regular_genderFemale = new JRadioButton("Female");
				
					JPanel regular_plan_P;
						JLabel regular_plan_L = new JLabel("Select a Plan:");
							String[] plans = {"Basic","Standard","Deluxe"};
							JComboBox<String> regular_plan_C = new JComboBox<String>(plans);
							
							
				JPanel formButtons_P = new JPanel();
				JPanel formControlButtonsPanel = new JPanel();
					JButton[]
						formButtons= {
								new JButton("Clear All"), // clear form button
								new JButton("Add Premium Member"),
								new JButton("Add Regular Member")
						};
				

			JButton[]
				//1D array of form control buttons
				formControlButtons = {
					formButtons[0],
					formButtons[1],
					formButtons[2]
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
							new JLabel("Upgrade? : "),
							new JLabel("Referral Source : "),
							new JLabel("Removal Reason : ")

					};
				
			JPanel individualMemberButtons_P = new JPanel();
	/*
	 * ARRAYS
	 */
	
	JLabel[]
		//array of body titles
		contentTitles= {
			dashboardTitle_L = new JLabel("Dashboard"),		
			addMemberTitle_L = new JLabel("Add a Member"),
			memberManagementTitle_L = new JLabel("Member Management")
		},
		//array of utility buttons
		utilityButtons_L= {
			new JLabel(backSymbol), //back button for addMember forms
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
		
		// array of memberManagement contents
		memberManagementPanels = {
			memberManagementButton_P = new JPanel(),	
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
		},
		//array of form panels
		formPanels = {
			prem_form_P = new JPanel(),
			regular_form_P = new JPanel()
		},
		// array of center body panels
		centralBodyPanels = {
			memberTypeSelect_P = new JPanel(),
			memberManagementButton_P = new JPanel()
		},
		//array of member card panels
		cardPanels = {
			cardCommonAttributes_P,
			cardUniqueAttributes_P
		};
	
	JButton[]
			//array of all buttons
			Buttons= {
				menuButtons[0],
				menuButtons[1],
						formControlButtons[0],
						formControlButtons[1],
						formControlButtons[2],
				menuButtons[2],
			},
			// array of body buttons
			centralPanelButtons = {
					memberTypeButtons[0],
					memberTypeButtons[1],
					manageMemberButton,
			};
	
	JLabel[][]
			//array of premium form input labels
			inputLabels = {
				{
					prem_id_L,
					prem_name_L,
					prem_location_L,
					prem_phone_L,
					prem_email_L,
					prem_gender_L,
					prem_DOB_L,
					prem_membershipStartDate_L,
					prem_personalTrainer_L	
				},
				{
					regular_id_L,
					regular_name_L,
					regular_location_L,
					regular_phone_L,
					regular_email_L,
					regular_gender_L,
					regular_DOB_L,
					regular_membershipStartDate_L,
					regular_referralSource_L,
					regular_plan_L
				}
			};
			
	
	JPanel[][]
		//array of premium member form panels
		inputPanels = {
		{
			prem_id_P = new JPanel(),
			prem_name_P = new JPanel(),
			prem_location_P = new JPanel(),
			prem_phone_P = new JPanel(),
			prem_email_P = new JPanel(),
			prem_gender_P = new JPanel(),
			prem_DOB_P = new JPanel(),
			prem_membershipStartDate_P = new JPanel(),
			prem_personalTrainer_P = new JPanel()	
		},
		{
			regular_id_P = new JPanel(),
			regular_name_P = new JPanel(),
			regular_location_P = new JPanel(),
			regular_phone_P = new JPanel(),
			regular_email_P = new JPanel(),
			regular_gender_P = new JPanel(),
			regular_DOB_P = new JPanel(),
			regular_membershipStartDate_P = new JPanel(),
			regular_referralSource_P = new JPanel()	,
			regular_plan_P = new JPanel()
		}
	};
	
	JTextField[][]
		//2D array of form fields
		inputFields = {
		{
			prem_id_F = new JTextField(),
			prem_name_F = new JTextField(),
			prem_location_F = new JTextField(),
			prem_phone_F = new JTextField(),
			prem_email_F = new JTextField(),
			prem_DOB_F = new JTextField(),
			prem_membershipStartDate_F = new JTextField(),
			prem_personalTrainer_F = new JTextField()	
		},
		{
			regular_id_F = new JTextField(),
			regular_name_F = new JTextField(),
			regular_location_F = new JTextField(),
			regular_phone_F = new JTextField(),
			regular_email_F = new JTextField(),
			regular_DOB_F = new JTextField(),
			regular_membershipStartDate_F = new JTextField(),
			regular_referralSource_F = new JTextField()	
		}
	};
	
	JRadioButton[][]
		//2D array of gender radio buttons
		genderRadioButtons = {
		{
			prem_genderMale = new JRadioButton("Male"),
			prem_genderFemale = new JRadioButton("Female")
		},
		{
			regular_genderMale = new JRadioButton("Male"),
			regular_genderFemale = new JRadioButton("Female")
		}
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
				// regular form placeholders (Referral source)
				"eg., Friend, Website, Ad"	
			}
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
		
		for(int i = 0 ; i < bodyContent.length - 1; i++) {
			
			// styling the central panel titles
			centralPanelTitles[i].setFont(HEADING1_FONT);
			centralPanelTitles[i].setForeground(MIDNIGHTBLUE);
			centralPanelTitles[i].setPreferredSize(new Dimension(600,150));
			centralPanelTitles[i].setBorder(CONTENT_MARGIN); // setting margin
			centralPanelTitles[i].setHorizontalAlignment(SwingConstants.CENTER);
			centralPanelTitles[i].setVerticalAlignment(SwingConstants.BOTTOM);
			
			//styling the central panel
			centralBodyPanels[i].setPreferredSize(new Dimension(1,1)); 
			centralBodyPanels[i].add(centralPanelTitles[i]);
			centralBodyPanels[i].setBackground(LIGHTGRAY);
			centralBodyPanels[i].setBorder(CONTENT_MARGIN);
			
			if(i==0) {
				// adding one extra button to the select member type panel
				centralBodyPanels[i].add(centralPanelButtons[i]);
			}
			
			// adding corresponding buttons to the panels
			centralBodyPanels[i].add(centralPanelButtons[i+1]);
			
			bodyContent[i+1].add(centralBodyPanels[i], BorderLayout.CENTER);
		}
		
		
		/*
		 * DASHBOARD SECTION
		 */
		
		
		
		/*
		 * addMember back button
		 */
		
		// setting attributes of utilityButton text using a for each loop
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
		 * ADD A MEMBER SECTION
		 */
		

		// forms styling
		for (int i = 0; i < formPanels.length; i++) {
		    
		    formPanels[i].setBackground(LIGHTGRAY);
		    formPanels[i].setLayout(new GridLayout(3, 3, 0, 0));

		    for (int j = 0; j < (inputPanels[0].length); j++) {

		        inputLabels[i][j].setForeground(MIDNIGHTBLUE);
		        inputLabels[i][j].setFont(INPUT_HEADING);

		        
		        // initializing panels
		        JPanel inputPanel = inputPanels[i][j]; //putting the current panel into a variable for easy future use

		        inputPanel.setBorder(FORM_INPUT_MARGIN);
		        inputPanel.setBackground(LIGHTGRAY);
		        inputPanel.setLayout(new BorderLayout());
		        inputPanel.add(inputLabels[i][j], BorderLayout.NORTH); // adding label at the top

		        if (j == 5) { 
		        	//adding gender radio buttons instead of a text field for panel 5
		        	
		        	inputPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0)); // setting different layout
		            inputLabels[i][j].setPreferredSize(new Dimension(200, 15)); // increasing the width of label to make it so that gender radio buttons appaer on next line


		            // to set attributes of radio buttons
		            
		            for (int k = 0; k < 2; k++) {

		                JRadioButton genderRadio = genderRadioButtons[i][k]; //putting the current radio button into a variable for easy future use
		                
		                GENDER.add(genderRadio);
		                genderRadio.setForeground(MIDNIGHTBLUE);
		                genderRadio.setBackground(LIGHTGRAY);
		                genderRadio.setFont(RADIO_FONT);
		                genderRadio.setFocusable(false);
		                genderRadio.setPreferredSize(new Dimension(80, 60));
		                
		                int l = i; // to pass i into the next block
		                
		                // to set isGenderSelected to true when a gender radio is clicked
		                genderRadio.addMouseListener(new MouseAdapter() {
		                	
		                	@Override
		                	public void mousePressed(MouseEvent e) {		                		
		                		for(int j = 0 ; j < inputFields[1].length ; j++) {
				        			inputFields[l][j].setFocusable(false);
				        			
				        			indexOfGenderSelected = e.getSource()==genderRadioButtons[l][0] ? 0 : 1;
				        			
				        		}
		                	}
		                	
		                	@Override
		                	public void mouseReleased(MouseEvent e) {		                		
		                		for(int j = 0 ; j < inputFields[1].length ; j++) {
				        			inputFields[l][j].setFocusable(true);
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
		        	
		        	JTextField inputField; //declaring a common variable 
		        	
		        	int k = j < 5 ? j : j-1; // to use/add [j-1] fields to the panels after panel 5
		        		
		        	// setting the placeholders for input variables
		        	String inputPlaceholder = k != 7 ? commonPlaceholders[k] : uniquePlaceholders[i][0];
		        	
	                inputPanel.add(inputFields[i][k], BorderLayout.SOUTH);
	                inputField = inputFields[i][k]; // assigning required/corresponding field to the common variable
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
		        formPanels[i].add(inputPanel);
		    }
		    

			 // for regular member form only (since one extra panel is added, the layout is changed)
//			if(i == 1) {
//				
//				regular_plan_L.setForeground(MIDNIGHTBLUE);
//		        regular_plan_L.setFont(INPUT_HEADING);
//				
//		        regular_plan_C.setSelectedIndex(-1); // setting null value by default
//		        regular_plan_C.setBackground(LIGHTGRAY);
//		        regular_plan_C.setPreferredSize(new Dimension(160,29));
//		        regular_plan_C.setForeground(MIDNIGHTBLUE);
//		        regular_plan_C.setFont(INPUT_FONT);
//		        regular_plan_C.setFocusable(false); // to make it so that the annoying highlight doesn't appear
//		        
//		        // to remove caret fromfields when the combobox is clicked
//		        regular_plan_C.addMouseListener(news MouseAdapter() {
//		        
//		        	@Override
//		        	public void mousePressed(MouseEvent e) {
//		        		for(int j = 0 ; j < inputFields[1].length ; j++) {
//		        			inputFields[1][j].setFocusable(false);		        			
//		        		}
//		        	}
//		        	
//		        	@Override
//		        	public void mouseReleased(MouseEvent e) {
//		        		for(int j = 0 ; j < inputFields[1].length ; j++) {
//		        			inputFields[1][j].setFocusable(true);		        			
//		        		}
//		        	}
//		        });
//		        
//				regular_plan_P.setBackground(LIGHTGRAY);
//				regular_plan_P.setPreferredSize(new Dimension(300,1));
//				regular_plan_P.setBorder(PLAN_SELECTION_MARGIN);
//				regular_plan_P.setLayout(new FlowLayout(FlowLayout.LEADING,planMarginLeft,planMarginTop));
//				
//				regular_plan_P.add(regular_plan_L, BorderLayout.NORTH);
//				regular_plan_P.add(regular_plan_C);
//				
//				formPanels[1].setLayout(new GridLayout (3, 3, 0, 0));
//				formPanels[1].setPreferredSize(new Dimension(1, 800));
//				
//				formPanels[1].add(regular_plan_P);
//				
//			}
			

		    // setting attributes and adding buttons to the panel below the form
		    
		    formButtons_P.setBackground(LIGHTGRAY);
		    formButtons_P.setPreferredSize(new Dimension(1, 100));
		    formButtons_P.setLayout(new BorderLayout());

			
			formControlButtonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,10,30));
			formControlButtonsPanel.setPreferredSize(new Dimension(400,1));
			formControlButtonsPanel.setBackground(LIGHTGRAY);
		}
		
		// for form control buttons functionality
		for(int k = 0 ; k < formControlButtons.length ; k++) {
			JButton formButtons = formControlButtons[k];
			
			formButtons.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					// event listener for clear all button of each form
					if(e.getSource()==formControlButtons[0]) {
						
						int currentFormIndex = isFormContent[0] ? 0:1;
						
						String options[]= {"Yes","No"}; // to explicitly define options in the following dialog box and select no as the focused button
						for(int i = 0 ; i < inputFields[currentFormIndex].length ; i++) {
				        	// setting the placeholders for input variables
				        	String inputPlaceholder = i < 7 ? commonPlaceholders[i] : uniquePlaceholders[currentFormIndex][0];
				        	
							// checking if input fields dont have their corresponding placeholders or empty value, and only allowing form clearing when that's the case
							if( ! (inputFields[currentFormIndex][i].getText().equals(inputPlaceholder)) && ! (inputFields[currentFormIndex][i].getText().equals(""))) {
							
								// showing a confirmation dialog to make sure that all fields are meant to be reset (byte to save memory)
								byte clearWish = (byte) JOptionPane.showOptionDialog(frame, "Are you sure you want to clear all fields?", "Caution: Fields will be reset", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						
								// only resetting the form if yes is clicked
								if(clearWish==0) {
									for(int j=0 ; j < inputFields[currentFormIndex].length ; j++ ) {
										
										// setting the placeholders for input variables
										String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
										
										inputFields[currentFormIndex][j].setForeground(PLACEHOLDERGRAY);
										inputFields[currentFormIndex][j].setText(placeholder);
										inputFields[currentFormIndex][j].setFocusable(false);
										inputFields[currentFormIndex][j].setBorder(DEFAULT_INPUT_BORDER);
									}
									
									GENDER.clearSelection(); // deselecting radio buttons
									isGenderSelected = false; // since gender is deselected
									
									//=========================
//									regular_plan_C.setSelectedIndex(-1); // deselecting plan
									//=========================
									
									JOptionPane.showMessageDialog(frame, "Form successfully cleared!", "Success", JOptionPane.INFORMATION_MESSAGE); // success message
								}
								else {
									break; //exiting out of the loop and doing nothing else when either 'no' or close button is pressed
								}
							}
							
							// removing caret and adding placeholder silently when atlest one field is null, and the clear all button is clicked
							if((inputFields[currentFormIndex][i].getText().equals(""))) {
								
								for(int j=0 ; j < inputFields[currentFormIndex].length ; j++ ) {
									
									// setting the placeholders for input variables
									String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
									
									inputFields[currentFormIndex][j].setForeground(PLACEHOLDERGRAY);
									inputFields[currentFormIndex][j].setText(placeholder);
									inputFields[currentFormIndex][j].setFocusable(false);
									inputFields[currentFormIndex][j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
								}
							}
						}
						
						GENDER.clearSelection(); // deselecting radio buttons
						isGenderSelected = false; // since gender is deselected
						
						//=========================
//						regular_plan_C.setSelectedIndex(-1); // deselecting plan
						//=========================
					}
					
					// event listener for add premium member/regular member buttons of respective forms
					if(e.getSource()==formControlButtons[1] || e.getSource()==formControlButtons[2]) {
						
						boolean allFieldsFilled = true; // assuming all fields are filled initially
						
						int currentFormIndex = e.getSource()==formControlButtons[1] ? 0 : 1;
						String memberType = isFormContent[0] ? "premium" : "regular";

						for (int k = 0; k < inputFields[currentFormIndex].length; k++) {
						    
							String inputPlaceholder = k != 7 ? commonPlaceholders[k] : uniquePlaceholders[currentFormIndex][0];
						    
				        	String text = inputFields[currentFormIndex][k].getText();

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
						
						 // checking if gender is selected after confirming all fields are filled OR checking if the current form is regular member form, and if the plan is selected but gender is not
						
						//=========================
//						else if (!isGenderSelected || (!isGenderSelected && isFormContent[1] && regular_plan_C.getSelectedIndex() != -1 )) {
						//=========================
						
						else if (!isGenderSelected) {	
							// remving caret from the fields
							for (int k = 0; k < inputFields[currentFormIndex].length; k++) {
								inputFields[currentFormIndex][k].setFocusable(false);
							}
							
						    JOptionPane.showMessageDialog(frame, "Please select a gender.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
						} 
						
						//=========================
						// checking if the form is the regular member form and if no plan is selected
//						else if (isFormContent[1] && regular_plan_C.getSelectedIndex() == -1 ) {
//							
//							// remving caret from the fields
//							for (int k = 0; k < inputFields[l].length; k++) {
//								inputFields[l][k].setFocusable(false);
//							}
//							
//							JOptionPane.showMessageDialog(frame, "Please choose a plan.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
//						}
						//=========================
						
						// proceeding with adding a member if all conditions are met
						else {  

							boolean isUniqueId = true; // boolean to check if id is unique (set to true by default)
							
							// using try/catch blocks to handle String -> int conversion issues
						    try {
						        int id = Integer.parseInt(inputFields[currentFormIndex][0].getText()); // Convert input to int
						        
						        for (GymMember member : members) {
						            if (member.id == id) {
						                isUniqueId = false;
						                break; // since there is no need to check for any more matches
						            }
						        }
						        
						        if(isUniqueId) {
						        	
						        	// declaring variables to pass into contructor of respective classes
						        	String 
					        		name = inputFields[currentFormIndex][1].getText(),
					        		location = inputFields[currentFormIndex][2].getText(),
					        		phone = inputFields[currentFormIndex][3].getText(),
					        		email = inputFields[currentFormIndex][4].getText(),
					        		gender = indexOfGenderSelected==0 ? "Male" : "Female", // use of ternary operator to dynamically assign value
					        		DOB = inputFields[currentFormIndex][5].getText(),
					        		membershipStartDate = inputFields[currentFormIndex][6].getText(),
					        		personalTrainer = inputFields[0][7].getText(), // unique field
					        		referralSource = inputFields[1][7].getText(); // unique field
						        	
						        	
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
									for(int j=0 ; j < inputFields[currentFormIndex].length ; j++ ) {
										
										// setting the placeholders for input variables
										String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
										
										inputFields[currentFormIndex][j].setForeground(PLACEHOLDERGRAY);
										inputFields[currentFormIndex][j].setText(placeholder);
										inputFields[currentFormIndex][j].setFocusable(false);
										inputFields[currentFormIndex][j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
									}
									
									GENDER.clearSelection(); // deselecting radio buttons
									isGenderSelected = false; // since gender is deselected
									
									//=========================
//									regular_plan_C.setSelectedIndex(-1); // deselecting plan
									//=========================
						        
						        }
						        else {
						        	JOptionPane.showMessageDialog(frame, "Member with ID "+inputFields[currentFormIndex][0].getText()+" already exists! Please enter a new ID.", "Duplicate input", JOptionPane.ERROR_MESSAGE);
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
					int currentFormIndex = isFormContent[0] ? 0:1;
					for(int j=0 ; j < inputFields[currentFormIndex].length ; j++ ) {
						inputFields[currentFormIndex][j].setFocusable(true);
					}
				}
			});
		}
		
		
		/*
		 * MEMBER MANAGEMENT SECTION
		 */
		
		/*
				
			JPanel individualMemberManagement_P = new JPanel();
				JPanel memberCard_P = new JPanel();
				JPanel cardCommonAttributes_P = new JPanel();
				
					JPanel memberPersonalDetails_P = new JPanel();
					JPanel memberContacts_P = new JPanel();
					JPanel memberAttendance = new JPanel();
					
				JPanel cardUniqueAttributes_P = new JPanel();
					
					JPanel memberTypeAttributes = new JPanel();
				
				JLabel[]
					// card detail labels
					cardLabels = {
								
						// common attributes
						new JLabel(), // name
						new JLabel(), // id
						new JLabel(), // activeStatus
						new JLabel(), // membershipStartDate
						new JLabel(), // location
						new JLabel(), // phone
						new JLabel(), // email
						new JLabel(), // DOB
						new JLabel(), // attendance
						new JLabel(), // loyalty points
						
						// unique attributes for premium members
						new JLabel(), // paid amount (premium)
						new JLabel(), // discount (premium)
						new JLabel(), // isFullPayment (premium)
						new JLabel(), // trainer name (premium)
						
						// unique attributes for regular members
						new JLabel(), // current plan (regular)
						new JLabel(), // isEligibleForUpgrade (regular)
						new JLabel(), // referralSource (regular)
						new JLabel(), // removal reason (regular)				
				};
		 */
		
		
		bodyContent[2].remove(memberManagementButton_P);
		bodyContent[2].add(individualMemberManagement_P);
		
		individualMemberManagement_P.setLayout(new BorderLayout());
		individualMemberManagement_P.add(memberCard_P, BorderLayout.NORTH);
		individualMemberManagement_P.add(individualMemberButtons_P, BorderLayout.CENTER);
		
		// card styling
		memberCard_P.setBorder(CARD_MARGIN);
		memberCard_P.setBackground(LIGHTGRAY);
		memberCard_P.setPreferredSize(new Dimension(1,280));
		memberCard_P.setLayout(new BorderLayout());
		memberCard_P.add(cardCommonAttributes_P, BorderLayout.WEST);
		memberCard_P.add(cardUniqueAttributes_P, BorderLayout.CENTER);
		
		// first card panel styling
		cardCommonAttributes_P.setBackground(LIGHTGRAY);
		cardCommonAttributes_P.setPreferredSize(new Dimension(475,1));
		cardCommonAttributes_P.setLayout(new GridLayout(0,2));
		
		// second card panel styling
		cardUniqueAttributes_P.setBackground(DARKGRAY);
		cardUniqueAttributes_P.setLayout(new GridLayout(0,1));
		
		// individual member management buttons panel styling 
		individualMemberButtons_P.setBackground(Color.blue);
	
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
		
		activeIndex = 2; // setting the lastContent to dashboardContent, initially
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
		                    	
		                        int currentFormPanelIndex = isFormContent[0] ? 0 : 1; // determining index of the current panel
		                        boolean isFormFilled = false;

		                        for (int j = 0; j < inputFields[currentFormPanelIndex].length; j++) {
		                            String inputPlaceholder = j != 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormPanelIndex][0];

		                            // checking if any field has user input
		                            if (!inputFields[currentFormPanelIndex][j].getText().equals(inputPlaceholder) && !inputFields[currentFormPanelIndex][j].getText().isEmpty()) {
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
		                        for (int j = 0; j < inputFields[currentFormPanelIndex].length; j++) {
		                            String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormPanelIndex][0];
		                            inputFields[currentFormPanelIndex][j].setForeground(PLACEHOLDERGRAY);
		                            inputFields[currentFormPanelIndex][j].setText(placeholder);
		                            inputFields[currentFormPanelIndex][j].setFocusable(false);
		                            inputFields[currentFormPanelIndex][j].setBorder(DEFAULT_INPUT_BORDER);
		                        }

		                        GENDER.clearSelection();
		                        isGenderSelected = false;

		                        // reverting title and remove back button
		                        addMemberTitle_L.setText("Add a Member");
		                        addMemberTitle_P.remove(utilityButtons_P[0]);

		                        // removing current form panel and form buttons
		                        bodyContent[1].remove(formPanels[currentFormPanelIndex]);
		                        bodyContent[1].remove(formButtons_P);
		                        
		                        // showing the memberTypeSelect panel
		                        bodyContent[1].add(memberTypeSelect_P, BorderLayout.CENTER);
		                        
		                        // making the fields be focusable after the form panel has been removed
		                        for(int j = 0 ; j < inputFields[currentFormPanelIndex].length ; j++) {
		                        	inputFields[currentFormPanelIndex][j].setFocusable(true);
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
		                bodyContent[2].remove(memberManagementButton_P);
		                bodyContent[2].remove(individualMemberManagement_P);
		                
		                // initializing the Runnable interface to declare a function to handle the dialog and input processing
		                inputDialogHandler = new Runnable() {
		                	
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
		                                    
		                                    String memberInstanceOf = "";
		                                    
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

		                                    // checking if the ID exists using a for-each loop of GymMember objects
		                                    for (GymMember member : members) {
		                                        if (member.id == memberId) {
		                                            isExistingId = true;
		                                            
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
			                                            memberDetails[13] = premiumMember.getPersonalTrainer();
			                                            
		                                            }
		                                            // regular member unique getters
		                                            else {

		                                            	RegularMember regularMember = (RegularMember) member; // downcasting to access child methods
		                                            	
		                                            	memberDetails[10] = regularMember.getPlan(); // converting double to string
		                                            	memberDetails[11] = regularMember.isEligibleForUpgrade() == true ? "Yes" : "No"; // getting boolean and using corresponding string for readability
		                                            	memberDetails[12] = regularMember.getReferralSource();
			                                            memberDetails[13] = regularMember.getRemovalReason().equals("")? "N/A" : regularMember.getRemovalReason(); // showing N/A when removal reason is empty
		                                            }
		                                            
		                                            break; // no need to check further
		                                        }
		                                    }

		                                    if (isExistingId) {
		                                    	
		                                    	memberManagementContent.revalidate();
		                                        memberManagementContent.repaint();
		                                    	
		                                        // updating the title
		                                        memberManagementTitle_L.setText(memberInstanceOf + " Member | " + memberDetails[0] + " (ID: " + memberId + ")");
		                                        
		                                        // removing previous content
		                                        memberManagementContent.remove(memberManagementButton_P);
		                                        memberManagementContent.remove(individualMemberManagement_P);
		                                        
		                                        memberManagementContent.add(individualMemberManagement_P, BorderLayout.CENTER); 
		                                        
		                                        String memberType = memberInstanceOf; // to pass into next block
		                                        
		                                        for(JPanel cardPanel : cardPanels) {
		                                        	cardPanel.removeAll(); // removing all previous labels completely
		                                        }
		                                        
		                                        // Looping through the updated member details and creating new labels dynamically  
		                                        for (int i = 0; i < cardLabels.length-4 ; i++) {  
		                                        	
		                                            // determining text color for active status (nested ternary operator)
		                                            String activeStatusTextColor = (i==2) ? (memberDetails[2].equals("Active") ? "color: green" : "color: red") : "";
		                                            
		                                            // using i+4 card titles when i is greater than 9 and the member is a regular member
		                                            String detailTitle = (i<10) ? cardLabels[i].getText() : cardLabels[i+(memberType.equals("Premium")? 0 : 4)].getText();
		                                            
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
			                                                activeStatusTextColor,
			                                                (i == 2) ? "⦿ " : "", // adding ⦿ symbol to active status value using ternary operator
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
		                    }
		                };
		                
		                // calling the run function initalized above, initially
		                inputDialogHandler.run();

		                // removing all mouse listeners from the manageMemberButton so that it doesnt keep it's pressed state even after changing panels
		                for (MouseListener listener : manageMemberButton.getMouseListeners()) {
		                    manageMemberButton.removeMouseListener(listener);
		                }
		                
		                // adding an action listener to the button (when it's re-added to the panel)
		                manageMemberButton.addMouseListener(new MouseAdapter() {
		                    @Override
		                    public void mousePressed(MouseEvent e) {
		                    	manageMemberButton.setForeground(MIDNIGHTBLUE);
		                    	
		                        // triggering the same dialog input handling (run() function) when the button is clicked
		                        inputDialogHandler.run();
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
						}
			        	
			        	// setting the form title accroding to the button pressed
			        	int formType = sourceButton == memberTypeButtons[0] ? 0 : 1;
			        	
			        	String formTitle = formType == 0 ? "Add a Premium Member" : "Add a Regular Member";
			        	addMemberTitle_L.setText(formTitle);
			        	
			    		// adding back buttons to premium and regular member content titles
			        	addMemberTitle_P.add(utilityButtons_P[0],BorderLayout.WEST);
			        	
			        	// removing previous buttons from button panel
			        	for(JButton controlButton : formControlButtons) {
			        		formControlButtonsPanel.remove(controlButton);
			        	}
			        	
			        	// adding buttons to buttons panel
			        		formControlButtonsPanel.add(formControlButtons[0]);
							formControlButtonsPanel.add(formControlButtons[1+formType]); // adding "add premium member" OR "add regular member" based on what form was clicked
			        	
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
					
						if (sourceLabel == utilityButtons_L[0]) {
							
							int currentFormIndex = isFormContent[0] == true ? 0 : 1; //determining index of current panel
							
							boolean isFormFilled = false; // since form is "empty" by default
							
							for(int j = 0 ; j < inputFields[currentFormIndex].length ; j++) {
								
								// setting the placeholders for input variables
								String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0]; 
								
								// checking if atleast one input field doesn't have it's corresponding placeholder or empty value, and setting emptyForm to false when that's the case
								if( ! (inputFields[currentFormIndex][j].getText().equals(placeholder)) && ! (inputFields[currentFormIndex][j].getText().equals("")) ) {
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
			                    
			                    //=========================
//			                    regular_plan_C.setSelectedIndex(-1); // deselecting plan
			                    //=========================
			                    
					        	for(int m = 0 ; m < inputFields[currentFormIndex].length ; m++) {
								
								// only resetting the form if yes is clicked
								if(backWish==0) {
									for(int j=0 ; j < inputFields[currentFormIndex].length ; j++ ) {
										
										// setting the placeholders for input variables
										String placeholder = j < 7 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];

										inputFields[currentFormIndex][j].setForeground(PLACEHOLDERGRAY);
										inputFields[currentFormIndex][j].setText(placeholder);
										inputFields[currentFormIndex][j].setFocusable(false);
										inputFields[currentFormIndex][j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
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
			                    
			                    //=========================
//			                    regular_plan_C.setSelectedIndex(-1); // deselecting plan
			                    //=========================
											                    
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
							
							for(int j=0 ; j < inputFields[currentFormIndex].length ; j++ ) {
								inputFields[currentFormIndex][j].setFocusable(true);
							}
						}
						
						
						
						// refreshing the frame layout to reflect the changes
						frame.revalidate(); // recalculating layout
						frame.repaint(); // redrawing the frame
						
				}
			});
		}
		
		// activate membership button  
		individualMemberButtons_P.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        for (GymMember member : members) {
		            if (lastMemberID.equals(Integer.toString(member.getId()))) {
		                if (showDialog) {
		                    showDialog = false; // setting to false so it doesn't show multiple times  

		                    // activating membership  
		                    member.activateMembership();  

		                    // handling dialog  
		                    SwingUtilities.invokeLater(() -> {
		                        inputDialogHandler.run(); // executing the dialog logic  
		                        showDialog = true; // setting to true after dialog is shown  
		                    });

		                    // refreshing ui  
		                    memberManagementContent.revalidate();
		                    memberManagementContent.repaint();
		                }
		                break; // stopping loop once found  
		            }
		        }
		    }
		});

		// deactivate membership button  
		menuBar.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        for (GymMember member : members) {
		            if (lastMemberID.equals(Integer.toString(member.getId()))) {
		            	
		                if (showDialog) {
		                    showDialog = false; // setting to false so it doesn't show multiple times  

		                    // deactivating membership  
		                    member.deactivateMembership();  

		                    // handling dialog  
		                    SwingUtilities.invokeLater(() -> {
		                        inputDialogHandler.run(); // executing the dialog logic  
		                        showDialog = true; // setting to true after dialog is shown  
		                    });

		                    // refreshing ui  
		                    memberManagementContent.revalidate();
		                    memberManagementContent.repaint();   
		                }
		                break; // stopping loop once found  
		            }
		        }
		    }
		});

	}

	// main metchod that calls constructor of this class
	public static void main(String[] args) {
		new GymGUI();
	}
}