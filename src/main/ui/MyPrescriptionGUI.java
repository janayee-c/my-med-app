package ui;

import model.Event;
import model.EventLog;
import model.PatientAccount;
import model.Prescription;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * myPrescriptionPanel requires no other files
 * Displays the PrescriptionApp function through a graphical user interface
 * CITATION: Java Swing TableDemo file
 */

//JPanel represents certain area with contained components for buttons/checkboxes
//JFrame represents framed window (used in showPrescriptionUI)
public class MyPrescriptionGUI extends JPanel {
    //default patient account and prescriptions to be displayed
    private PatientAccount myPatientAccount;
    private Prescription escitalopram;
    private Prescription buproprion;
    //json storage and loading fields
    private static final String JSON_STORE_FILE = "./data/patientaccountGUI.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //image Frame
    private JPanel imagePanel;
    //default prescription table and model
    private DefaultTableModel tableModel;
    private JTable prescriptionTable;
    //labels for buttons
    private static final String addPillString = "Add Prescription";
    private static final String removePillString = "Remove Prescription";
    private static final String saveString = "Save Account";
    private static final String loadString = "Load Account";
    private static final String rescheduleString = "Reschedule Time";

    //prescription visual component
    private static final String IMAGE_STORE_FILE = "./src/images/minimizedvisualcomponent.png";
    private ImageIcon image;
    private JLabel imageLabel;

    //buttons
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton rescheduleButton;

    //button pane
    private JPanel buttonPane;

    //text fields
    private JTextField pillName; //text field to input prescription name
    private JTextField pillID; // text field to input prescription ID
    private JTextField pillNumber; // text field to input prescription number
    private JTextField pillDose; // text field to input pill dose amount
    private JTextField pillDirections; // text field to input prescription directions
    private JTextField pillBuffer; // text field to input prescription buffer
    private JTextField pillFrequency; // text field to input prescription frequency
    private JTextField pillLastRefill; // text field to input last prescription refill
    private JTextField pillReschedule; // text field to input rescheduled first time
    //labels for text fields
    private JLabel inputName; //label for pillName text field
    private JLabel inputID; // label for pillID text field
    private JLabel inputNumber; // label for pillNumber text field
    private JLabel inputDose; // label for pillDose text field
    private JLabel inputDirections; // label for pillDirections text field
    private JLabel inputBuffer; // label for pillBuffer text field
    private JLabel inputFrequency; // label for pillFrequency text field
    private JLabel inputLastRefill; // label for pillLastRefill text field
    private JLabel inputReschedule; // label for pillReschedule text field


    //MODIFIES: this
    //EFFECTS: creates a main panel that displays the prescription table, text fields, buttons
    public MyPrescriptionGUI() {
        //constructs grid panel with single row
        super(new GridLayout(1, 0));

        //initializes jsonwriter and reader
        jsonWriter = new JsonWriter(JSON_STORE_FILE);
        jsonReader = new JsonReader(JSON_STORE_FILE);

        initializePatientAccount();
        initializeButtons();
        initializeTextLabels();
        initializeTable();
        initializeScrollPane();
        initializeButtonPane();
        initializeVisualComponent();
    }

