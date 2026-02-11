package gui.mainpages;

import javax.swing.*;
import java.awt.*;

public class AdminSidebar {

    protected JButton sch, reg, bill, list;
    protected AdminSidebar(){

        sch = new JButton("Appointment Schedule");
        reg = new JButton("Patient Entry");
        bill = new JButton("Billing");
        list = new JButton("Doctor List and Schedule");

        sch.setPreferredSize(new Dimension(200, 35));
        reg.setPreferredSize(new Dimension(200, 35));
        bill.setPreferredSize(new Dimension(200, 35));
        list.setPreferredSize(new Dimension(200, 35));

        sch.setBackground(new Color(0xdddddd));
        reg.setBackground(new Color(0xdddddd));
        bill.setBackground(new Color(0xdddddd));
        list.setBackground(new Color(0xdddddd));
    }
}