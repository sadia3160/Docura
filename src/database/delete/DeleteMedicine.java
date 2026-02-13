package database.delete;

import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class DeleteMedicine {

    public DeleteMedicine(String id, String m, String q){

        int k = 0;
        ConSQL connection = new ConSQL();

        try {
            String query = "DELETE FROM Prescription WHERE user_id = '" +id+ "' and medicine = '" +m+ "'and quantity ='" +q+"'";
            PreparedStatement pst = connection.con.prepareStatement(query);
            k = pst.executeUpdate(); //executeQuery for Select

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (k > 0) {
            JOptionPane.showMessageDialog(null, "Data deleted successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);

        }

    }
}