    //MODIFIES: this.
    //EFFECTS: initializes JTable with columns and data following default model
    public void initializeTable() {
        String[] columnNames = {"Name", "Imprint", "Dosage", "Directions", "Frequency", "Dose Schedule", "Time Buffer",
                "Pill Number", "Daily Counter", "Last Refill", "Next Refill Date",
        };
        //preliminary data setup (with default pills)
        Object[][] data = {
                {"escitalopram", "P20", "500 mg", "water", 2, "08:00", 12, 30, 0, "2023-02-08",
                        LocalDate.of(2023, 3, 10)},
                {"zoloft", "3", "1 mg", "no food", 2, "09:00", 1, 31, 0, "2022-01-20",
                        LocalDate.of(2022, 2, 20)}
        };
        //Initializes table object with dimensions
        prescriptionTable = new JTable();
        prescriptionTable.setPreferredSize(new Dimension(5000, 10));
        prescriptionTable.setFillsViewportHeight(true);
        ListSelectionModel modelSelector = prescriptionTable.getSelectionModel();
        modelSelector.setSelectionMode((ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
        //Initialize model object with default table model (so we can use addRow later)
        tableModel = new DefaultTableModel(data, columnNames);
        prescriptionTable.setModel(tableModel);
        prescriptionTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        prescriptionTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        prescriptionTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        prescriptionTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        prescriptionTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        prescriptionTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        prescriptionTable.getColumnModel().getColumn(6).setPreferredWidth(120);
    }

    //MODIFIES: this.
    //EFFECTS: initializes scrollpane with prescriptionTable
    public void initializeScrollPane() {
        //Initializes the scroll pane with a button panel and adds the table to it.
        JScrollPane prescriptionScrollPane = new JScrollPane(prescriptionTable);
        //Add the scroll pane to this panel.
        add(prescriptionScrollPane);
    }

    //MODIFIES: this.
    //EFFECTS: initializes the visual component in an image panel
    public void initializeVisualComponent() {
        //initializes images
        imagePanel = new JPanel();
        image = new ImageIcon(IMAGE_STORE_FILE);
        imageLabel = new JLabel(image);
        imageLabel.setSize(image.getIconWidth(), image.getIconHeight());
        imagePanel.add(imageLabel);
        imagePanel.setSize(image.getIconWidth(), image.getIconHeight());
        imagePanel.setVisible(true);
        add(imagePanel);
    }

    //MODIFIES: this and Patient Account.
    //EFFECTS: initializes default patient account and persistence objects
    public void initializePatientAccount() {
        //initializes jsonWriter and jsonReader to storage files
        myPatientAccount = new PatientAccount("Janaye Cheong", 2003, "CPSC 210");
        escitalopram = new Prescription("escitalopram", "P20",
                30, "500 mg", "water", 12, 2,
                LocalDate.of(2023, Month.FEBRUARY, 8));
        buproprion = new Prescription("zoloft", "142", 31,
                "1 mg", "take after eating", 1, 2,
                LocalDate.of(2023, Month.JANUARY, 20));
        myPatientAccount.addPrescription(escitalopram);
        myPatientAccount.addPrescription(buproprion);
    }

    //MODIFIES: this.
    //EFFECTS: instantiates buttons
    public void initializeButtons() {
        //addButton configuration
        addButton = new JButton(addPillString); //text display of button
        addButton.setActionCommand(addPillString); //sets action to add a pill
        addButton.addActionListener(new AddPillListener());
        //removeButton configuration
        removeButton = new JButton(removePillString); //text display of button
        removeButton.setActionCommand(removePillString); //sets action to add a pill
        removeButton.addActionListener(new RemovePillListener());
        //saveButton configuration
        saveButton = new JButton(saveString); // text display of save button
        saveButton.setActionCommand(saveString); // sets action to save a pill
        saveButton.addActionListener(new SavePillListener());
        //loadButton configuration
        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadPillListener());
        //rescheduleButton configuration
        rescheduleButton = new JButton(rescheduleString);
        rescheduleButton.setActionCommand(rescheduleString);
        rescheduleButton.addActionListener(new SchedulePillListener());

    }

    //MODIFIES: this.
    //EFFECTS: instantiates text fields
    public void initializeTextLabels() {
        //create text fields
        pillName = new JTextField(5);
        pillID = new JTextField(5);
        pillNumber = new JTextField(5);
        pillDose = new JTextField(5);
        pillDirections = new JTextField(5);
        pillBuffer = new JTextField(5);
        pillFrequency = new JTextField(5);
        pillLastRefill = new JTextField(5);
        pillReschedule = new JTextField(5);
        //create labels for text fields
        inputName = new JLabel("Prescription Name");
        inputID = new JLabel("Prescription ID");
        inputNumber = new JLabel("Number of Pills");
        inputDose = new JLabel("Dosage (include units)");
        inputDirections = new JLabel("Directions for Dosage");
        inputBuffer = new JLabel("Time before Doses (hrs)");
        inputFrequency = new JLabel("Dosages per Day");
        inputLastRefill = new JLabel("Last Refill Date (YYYY-MM-DD) ie. (2018-10-23)");
        inputReschedule = new JLabel("Reschedule First Dose ie. (04:00)");
    }

    //MODIFIES: this.
    //EFFECTS: removes prescriptions currently in table
    public void clearPrescriptionTable() {
        tableModel.setRowCount(0);
    }

    //MODIFIES: this.
    //EFFECTS: resets all text fields
    public void clearTextFields() {
        pillName.setText("");
        pillID.setText("");
        pillNumber.setText("");
        pillDose.setText("");
        pillDirections.setText("");
        pillBuffer.setText("");
        pillFrequency.setText("");
        pillLastRefill.setText("");
    }

    //MODIFIES: this.
    //EFFECTS: instantiates the button pane
    public void initializeButtonPane() {
        //button panel containing functionality
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(15, 1));
        //addButton functionality
        buttonPane.add(inputName);
        buttonPane.add(pillName);
        buttonPane.add(inputID);
        buttonPane.add(pillID);
        buttonPane.add(inputNumber);
        buttonPane.add(pillNumber);
        buttonPane.add(inputDose);
        buttonPane.add(pillDose);
        buttonPane.add(inputDirections);
        buttonPane.add(pillDirections);
        buttonPane.add(inputBuffer);
        buttonPane.add(pillBuffer);
        buttonPane.add(inputFrequency);
        buttonPane.add(pillFrequency);
        buttonPane.add(inputLastRefill);
        buttonPane.add(pillLastRefill);
        buttonPane.add(inputReschedule);
        buttonPane.add(pillReschedule);
        addButtonsToPane();
        add(buttonPane, BorderLayout.AFTER_LAST_LINE);
    }

