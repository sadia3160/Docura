package gui.applications.doctors;

import gui.applications.admins.DepartmentNames;

import javax.swing.*;
import java.awt.*;

public class DoctorForm {

    JComboBox dptField;
    JTextField dField, qualField, tsField, conField;
    public JPanel docForm(){

        JPanel doctorForm = new JPanel();
        doctorForm.setLayout(null);
        doctorForm.setBackground(new Color(0xdddddd));

        JLabel dpt = new JLabel("Department Name");
        dpt.setBounds(20, 120, 125, 30);

        //Ask dept of doc
        DepartmentNames dn = new DepartmentNames();
        String[] depts = dn.getDepts();
        dptField = new JComboBox(depts);
        dptField.setEditable(true);
        dptField.setBounds(139, 123, 329, 30);

        JLabel dname = new JLabel("Doctor Name");
        dname.setBounds(20, 160, 125, 30);
        dField = new JTextField();
        dField.setBounds(139, 163, 329, 30);

        JLabel qual = new JLabel("Qualification");
        qual.setBounds(20, 200, 125, 30);
        qualField = new JTextField();
        qualField.setBounds(139, 203, 329, 30);

        JLabel tslot = new JLabel("Time Slot");
        tslot.setBounds(20, 240, 125, 30);
        tsField = new JTextField();
        tsField.setBounds(139, 243, 329, 30);

        JLabel con = new JLabel("Contact");
        con.setBounds(20, 280, 125, 30);
        conField = new JTextField();
        conField.setBounds(139, 283, 329, 30);

        doctorForm.add(dpt);
        doctorForm.add(dptField);
        doctorForm.add(dname);
        doctorForm.add(dField);
        doctorForm.add(qual);
        doctorForm.add(qualField);
        doctorForm.add(tslot);
        doctorForm.add(tsField);
        doctorForm.add(con);
        doctorForm.add(conField);

        doctorForm.setSize(551, 455);

        return doctorForm;
    }

    //dptField, dField, qualField, tsField, conField
    public String getdpt(){
        try{
            String d = (String)dptField.getSelectedItem();
            if(d != "") return d;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getdnm(){
        try{
            String d = dField.getText();
            if(d != "") return d;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getqual(){
        try{
            String d = qualField.getText();
            if(d != "") return d;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getts(){
        try{
            String d = tsField.getText();
            if(d != "") return d;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getcon(){
        try{
            String d = conField.getText();
            if(d != "") return d;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
}
