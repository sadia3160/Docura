package database.pull;

import database.ConSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PullDiagnosis {
    public JScrollPane PullDig(String id) {

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setShowGrid(true);
        model.addColumn("Previous Diagnosis");

        try {
            ConSQL dConnection = new ConSQL();
            String query = "SELECT * FROM PatientDiagnosis WHERE user_id = '" + id + "'";
            PreparedStatement pst = dConnection.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                Object[] rec = {b};
                model.addRow(rec);
            }
        } catch (Exception ev) {
            ev.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setPreferredSize(new Dimension(200,200));
        return jsp;
    }
}
