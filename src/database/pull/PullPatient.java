package database.pull;

import database.ConSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PullPatient {

    public JScrollPane Pulp(String id) {

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setShowGrid(true);

        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        model.addColumn("Birth Date");
        model.addColumn("Blood Group");
        model.addColumn("Gender");
        model.addColumn("Patient Phone");
        model.addColumn("Guardian Phone");
        model.addColumn("Address");
        model.addColumn("Profession");
        model.addColumn("Notes");
        model.addColumn("Type");
        model.addColumn("Ward");
        model.addColumn("Room");


        try {
            ConSQL dConnection = new ConSQL();
            String query = "SELECT * FROM PatientForm WHERE user_id = '" + id + "'";
            PreparedStatement pst = dConnection.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

//
            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                String f = rs.getString(6);
                String g = rs.getString(7);
                String h = rs.getString(8);
                String i = rs.getString(9);
                String j = rs.getString(10);
                String k = rs.getString(11);
                String l = rs.getString(12);
                String m = rs.getString(13);
                Object[] rec = {a, b, c, d, e, f, g, h, i, j, k, l, m};
                model.addRow(rec);
            }
            if(table.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "No Patient Found!", "Error", JOptionPane.ERROR_MESSAGE, null);
            }

        } catch (Exception ev) {
            ev.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        return jsp;
    }
}

//exception handling, refreshing table, error message
