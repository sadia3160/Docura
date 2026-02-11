package database.pull;

import database.ConSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PullDoctors {

    public JScrollPane PullDoc(String dept) {

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setShowGrid(true);

        model.addColumn("Department Name");
        model.addColumn("Doctor Name");
        model.addColumn("Qualification");
        model.addColumn("Time Slot");
        model.addColumn("Contact");

        try {
            ConSQL dConnection = new ConSQL();
            String query = "SELECT * FROM DoctorList WHERE dept_name = '" + dept + "'";
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
