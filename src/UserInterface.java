import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;

// TODO: add button to automatically generate a random list of students.

public class UserInterface {
	
	JFrame frame;
	final String DEFAULT_TEXT = "Start by adding\nsome students...\n\n"
			+ "Set SID = 0 to\ngenerate random\ndata...\n\n"
			+ "Set Sort Limit = 0\nto remove limit";
	
	ArrayList<Student> inputList = new ArrayList<Student>();
	
	public UserInterface() {
		JButton addButton;
		JButton sortButton;
		JButton clearButton;
		JButton randomButton;
		
		JSpinner idInput;
		JSpinner gradeInput;
		JSpinner sortLimit;
		
		ButtonGroup orderGroup;
		JRadioButton ascRadio;
		JRadioButton descRadio;
		
		JScrollPane listScroll;
		JTextArea listField;
		
		JScrollPane outputScroll;
		JTextArea outputField;
		
		// Window setup ---------------------------------------
		frame = new JFrame("Selection Sort Interactive Example");
		frame.setResizable(false);
		frame.setSize(570, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		// Input panel setup ---------------------------------------
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(10, 10, 115, 420);
		inputPanel.setBackground(new Color(230, 230, 230));
		inputPanel.setLayout(null);
		
		// input spinners setup ---------------------------------------
		JLabel idLabel = new JLabel("SSID:");
		idLabel.setBounds(4, 6, 40, 30);
		
		idInput = new JSpinner();
		idInput.setBounds(40, 6, 70, 28);
		
		JLabel gradeLabel = new JLabel("Grade:");
		gradeLabel.setBounds(4, 38, 40, 30);
		
		gradeInput = new JSpinner();
		gradeInput.setBounds(40, 38, 70, 28);
		
		// Add button setup ---------------------------------------
		addButton = new JButton("Add Student");
		addButton.setBounds(2, 70, 110, 25);
		addButton.setFocusable(false);
		
		// Clear button setup ------------------------------------
		clearButton = new JButton("Clear List");
		clearButton.setBounds(2, 100, 110, 25);
		clearButton.setFocusable(false);
		
		// Random button setup ------------------------------------
		randomButton = new JButton("Random List");
		randomButton.setBounds(2, 130, 110, 25);
		randomButton.setFocusable(false);
		
		// sort limit spinner setup --------------------------------
		JLabel sortLabel = new JLabel("Sort Limit:");
		sortLabel.setBounds(2, 180, 50, 28);
		
		sortLimit = new JSpinner(new SpinnerNumberModel(10, 0, 1000, 1));
		sortLimit.setBounds(60, 180, 50, 28);
		
		// Radio buttons setup -------------------------------------
		orderGroup = new ButtonGroup();
		
		ascRadio = new JRadioButton("Lowest");
		ascRadio.setBounds(5, 210, 100, 20);
		
		descRadio = new JRadioButton("Highest");
		descRadio.setBounds(5, 230, 100, 20);
		descRadio.setSelected(true);
		
		orderGroup.add(ascRadio);
		orderGroup.add(descRadio);
		
		// sort button setup ------------------------------------------
		sortButton = new JButton("Sort it!");
		sortButton.setBounds(2, 260, 110, 35);
		sortButton.setFocusable(false);
		
		// listView panel setup -----------------------------------------
		listField = new JTextArea(DEFAULT_TEXT);
		listField.setEditable(false);

		listScroll = new JScrollPane(listField);
		listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.setBounds(135, 10, 200, 420);
		listScroll.setBorder(null);
		
		// output panel setup -----------------------------------------
		outputField = new JTextArea("Sorted List\ngoes here...");
		outputField.setEditable(false);

		outputScroll = new JScrollPane(outputField);
		outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		outputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		outputScroll.setBounds(345, 10, 200, 420);
		outputScroll.setBorder(null);
		
		// events ------------------------------------------
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					idInput.commitEdit();
					gradeInput.commitEdit();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(frame, "Invalid Inputs! Integers Only...", 
							"INPUT ERROR", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int SID = (int) idInput.getValue();
				int grade = (int) gradeInput.getValue();
				
				Student student;
				
				if (SID > 0) {
					student = new Student(SID, grade);
				} else {
					// generate random student if SID <= 0
					student = new Student();
				}
				
				inputList.add(student);
				inputList.sort(null);
				
				listField.setText(Student.formatList(inputList));
			}
		});
		
		// clear button handler
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listField.setText(DEFAULT_TEXT);
				inputList.clear();
			}
		});
		
		// random button handler
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputList = Student.generateRandomList();
				listField.setText(Student.formatList(inputList));
			}	
		});
		
		// sort button handler
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (inputList.size() == 0) {
					return;
				}
				
				try {
					sortLimit.commitEdit();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(frame, "Invalid Inputs! Integers Only...", 
							"INPUT ERROR", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int limit = (int) sortLimit.getValue();
				boolean isAscending = ascRadio.isSelected();
				String output;
				
				// decide what sorting function to use
				// (refer to the class SelectionSort)
				if (limit == 0) {
					if (isAscending) {
						output = "Lowest grades:\n" + Student.formatList(SelectionSort.Ascending(inputList));		
					} else {
						output = "Highest grades:\n" + Student.formatList(SelectionSort.Descending(inputList));
					}
				} else {
					if (isAscending) {
						output = limit + " Lowest grades:\n" +Student.formatList(SelectionSort.Ascending(inputList, limit));		
					} else {
						output = limit + " Highest grades:\n" +Student.formatList(SelectionSort.Descending(inputList, limit));
					}
				}
				
				outputField.setText(output);		
			}
		});
		
		// additions ---------------------------------------
		inputPanel.add(idLabel);
		inputPanel.add(gradeLabel);
		inputPanel.add(sortLabel);
		
		inputPanel.add(addButton);
		inputPanel.add(clearButton);
		inputPanel.add(randomButton);
		inputPanel.add(sortButton);
		
		inputPanel.add(idInput);
		inputPanel.add(gradeInput);
		inputPanel.add(sortLimit);
		
		inputPanel.add(ascRadio);
		inputPanel.add(descRadio);
		
		frame.add(inputPanel);
		frame.add(listScroll);
		frame.add(outputScroll);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception weTried) { }
		
		UserInterface ui = new UserInterface();
		ui.frame.setVisible(true);
	}
}

class Student implements Comparable<Student> {
	public int SID;
	public int grade;
	
	/**
	 * Creates a student with a random SID & grade
	 */
	public Student() {
		this.SID = (int) (1000 + (Math.random() * 9000));
		this.grade = (int) (35 + (Math.random() * 66));
	}
	
	/**
	 * Creates a student with given SID & grade
	 */
	public Student(int SID, int grade) {
		this.SID = SID;
		this.grade = grade;
	}
	
	public static String formatList(ArrayList<Student> list) {
		String result = "";
		
		for (Student std : list) {
			result += std.toString() + "\n";
		}
		
		return result;
	}

	public static ArrayList<Student> generateRandomList() {
		ArrayList<Student> list = new ArrayList<Student>();
		
		for (int i = 1; i <= 64; i++)
			list.add(new Student());
		
		list.sort(null);
		return list;
	}
	
	@Override
	public String toString() {
		return "SID: " + SID + ", Grade: " + grade;
	}

	@Override
	public int compareTo(Student other) {
        return Integer.compare(this.SID, other.SID);
	}
}
