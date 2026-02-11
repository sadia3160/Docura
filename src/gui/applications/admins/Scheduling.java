package gui.applications.admins;

import com.toedter.calendar.JDateChooser;
import database.pull.PullAppointments;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Scheduling {

    JPanel panel = new JPanel();
    JLabel pid, aID, aDate, doc, atime;
    JTextField idField, aidField;
    JDateChooser dateChooser;
    JComboBox doctors, timeSelect;

    //Creating NEW APPINTMENT Button
    public JButton createButton(){
        JButton button = new JButton("New Appointment");
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 27));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        button.setFont(new Font(null, Font.BOLD, 13));
        return button;
    }

    //Creating the APPOINTMENT FORM
    public JPanel createForm() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xdddddd));

        pid = new JLabel("Patient ID");
        pid.setBounds(20, 130, 125, 30);
        idField = new JTextField();
        idField.setBounds(130, 133, 325, 30);

        aID = new JLabel("Appointment ID");
        aID.setBounds(20, 170, 125, 30);

        aidField = new JTextField();
        aidField.setBounds(130, 173, 325, 30);

        aDate = new JLabel("Appointment Date");
        aDate.setBounds(20, 210, 125, 30);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(130, 210, 325, 30);
        dateChooser.setBorder(BorderFactory.createEtchedBorder());

        atime = new JLabel("Appointment Time");
        atime.setBounds(20, 250, 125, 30);
        String[] time = {"5:00", "6:00", "7:00",
                "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
                "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00", "3:00", "4:00"
        };
        timeSelect = new JComboBox(time);
        timeSelect.setBounds(130, 253, 325, 30);


        doc = new JLabel("Doctor in Charge");
        doc.setBounds(20, 290, 125, 30);
        String[] opt = {"Md.Shahnewaz Chowdhury", "Md.Abdul Mannan","Md.Kamal Uddin"}; //**
        doctors = new JComboBox(opt);
        doctors.setBounds(130, 293, 325, 30);
        doctors.setBackground(new Color(0xe4e3e1));
        doctors.setEditable(true);

        panel.add(pid);
        panel.add(idField);
        panel.add(aID);
        panel.add(dateChooser);
        panel.add(aDate);
        panel.add(aidField);
        panel.add(doc);
        panel.add(doctors);
        panel.add(timeSelect);
        panel.add(atime);

        panel.setSize(551, 555);
        return panel;
    }

    //GETTING info from the APPOINTMENT FORM with catching exception

    public String getID(){
        try {
            String id = idField.getText();
            if(id != "") return id;
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }
    public String appointID(){
        try {
            String ad = aidField.getText();
            if(ad != "") return ad;
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }
    public Date getAppointDate(){
        try {
            Date dt = dateChooser.getDate();
            return dt;
        }
        catch(Exception e){
            return null;
        }
    }
    public String getAppointTime(){
        try {
            String ts = (String) timeSelect.getSelectedItem();
            if(ts != "") return ts;
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }
    public String getDocInCharge(){
        try {
            String doc = (String) doctors.getSelectedItem();
            if(doc != "") return doc;
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }

    public JScrollPane getAppointmentInfo(Date date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            String adate = sdf.format(date);
            PullAppointments p = new PullAppointments();
            JScrollPane jsp = p.Pull(adate);
            return jsp;
        }
        catch(Exception e){
            return null;
        }
    }
}
