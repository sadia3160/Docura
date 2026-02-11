package gui.applications.admins;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientForm {
    JLabel type, ward, room, patientID, name, birthDate, bGroup, gender, patientPhone, guardianPhone, address, profession, notes;
    JTextField patientIDt, namet, pt, gt, addi, prf, wardt, roomt, typet;
    JTextArea notesT;
    JDateChooser jdc;
    JComboBox jcb1, jcb2;
    JPanel panel;
    JScrollPane scroll;

    public JPanel createJPanel() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xdddddd));

        createLabels();

        panel.add(name);
        panel.add(birthDate);
        panel.add(jcb1);
        panel.add(jdc);
        panel.add(bGroup);
        panel.add(gender);
        panel.add(jcb2);
        panel.add(patientPhone);
        panel.add(guardianPhone);
        panel.add(address);
        panel.add(profession);
        panel.add(notes);
        panel.add(namet);
        panel.add(pt);
        panel.add(gt);
        panel.add(addi);
        panel.add(prf);
        panel.add(scroll);
        panel.add(patientID);
        panel.add(patientIDt);
        panel.add(type);
        panel.add(typet);
        panel.add(room);
        panel.add(roomt);
        panel.add(ward);
        panel.add(wardt);

        panel.setSize(700,750);

        return panel;
    }

    private void createLabels(){

        patientID = new JLabel("Patient ID");
        patientID.setBounds(20,100, 100, 30);
        patientIDt = new JTextField();
        patientIDt.setBounds(130, 103, 300, 30);

        name = new JLabel("Name");
        name.setBounds(20,140, 100,30);
        namet = new JTextField();
        namet.setBounds(130, 143, 300, 30);

        birthDate = new JLabel("Date of Birth");
        birthDate.setBounds(20,180,100,30);

        jdc = new JDateChooser();
        jdc.setBounds(130, 183, 300, 30);
        jdc.setBorder(BorderFactory.createEtchedBorder());

        bGroup = new JLabel("Blood Group");
        bGroup.setBounds(20, 220, 100, 30);
        String[] options = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        jcb1 = new JComboBox(options);
        jcb1.setBounds(130, 223, 300, 30);
        jcb1.setBackground(new Color(0xe4e3e1));
        jcb1.setEditable(true);

        gender = new JLabel("Gender");
        gender.setBounds(20, 260, 100, 30);
        String[] opt = {"Female", "Male"};
        jcb2 = new JComboBox(opt);
        jcb2.setBounds(130, 263, 300, 30);
        jcb2.setBackground(new Color(0xe4e3e1));

        patientPhone = new JLabel("Patient Phone");
        patientPhone.setBounds(20, 300, 100, 30);
        pt = new JTextField();
        pt.setBounds(130, 303, 300, 30);

        guardianPhone = new JLabel("Guardian Phone");
        guardianPhone.setBounds(20,340, 100, 30);
        gt = new JTextField();
        gt.setBounds(130, 343, 300, 30);

        address = new JLabel("Address");
        address.setBounds(20,380,100,30);
        addi = new JTextField();
        addi.setBounds(130, 383, 300, 30);

        profession = new JLabel("Profession");
        profession.setBounds(20,420,100,30);
        prf = new JTextField();
        prf.setBounds(130, 423, 300, 30);

        notes = new JLabel("Notes");
        notes.setBounds(20,460,100,30);
        notesT = new JTextArea();
        notesT.setLineWrap(true); //for line
        notesT.setWrapStyleWord(true); //for word

        scroll = new JScrollPane(notesT, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(130, 463, 300, 100);

        type = new JLabel("Patient Type");
        typet = new JTextField();

        type.setBounds(450,130, 150, 15);
        typet.setBounds(450, 145, 150, 30);

        wardt = new JTextField(); roomt = new JTextField();
        ward = new JLabel("Ward");
        ward.setBounds(450, 190, 150, 15);
        wardt = new JTextField();
        wardt.setBounds(450, 205 , 150, 30);
        room = new JLabel("Room");
        room.setBounds(450, 240, 150, 15);
        roomt = new JTextField();
        roomt.setBounds(450,255, 150, 30);
    }
    // patID, patName, birthDate, bloodGroup, gender, patientPhone, guardianPhone,address, notes, patType, ward, room

    public String getPatID(){
        try {
            String p = patientIDt.getText();
            if(p != "") return p;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getPatName(){
        try {
            String n = namet.getText();
            if(n != "") return n;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getBirthDate(){
        try {
            Date date = jdc.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            String bDate = sdf.format(date);
            return bDate;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getBloodGroup(){
        try {
            String s1 = (String) jcb1.getSelectedItem();
            if(s1 != "") return s1;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getGender(){
        try {
            String s2 = (String) jcb2.getSelectedItem();
            if(s2 != "") return s2;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getPatPhone(){
        try {
            String ph = pt.getText();
            if(ph != "") return ph;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getGurPhone(){
        try {
            String gh = gt.getText();
            if(gh != "") return gh;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getAddress(){
        try {
            String adr = addi.getText();
            if(adr != "") return adr;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getPrf(){
        try {
            String pr = prf.getText();
            if(pr != "") return pr;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getNotes(){
        try {
            String nt = notesT.getText();
            if(nt != "") return nt;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getPatType(){
        try {
            String tp = typet.getText();
            if(tp != "") return tp;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getWard(){
        try {
            if(wardt.getText() != "") return wardt.getText();
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String getRoom(){
        try {
            String rm =  roomt.getText();
            if(rm != "") return rm;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
}
