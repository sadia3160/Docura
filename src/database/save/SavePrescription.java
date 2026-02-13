package database.save;

import com.mysql.cj.util.StringUtils;
import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class SavePrescription {

    public SavePrescription(String id, String medicine, String quantity) {

        int k = 0;
        ConSQL connection = new ConSQL();

        try {
            String query = "INSERT INTO Prescription (user_id, medicine, quantity) VALUES (?,?,?)";
            PreparedStatement pst = connection.con.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, medicine);
            pst.setString(3, quantity);
            k = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (k > 0) {
            JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);

        }
    }

}
