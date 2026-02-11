package database.pull;

import database.ConSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PullAppointments {

    public JScrollPane Pull(String date) {

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setShowGrid(true);

        model.addColumn("Patient ID");
        model.addColumn("Appointment ID");
        model.addColumn("Appointment date");
        model.addColumn("Appointment time");
        model.addColumn("Doctor in charge");

        try {
            ConSQL dConnection = new ConSQL();
            String query = "SELECT * FROM AppointmentForm WHERE apnt_date = '" + date + "'";
            PreparedStatement pst = dConnection.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                Object[] rec = {a, b, c, d, e};
                model.addRow(rec);
            }
        } catch (Exception ev) {
            ev.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        return jsp;
        //Adding data records in a table, then returning the scrollpane
    }

    public JScrollPane Pull(String date, String name) {

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setShowGrid(true);

        model.addColumn("Patient ID");
        model.addColumn("Appointment ID");
        model.addColumn("Appointment date");
        model.addColumn("Appointment time");
        model.addColumn("Doctor in charge");

        try {
            ConSQL dConnection = new ConSQL();
            String query = "SELECT * FROM AppointmentForm WHERE apnt_date = '" + date + "' and doc_in_charge = '" + name + "'";
            PreparedStatement pst = dConnection.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                Object[] rec = {a, b, c, d, e};
                model.addRow(rec);
            }
        } catch (Exception ev) {
            ev.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        return jsp;
        //Adding data records in a table, then returning the scrollpane
    }
}
