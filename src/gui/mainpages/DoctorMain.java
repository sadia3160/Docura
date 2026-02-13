package gui.mainpages;

import com.mysql.cj.util.StringUtils;
import database.delete.DeleteMedicine;
import database.pull.PullDiagnosis;
import database.pull.PullPatient;
import database.save.SaveDiagnosis;
import database.save.SavePrescription;
import gui.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
FEATURES OF DOCTOR PORTAL
1. Patient details: Patient details viewing with prev diagnosis
2. Diagnosis: New diagnosis writing
3. Prescription:
*/

public class DoctorMain extends MainFrame {

    JScrollPane p, t, scroll;
    JTextField patId, patId2, pidt, med, quan;
    DefaultTableModel tableModel; JTable table;

    public DoctorMain(String id){

        //Frame
        frame.setTitle("Doctor Portal");
        top_panel.setBackground(new Color(0x31487A));
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
        drg.addActionListener(e -> drgActions());

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

    //**WRITE PRESCRIPTION for Patient
    private void drgActions(){
        center_panel.removeAll();

        JPanel top = new JPanel();
        JPanel top2 = new JPanel(new BorderLayout());
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(250,200));
        right.setBackground(new Color(0xD9E1F1));

        JPanel mid = new JPanel(new BorderLayout());

        JLabel ep = new JLabel("Enter Patient ID:");
        pidt = new JTextField();
        pidt.setPreferredSize(new Dimension(177, 25));

        JLabel nw = new JLabel("New Medicine:");
        med = new JTextField();
        med.setPreferredSize(new Dimension(200, 25));

        JLabel qn = new JLabel("Quantity:");
        quan = new JTextField();
        quan.setPreferredSize(new Dimension(200, 25));

        JButton add = new JButton("Add");
        add.addActionListener(e -> NewMedicine());

        JButton del = new JButton("Delete");
        del.addActionListener(e -> RemoveMedicine());
        JButton print = new JButton("Print"); //
        print.addActionListener(e -> PrintPrescription());

        top.add(ep); top.add(pidt);
        JSeparator vsep = new JSeparator(SwingConstants.VERTICAL); top.add(vsep);
        top.add(print);
        right.add(nw); right.add(med);
        right.add(qn); right.add(quan);
        right.add(add); right.add(del);

        //Center table view
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Medicines");
        tableModel.addColumn("Quantity");
        table = new JTable(tableModel);
        table.setShowGrid(true);

        JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mid.add(jsp, BorderLayout.CENTER);

        top2.add(top, BorderLayout.WEST);
        center_panel.add(top2, BorderLayout.NORTH);
        center_panel.add(right, BorderLayout.EAST);
        center_panel.add(mid, BorderLayout.CENTER);

        center_panel.revalidate();
        center_panel.repaint();
    }

    private void NewMedicine(){

        String id = pidt.getText();
        String m = med.getText();
        String q = quan.getText();

        if(!StringUtils.isEmptyOrWhitespaceOnly(id) && !StringUtils.isNullOrEmpty(id) &&
                !StringUtils.isEmptyOrWhitespaceOnly(m) && !StringUtils.isNullOrEmpty(m) &&
        !StringUtils.isEmptyOrWhitespaceOnly(q) && !StringUtils.isNullOrEmpty(q)) {

            new SavePrescription(id, m, q);
            Object[] data = {m,q};
            tableModel.addRow(data);
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter all information!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    private void RemoveMedicine(){

        int rowIdx = table.getSelectedRow();
        String id = pidt.getText();
        if(rowIdx == -1 && !StringUtils.isEmptyOrWhitespaceOnly(id) && !StringUtils.isNullOrEmpty(id)){
            JOptionPane.showMessageDialog(null, "Select a row!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
        else{
            int count = table.getColumnCount();
            String m = (String) tableModel.getValueAt(rowIdx, 0);
            String q = (String) tableModel.getValueAt(rowIdx, 1);
            tableModel.removeRow(rowIdx);
            new DeleteMedicine(id, m, q);
        }
    }

    private void PrintPrescription(){
        JOptionPane.showMessageDialog(null, "This feature is not developed yet!", "Message", JOptionPane.INFORMATION_MESSAGE, null);
    }
}
