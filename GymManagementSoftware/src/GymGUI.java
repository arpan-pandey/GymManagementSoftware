/*
 * Color palette:
 * 
 * #0D1B2A (Dark Navy)
 * #1B263B (Midnight Blue)
 * #2E4057 (Gunmetal Blue)
 * #415A77 (Steel Blue)
 * #6D89A0 (Dusty Blue)
 * #A3B7C8 (Pastel Blue)
 * #F4F4F4 (Light Gray)
 * 
 * Extra Colors:
 * 
 * #FFFFFF (White)
 * #DDDEEE (Dark Gray)
 * #EAEAEA (Gray)
 * #696969 (Placeholder Gray)   
 * 
 * #CB0000 (Red)
 * #009400 (Green)
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("unchecked") // for combobox type safety issue
public class GymGUI{
    /*
     * color variables
     */
    
    // palette colors
    private final Color DARKNAVY = new Color(0x0D1B2A);
    private final Color MIDNIGHTBLUE = new Color(0x1B263B);
    private final Color GUNMETALBLUE = new Color(0x2E4057);
    private final Color STEELBLUE = new Color(0x415A77);
    private final Color DUSTYBLUE = new Color(0x6D89A0);
    private final Color PASTELBLUE = new Color(0xA3B7C8);
    private final Color LIGHTGRAY = new Color(0xF4F4F4);
    
    //extra colors
    private final Color DARKGRAY = new Color(0xDADADA);
    private final Color GRAY = new Color(0xEAEAEA);
    private final Color WHITE = new Color(0xFFFFFF);
    private final Color PLACEHOLDERGRAY = new Color(0x696969); // input field placeholder color
    
    private final Color RED = new Color(0xCB0000);
    private final Color GREEN = new Color(0x009400);


    
    
    /*
     * font variables
     */  
    
    // button fonts  
    private final Font CENTURY_GOTHIC_14 = new Font("Century Gothic", Font.PLAIN, 14);  
    private final Font CENTURY_GOTHIC_BOLD_14 = new Font("Century Gothic", Font.BOLD, 14);  

    // general fonts  
    private final Font CENTURY_GOTHIC_20 = new Font("Century Gothic", Font.PLAIN, 20);  
    private final Font CENTURY_GOTHIC_BOLD_20 = new Font("Century Gothic", Font.BOLD, 20);  
    private final Font CENTURY_GOTHIC_BOLD_18 = new Font("Century Gothic", Font.BOLD, 18);  
    private final Font CENTURY_GOTHIC_16 = new Font("Century Gothic", Font.PLAIN, 16);
    private final Font CENTURY_GOTHIC_BOLD_13 = new Font("Century Gothic", Font.BOLD, 13); 
    private final Font CENTURY_GOTHIC_12 = new Font("Century Gothic", Font.PLAIN, 12);  
    private final Font CENTURY_GOTHIC_BOLD_11 = new Font("Century Gothic", Font.BOLD, 11);  

    // form fonts  
    private final Font CENTURY_GOTHIC_BOLD_15 = new Font("Century Gothic", Font.BOLD, 15);  
    private final Font HELVETICA_14 = new Font("Helvetica", Font.PLAIN, 14);  
    private final Font HELVETICA_BOLD_14 = new Font("Helvetica", Font.BOLD, 14);  

    
    
    /*
     * Border, Margin & Padding Variables
     */
    
    private final int horizontalMargin = 15;
    
    private final int dashboardMarginVertical = 17;
    
    private final int marginTopBottom = 38, marginLeftRight = 50; // margins for content sections
    private final int buttonPaddingVertical = 10; // padding inside buttons
    private final int formMarginTop = 45, formMarginBottom = 28; // margins for content sections
    
    private final int cardMarginTop = 22, cardMarginBottom = 5, cardMarginHorizontal = 20; // margin for individual member card

    private final int cardOddLabelMarginLeft = 11, cardUniqueLabelMarginLeft = 19;
    
    private final int managementInputMarginTop = 8, managementInputMarginBottom = 18, managementInputMarginHorizontal = 18;

    // content margin
    private final Border CONTENT_MARGIN = BorderFactory.createEmptyBorder(marginTopBottom, marginLeftRight, marginTopBottom, marginLeftRight);

    // borders for button states
    private final Border DEFAULT_BUTTON_OUTLINE = BorderFactory.createLineBorder(DARKNAVY, 1);
    private final Border ACTIVATE$DEACTIVATE_BUTTON_OUTLINE = BorderFactory.createLineBorder(STEELBLUE, 1);
    private final Border ACTIVE_BUTTON_HIGHLIGHT = BorderFactory.createMatteBorder(0, 2, 0, 0, LIGHTGRAY);
    
    // border for dashboard content
    
    private final Border FILE_BUTTONS_MARGIN = BorderFactory.createEmptyBorder(0,5,0,5);
    private final Border DASHBOARD_CONTENT_MARGIN = BorderFactory.createEmptyBorder(dashboardMarginVertical, 0, dashboardMarginVertical, 0);
    
    // borders for input field
    private final Border FORM_INPUT_OUTLINE = BorderFactory.createMatteBorder(0, 0, 1, 0, MIDNIGHTBLUE);
    private final Border FORM_INPUT_ACTIVE_OUTLINE = BorderFactory.createMatteBorder(0, 0, 2, 0, STEELBLUE);
    private final Border FORM_INPUT_PADDING = BorderFactory.createEmptyBorder(0, horizontalMargin, 0, horizontalMargin);
    private final Border FORM_INPUT_MARGIN = BorderFactory.createEmptyBorder(formMarginTop, horizontalMargin, formMarginBottom, horizontalMargin);
    
    // padding for member management card
    private final Border CARD_MARGIN = BorderFactory.createEmptyBorder(cardMarginTop, cardMarginHorizontal, cardMarginBottom, cardMarginHorizontal);
    
    // margin for member management card labels
    private final Border CARD_UNIQUE_LABEL_MARGIN_LEFT = BorderFactory.createEmptyBorder(0,cardUniqueLabelMarginLeft,0,0);
    private final Border CARD_ODD_LABEL_MARGIN_LEFT = BorderFactory.createEmptyBorder(0,cardOddLabelMarginLeft,0,0);
    
    // margin for member management button panel
    private final Border MANAGEMENT_BUTTON_PANEL_MARGIN = BorderFactory.createEmptyBorder(cardMarginTop,0,cardMarginBottom,0);
    
    // margin for member management input field
    private final Border MEMBER_MANAGEMENT_INPUT_MARGIN = BorderFactory.createEmptyBorder(managementInputMarginTop,managementInputMarginHorizontal,managementInputMarginBottom,managementInputMarginHorizontal);
    
    // padding inside buttons
    private final Border BUTTON_INNER_PADDING = BorderFactory.createEmptyBorder(buttonPaddingVertical, horizontalMargin, buttonPaddingVertical, horizontalMargin);

    // combined borders
    private final Border DEFAULT_BUTTON_BORDER = BorderFactory.createCompoundBorder(DEFAULT_BUTTON_OUTLINE, BUTTON_INNER_PADDING);
    private final Border ACTIVE_BUTTON_BORDER = BorderFactory.createCompoundBorder(ACTIVE_BUTTON_HIGHLIGHT, BUTTON_INNER_PADDING);
    
    private final Border DEFAULT_INPUT_BORDER = BorderFactory.createCompoundBorder(FORM_INPUT_OUTLINE, FORM_INPUT_PADDING);
    private final Border ACTIVE_INPUT_BORDER = BorderFactory.createCompoundBorder(FORM_INPUT_ACTIVE_OUTLINE, FORM_INPUT_PADDING);
    
    
    /*
     * Extra variables
     */
    
    // array of String icons
    private String[] icons = {
        "◄", // back arrow
        "⌕"  // search symbol
    };
    
    private String planColor=""; // storing the member card plan color in a global variable
    
    private boolean isAccessedFromTable=false; // for dashboard table row mouse listener
    
    
    /*
     * Frame variables
     */
    
    private JFrame frame = new JFrame("Gym Management Software"); // creating a new frame with the title "Gym GUI"
        private final int FRAME_HEIGHT = 650, FRAME_WIDTH = 1100; //height and width of frame
    
    
    /*
     * MENUBAR VARIABLES
     */
    
    private JPanel menuBar, menuButtonPanel;
        private final int MENU_BAR_WIDTH=225; // width of menuBar

    private JLabel menuTitle;
    
    //array of menuBar buttons
    private JButton[]
        menuButtons = {
                new JButton("Dashboard"),
                new JButton("Add a Member"),
                new JButton("Member Management")
        };
    
    private Runnable menuButtonHighlight;
    
    private int activeIndex; // variable to store index of active content
    private int lastIndex; // variable to store index of last body content
    
    
    /*
     * dashboard VARIABLES
     */
    
    private JPanel dashboardContent;
    
        private JPanel dashboardTitle_P;
            private JLabel dashboardTitle_L;
            
            JPanel fileSystemButtons_P = new JPanel();
            
                private JPanel importButton_P = new JPanel();
                    private JButton importFileButton;
                
                private JPanel exportButton_P = new JPanel();
                    private JButton exportFileButton;
    
        private JPanel dashboardContentWrapper_P = new JPanel();
        
            // control panel at the top
            private JPanel dashboardTableControls_P = new JPanel();
            
                private JPanel tableControlSearch_P = new JPanel();
                    private JLabel tableControlSearch_L = new JLabel("Who are you looking for?");
                    private String searchPlaceholder = icons[1] + " Enter id, name, etc";
                    private JTextField tableControlSearch_F = new JTextField();
                        private TableRowSorter<DefaultTableModel> rowSorter; // for sorting
                        private Runnable filter;
                    
                private JPanel tableControlComboBox_P = new JPanel();
                
                    private JPanel controlMemberType_P = new JPanel();
                        private JLabel controlMemberType_L = new JLabel("Member Type:");
                        private String[] memberTypes = {"All","Premium","Regular"};
                        private JComboBox<String> controlMemberType_C = new JComboBox<String>(memberTypes);
                
                    private JPanel controlActiveStatus_P = new JPanel();
                        private JLabel controlActiveStatus_L = new JLabel("Status:");
                        private String[] statuses = {"All","Active","Inactive"};
                        private JComboBox<String> controlActiveStatus_C = new JComboBox<String>(statuses);
                        
                        private JPanel clearFilterButton_P = new JPanel();
                        private JButton clearFilterButton;
                
                Runnable loadTableData; // runnable to load table data
                    
                // table wrapper panel
                private JPanel tableWrapper_P = new JPanel();
                    
                    // header above the actual table 
                    private JPanel tableHeader_P = new JPanel();
                            
                        // array of header panels
                        JPanel[] tableHeaderPanels = {
                                    new JPanel(), // title panel
                                    new JPanel()  // info panel
                            };
                        
                        // array of labels for panels above
                        JLabel[] tableHeaderLabels = {
                                new JLabel("Member Overview"), // title panel
                                new JLabel("(Double-click a member to display or manage details)")  // info panel
                        };
                            
                    
                    // panel which has the table
                    private JPanel table_P = new JPanel();
                    
                        private JTable table;
                        private DefaultTableModel model;
                        private JScrollPane scrollPane;
                        
                            // general fields
                            private String[] allMembersColumns = {
                                    "ID", "NAME", "GENDER", "STATUS", "MEMBER TYPE", "ATTENDANCE", "LOYALTY POINTS",
                                    "START DATE", "EMAIL", "PHONE NO.", "DATE OF BIRTH", "LOCATION",
                                };
                
        JPanel[] controlPanels = {
                    tableControlSearch_P,
                    controlMemberType_P,
                    controlActiveStatus_P,
            },
                fileSystemButtonPanels = {
                    importButton_P,
                    exportButton_P    
            };
        
        JLabel[] controlHeadings = {
                tableControlSearch_L,
                controlMemberType_L,
                controlActiveStatus_L
            };
        
        JPanel[] comboBoxPanels = {
                controlMemberType_P,
                controlActiveStatus_P
            };
        
        JComboBox[] controlComboBoxes = {
                controlMemberType_C,
                controlActiveStatus_C
            };
        
        // array of file system buttons                    
        JButton[] dashboardButtons = {
                
                importFileButton = 
                        new JButton(
                                "<html>"
                                    + "<span style=\"font-family: Tahoma; font-size: 14px; font-weight: bold\">"
                                        + "⤵ "
                                    +"</span>"
                                    + "<span style=\"font-family: Century Gothic; font-size: 11px\">"
                                        + "Import File"
                                    +"</span>"
                                + "</html>" 
                                ),
                        
                        
                exportFileButton = 
                        new JButton(
                                "<html>"
                                    + "<span style=\"font-family: Tahoma; font-size: 14px; font-weight: bold\">"
                                        + "⤴ "
                                    +"</span>"
                                    + "<span style=\"font-family: Century Gothic; font-size: 11px\">"
                                        + "Export File"
                                    +"</span>"
                                + "</html>" 
                                ),
                        
                clearFilterButton = 
                        new JButton(
                                "<html>"
                                    + "<span style=\"font-family: Tahoma; font-size: 14px; font-weight: 100\">"
                                        + "⨯ "
                                    +"</span>"
                                    + "<span style=\"font-family: Century Gothic; font-size: 11px\">"
                                        + "Clear Filters"
                                    +"</span>"
                                + "</html>" 
                                )
            };
        
        String projectPath = System.getProperty("user.dir"); // path of the project folder
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Text Files","txt"); // to show only .txt files when users are presented with file save/open dialog
        String filename = ""; // empty global string to store filename
    /*
     * addMember VARIABLES
     */

    private JPanel addMemberContent;
        private JPanel addMemberTitle_P;
            private JLabel addMemberTitle_L;

        private JPanel memberTypeSelect_P;
                private JButton[]
                    memberTypeButtons = {
                        new JButton("Premium Member"),
                        new JButton("Regular Member")
                    };
                
                // boolean for if the current panel is membertype content or not (intially false)
                private boolean[] isFormContent = {
                        false, //premium
                        false //regular
                        }; 
                
                private JPanel[]
                //array of form panels
                formPanels = {
                    new JPanel(),
                    new JPanel()
                };                
                        
                /*
                 * FORM ARRAYS
                 */

                // array of form panels + initialization
                private JPanel[] inputPanels = {
                    new JPanel(), // id
                    new JPanel(), // name
                    new JPanel(), // location
                    new JPanel(), // phone
                    new JPanel(), // email
                    new JPanel(), // gender
                    new JPanel(), // DOB
                    new JPanel(), // start date
                    new JPanel(), // personal trainer
                    new JPanel(), // referral source
                };

                // array of input labels + initialization
                private JLabel[] inputLabels = {
                    new JLabel("ID:"), // id
                    new JLabel("Name:"), // name
                    new JLabel("Location:"), // location
                    new JLabel("Phone:"), // phone
                    new JLabel("Email:"), // email
                    new JLabel("Gender:"), // gender
                    new JLabel("Date of Birth:"), // DOB
                    new JLabel("Start Date:"), // start date
                    new JLabel("Trainer Name:"), // personal trainer
                    new JLabel("Referral Source:"), // referral source
                };

                // array of input fields + initialization
                private JTextField[] inputFields = {
                    new JTextField(), // id
                    new JTextField(), // name
                    new JTextField(), // location
                    new JTextField(), // phone
                    new JTextField(), // email
                    new JTextField(), // personal trainer
                    new JTextField()  // referral source
                };
                
                private JPanel[] comboContainers = {
                	new JPanel(),
                	new JPanel()
                };
                
                private String[][] inputComboItems = {
                	{   // year (dob)
                		"Year", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998",
                        "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988",
                        "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980"
                	},
                	{   // month (shared for dob and start date)
                		"Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
                	},
                	{   // day (shared for dob and start date)
                		"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                		"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                		"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
                	},
                	{   // year (start date)
                		"Year","2025", "2024", "2023", "2022", "2021", "2020", "2019",
                		"2018", "2017", "2016", "2015"
                	}
                };
                
                private JComboBox[] inputCombos = {
                	new JComboBox<String>(inputComboItems[0]), // year (dob)
                	new JComboBox<String>(inputComboItems[1]), // month
                	new JComboBox<String>(inputComboItems[2]), // day
                	new JComboBox<String>(inputComboItems[3]), // year (start date)
                	new JComboBox<String>(inputComboItems[1]), // month
                	new JComboBox<String>(inputComboItems[2])  // day
                };

                
                private final ButtonGroup GENDER = new ButtonGroup(); // button group for gender radio buttons
                private boolean isGenderSelected = false;
                private int indexOfGenderSelected = -1; // initially
                
                // array of gender radio buttons + initialization
                private JRadioButton[] genderRadioButtons = {
                        new JRadioButton("Male"), // male radio button
                        new JRadioButton("Female") // female radio button
                };
                
                private String[]
                        //array of common input placeholders
                        commonPlaceholders = {
                                "Enter a unique ID",     // id
                                "Enter your name",         // name
                                "Enter city",            // location
                                "+977 9XXXXXXXX",        // phone no.
                                "name@domain.com",        // email
                            };
                    
                private String[][]
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
                            
                private JPanel formButtons_P = new JPanel();
                private JPanel formControlButtonsPanel = new JPanel();
                    private JButton[]
                        formButtons= {
                                new JButton("Clear All"), // clear form button
                                new JButton("Add Premium Member"),
                                new JButton("Add Regular Member")
                        };

    
    /*
     * memberManagement VARIABLES
     */
    
    private JPanel memberManagementContent;
    
        private JPanel memberManagementTitle_P;
            private JLabel memberManagementTitle_L;
                
            private JPanel memberManagementButton_P;
                private JButton manageMemberButton = new JButton("Enter member ID");
                
            private JPanel individualMemberManagement_P = new JPanel();
                private JPanel memberCard_P = new JPanel();
                
                private JPanel cardCommonAttributes_P = new JPanel();
                private JPanel cardUniqueAttributes_P = new JPanel();
                
                private JPanel[]
                //array of member card panels
                cardPanels = {
                    cardCommonAttributes_P,
                    cardUniqueAttributes_P
                };
                
                // public and static so that the subclasses can access and modify these JLabels
                public static JLabel[] 
                        // card keyword labels
                        cardLabels = {
                            // common attributes
                            new JLabel(""), // Name
                            new JLabel(""), // ID
                            new JLabel(""), // Active Status
                            new JLabel(""), // Start Date
                            new JLabel(""), // Location
                            new JLabel(""), // Phone no.
                            new JLabel(""), // Email
                            new JLabel(""), // DOB
                            new JLabel(""), // Attendance
                            new JLabel(""), // Loyalty Points

                            // unique attributes
                            new JLabel(""), // Paid Amount || Current Plan
                            new JLabel(""), // Remaining Amount || Plan Price
                            new JLabel(""), // Full Payment? || Can Upgrade?
                            new JLabel(""), // Discount Amount || Referral Source
                            new JLabel(""), // Trainer Name || Removal Reason
                        };

                
                private JPanel individualMemberButtonsWrapper_P = new JPanel();
            
                    private JPanel[]
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
                
                    private JTextField[]
                        individualMemberFields = {
                            new JTextField(), // discount amount field || plan price
                            new JTextField(), // paid amount || removal reason
                            new JTextField()  // premium plan charge 
                    };
                    
                    private String[] plans = {"Basic","Standard","Deluxe"};
                    private JComboBox<String> plan_C = new JComboBox<String>(plans);
                    
                    private JLabel[]
                        individualMemberFieldTitle = {
                            new JLabel("Discount Amount:"),
                            new JLabel("Select A Plan:"),
                            new JLabel("Plan Price:"),
                            new JLabel("Removal Reason:"),
                            new JLabel("Amount:"),
                            new JLabel("Total Charge")
                        };
                
                    private JButton[]
                        individualMemberButtons = {
                            new JButton("Activate Membership"),
                            new JButton("Mark Attendance"),
                            new JButton("Revert Premium Member"), // seperate button as required
                            new JButton("Revert Regular Member"), // seperate button as required
                            
                            new JButton("Calculate Discount"), // OR Upgrade plan 
                            new JButton("Pay Due Amount"),
                    };
                    
        // last member id and type of member
        private int currentMemberIndex = 0;
        private String lastMemberID = "-1"; // resetting lastMemberID
        private String memberInstanceOf = "";
        
        private Runnable updateInidividualMemberPanel; // declaring a runnable
        
        // for member card buttons funcitonality
        private boolean showDialog = true;
        
        private String fullyPaidText = "Fully Paid❗"; // creating a String with a 'heavy exclamation' unicode character so that users cannot lock themselves out of the field accidentally

            
    /*
     * ARRAYS
     */            
    
    private JLabel[]
        //array of body titles
        contentTitles= {
            dashboardTitle_L = new JLabel("Dashboard"),        
            addMemberTitle_L = new JLabel("Add a Member"),
            memberManagementTitle_L = new JLabel("Member Management")
        },
        //array of utility buttons
        utilityButtons_L= {
            new JLabel(icons[0]), //back button for internal forms
            new JLabel(icons[0])  //back button for member management
        },
        // array of central body panel titles
        centralPanelTitles = {
            new JLabel("Choose a type of member to add:"),
            new JLabel("Click to re-open the member ID entry dialog:")
        };
    
    
    private JPanel[] 
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
            new JPanel(), //back button for add a member forms
            new JPanel(), // back button for member management
        },
        // array of center body panels
        centralBodyPanels = {
            dashboardContentWrapper_P = new JPanel(),
            memberTypeSelect_P = new JPanel(),
            memberManagementButton_P = new JPanel()
        };
    
    private JButton[]
            //array of all buttons
            Buttons= {
                menuButtons[0],
                        dashboardButtons[0],
                        dashboardButtons[1],
                        dashboardButtons[2],
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
    private ArrayList<GymMember> members = new ArrayList<>(); // private for data hiding 
    
    GymMember[] testMembers = {
            new PremiumMember(1, "Ram Sapkota", "Kathmandu", "+977 9812345678", "test_member1@gmail.com", "Male", "2004-12-12", "2025-02-03", "Alex Smith"),
            new RegularMember(2, "Sita Phuyal", "Pokhara", "+977 9876543210", "test_member2@gmail.com", "Female", "2002-04-29", "2025-01-13", "Instagram Ad"),
            new RegularMember(3, "Deepak Maharjan", "Hetauda", "+977 9823456780", "test_member3@gmail.com", "Male", "1994-07-18", "2025-07-20", "Billboard Ad"),
            new PremiumMember(4, "Pradeep Sharma", "Chitwan", "+977 9876541234", "test_member4@gmail.com", "Male", "1999-03-15", "2025-05-22", "Michael Johnson"),
            new RegularMember(5, "Pooja Thapa", "Kathmandu", "+977 9801234789", "test_member5@gmail.com", "Female", "2000-08-14", "2025-02-18", "Friend"),
            new PremiumMember(6, "Anjali Rai", "Dhulikhel", "+977 9845613247", "test_member6@gmail.com", "Female", "2003-11-23", "2025-02-08", "John Doe"),
            new RegularMember(7, "Nirakar Khadka", "Bhaktapur", "+977 9812346701", "test_member7@gmail.com", "Male", "1997-07-29", "2025-04-30", "Flyer"),
            new PremiumMember(8, "Nabin KC", "Butwal", "+977 9836729145", "test_member8@gmail.com", "Male", "2000-01-02", "2025-07-17", "Sophia Williams"),
            new RegularMember(9, "Sandesh Thapa", "Pokhara", "+977 9845671234", "test_member9@gmail.com", "Male", "2003-04-09", "2025-05-25", "Friend Referral"),
            new PremiumMember(10, "Asha Bista", "Lalitpur", "+977 9815678901", "test_member10@gmail.com", "Female", "2003-02-24", "2025-08-14", "Emma Watson"),
            new RegularMember(11, "Suman Baral", "Janakpur", "+977 9823456789", "test_member11@gmail.com", "Male", "1996-11-01", "2025-06-22", "Flyer"),
            new PremiumMember(12, "Bishal Ghimire", "Biratnagar", "+977 9865432109", "test_member12@gmail.com", "Male", "1997-05-19", "2025-08-11", "David Brown"),
            new RegularMember(13, "Rina Gurung", "Pokhara", "+977 9811122334", "test_member13@gmail.com", "Female", "2001-03-05", "2025-09-25", "Google Ad"),
            new PremiumMember(14, "Anil Gurung", "Pokhara", "+977 9812345610", "test_member14@gmail.com", "Male", "1995-12-02", "2025-11-30", "Jessica Adams"),
            new RegularMember(15, "Kiran Acharya", "Biratnagar", "+977 9812345679", "test_member15@gmail.com", "Male", "2002-12-12", "2025-10-10", "Instagram Ad"),
            new PremiumMember(16, "Maya Tamang", "Lalitpur", "+977 9812349087", "test_member16@gmail.com", "Female", "1998-06-10", "2025-03-30", "Splinter Smith"),
            new RegularMember(17, "Meera Shrestha", "Dharan", "+977 9804567890", "test_member17@gmail.com", "Female", "1998-10-05", "2025-06-10", "Google Ad"),
            new PremiumMember(18, "Sushila Sharma", "Bhaktapur", "+977 9816712345", "test_member18@gmail.com", "Female", "2005-09-25", "2025-04-05", "Joseph Kirk"),
            new RegularMember(19, "Kusum Karki", "Butwal", "+977 9812349987", "test_member19@gmail.com", "Female", "2001-01-29", "2025-03-15", "Newspaper Ad"),
            new RegularMember(20, "Manisha Kharel", "Lalitpur", "+977 9812345672", "test_member20@gmail.com", "Female", "1999-06-21", "2025-09-30", "Flyer"),
        };

    
    //constructor
    public GymGUI() {
        
        for(GymMember member : testMembers) {
            members.add(member);
        }
        
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
            button.setFont(CENTURY_GOTHIC_14);
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
            button.setFont(CENTURY_GOTHIC_16);
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
        menuButtonPanel.setLayout(new GridLayout(3,1));
        
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
        
        // runnable to update menuButton highlight
        menuButtonHighlight = new Runnable() {
            @Override
            public void run() {
                menuButtons[lastIndex].setFont(CENTURY_GOTHIC_14);
                menuButtons[lastIndex].setBorder(DEFAULT_BUTTON_BORDER);

                menuButtons[activeIndex].setFont(CENTURY_GOTHIC_BOLD_14);
                menuButtons[activeIndex].setBorder(ACTIVE_BUTTON_BORDER);
            }
        };
        
        
        /*
         * BODY CONTENT SECTION (COMMON ATTRIBUTES)
         */

        // setting title attributes using a for each loop
        for(JLabel titles : contentTitles) {
            titles.setForeground(DARKNAVY);
            titles.setFont(CENTURY_GOTHIC_BOLD_20);
        }

        // setting title panel attributes using a for loop
        for(int i=0; i<contentTitlePanel.length;i++) {
            JPanel titlePanel = contentTitlePanel[i];
            titlePanel.setVisible(true);
            titlePanel.setPreferredSize(new Dimension(1,40));
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
            centralPanelTitles[i-1].setFont(CENTURY_GOTHIC_BOLD_18);
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
            utilityButton_L.setFont(CENTURY_GOTHIC_20);
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
        }
        
        
        /*
         * DASHBOARD SECTION
         */
        
        // addding import button to the dashboard title
        dashboardTitle_P.add(fileSystemButtons_P,BorderLayout.EAST); // adding button to the title panel
        
        fileSystemButtons_P.setPreferredSize(new Dimension(270,1));
        fileSystemButtons_P.setLayout(new GridLayout(1,0));
        fileSystemButtons_P.add(importButton_P);
        fileSystemButtons_P.add(exportButton_P);
        
        // setting attributes for the file system buttons
        for(JPanel fileSystemButtonPanel : fileSystemButtonPanels) {
            
            JButton fileSystemButton = fileSystemButtonPanel == importButton_P ? importFileButton : exportFileButton;
            
            fileSystemButtonPanel.setLayout(new BorderLayout());
            fileSystemButtonPanel.setPreferredSize(new Dimension(125,1));
            fileSystemButtonPanel.setBorder(FILE_BUTTONS_MARGIN);
            fileSystemButtonPanel.add(fileSystemButton,BorderLayout.CENTER);
        }
        
        // styling buttons of dashboard
        for(JButton button : dashboardButtons) {
            button.setPreferredSize(new Dimension(130,40));
            
            // changing dashboard button colors and mouse effects
            button.setBackground(MIDNIGHTBLUE);
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
        
        // styling the main dashboard panel
        dashboardContent.add(dashboardContentWrapper_P);
        dashboardContentWrapper_P.setBackground(LIGHTGRAY);
        dashboardContentWrapper_P.setBorder(DASHBOARD_CONTENT_MARGIN);
        dashboardContentWrapper_P.setLayout(new BorderLayout());

        // styling the control panel wrapper
        dashboardContentWrapper_P.add(dashboardTableControls_P,BorderLayout.NORTH);
        dashboardTableControls_P.setBackground(LIGHTGRAY);
        dashboardTableControls_P.setBorder(DASHBOARD_CONTENT_MARGIN);
        dashboardTableControls_P.setPreferredSize(new Dimension(1,118));
        dashboardTableControls_P.setLayout(new BorderLayout());
        
        // styling the individual panels of control panel wrapper
        for(JPanel panel : controlPanels) {
            panel.setBackground(WHITE);
            panel.setLayout(new FlowLayout(FlowLayout.LEADING, 15,4));
        }
        
        // styling control panel headings
        for(int i = 0; i<controlHeadings.length ; i++) {
            JLabel heading = controlHeadings[i];

            heading.setFont(CENTURY_GOTHIC_BOLD_13);
            heading.setForeground(MIDNIGHTBLUE);
            heading.setPreferredSize(new Dimension(800,30));
            
            controlPanels[i].add(heading); // putting heading in corresponding panel
        }
        
        dashboardTableControls_P.add(tableControlSearch_P,BorderLayout.WEST);
        tableControlSearch_P.setPreferredSize(new Dimension(250,1));
        
        // styling the search field
        tableControlSearch_P.add(tableControlSearch_F);
        tableControlSearch_F.setBorder(DEFAULT_INPUT_BORDER);
        tableControlSearch_F.setPreferredSize(new Dimension(218,26));
        tableControlSearch_F.setFont(HELVETICA_14);
        tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
        tableControlSearch_F.setText(searchPlaceholder);
        tableControlSearch_F.setFocusable(false);
        
        // field mouse listener
        tableControlSearch_F.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tableControlSearch_F.setFocusable(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // making caret disappear when pressed outside field 
                dashboardContent.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e1) {
                        tableControlSearch_F.setFocusable(false);
                        
                        // adding placeholder if input is null
                        if(tableControlSearch_F.getText().trim().equals("")) {
                            tableControlSearch_F.setText(searchPlaceholder);
                            tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        }
                    }
                });
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                tableControlSearch_F.setForeground(MIDNIGHTBLUE);
                
                // removing placeholder when field is clicked
                if(tableControlSearch_F.getText().equals(searchPlaceholder)) {
                    tableControlSearch_F.setText("");
                }
            }
        });
        
        // styling the middle combo box panel wrapper
        dashboardTableControls_P.add(tableControlComboBox_P,BorderLayout.CENTER);
        tableControlComboBox_P.setLayout(new GridLayout(1,0));
        
        // styling the combo boxes
        for(int i = 0; i < controlComboBoxes.length ; i++){
            
            controlComboBoxes[i].setPreferredSize(new Dimension(100,27));
            controlComboBoxes[i].setFocusable(false);
            controlComboBoxes[i].setBackground(WHITE);
            controlComboBoxes[i].setForeground(GUNMETALBLUE);
            
            // adding to corresponding panel
            comboBoxPanels[i].add(controlComboBoxes[i]);
            tableControlComboBox_P.add(comboBoxPanels[i]);
        }

        // styling the search button panel
        dashboardTableControls_P.add(clearFilterButton_P,BorderLayout.EAST);
        clearFilterButton_P.setPreferredSize(new Dimension(245,1));
        clearFilterButton_P.setBackground(WHITE);
        clearFilterButton_P.setLayout(new FlowLayout(FlowLayout.TRAILING,15,26)); // overriding layout
        clearFilterButton_P.add(clearFilterButton);
        
        // styling the table wrapper
        dashboardContentWrapper_P.add(tableWrapper_P,BorderLayout.CENTER);
        tableWrapper_P.setBackground(LIGHTGRAY);
        tableWrapper_P.setLayout(new BorderLayout());
        
        // styling table header panel
        tableWrapper_P.add(tableHeader_P, BorderLayout.NORTH);
        tableHeader_P.setPreferredSize(new Dimension(1,40));
        tableHeader_P.setBackground(WHITE);
        tableHeader_P.setLayout(new BorderLayout());    
        
        // setting attributes of table header panels and labels using a for each loop
        for(JPanel tableHeaderPanel : tableHeaderPanels) {
            boolean isTableTitlePanel = tableHeaderPanel == tableHeaderPanels[0]; // if the panel is table title panel
            
            String panelPosition = isTableTitlePanel ? BorderLayout.WEST : BorderLayout.EAST;
            int panelWidth = isTableTitlePanel ? 140 : 322;
            JLabel tableHeaderLabel = isTableTitlePanel ? tableHeaderLabels[0] : tableHeaderLabels[1];
            Font headerLabelFont = isTableTitlePanel ? CENTURY_GOTHIC_BOLD_14 : CENTURY_GOTHIC_12;
            Color headerLabelColor = isTableTitlePanel ? GUNMETALBLUE : DUSTYBLUE;
            
            /*
             *  setting attributes
             */
            
            // styling table title panel
            tableHeader_P.add(tableHeaderPanel, panelPosition);
            tableHeaderPanel.setPreferredSize(new Dimension(panelWidth, 1));
            tableHeaderPanel.setBackground(WHITE);
            tableHeaderPanel.setLayout(new BorderLayout());
                
            // styling table title label
            tableHeaderPanel.add(tableHeaderLabel);
            tableHeaderLabel.setFont(headerLabelFont);
            tableHeaderLabel.setForeground(headerLabelColor);
            tableHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER); // centering horizontally
        }
        
        // styling table panel
        tableWrapper_P.add(table_P,BorderLayout.CENTER);
        table_P.setLayout(new BorderLayout());

        // runnable to update/load table
        loadTableData = new Runnable() {
            @Override
            public void run() {
                
                // adding spaces before each column title
                for (int i = 0; i < allMembersColumns.length; i++) {
                    allMembersColumns[i] = " " + allMembersColumns[i].trim(); // adding space before the data
                }
                
                // initializing the table model
                model = new DefaultTableModel(allMembersColumns, 0);
                
                // populating the model with data
                for (GymMember member : members) {
                    
                    String[] actualData = {
                        Integer.toString(member.getId()), // id
                        member.getName(), // name
                        member.getGender(), // gender
                        member.isActiveStatus()?"Active":"Inactive", // status (set to "Active"|"Inactive" based on value)
                        member instanceof PremiumMember ? "Premium" : "Regular", // type of member
                        Integer.toString(member.getAttendance()), // attendance (converting int to String)
                        Double.toString(member.getLoyaltyPoints()), // loyalty points (converting double to String)
                        member.getMembershipStartDate(), // start date
                        member.getEmail(), // email
                        member.getPhone(), // phone number
                        member.getDOB(), // DOB
                        member.getLocation(), // location
                    };
                    
                    // adding spaces before each data String
                    for (int i = 0; i < actualData.length; i++) {
                        actualData[i] = "  " + actualData[i];
                    }
                    
                    model.addRow(actualData);
                }
                
                // initializing table and applying settings
                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setDefaultEditor(Object.class, null); // making the rows non editable
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // making user able to select one row at a time
                
                // styling scroll panel
                scrollPane = new JScrollPane(table); // initializing scroll panel
                scrollPane.getViewport().setBackground(LIGHTGRAY); // setting table background
                
                scrollPane.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        tableControlSearch_F.setFocusable(false); // removing caret
                        
                        // adding placeholder if nothing is input
                        if(tableControlSearch_F.getText().trim().equals("")) {
                            tableControlSearch_F.setText(searchPlaceholder);
                            tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        }
                        
                    }
                });
                
                /*
                 * ROW AND TABLE STYLING
                 */
                
                int rowHeight = 30; // putting row height in a variable
                
                // row styling
                table.setRowHeight(rowHeight);
                table.setFont(CENTURY_GOTHIC_12); // setting font
                
                // setting alternate rows to have different background color using a custom cell renderer
                table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    // overriding default rendering method
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        
                        // calling parent class method to get default rendered cell 
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                        /*
                         * ALTERNATE COLORED ROWS
                         */
                        if (!isSelected) { // ensuring selection highlight is visible
                            if (row % 2 == 1) {
                                c.setBackground(LIGHTGRAY);
                            } else {
                                c.setBackground(WHITE);
                            }
                        }
                        
                        /*
                         * ACTIVE/INACTIVE COLORS
                         */
                        if (column == 3) {
                            String cellText = value.toString().trim();

                            c.setForeground(cellText.equalsIgnoreCase("Active")? GREEN : RED);
                        }
                        else {
                            // setting other cells to default color since the default cell renderer will make all cells inherit the color above
                            c.setForeground(MIDNIGHTBLUE);
                        }
                        
                        return c; // returning the modified component (row), which the table paints on the screen
                    }
                });

                // to go to a member's management panel
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        /*
                         * readding the search placeholder if input is null
                         */
                        
                        // adding placeholder if nothing is input
                        if(tableControlSearch_F.getText().trim().equals("")) {
                            tableControlSearch_F.setText(searchPlaceholder);
                            tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        }
                        
                        // checking for double click
                        if(e.getClickCount()==2) {
                            int row = table.getSelectedRow(); // getting selected row index
                            
                            // ensuring a valid row is selected
                            if (row != -1) { 
                                String selectedMemberID = table.getValueAt(row, 0).toString().trim(); // getting id of selected row, and casting from Object to String, and trimming any spaces
                            
                                showDialog=false; // so that dialog doesnt appear
                                lastMemberID = selectedMemberID; // passing selected member id to runnable
                                
                                updateInidividualMemberPanel.run(); // running runnable
                                
                                showDialog=true; // reverting the boolean
                                
                                // removing and adding contetnt
                                frame.remove(dashboardContent);
                                frame.add(memberManagementContent,BorderLayout.CENTER);
                                memberManagementContent.add(individualMemberManagement_P,BorderLayout.CENTER);
                                
                                
                                /*
                                 * UPDATING INDEXES AND BUTTON HIGHLIGHTS
                                 */
                                lastIndex = 0; // updating last index
                                activeIndex = 2; // updating active index
                                
                                menuButtonHighlight.run(); // updating menu button highlight
                                
                                isAccessedFromTable = true;
                                
                                frame.revalidate();
                                frame.repaint();
                           }
                        }
                    }
                });
                
                // Styling header row
                JTableHeader columnHeader = table.getTableHeader();
                
                columnHeader.setReorderingAllowed(false); // disabling column dragging
                columnHeader.setResizingAllowed(false); // disabling column resizing
                
                columnHeader.setFont(CENTURY_GOTHIC_BOLD_11);
                columnHeader.setForeground(GUNMETALBLUE);
                columnHeader.setBackground(DARKGRAY);
                columnHeader.setPreferredSize(new Dimension(columnHeader.getPreferredSize().width, 30));

                // Access the default columnHeader renderer and set alignment to LEFT
                DefaultTableCellRenderer columnHeaderRenderer = (DefaultTableCellRenderer) columnHeader.getDefaultRenderer(); // getting the columnHeader's default renderer
                columnHeaderRenderer.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
                
                columnHeader.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                        tableControlSearch_F.setFocusable(false); // removing caret
                        
                        // adding placeholder if nothing is input
                        if(tableControlSearch_F.getText().trim().equals("")) {
                            tableControlSearch_F.setText(searchPlaceholder);
                            tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        }
                    }
                });
                
                // adding a row sorter to the table to sort by ascending id
                rowSorter = new TableRowSorter<DefaultTableModel>(model);
                
                // setting comparator of rowSorter to compare the ids of members and sort in descending order
                rowSorter.setComparator(0, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						return Integer.compare(Integer.parseInt(o2.trim()), Integer.parseInt(o1.trim()));
					}
                });
                
                table.setRowSorter(rowSorter); // setting sorter
                rowSorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING))); // Sort by ID (Column 0)
                
                // runnable to filter table based on searchText
                filter = new Runnable() {
                    @Override
                    public void run() {
                        
                        String fieldText = tableControlSearch_F.getText().trim();
                        String selectedStatus = (String) controlActiveStatus_C.getSelectedItem(); // casting to String from Object
                        String selectedType = (String) controlMemberType_C.getSelectedItem(); // casting to String from Object
                        String safeSearchText = Pattern.quote(fieldText); // excaping all special regex characters to treat them as normal characters
                        
                        // list to hold the filters
                        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

                        /*
                         *  search filter for text (TextField)
                         */
                        if (!fieldText.isEmpty() && !fieldText.equals(searchPlaceholder)) {
                            RowFilter<DefaultTableModel, Integer> searchFilter; // declaring a filter outside the if block below
                            
                            // when searching male
                            if(fieldText.equalsIgnoreCase("Male")) {
                                searchFilter = RowFilter.regexFilter("Male",2); // manual filter for males
                            }
                            // for other searchTexts
                            else {
                                searchFilter = RowFilter.regexFilter("(?i)" + safeSearchText); // case-insensitive search
                            }
                            
                            filters.add(searchFilter); // adding filter to list
                        }

                        /*
                         *  filter for status (ComboBox)
                         */
                        if (selectedStatus.equalsIgnoreCase("Active")) {
                            
                            RowFilter<DefaultTableModel, Integer> statusFilter = RowFilter.regexFilter("Active", 3); // searching for "Active" in status column
                            filters.add(statusFilter); // adding to filter list
                        } 
                        else if (selectedStatus.equalsIgnoreCase("Inactive")) {
                            
                            RowFilter<DefaultTableModel, Integer> statusFilter = RowFilter.regexFilter("Inactive", 3); // searching for "Inactive" in status column
                            filters.add(statusFilter); // adding to filter list
                        }
                        
                        /*
                         *  filter for member type (ComboBox)
                         */
                        if (selectedType.equalsIgnoreCase("Premium")) {
                            
                            RowFilter<DefaultTableModel, Integer> typeFilter = RowFilter.regexFilter("Premium", 4); // searching for "Active" in status column
                            filters.add(typeFilter); // adding to filter list
                        } 
                        else if (selectedType.equalsIgnoreCase("Regular")) {
                            
                            RowFilter<DefaultTableModel, Integer> typeFilter = RowFilter.regexFilter("Regular", 4); // searching for "Inactive" in status column
                            filters.add(typeFilter); // adding to filter list
                        }

                        /*
                         *  applying filters if there are any
                         */
                        if (filters.isEmpty()) {
                            rowSorter.setRowFilter(null); // showing all rows if no filters are applied
                        }
                        else {
                            // combining the filters using AND logic
                            RowFilter<DefaultTableModel, Integer> combinedFilter = RowFilter.andFilter(filters);
                            rowSorter.setRowFilter(combinedFilter); // applying combined filter
                        }
                    }
                };
                
                // removing mouse listener of exportFileButton to avoid a bug where no. of dialog boxes keep increasing
                MouseListener[] exportListeners = exportFileButton.getMouseListeners();
                MouseListener[] importListeners = importFileButton.getMouseListeners();
                for (int i = 0 ; i < exportListeners.length ; i++) {
                    exportFileButton.removeMouseListener(exportListeners[i]);
                    importFileButton.removeMouseListener(importListeners[i]);
                }
                
                /*
                 * adding mouse interaction effects again because mouseListeners of exportFileButton are removed every call of loadTableData.run()
                 */
                
                for(int i = 0 ; i < dashboardButtons.length - 1 ; i++) {
                    JButton button = dashboardButtons[i]; // putting button into a common variable
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

                // export button functionality
                exportFileButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                        // removing caret from search field
                        if(tableControlSearch_F.getText().trim().equals("")) {
                            tableControlSearch_F.setText(searchPlaceholder);
                            tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        }
                        tableControlSearch_F.setFocusable(false);
                        
                        JFileChooser fileChooser = new JFileChooser(); // creating a JFileChooser instance
                        fileChooser.setCurrentDirectory(new File(projectPath)); // setting default directory to project directory
                        fileChooser.setFileFilter(fileFilter); // showing only .txt files
                        
                        int option = fileChooser.showSaveDialog(null); // opening dialog box to save a file, assisnging it to a variable
                        
                        // if user chooses a file
                        if(option == JFileChooser.APPROVE_OPTION) {
                        	File file = fileChooser.getSelectedFile(); // getting the file selected and putting it into a File variable
                        	filename = file.getName();
                        	
                        	try (FileWriter writer = new FileWriter(file)) {

                                // Header with 19 columns
                                String header = String.format(
                                    "%-10s %-25s %-15s %-20s %-20s %-15s %-25s %-25s %-15s %-20s %-15s %-15s" // common
                                    + " %-20s %-15s" // premium
                                    + " %-12s %-20s", // regular

                                    // common attributes
                                    "ID", "Name", "Gender", "Location", "Phone", "DOB", "Email", "Membership Start Date", "Attendance", "Loyalty Points", "Active Status", "Member Type", 

                                    // premium member attributes
                                    "Trainer Name", "Paid Amount",

                                    // regular member attributes
                                    "Plan", "Referral Source"
                                );
                                writer.write(header + "\n"); // using "\n" for new line
                                writer.write("=".repeat(299)+ "\n"); // using '=' as header separator

                                // Writing each member's details
                                for (GymMember member : members) {
                                    String memberData = "";

                                    if (member instanceof PremiumMember) {
                                        PremiumMember premiumMember = (PremiumMember) member;

                                        memberData = String.format(
                                            "%-10s %-25s %-15s %-20s %-20s %-15s %-25s %-25s %-15s %-20s %-15s %-15s" // common
                                            + " %-20s %-15s" // premium
                                            + " %-12s %-20s", // regular

                                            premiumMember.getId(),
                                            premiumMember.getName(),
                                            premiumMember.getGender(),
                                            premiumMember.getLocation(),
                                            premiumMember.getPhone(),
                                            premiumMember.getDOB(),
                                            premiumMember.getEmail(),
                                            premiumMember.getMembershipStartDate(),
                                            premiumMember.getAttendance(),
                                            premiumMember.getLoyaltyPoints(),
                                            premiumMember.isActiveStatus() ? "Active" : "Inactive",
                                            "Premium", 
                                            premiumMember.getPersonalTrainer(),
                                            premiumMember.getPaidAmount(),
                                            "~","~" // regular member columns set to "~" (empty/null)
                                        );

                                    } 
                                    else if (member instanceof RegularMember) {
                                        RegularMember regularMember = (RegularMember) member;
                                        memberData = String.format(
                                            "%-10s %-25s %-15s %-20s %-20s %-15s %-25s %-25s %-15s %-20s %-15s %-15s" // common
                                            + " %-20s %-15s" // premium
                                            + " %-12s %-20s", // regular

                                            regularMember.getId(),
                                            regularMember.getName(),
                                            regularMember.getGender(),
                                            regularMember.getLocation(),
                                            regularMember.getPhone(),
                                            regularMember.getDOB(),
                                            regularMember.getEmail(),
                                            regularMember.getMembershipStartDate(),
                                            regularMember.getAttendance(),
                                            regularMember.getLoyaltyPoints(),
                                            regularMember.isActiveStatus() ? "Active" : "Inactive",
                                            "Regular",
                                            "~","~", // Premium member columns set to "~" (empty/null)
                                            regularMember.getPlan(),
                                            regularMember.getReferralSource()
                                        );
                                    }

                                    writer.write(memberData + "\n"); // ensuring new line for each member
                                }

                                // success message
                                JOptionPane.showMessageDialog(null, "Data has successfully been exported to \""+filename+"\"!","Export Successful",JOptionPane.INFORMATION_MESSAGE);
                            }
                            catch (IOException e1) {
                                // error message
                                JOptionPane.showMessageDialog(null, "Failed to export member data! Please try again.","Export Failed",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                   }
                });
                
                // import button functionlity
                importFileButton.addMouseListener(new MouseAdapter() {
                    
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                        // removing caret from search field
                        if(tableControlSearch_F.getText().trim().equals("")) {
                            tableControlSearch_F.setText(searchPlaceholder);
                            tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        }
                        tableControlSearch_F.setFocusable(false);
                        
                        JFileChooser fileChooser = new JFileChooser(); // creating a JFileChooser instance
                        fileChooser.setCurrentDirectory(new File(projectPath)); // setting default directory to project directory
                        fileChooser.setFileFilter(fileFilter); // showing only .txt files
                        
                        int option = fileChooser.showOpenDialog(null); // opening dialog box to save a file, assisnging it to a variable
                        
                        // if the user chooses a file
                        if(option == JFileChooser.APPROVE_OPTION) {
                        	File file = fileChooser.getSelectedFile();
                        	filename = file.getName();
                        	
                        	try (FileReader reader = new FileReader(file)) {
                                members.clear();
                                StringBuilder fileContent = new StringBuilder();
                                int character;
                                
                                // reading character by character
                                while ((character = reader.read()) != -1) {
                                    fileContent.append((char) character);
                                }

                                // splitting file content into lines
                                String[] lines = fileContent.toString().split("\n");

                                // skipping header and separator (assuming first 2 lines are header)
                                for (int i = 2; i < lines.length; i++) {
                                    String line = lines[i].trim();
                                    
                                    // splitting line into columns (based on fixed-width spacing)
                                    String[] columns = line.split("\\s{2,}"); // at least 2 spaces as delimiter
                                    
                                    // extracting all attributes if possible
                                    int id = Integer.parseInt(columns[0]);
                                    String name = columns[1];
                                    String gender = columns[2];
                                    String location = columns[3];
                                    String phone = columns[4];
                                    String DOB = columns[5];
                                    String email = columns[6];
                                    String membershipStartDate = columns[7];
                                    int attendance = Integer.parseInt(columns[8]);
                                    boolean isActive = columns[10].equalsIgnoreCase("Active");
                                    String memberType = columns[11];
                                    String personalTrainer = columns[12].equals("~")? "" : columns[12];
                                    double paidAmount = columns[13].equals("~")? 0.0 : Double.parseDouble(columns[13]);
                                    String plan = columns[14].equals("~")? "" : columns[14];
                                    String referralSource = columns[15].equals("~")? "" : columns[15];
                                    
                                    /*
                                     * INITIALIZING PREMIUM OR REGULAR MEMBER BASED ON DATA
                                     */
                                    GymMember member = memberType.equals("Premium")?
                                                new PremiumMember(id,name,location,phone,email,gender,DOB,membershipStartDate,personalTrainer)
                                                :
                                                new RegularMember(id,name,location,phone,email,gender,DOB,membershipStartDate,referralSource) 
                                        ;

                                    /*
                                     * UPDATING ACTIVE STATUS
                                     */
                                    if(isActive) {
                                        member.activateMembership();
                                    }
                                    
                                    /*
                                     * UPDATING ATTEDANCE
                                     */
                                    for(int j = 0 ; j < attendance ; j++) {
                                        member.markAttendance();
                                    }
                                    
                                    /*
                                     * UPDATING PAID AMOUNT OF PREMIUM MEMBERS (if paidAmount is not 0)
                                     */
                                    if(paidAmount != 0.0 && memberType.equals("Premium")) {
                                        // casting member to PremiumMember, to pay due amount
                                        PremiumMember premiumMember = (PremiumMember) member;
                                        
                                        premiumMember.payDueAmount(paidAmount);
                                    }
                                    
                                    /*
                                     * UPDATING PLAN OF REGULAR MEMBERS (if plan is not the default "Basic" one)
                                     */
                                    if(!plan.equals("Basic") && memberType.equals("Regular")) {
                                        
                                        // casting member to RegularMember, to upgradePlan
                                        RegularMember regularMember = (RegularMember) member;

                                        regularMember.upgradePlan(plan);
                                    }
                                    
                                    /*
                                     * ADDING MEMBER TO ARRAYLIST
                                     */
                                    members.add(member);
                                }
                                
                                // reloading table data
                                loadTableData.run();
                                
                                // success message
                                JOptionPane.showMessageDialog(null, "Data has successfully been imported from \""+filename+"\"!","Import Successful",JOptionPane.INFORMATION_MESSAGE);

                            }
                            catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e1) {
                                JOptionPane.showMessageDialog(null, "Failed to import member data, since the selected file (\""+filename+"\") has invalid format.","Import Failed",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                
                // adding key listener to search field
                tableControlSearch_F.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) { filter.run(); }
                });
                
                // adding item state change listener to Status comboBox
                controlActiveStatus_C.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        
                        // checking if an option was selected
                        if (e.getStateChange() == ItemEvent.SELECTED) { filter.run(); }
                    }
                });
                
                // adding item state change listener to Status comboBox
                controlMemberType_C.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        
                        tableControlSearch_F.setFocusable(false);
                        
                        // checking if an option was selected
                        if (e.getStateChange() == ItemEvent.SELECTED) { filter.run(); }
                    }
                });
                
                for(int i = 0; i<controlComboBoxes.length ; i++) {
                    controlComboBoxes[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            
                            // removing caret from search field
                            if(tableControlSearch_F.getText().trim().equals("")) {
                                tableControlSearch_F.setText(searchPlaceholder);
                                tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                            }
                            tableControlSearch_F.setFocusable(false);
                        }
                    });
                }
                
                clearFilterButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                        boolean isSearching = !(tableControlSearch_F.getText().trim().equals("") || tableControlSearch_F.getText().equals(searchPlaceholder));
                        boolean hasSelectedStatus = controlActiveStatus_C.getSelectedIndex()!=0;
                        boolean hasSelectedType = controlMemberType_C.getSelectedIndex()!=0;
                        
                        // resetting the field
                        tableControlSearch_F.setText(searchPlaceholder);
                        tableControlSearch_F.setForeground(PLACEHOLDERGRAY);
                        tableControlSearch_F.setFocusable(false);
                        
                        if( isSearching || hasSelectedStatus || hasSelectedType) {
                            
                            // resetting the combo boxes
                            for(int i = 0; i<controlComboBoxes.length; i++) {
                                controlComboBoxes[i].setSelectedIndex(0);
                            }
                            
                            filter.run(); // running the filter once everything is reset
                            
                            // success message
                            JOptionPane.showMessageDialog(
                                            null,
                                            "All filters cleared successfully.",
                                            "Filters Cleared!",
                                            JOptionPane.INFORMATION_MESSAGE
                                        );
                        }
                    }
                });
                
                
                /*
                 * DYNAMIC COLUMN WIDTH
                 */
                
                for(int colIndex = 0; colIndex < table.getColumnCount(); colIndex++) {
                    int padding = 25;
                    
                    TableColumn column = table.getColumnModel().getColumn(colIndex); // getting each column
                    int titleWidth = table.getFontMetrics(table.getFont()).stringWidth(allMembersColumns[colIndex]); // width of column name
                    
                    // setting finalCellWidth to whatever is larger among titleWidth (with some padding) and 50 (placeholder value)
                    int finalCellWidth = Math.max(50, titleWidth + padding); 
                    
                    for(int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
                        
                        Object value = table.getValueAt(rowIndex, colIndex); // getting data from each cell of given column
                        
                        // getting width of current cell
                        int cellWidth = table.getFontMetrics(table.getFont()).stringWidth(value.toString());
                        
                        // setting finalCellWidth to whatever is larger between 50, titleWidth (with padding) or cellWidth (with padding)
                        finalCellWidth = Math.max(finalCellWidth, cellWidth + padding); 
                    }
                    
                    column.setPreferredWidth(finalCellWidth);
                }

                // Add scroll pane to the panel
                table_P.removeAll(); // Clear previous content if reloading
                table_P.add(scrollPane, BorderLayout.CENTER);
                table_P.revalidate();
                table_P.repaint();
            }
        };

        loadTableData.run(); // loading dashboard table initially            

        
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
                    int comboIndex = 0;
                    
                    for (int j = 0; j < inputPanels.length - 1; j++) {

                        int l = j < 8 ? j : j+formIndex;
                        
                        inputLabels[l].setForeground(MIDNIGHTBLUE);
                        inputLabels[l].setFont(CENTURY_GOTHIC_BOLD_15);
                        
                        // for panels with flow layout
                        if(j==5 || j==6 || j==7) {
                        	inputLabels[j].setPreferredSize(new Dimension(200, 15)); // increasing the width of label to make it so that panels with flow layout work properly
                        }

                        // initializing panels
                        JPanel inputPanel = inputPanels[j]; //putting the current panel into a variable for easy future use

                        inputPanel.removeAll();
                        inputPanel.setBorder(FORM_INPUT_MARGIN);
                        inputPanel.setBackground(LIGHTGRAY);
                        
                        // flow layout for 3 panels, with others being border layout
                        inputPanel.setLayout(j==5 ?
                        					new FlowLayout(FlowLayout.LEADING, 0, 3)
                        					:
                        					new BorderLayout());
                        
                        inputPanel.add(inputLabels[l], BorderLayout.NORTH); // adding label at the top

                        //adding gender radio buttons instead of a text field for panel 5
                        if (j == 5) { 
                        	
                            // to set attributes of radio buttons
                            for (int k = 0; k < 2; k++) {

                                JRadioButton genderRadio = genderRadioButtons[k]; //putting the current radio button into a variable for easy future use
                                
                                GENDER.add(genderRadio);
                                genderRadio.setForeground(MIDNIGHTBLUE);
                                genderRadio.setBackground(LIGHTGRAY);
                                genderRadio.setFont(CENTURY_GOTHIC_BOLD_14);
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
                        else if(j==6 || j==7) {
                        	int containerIndex = j==6 ? 0 : 1;
                        	JPanel currentContainer = comboContainers[containerIndex]; // comon variable
                        	
                        	currentContainer.setBackground(LIGHTGRAY);
                        	currentContainer.setPreferredSize(new Dimension(1,30));
                        	currentContainer.setLayout(new GridLayout(1,3,5,0));
                        	inputPanel.add(currentContainer, BorderLayout.SOUTH);
                        	
                        	currentContainer.add(inputCombos[comboIndex]);
                        	currentContainer.add(inputCombos[comboIndex+1]);
                        	currentContainer.add(inputCombos[comboIndex+2]);
                        	comboIndex+=3;
                        	
                        	for(int k = 0 ; k < inputCombos.length ; k++) {
                        		inputCombos[k].setFocusable(false);
                        		inputCombos[k].setSelectedIndex(0);
                        		inputCombos[k].setForeground(PLACEHOLDERGRAY);
                        		inputCombos[k].setBackground(LIGHTGRAY);
                        		int m = k; // to pass into next block
                        		
                        		inputCombos[k].addPopupMenuListener(new PopupMenuListener() {

									@Override
									public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
										
										// removing the placeholders from the comboboxes, only after the popup is fully loaded
								        SwingUtilities.invokeLater(new Runnable(){
								        	
								            @Override
								            public void run() {
								            	String firstItem = (String) inputCombos[m].getItemAt(0);
									            if (firstItem.equals("Year") || firstItem.equals("Month") || firstItem.equals("Day")) {
									            	inputCombos[m].removeItemAt(0);
									            	inputCombos[m].setSelectedIndex(-1);
									        	}
								            }
								        });
									}

									// adding the popup back if the popup is closed without any item selected
									@Override
									public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
										if(inputCombos[m].getSelectedIndex()==-1) {
											String placeholder = (m == 0 || m == 3) ? "Year" : 
																 ((m == 1 || m == 4) ? "Month" : "Day");

											if (!inputCombos[m].getItemAt(0).equals(placeholder)) {
												inputCombos[m].insertItemAt(placeholder, 0);
												inputCombos[m].setSelectedIndex(0);
											}
										}
									}

									// adding the popup back if the popup is cancelled
									@Override
									public void popupMenuCanceled(PopupMenuEvent e) {
										if(inputCombos[m].getSelectedIndex()==-1) {
											String placeholder = (m == 0 || m == 3) ? "Year" : 
																 ((m == 1 || m == 4) ? "Month" : "Day");

											if (!inputCombos[m].getItemAt(0).equals(placeholder)) {
												inputCombos[m].insertItemAt(placeholder, 0);
												inputCombos[m].setSelectedIndex(0);
											}
										}
										
									}
                        			
                        		});
                        	}
                        }
                        else {
                            
                            int k = j < 5 ? j : (j==inputFields.length - 1 && isFormContent[1]) ? j : j-3; // to use/add [j-1] fields to the panels after panel 5
                                
                            // setting the placeholders for input variables
                            String inputPlaceholder = k < (inputFields.length - 2) ? commonPlaceholders[k] : uniquePlaceholders[formIndex][0];
                            
                            JTextField inputField = inputFields[k]; // assigning required/corresponding field to a common variable
                            
                            inputPanel.add(inputField, BorderLayout.SOUTH);
                            inputField.setText(inputPlaceholder); //setting corresponding placeholder
                            
                            //styling the text fields
                            inputField.setPreferredSize(new Dimension(1,35));
                            inputField.setBorder(DEFAULT_INPUT_BORDER); // setting default border initially
                            inputField.setFont(HELVETICA_14);
                            inputField.setForeground(PLACEHOLDERGRAY);
                            inputField.setBackground(LIGHTGRAY);
                            
                            // using the anonymous focusAdapter class to override focus methods for textFields
                            inputField.addFocusListener(new FocusAdapter() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    if (inputField.getText().equals(inputPlaceholder)) {
                                        inputField.setText(""); // Clear placeholder instantly
                                        inputField.setForeground(MIDNIGHTBLUE); // Set active text color
                                    }
                                    inputField.setBorder(ACTIVE_INPUT_BORDER); // setting different border when field gains focus
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
                            JTextField inputField = inputFields[i];
                            
                            // checking if input fields dont have their corresponding placeholders or empty value, and only allowing form clearing when that's the case
                            if( ! (inputField.getText().equals(inputPlaceholder)) && ! (inputField.getText().equals(""))) {
                            
                                // showing a confirmation dialog to make sure that all fields are meant to be reset (byte to save memory)
                                byte clearWish = (byte) JOptionPane.showOptionDialog(
                                                                frame,
                                                                "Are you sure you want to clear all fields?",
                                                                "Caution: Fields will be reset",
                                                                JOptionPane.YES_NO_OPTION,
                                                                JOptionPane.WARNING_MESSAGE,
                                                                null,
                                                                options,
                                                                options[1]);
                        
                                // only resetting the form if yes is clicked
                                if(clearWish==0) {
                                    for(int j=0 ; j < inputFields.length ; j++ ) {
                                        
                                        // setting the placeholders for input variables
                                        String placeholder = j < 5 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
                                        
                                        inputFields[j].setForeground(PLACEHOLDERGRAY);
                                        inputFields[j].setText(placeholder);
                                        inputFields[j].setFocusable(false);
                                        inputFields[j].setBorder(DEFAULT_INPUT_BORDER);
                                    }
                                    
                                    GENDER.clearSelection(); // deselecting radio buttons
                                    isGenderSelected = false; // since gender is deselected
                                    
                                    // resetting combo box placeholders
                                    for(int m = 0 ; m < inputCombos.length ; m++) {
    									String placeholder = (m == 0 || m == 3) ? "Year" : 
   										 					 ((m == 1 || m == 4) ? "Month" : "Day");

    									if(!inputCombos[m].getItemAt(0).equals(placeholder)) {
    										inputCombos[m].insertItemAt(placeholder, 0);
    										inputCombos[m].setSelectedIndex(0);
    									}
                                    }
                                    
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
                                    String placeholder = j < 5 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
                                    
                                    inputFields[j].setForeground(PLACEHOLDERGRAY);
                                    inputFields[j].setText(placeholder);
                                    inputFields[j].setFocusable(false);
                                    inputFields[j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
                                }
                            }
                        }
                        
                        GENDER.clearSelection(); // deselecting radio buttons
                        isGenderSelected = false; // since gender is deselected
                        
                        // resetting combo box placeholders, silently
                        for(int m = 0 ; m < inputCombos.length ; m++) {
							String placeholder = (m == 0 || m == 3) ? "Year" : 
								 ((m == 1 || m == 4) ? "Month" : "Day");

							if(!inputCombos[m].getItemAt(0).equals(placeholder)) {
								inputCombos[m].insertItemAt(placeholder, 0);
								inputCombos[m].setSelectedIndex(0);
							}
                        }
                    }
                    
                    // event listener for add premium member/regular member buttons of respective forms
                    if(e.getSource()==formButtons[1] || e.getSource()==formButtons[2]) {
                        
                        boolean allFieldsFilled = true; // assuming all fields are filled initially
                        
                        boolean isValidDOB = !inputCombos[0].getSelectedItem().equals("Year") && !inputCombos[1].getSelectedItem().equals("Month") && !inputCombos[2].getSelectedItem().equals("Day");
                        boolean isValidStartDate = !inputCombos[3].getSelectedItem().equals("Year") && !inputCombos[4].getSelectedItem().equals("Month") && !inputCombos[5].getSelectedItem().equals("Day");
                        
                        int currentFormIndex = e.getSource()==formButtons[1] ? 0 : 1;
                        
                        String memberType = isFormContent[0] ? "premium" : "regular";

                        for (int k = 0; k < inputFields.length - 1; k++) {
                            
                            // setting the corresponding placeholders for input fields into a common variable
                            String inputPlaceholder = k < (inputFields.length - 2) ? commonPlaceholders[k] : uniquePlaceholders[currentFormIndex][0];
                            
                            // seetting the corresponding input fields into a common variable
                            JTextField inputField = k < (inputFields.length - 2) ? inputFields[k] : inputFields[k+currentFormIndex];
                            
                            String text = inputField.getText(); // getting text from input fields

                            // setting allFieldsFilled to false, if atleast one input field is "empty"
                            if (!text.equals(inputPlaceholder) || !text.equals("")) {
                                allFieldsFilled = true;
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
                        
                        else if (!isValidDOB) {
                            // remving caret from the fields
                            for (int k = 0; k < inputFields.length; k++) {
                                inputFields[k].setFocusable(false);
                            }
                            
                            JOptionPane.showMessageDialog(frame, "Please select a valid Date of Birth.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
                        }
                        
                        else if (!isValidStartDate) {
                            // remving caret from the fields
                            for (int k = 0; k < inputFields.length; k++) {
                                inputFields[k].setFocusable(false);
                            }
                            
                            JOptionPane.showMessageDialog(frame, "Please select a valid Start Date.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
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
                                    		
                                    // getting combo box selection, and adding 0 in front of single digit choices
                                    DOB = inputCombos[0].getSelectedItem()
                                    	  +
                                    	  "-" 
                                    	  + 
                                    	  (inputCombos[1].getSelectedItem().toString().length() == 1 ? "0" : "") + inputCombos[4].getSelectedItem()
                                    	  +
                                    	  "-"
                                    	  +
                                    	  (inputCombos[2].getSelectedItem().toString().length() == 1 ? "0" : "") + inputCombos[5].getSelectedItem(),
                                    
                    				// getting combo box selection, and adding 0 in front of single digit choices
                                    membershipStartDate = inputCombos[3].getSelectedItem()
                                    					  +
                                    					  "-" 
                                    					  + 
                                    					  (inputCombos[4].getSelectedItem().toString().length() == 1 ? "0" : "") + inputCombos[4].getSelectedItem()
                                    					  +
                                    					  "-"
                                    					  +
                                    					  (inputCombos[5].getSelectedItem().toString().length() == 1 ? "0" : "") + inputCombos[5].getSelectedItem(),
                                    
                                    personalTrainer = inputFields[5].getText(), // unique field
                                    referralSource = inputFields[6].getText(); // unique field
                                    
                                    
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
                                    JOptionPane.showMessageDialog(frame,
                                            "You have successfully added a " + memberType + " member!",
                                            "Member Added",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    
                                    // clearing the forms after object creation
                                    for(int j=0 ; j < inputFields.length ; j++ ) {
                                        
                                        // setting the placeholders for input variables
                                        String placeholder = j < 5 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
                                        
                                        inputFields[j].setForeground(PLACEHOLDERGRAY);
                                        inputFields[j].setText(placeholder);
                                        inputFields[j].setFocusable(false);
                                        inputFields[j].setBorder(DEFAULT_INPUT_BORDER); // setting default border
                                    }
                                    
                                    GENDER.clearSelection(); // deselecting radio buttons
                                    isGenderSelected = false; // since gender is deselected
                                    
                                    // resetting combo box placeholders
                                    for(int m = 0 ; m < inputCombos.length ; m++) {
    									String placeholder = (m == 0 || m == 3) ? "Year" : 
   										 					 ((m == 1 || m == 4) ? "Month" : "Day");

   										if(!inputCombos[m].getItemAt(0).equals(placeholder)) {
   											inputCombos[m].insertItemAt(placeholder, 0);
   											inputCombos[m].setSelectedIndex(0);
   										}
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(
                                            frame,
                                            "Member with ID "+inputFields[0].getText()+" already exists! Please enter a new ID.",
                                            "Duplicate input",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } 
                            
                            catch (NumberFormatException e1) {
                                
                                // error message
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Invalid ID! Please enter a valid number.",
                                        "Invalid input",
                                        JOptionPane.ERROR_MESSAGE);
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
        
        individualMemberManagement_P.setLayout(new BorderLayout());
        individualMemberManagement_P.setBackground(Color.green);
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
        memberCard_P.setPreferredSize(new Dimension(1,244));
        memberCard_P.setLayout(new BorderLayout());
        memberCard_P.add(cardCommonAttributes_P, BorderLayout.WEST);
        memberCard_P.add(cardUniqueAttributes_P, BorderLayout.CENTER);
        
        // initializing the Runnable interface to declare a function to handle the dialog and input processing
        updateInidividualMemberPanel = new Runnable() {
            
            // initializing memberId with a placeholder value
            int memberId = 0x696969;
            @Override
            public void run() {                        
                // looping until input is valid
                while (true) {
                    String input;
                    
                    if(showDialog==true) {
                        // dialog to input member ID
                         input = JOptionPane.showInputDialog(frame,
                                 "Enter a member ID:",
                                 "Manage Membership",
                                 JOptionPane.INFORMATION_MESSAGE);
                         
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
                            
                            boolean isExistingId = false; // to check if ID exists
                            String[] splitMemberData = new String[15]; // array to store split data from display method

                            // checking if the ID exists using a for loop of GymMember objects
                            for (int i = 0; i<members.size(); i++) {
                                GymMember member = members.get(i);
                                
                                if (member.id == memberId) {
                                    isExistingId = true;
                                    
                                    currentMemberIndex = i;
                                    memberInstanceOf = member instanceof PremiumMember ? "Premium" : "Regular";
                                    
                                    // premium member unique getters
                                    if(memberInstanceOf.equals("Premium")) { 
                                        
                                        PremiumMember premiumMember = (PremiumMember) member; // downcasting to access child methods
                                        
                                        premiumMember.display();
                                        
                                        // setting text of 1st non-editable field (Rs. as prefix for amount)                          
                                        individualMemberFields[1].setText(premiumMember.isFullPayment() == true ? fullyPaidText : "");
                                        
                                        if(premiumMember.isFullPayment()) {
                                            individualMemberFields[1].setFont(HELVETICA_BOLD_14);
                                            individualMemberFields[1].setForeground(STEELBLUE);
                                        }
                                        
                                        // setting text of 2nd non-editable field
                                        individualMemberFields[2].setText("Rs. "+Double.toString(premiumMember.getPremiumCharge()));
                                    }
                                    // regular member unique getters
                                    else {
                                        
                                        RegularMember regularMember = (RegularMember) member; // downcasting to access child methods
                                        
                                        regularMember.display();
                                    }
                                    
                                    
                                    /*
                                     * STYLING THE ACTIVATE/DEACTIVATE BUTTON
                                     */
                                    
                                    // setting corresponding text of first management button for each member
                                    individualMemberButtons[0].setText(member.isActiveStatus()?"Deactivate Membership":"Activate Membership"); 
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
                                String[] memberNameParts = cardLabels[0].getText().split(":", 2);
                                String memberName = memberNameParts[1].trim();
                                
                                memberManagementTitle_L.setText(memberInstanceOf + " Member | " + memberName + " (ID: " + memberId + ")");
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
                                                plan_C.setFont(HELVETICA_14);
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
                                                individualMemberFields[0].setText("");
                                            }
                                            
                                            break;
                                    }
                                }
                                
                                for(JPanel cardPanel : cardPanels) {
                                    cardPanel.removeAll(); // removing all previous labels completely
                                }
                                
                                // looping through the split member details and creating new labels dynamically  
                                for (int i = 0; i < cardLabels.length; i++) {

                                    // splitting title and value into two seperate variables, in an array
                                    splitMemberData = cardLabels[i].getText().split(":",2);          
                                    
                                    // taking the attribute value into a variable
                                    String value = splitMemberData[1].trim();
                                    
                                    // setting the text of the label to only contain the heading and a colon
                                    cardLabels[i].setText(splitMemberData[0]+": ");
                                    
                                    // initializing default values for prefix and custom attributes
                                    String prefix = "";
                                    String customDetailAttribute = "";
                                    String bold = "font-weight: bold; "; // putting CSS attribute for bold text in a variable

                                    // determining active status style
                                    if (i == 2) {
                                        prefix = "⦿ ";
                                        customDetailAttribute = value.equals("Active") ? "color: green" : "color: red"; // green for active, red for inactive
                                    }
                                    // handling boolean values (isFullPayment && isEligibleForUpgrade)
                                    else if (i == 12) {
                                        customDetailAttribute = value.equals("Yes") ? bold + "color: green" : bold + "color: red";
                                    }
                                    
                                    // Regular Member specific logic
                                    else if (memberInstanceOf.equals("Regular")) {
                                        
                                        if(i==10) {
                                            
                                            // using switch to set colors based on plan type
                                            switch (value) {
                                                case "Basic": planColor = bold + "color: #3AACAC"; break;  // Dark cyan
                                                case "Standard": planColor = bold + "color: #9B59B6"; break; // Dark pastel purple
                                                case "Deluxe": planColor = bold + "color: #BD9934"; break; // Dark gold
                                                default: break;
                                            }
                                            
                                            customDetailAttribute = planColor;
                                        }
                                        
                                        // handle plan price with different colors and prefix
                                        else if (i == 11) {
                                            
                                            customDetailAttribute = planColor;
                                            prefix = "Rs."; // for price, setting prefix to Rs.
                                        }
                                        // handling removal reason
                                        else if (i == 14 && !value.equals("N/A")) {
                                            customDetailAttribute = "color: red";
                                        }
                                    }
                                    // Premium Member specific logic
                                    else if (memberInstanceOf.equals("Premium")) {
                                        
                                        // handling Paid Amount and Remaining Amount
                                        if (i == 10 || i == 11) {
                                            
                                            prefix = "Rs.";
                                            customDetailAttribute = "color: green";
                                            customDetailAttribute = i==10? "color: green" : "color: red";
                                        }
                                        // handling Discount Amount
                                        else if (i == 13) {
                                            
                                            String wineRed = "#800020"; // putting a CSS color in a variable
                                            
                                            
                                            // Rs. prefix when actual price is shown
                                            prefix = !(value.equals("Ineligible") || value.equals("Uncalculated"))?"Rs.":"";
                                            
                                            // green for discount amount, maroon for text
                                            customDetailAttribute = (value.equals("Uncalculated") || value.equals("Ineligible")) ? bold + "color: "+wineRed: bold + "color: green";
                                            
                                            individualMemberFields[0].setText(prefix+value); // setting text of uneditable field to corresponding text/value
                                        }
                                    }

                                    
                                    // puttin the card title into a variable for readability
                                    String detailTitle = cardLabels[i].getText();
                                    
                                    
                                    // to add unique labels to another panel
                                    JPanel cardPanel = (i<10) ?  cardCommonAttributes_P :  cardUniqueAttributes_P;
                                                                        
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
                                            prefix,
                                            value
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
                                JOptionPane.showConfirmDialog(
                                        frame,
                                        "Invalid ID! This ID doesn't exist.",
                                        "Invalid input",
                                        JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException e1) {
                        // error message
                        byte errorWish = (byte) JOptionPane.showConfirmDialog(
                                                        frame,
                                                        "Invalid ID! Please enter a valid number.",
                                                        "Invalid input",
                                                        JOptionPane.DEFAULT_OPTION,
                                                        JOptionPane.ERROR_MESSAGE);

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
        
        // first card panel styling
        cardCommonAttributes_P.setBackground(LIGHTGRAY);
        cardCommonAttributes_P.setPreferredSize(new Dimension(455,1));
        cardCommonAttributes_P.setLayout(new GridLayout(0,2));
        
        // second card panel styling
        cardUniqueAttributes_P.setBackground(GRAY);
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
            inputField.setFont(HELVETICA_14);
            inputField.setForeground(MIDNIGHTBLUE);
            inputField.setBackground(LIGHTGRAY);
            inputField.setFocusable(false);
        }
        
        // setting font for non-editable fields
        for(int i = 0; i < individualMemberFields.length ; i+=2) { 
            individualMemberFields[i].setForeground(GUNMETALBLUE);
            individualMemberFields[i].setFont(HELVETICA_BOLD_14); // setting font of non focusable field
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
        menuButtons[activeIndex].setFont(CENTURY_GOTHIC_BOLD_14);
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
                	
                	boolean exit = false;
                    
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
                                    
                                    // setting the corresponding input fields into a common variable
                                    JTextField inputField = inputFields[j];
                                    
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
                                    	exit = true;
                                        break; // skipping reset if "No" is selected
                                    }
                                }

                                // resetting form fields
                                for (int j = 0; j < inputFields.length; j++) {
                                    String placeholder = j < 5 ? commonPlaceholders[j] : uniquePlaceholders[currentFormIndex][0];
                                    inputFields[j].setForeground(PLACEHOLDERGRAY);
                                    inputFields[j].setText(placeholder);
                                    inputFields[j].setFocusable(false);
                                    inputFields[j].setBorder(DEFAULT_INPUT_BORDER);
                                }

                                GENDER.clearSelection();
                                isGenderSelected = false;
                                
                                // resetting combo box placeholders
                                for(int m = 0 ; m < inputCombos.length ; m++) {
									String placeholder = (m == 0 || m == 3) ? "Year" : 
										 				 ((m == 1 || m == 4) ? "Month" : "Day");


									if(!inputCombos[m].getItemAt(0).equals(placeholder)) {
										inputCombos[m].insertItemAt(placeholder, 0);
										inputCombos[m].setSelectedIndex(0);
									}
                                }

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
                            
                            menuButtonHighlight.run(); // updating menu button highlight

                            frame.remove(bodyContent[lastIndex]);
                            frame.add(bodyContent[i]);

                            // reseting form content booleans
                            for (int k = 0; k < isFormContent.length; k++) {
                                isFormContent[k] = false;
                            }                            
                        }
                    }
                    
                    if(!exit) {
                    	// refreshing the frame layout to reflect the changes
                        frame.revalidate(); // recalculating layout
                        frame.repaint();    // redrawing the frame
                        manageMemberButton.setEnabled(true);
       
                        // updating table when dashboard button is pressed
                        if(e.getSource()==menuButtons[0] && lastIndex!=0) {
                                loadTableData.run();
                                filter.run();
                        }
                        // showing input dialog box if the member management panel is clicked and the current panel isn't member management panel
                        else if (e.getSource() == menuButtons[2] && lastIndex != 2) {
                            
                            // removing mouse listener of manageMemberButton to avoid one bug where no. of dialog boxes keep increasing
                            MouseListener[] listeners = manageMemberButton.getMouseListeners();
                            for (MouseListener listener : listeners) {
                                manageMemberButton.removeMouseListener(listener);
                            }
                            
                            showDialog=true;
                            isAccessedFromTable=false; // since back button should not fo to dashboard when accessed from menu button
                            lastMemberID = "-1"; // since last member id isn't needed (resetting it to default placeholder value)

                            memberManagementTitle_L.setText("Member Management"); // reverting to original title
                            memberManagementTitle_P.remove(utilityButtons_P[1]); // removing back button

                            
                            bodyContent[2].remove(memberManagementButton_P);
                            bodyContent[2].remove(individualMemberManagement_P);
                            
                            // calling updateInidividualMemberPanel runnable declared above
                            updateInidividualMemberPanel.run();
                            
                            // adding an action listener to the button (when it's re-added to the panel)
                            manageMemberButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    manageMemberButton.setForeground(MIDNIGHTBLUE);

                                    // triggering the same dialog input handling (run() function) when the button is clicked
                                    updateInidividualMemberPanel.run();
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
                                byte backWish = (byte) JOptionPane.showOptionDialog(
                                                                frame,
                                                                "Are you sure you want to clear all fields and go back?",
                                                                "Caution: Fields will be reset",
                                                                JOptionPane.YES_NO_OPTION,
                                                                JOptionPane.WARNING_MESSAGE,
                                                                null,
                                                                options, 
                                                                options[1]);
                                
                                GENDER.clearSelection(); // deselecting radio buttons
                                isGenderSelected = false; // since gender is deselected
                                
                                // resetting combo box placeholders
                                for(int m = 0 ; m < inputCombos.length ; m++) {
									String placeholder = (m == 0 || m == 3) ? "Year" : 
										 					 ((m == 1 || m == 4) ? "Month" : "Day");

										if(!inputCombos[m].getItemAt(0).equals(placeholder)) {
											inputCombos[m].insertItemAt(placeholder, 0);
											inputCombos[m].setSelectedIndex(0);
										}
                                }
                                
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
                               
                                // resetting combo box placeholders
                                for(int m = 0 ; m < inputCombos.length ; m++) {
									String placeholder = (m == 0 || m == 3) ? "Year" : 
										 					 ((m == 1 || m == 4) ? "Month" : "Day");

										if(!inputCombos[m].getItemAt(0).equals(placeholder)) {
											inputCombos[m].insertItemAt(placeholder, 0);
											inputCombos[m].setSelectedIndex(0);
										}
                                }
                                
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
                            
                            // when accessing from table
                            if(isAccessedFromTable) {
                                 
                                frame.remove(memberManagementContent); // removing content
                                
                                loadTableData.run(); // loading table data
                                frame.add(dashboardContent); // adding dashboard content
                                
                                lastIndex = 2;
                                activeIndex = 0;
                                
                                menuButtonHighlight.run(); // updating menu button highlight
                                filter.run();
                                
                                frame.revalidate();
                                frame.repaint();
                                
                                isAccessedFromTable = false;
                            }
                            // when directly accessing individual member management
                            else {
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
                int activateWish = JOptionPane.showOptionDialog(
                                            frame,
                                            wishKeyword+" "+member.getName()+"'s Membership?",
                                            "Confirm "+wishKeyword.substring(0,wishKeyword.length()-1)+"ion",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            null,
                                            0);
                
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
                    
                    updateInidividualMemberPanel.run(); // executing the card text getting/setting runnable  
                    showDialog = true; // reverting to true after text update is finished
                    
                    // success dialog
                    JOptionPane.showOptionDialog(
                            frame,
                            member.getName()+"'s membership has been successfully "+wishKeyword.toLowerCase()+"d!",
                            wishKeyword.substring(0,wishKeyword.length()-1)+"ion Successful!",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            null,
                            0); 
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
                    int markWish = JOptionPane.showOptionDialog(
                            frame,
                            "Mark "+member.getName()+"'s attendance?",
                            "Confirm Attendance",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            0);
                    
                    if(markWish==0) {
                        member.markAttendance();
                        
                        updateInidividualMemberPanel.run(); // executing the card text getting/setting runnable  
                        showDialog = true; // reverting to true after text update is finished
                        
                        // success dialog
                        JOptionPane.showOptionDialog(
                                frame,
                                member.getName()+"'s attendance has been successfully marked.", "Attendance Successful!",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                0);
                    }
                }
                else {
                    JOptionPane.showOptionDialog(
                            frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.",
                            "Inactive Member",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            null, 
                            0);
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
                int revertWish = JOptionPane.showOptionDialog(
                                            frame,
                                            "Proceeding will reset "+member.getName()+"'s data. Are you sure?",
                                            "Confirm Reset",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.WARNING_MESSAGE,
                                            null,
                                            options,
                                            options[1]);
                
                if(revertWish==0) {
                    
                    if(memberInstanceOf.equals("Regular")) {
                        
                        String removalReason = individualMemberFields[1].getText();
                        RegularMember regularMember = (RegularMember) member; // downcasting to GymMember RegularMember
                        
                        // showing error if removal reason field is empty
                        if(removalReason.equals("")) {
                            proceedUpdate = false;
                            JOptionPane.showOptionDialog(
                                    frame,
                                    "Please enter removal reason first.",
                                    "Invalid Reason",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.ERROR_MESSAGE,
                                    null,
                                    null,
                                    0);
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
                        updateInidividualMemberPanel.run(); // executing the card text getting/setting runnable  
                        showDialog = true; // reverting to true after text update is finished
                        
                        // success dialog
                        JOptionPane.showOptionDialog(
                                frame,
                                member.getName()+"'s data has been successfully reset.",
                                "Reset Successful!",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                0);
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
                showDialog = false; // sett0ing to false so dialog doesn't appear again when we a re already in the management screen
                
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
                        
                        // checking if user is downgrading 
                        boolean isDowngrade = regularMember.getPlanPrice(regularMember.getPlan()) > regularMember.getPlanPrice(selectedPlan); 
                        
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
                                
                                // seperate message for downgrade
                                message = "Your plan has been downgraded to "+selectedPlan+" for Rs."+regularMember.getPlanPrice(selectedPlan)+".";
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

                        
                        if(!premiumMember.isFullPayment()) {
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
                        updateInidividualMemberPanel.run(); // executing the card text getting/setting runnable  
                        showDialog = true; // reverting to true after text update is finished
                    }
                }
                else {
                    JOptionPane.showOptionDialog(
                            frame,
                            member.getName()+"'s membership is currently inactive. Activate membership to proceed.",
                            "Inactive Member",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            null,
                            0);
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
                    JOptionPane.showOptionDialog(
                            frame,
                            member.getName()+"'s membership is currently inactive. Activate membership to proceed.",
                            "Inactive Member",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            null,
                            0);
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
                            if(premiumMember.isFullPayment()) {
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
                        updateInidividualMemberPanel.run(); // executing the card text getting/setting runnable  
                        showDialog = true; // reverting to true after text update is finished
                    }
                }
                else {
                    JOptionPane.showOptionDialog(
                            frame, member.getName()+"'s membership is currently inactive. Activate membership to proceed.",
                            "Inactive Member",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            null,
                            0);
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
                            JOptionPane.showOptionDialog(
                                    frame,
                                    member.getName()+"'s membership is currently inactive. Activate membership to proceed.",
                                    "Inactive Member",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.ERROR_MESSAGE,
                                    null,
                                    null,
                                    0);
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

    // main method that calls constructor of this class
    public static void main(String[] args) {
        new GymGUI();
    }
}