    //MODIFIES: this.
    //EFFECTS: adds buttons to the instantiated buttonPane
    public void addButtonsToPane() {
        buttonPane.add(addButton);
        //removebutton functionality
        buttonPane.add(removeButton);
        //savebutton functionality
        buttonPane.add(saveButton);
        //loadbutton functionality
        buttonPane.add(loadButton);
        buttonPane.add(rescheduleButton);
    }

    class SchedulePillListener implements ActionListener {
        @Override
        //MODIFIES: this and Prescription
        //EFFECTS: reschedules the Prescription selected to the specified time
        public void actionPerformed(ActionEvent e) {
            String time = pillReschedule.getText();
            if (prescriptionTable.getSelectedRow() != -1) {
                Object prName = tableModel.getValueAt(prescriptionTable.getSelectedRow(), 0);
                for (Prescription p : myPatientAccount.getPrescriptions()) {
                    if (prName == p.getName()) {
                        p.editSchedule(time);
                        tableModel.setValueAt(scheduleToString(p.getDoseSchedule()),
                                prescriptionTable.getSelectedRow(), 5);
                    }
                    pillReschedule.setText("");
                }
            }
        }
    }

    class AddPillListener implements ActionListener {
        @Override
        //MODIFIES: this and PatientAccount
        //EFFECTS: adds prescription to the PatientAccount and the table
        public void actionPerformed(ActionEvent e) {
            String prescriptionName = pillName.getText();
            String prescriptionID = pillID.getText();
            int prescriptionNumber = Integer.parseInt(pillNumber.getText());
            String prescriptionDose = pillDose.getText();
            String prescriptionDirections = pillDirections.getText();
            int prescriptionBuffer = Integer.parseInt(pillBuffer.getText());
            int prescriptionFrequency = Integer.parseInt(pillFrequency.getText());
            String dateString = pillLastRefill.getText();
            LocalDate prescriptionLastRefill = LocalDate.parse(dateString);
            //make the new prescription
            Prescription newPrescription = new Prescription(prescriptionName, prescriptionID,
                    prescriptionNumber, prescriptionDose,
                    prescriptionDirections, prescriptionBuffer,
                    prescriptionFrequency, prescriptionLastRefill);
            //add the new Prescription to PatientAccount
            myPatientAccount.addPrescription(newPrescription);
            //add the new Prescription to the table
            //used scheduleToString method for formatting of the dose schedule
            tableModel.addRow(new Object[]{newPrescription.getName(), newPrescription.getImprint(),
                    newPrescription.getDosage(), newPrescription.getDirections(), newPrescription.getFrequency(),
                    scheduleToString(newPrescription.getDoseSchedule()), newPrescription.getTimeBuffer(),
                    newPrescription.getPillNumber(), newPrescription.getDailyCounter(), newPrescription.getLastRefill(),
                    newPrescription.getRefillDate()});
            clearTextFields();
        }
    }

