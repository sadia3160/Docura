package gui.mainpages;

import com.mysql.cj.util.StringUtils;
import com.toedter.calendar.JCalendar;
import database.delete.DeleteItem;
import database.delete.DeleteMedicine;
import database.pull.*;
import database.save.*;
import gui.MainFrame;
import gui.applications.admins.*;
import gui.applications.doctors.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
FEATURES OF ADMIN PORTAL
1. Appointment scheduling: Appointment create, save, view
2. Patient Entry: Patient reg and search
3. Billing:
4. Doctor list and schedule: New doctor add and doctor info view
*/

public class AdminMain extends MainFrame {

    AdminSidebar asb;
    Scheduling obj;
    PatientForm pf; DoctorForm df; InvoiceForm ifr;

    JPanel doctorPanel;
    JDialog appointment_dialog, patient_dialog, doctor_dialog, bill_dialog;
    JScrollPane jsp, docs;
    JCalendar calendar; Date date;
    JComboBox deptSelection;

    DefaultTableModel tableModel; JTable table;
    JTextField pidt;

    public AdminMain(String id){

        //Frame
        frame.setTitle("Admin Portal");
        top_panel.setBackground(new Color(0x768692));
        side_bar.setBackground(new Color(0xdddddd));
        center_panel.setBackground(Color.WHITE);

        //Sidebar Buttons
        asb = new AdminSidebar();

        asb.sch.addActionListener(e -> schActions());
        asb.reg.addActionListener(e -> regActions());
        asb.bill.addActionListener(e -> billActions());
        asb.list.addActionListener(e -> listActions());

        side_bar.add(asb.sch);
        side_bar.add(asb.reg);
        side_bar.add(asb.bill);
        side_bar.add(asb.list);

        //User Intro
        JLabel idLabel = new JLabel("User ID: "+id);
        JLabel roleLabel = new JLabel("Role: Administration");
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

    //APPOINTMENT SCHEDULING ACTIONS
    private void schActions(){
        center_panel.removeAll();

        obj = new Scheduling();
        calendar = new JCalendar();
        JPanel cal = new JPanel();
        date = calendar.getDate();

        //cal.setLayout(new BorderLayout());

        //ADDING the CALENDER in a panel
        cal.setBorder(BorderFactory.createBevelBorder(3));

        cal.setPreferredSize(new Dimension(200,200));
        calendar.setPreferredSize(new Dimension(200,150));

        JButton see = new JButton("Show Appointments");
        see.setForeground(Color.DARK_GRAY);
        //see.setBackground(new Color(0xd4dcdc));
        see.setFocusable(false);
        see.setPreferredSize(new Dimension(150,25));
        //see.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        see.addActionListener(e-> SeeAppointments());

        cal.add(calendar); //, BorderLayout.NORTH);
        cal.add(see); // BorderLayout.CENTER);

        jsp = obj.getAppointmentInfo(date); //To PULL APPOINTMENT via DATE

        JButton appointment = obj.createButton();
        appointment.addActionListener(e -> appointment_form());

        center_panel.add(cal, BorderLayout.WEST); //Adding calender
        center_panel.add(appointment, BorderLayout.NORTH); //Adding appointment button
        center_panel.add(jsp, BorderLayout.CENTER); //Adding the appointments fetched via DATE

        center_panel.revalidate();
        center_panel.repaint();
    }

    private void appointment_form(){

        JPanel form = obj.createForm();
        appointment_dialog = new JDialog(frame, "Appointment Registration Form", true);
        appointment_dialog.setLayout(null);
        ImageIcon icon = new ImageIcon("src/gui/pictures/docura_logo.png");
        appointment_dialog.setIconImage(icon.getImage());
        appointment_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JButton save = new JButton("Save"), cancel = new JButton("Cancel");
        save.setBounds(427,453,77,25); cancel.setBounds(339,453,77,25);

        cancel.addActionListener(e -> appointment_dialog.dispose());
        save.addActionListener(e -> saveAppointment());

        form.add(save);
        form.add(cancel);

        appointment_dialog.add(form);
        appointment_dialog.setSize(551,555);
        appointment_dialog.setResizable(false);
        appointment_dialog.setLocationRelativeTo(null);
        appointment_dialog.setVisible(true);
    }

    private void saveAppointment(){

        String pid = obj.getID();
        String aid = obj.appointID();
        Date appo = obj.getAppointDate();
        String atime = obj.getAppointTime();
        String docInC = obj.getDocInCharge(); //Date check!

        if(pid != null && aid != null && appo != null && atime != null && docInC != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            String adate = sdf.format(appo);
            SaveAppointments sv = new SaveAppointments(appointment_dialog, pid, aid, adate, atime, docInC);
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter all information!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }

        SeeAppointments();
    }

    private void SeeAppointments(){
        if(jsp != null){
            center_panel.remove(jsp);
        }
        Date date = calendar.getDate();
        jsp = obj.getAppointmentInfo(date);
        center_panel.add(jsp, BorderLayout.CENTER);
        center_panel.revalidate();
        center_panel.repaint();
    }

    //PATIENT REG AND SEARCHING ACTIONS
    private void regActions(){
        center_panel.removeAll();

        JPanel top = new JPanel();
        JButton patientAdd = new JButton("Add New Patient");
        JButton search = new JButton("Search");
        JTextField patientSearch = new JTextField();
        JLabel enter = new JLabel("Enter Patient ID:");
        patientSearch.setPreferredSize(new Dimension(200,25));
        top.add(patientAdd);
        top.add(enter);
        top.add(patientSearch);
        top.add(search);
        patientAdd.addActionListener(e -> patient_form());

        center_panel.add(top, BorderLayout.NORTH);
        jsp = new JScrollPane();
        search.addActionListener(e -> GetPatientInfo(patientSearch.getText()));

        center_panel.revalidate();
        center_panel.repaint();
    }

    private void GetPatientInfo(String id){
        if(jsp != null){
            center_panel.remove(jsp);
        }
        if(StringUtils.isNullOrEmpty(id) || StringUtils.isEmptyOrWhitespaceOnly(id)){
            JOptionPane.showMessageDialog(null, "Enter Patient ID!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
        else {
            PullPatient pp = new PullPatient();
            jsp = pp.Pulp(id);
            center_panel.add(jsp, BorderLayout.CENTER);
            center_panel.revalidate();
            center_panel.repaint();
        }
    }

    private void patient_form(){
        pf = new PatientForm();
        JPanel jp = pf.createJPanel();

        patient_dialog = new JDialog(frame, "Patient Registration Form", true);
        patient_dialog.setLayout(null);
        ImageIcon icon = new ImageIcon("src/gui/pictures/docura_logo.png");
        patient_dialog.setIconImage(icon.getImage());
        patient_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //save, cancel button
        JButton save = new JButton("Save"), cancel = new JButton("Cancel");
        save.setBounds(470,650,77,25); cancel.setBounds(565,650,77,25);

        cancel.addActionListener(e -> patient_dialog.dispose());
        save.addActionListener(e -> savePatient());

        patient_dialog.add(save);
        patient_dialog.add(cancel);

        patient_dialog.add(jp);
        patient_dialog.setSize(700,750);
        patient_dialog.setLocationRelativeTo(null);
        patient_dialog.setVisible(true);
    }

    private void savePatient(){

        String pid = pf.getPatID();
        String pname = pf.getPatName();
        String bdate = pf.getBirthDate();
        String bgroup = pf.getBloodGroup();
        String gen = pf.getGender();
        String pphone = pf.getPatPhone();
        String gphone = pf.getGurPhone();
        String addr = pf.getAddress();
        String prof = pf.getPrf();
        String note = pf.getNotes();
        String ptyp = pf.getPatType();
        String wrd = pf.getWard();
        String rm  = pf.getRoom();

        if(pid != null && pname != null && bdate != null &&
                bgroup != null && gen != null && pphone != null && gphone != null
                && addr != null && prof != null && note != null && ptyp != null && wrd != null && rm != null) {
            SavePatient sp = new SavePatient(patient_dialog, pid, pname, bdate, bgroup, gen, pphone, gphone, addr, prof, wrd, rm, ptyp, note);
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter all information!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    //DOCTOR LIST AND TIME SCHEDULE
    private void listActions(){

        center_panel.removeAll();
        doctorPanel = new JPanel(new BorderLayout());
        JButton addDoctor = new JButton("Add Doctor");
        JButton show = new JButton("Show");

        DepartmentNames dn = new DepartmentNames();
        String[] depts = dn.getDepts();
        deptSelection = new JComboBox(depts);
        deptSelection.setEditable(true);

        show.addActionListener(e -> ShowDept());
        addDoctor.addActionListener(e -> InsertDoctor());

        JPanel top = new JPanel();
        top.add(addDoctor);
        top.add(deptSelection);
        top.add(show);

        center_panel.add(top, BorderLayout.NORTH);

        center_panel.revalidate();
        center_panel.repaint();
    }

    private void ShowDept(){

        //Retrive Doctors from database
        if(docs != null){
            center_panel.remove(docs); //***
        }

        String selectedDept = (String)deptSelection.getSelectedItem();
        PullDoctors pullDoctors = new PullDoctors();
        docs = pullDoctors.PullDoc(selectedDept);

        //doctorPanel.add(docs, BorderLayout.CENTER);

        center_panel.add(docs, BorderLayout.CENTER);

        center_panel.revalidate();
        center_panel.repaint();
    }
    private void InsertDoctor(){

        df = new DoctorForm();
        JPanel dform = df.docForm();

        doctor_dialog = new JDialog(frame,"New Doctor", true);
        doctor_dialog.setLayout(null);
        ImageIcon icon = new ImageIcon("src/gui/pictures/docura_logo.png");
        doctor_dialog.setIconImage(icon.getImage());
        doctor_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JButton save = new JButton("Save"), cancel = new JButton("Cancel");
        save.setBounds(427,345,77,25); cancel.setBounds(339,345,77,25);

        cancel.addActionListener(e -> doctor_dialog.dispose());
        save.addActionListener(e -> saveDoctor());

        dform.add(save);
        dform.add(cancel);

        doctor_dialog.add(dform);
        doctor_dialog.setSize(551,455);
        doctor_dialog.setResizable(false);
        doctor_dialog.setLocationRelativeTo(null);
        doctor_dialog.setVisible(true);
    }

    private void saveDoctor(){

        String dt = df.getdpt();
        String dn = df.getdnm();
        String ql = df.getqual();
        String ts = df.getts();
        String cn = df.getcon();

        if(dt != null && dt != "" &&
                dn != null && dn != "" &&
                ql != null && ql != "" &&
                ts != null && ts != "" &&
                cn != null && cn != ""){

                new SaveDoctor(doctor_dialog, dt,dn,ql,ts,cn);
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter all information!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    //**BILLING
    private void billActions(){
        center_panel.removeAll();

        JPanel top = new JPanel();
        JPanel top2 = new JPanel(new BorderLayout());
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(250,200));
        right.setBackground(new Color(0xdddddd));

        JPanel mid = new JPanel(new BorderLayout());

        JLabel ep = new JLabel("Enter Patient ID:");
        pidt = new JTextField();
        pidt.setPreferredSize(new Dimension(177, 25));


        JButton add = new JButton("Add New +");
        add.addActionListener(e -> InsertService());
        JButton del = new JButton("Delete");
        del.addActionListener(e -> RemoveItem());
        add.setPreferredSize(new Dimension(200, 25));
        del.setPreferredSize(new Dimension(200, 25));
        JButton print = new JButton("Print"); //
        print.addActionListener(e -> PrintBill());

        top.add(ep); top.add(pidt);
        JSeparator vsep = new JSeparator(SwingConstants.VERTICAL); top.add(vsep);
        top.add(print);
        right.add(add); right.add(del);

        //Center table view
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Service");
        tableModel.addColumn("Price");
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

    private void InsertService(){

        ifr = new InvoiceForm();
        JPanel iform = ifr.inForm();

        bill_dialog = new JDialog(frame,"New Service", true);
        bill_dialog.setLayout(null);
        ImageIcon icon = new ImageIcon("src/gui/pictures/docura_logo.png");
        bill_dialog.setIconImage(icon.getImage());
        bill_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JButton save = new JButton("Save"), cancel = new JButton("Cancel");
        save.setBounds(427,200,77,25); cancel.setBounds(339,200,77,25);

        cancel.addActionListener(e -> bill_dialog.dispose());
        save.addActionListener(e -> NewItem());

        iform.add(save);
        iform.add(cancel);

        bill_dialog.add(iform);
        bill_dialog.setSize(551,300);
        bill_dialog.setResizable(false);
        bill_dialog.setLocationRelativeTo(null);
        bill_dialog.setVisible(true);

    }

    private void NewItem(){

        String id = pidt.getText();
        String s = ifr.getService();
        String p = ifr.getPrice();
        String q = ifr.getQuantity();

        if(!StringUtils.isEmptyOrWhitespaceOnly(id) && !StringUtils.isNullOrEmpty(id) &&
                !StringUtils.isEmptyOrWhitespaceOnly(s) && !StringUtils.isNullOrEmpty(s) &&
                !StringUtils.isEmptyOrWhitespaceOnly(p) && !StringUtils.isNullOrEmpty(p) &&
                !StringUtils.isEmptyOrWhitespaceOnly(q) && !StringUtils.isNullOrEmpty(q)) {

            new SaveBill(id, s, p, q);
            Object[] data = {s,p,q};
            tableModel.addRow(data);
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter all information!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }

    }
    private void RemoveItem(){

        int rowIdx = table.getSelectedRow();
        String id = pidt.getText();
        if(rowIdx == -1 && !StringUtils.isEmptyOrWhitespaceOnly(id) && !StringUtils.isNullOrEmpty(id)){
            JOptionPane.showMessageDialog(null, "Select a row!", "Message", JOptionPane.ERROR_MESSAGE, null);
        }
        else{
            int count = table.getColumnCount();
            String s = (String) tableModel.getValueAt(rowIdx, 0);
            String p = (String) tableModel.getValueAt(rowIdx, 1);
            String q = (String) tableModel.getValueAt(rowIdx, 2);
            tableModel.removeRow(rowIdx);
            new DeleteItem(id, s, p, q);
        }
    }

    private void PrintBill(){
        JOptionPane.showMessageDialog(null, "This feature is not developed yet!", "Message", JOptionPane.INFORMATION_MESSAGE, null);
    }
}
