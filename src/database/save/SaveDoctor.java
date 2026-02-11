package database.save;

import com.mysql.cj.util.StringUtils;
import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class SaveDoctor {

    public SaveDoctor(JDialog dialog, String dpt, String dnm, String qul, String tslt, String con){

        if(!StringUtils.isEmptyOrWhitespaceOnly(dpt) && !StringUtils.isEmptyOrWhitespaceOnly(dnm) &&
                !StringUtils.isEmptyOrWhitespaceOnly(qul) && !StringUtils.isEmptyOrWhitespaceOnly(tslt) &&
                !StringUtils.isEmptyOrWhitespaceOnly(con)){
            int k = 0;
            ConSQL connection = new ConSQL();

            try {
                String query = "INSERT INTO DoctorList (dept_name, doctor_name, qualification, time_slot, contact) VALUES (?,?,?,?,?)";
                PreparedStatement pst = connection.con.prepareStatement(query);
                pst.setString(1, dpt);
                pst.setString(2, dnm);
                pst.setString(3, qul);
                pst.setString(4, tslt);
                pst.setString(5, con);;
                k = pst.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (k > 0) {
                JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);
                dialog.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter All Data", "Error", JOptionPane.ERROR_MESSAGE, null);}
    }
}