    class RemovePillListener implements ActionListener {
        @Override
        //MODIFIES: this and PatientAccount
        //EFFECTS: removes the Prescription selected to the specified time
        public void actionPerformed(ActionEvent e) {
            if (tableModel.getRowCount() == 0) { //table is empty
                removeButton.setEnabled(false);
            } else if (prescriptionTable.getSelectedRow() >= 0) {
                // remove selected row from the model;
                Object prName = tableModel.getValueAt(prescriptionTable.getSelectedRow(), 0);
                tableModel.removeRow(prescriptionTable.getSelectedRow());

/*                Iterator<Prescription> iterator = myPatientAccount.getPrescriptions().iterator();
                while (iterator.hasNext()) {
                    Prescription p = iterator.next();
                    if (prName == p.getName()) {
                        myPatientAccount.removePrescription(p);
                        iterator.remove();
                    }*/

                for (Prescription p : myPatientAccount.getPrescriptions()) {
                    if (prName == p.getName()) {
                        myPatientAccount.removePrescription(p);
                        break;
                    }
                }
            }
        }
    }

    public class SavePillListener implements ActionListener {
        @Override
        //MODIFIES: this, JsonWriter
        //EFFECTS: adds the PatientAccount ot the JsonWriter and clears the table
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(myPatientAccount);
                jsonWriter.close();
                clearPrescriptionTable();
                System.out.println("Successfully saved file");
                System.out.println("Saved " + myPatientAccount.getName() + " to " + JSON_STORE_FILE);
            } catch (IOException ex) {
                System.out.println("Could not save file");
            }
        }
    }

    class LoadPillListener implements ActionListener {
        @Override
        //MODIFIES: this, JsonReader
        //EFFECTS: loads the PatientAccount from saved file
        public void actionPerformed(ActionEvent e) {
            jsonReader = new JsonReader(JSON_STORE_FILE);
            try {
                clearPrescriptionTable();
                myPatientAccount = jsonReader.read();
                for (Prescription p : myPatientAccount.getPrescriptions()) {
                    tableModel.addRow(new Object[]{p.getName(), p.getImprint(),
                            p.getDosage(), p.getDirections(), p.getFrequency(),
                            scheduleToString(p.getDoseSchedule()), p.getTimeBuffer(),
                            p.getPillNumber(), p.getDailyCounter(),
                            p.getLastRefill(), p.getRefillDate()});
                }
            } catch (IOException ie) {
                System.out.println("null");
            }
        }
    }

    //EFFECTS: returns a string containing patient's schedule in a list
    public String scheduleToString(List<LocalTime> localSchedule) {
        String stringSchedule = "";
        for (LocalTime t : localSchedule) {
            if (localSchedule.indexOf(t) == (localSchedule.size() - 1)) {
                stringSchedule = stringSchedule.concat(t.toString());
            } else {
                stringSchedule = stringSchedule.concat(t.toString() + " ");
            }
        }
        return stringSchedule;
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void showPrescriptionAppUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MyPrescriptionPanel"); //frame is here
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //window adapter allows overriding of method that occurs when the window is closing
        //rather than reimplementing the WindowListener interface
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing application... recording events.");
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
        //print out all events that have occurred upon closing window
        //Create and set up the content pane.
        MyPrescriptionGUI prescriptionContentPane = new MyPrescriptionGUI();
        prescriptionContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(prescriptionContentPane);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: prints the events in the single EventLog
    public static void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next);
        }
    }

    //EFFECTS: runs the PrescriptionAppUi
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showPrescriptionAppUI();
            }
        });
    }
}

