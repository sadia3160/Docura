package gui.mainpages;

import com.mysql.cj.util.StringUtils;
import database.pull.PullDiagnosis;
import database.pull.PullPatient;
import database.save.SaveDiagnosis;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

/*
FEATURES OF DOCTOR PORTAL
1. Patient details: Patient details viewing with prev diagnosis
2. Diagnosis: New diagnosis writing
3. Prescription:
*/

public class DoctorMain extends MainFrame {

    JScrollPane p, t, scroll;
    JTextField patId, patId2;
    public DoctorMain(String id){

        //Frame
        frame.setTitle("Doctor Portal");
        top_panel.setBackground(new Color(0x476681));
        side_bar.setBackground(new Color(0xE8EDEE));
        center_panel.setBackground(Color.WHITE);

        //Sidebar Buttons
        JButton pat = new JButton("Patient Details");
        JButton dig = new JButton("Diagnosis");
        JButton drg = new JButton("Prescription");
        pat.setPreferredSize(new Dimension(200, 35));
        dig.setPreferredSize(new Dimension(200, 35));
        drg.setPreferredSize(new Dimension(200, 35));
        pat.setBackground(new Color(0xE8EDEE));
        drg.setBackground(new Color(0xE8EDEE));
        dig.setBackground(new Color(0xE8EDEE));

        //Action Lisenter
        pat.addActionListener(e -> patActions());
        dig.addActionListener(e -> digActions());

        side_bar.add(pat);
        side_bar.add(dig);
        side_bar.add(drg);

        //User Intro
        JLabel idLabel = new JLabel("User ID: "+id);
        JLabel roleLabel = new JLabel("Role: Doctor");
        idLabel.setFont(new Font("Sans Serif", Font.BOLD, 13));
        roleLabel.setFont(new Font("Sans Serif", Font.BOLD, 13));
        idLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);
        idLabel.setBorder(BorderFactory.createEtchedBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY));
        //roleLabel.setBorder(BorderFactory.createEtchedBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY));
        idLabel.setBounds(37,47,165, 25);
        roleLabel.setBounds(39,71,165, 25);

        top_panel.add(idLabel);
        top_panel.add(roleLabel);
    }

    //To SEARCH PATIENT
    private void patActions(){
        center_panel.removeAll();

        patId = new JTextField();
        JLabel enter = new JLabel("Enter Patient ID:");
        JButton srch = new JButton("Search");
        patId.setPreferredSize(new Dimension(177,25));

        srch.addActionListener(e -> GetPatInfo());

        JPanel tmp = new JPanel();
        tmp.add(enter);
        tmp.add(patId);
        tmp.add(srch);

        center_panel.add(tmp, BorderLayout.NORTH);
        center_panel.revalidate();
        center_panel.repaint();
    }
    private void GetPatInfo(){
        if(p != null){
            center_panel.remove(p);
            center_panel.remove(t);
        }
        PullPatient pp = new PullPatient();
        String id = patId.getText();
        if(StringUtils.isNullOrEmpty(id) || StringUtils.isEmptyOrWhitespaceOnly(id)){
            JOptionPane.showMessageDialog(null, "Enter Patient ID!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
        else {
            p = pp.Pulp(id);
            PullDiagnosis pd = new PullDiagnosis();
            t = pd.PullDig(id);
            center_panel.add(p, BorderLayout.CENTER);
            center_panel.add(t, BorderLayout.EAST);
            center_panel.revalidate();
            center_panel.repaint();
        }
    }

    //To ADD DIAGNOSIS
    private void digActions(){
        center_panel.removeAll();

        patId2 = new JTextField();

        JButton sav = new JButton("Save");
        patId2.setPreferredSize(new Dimension(177,25));

        sav.addActionListener(e -> SaveDiagnosis());

        JPanel tmp = new JPanel();
        JLabel enter = new JLabel("Enter Patient ID:");
        tmp.add(enter);
        tmp.add(patId2);
        tmp.add(sav);

        JTextArea doctorNotes = new JTextArea();
        doctorNotes.setLineWrap(true); //for line
        doctorNotes.setWrapStyleWord(true); //for word

        scroll = new JScrollPane(doctorNotes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel("Write Diagnosis");
        label.setBackground(Color.LIGHT_GRAY);
        label.setFont(new Font("Verdana", Font.BOLD, 11));

        JPanel btm = new JPanel(new BorderLayout(5,5));
        btm.add(label, BorderLayout.NORTH);
        btm.add(scroll, BorderLayout.CENTER);

        center_panel.add(tmp, BorderLayout.NORTH);
        center_panel.add(btm, BorderLayout.CENTER);
        center_panel.revalidate();
        center_panel.repaint();
    }

    private void SaveDiagnosis(){
        String id = patId2.getText();
        JTextArea ta = (JTextArea) scroll.getViewport().getView();
        String nt = ta.getText();
        if(!StringUtils.isEmptyOrWhitespaceOnly(nt) && !StringUtils.isNullOrEmpty(nt) && !StringUtils.isEmptyOrWhitespaceOnly(id) && !StringUtils.isNullOrEmpty(id)) {
            new SaveDiagnosis(id, nt);
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter all information!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    //WRITE PRESCRIPTION for Patient

}
