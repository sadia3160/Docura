package database.delete;

import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class DeleteItem {

    public DeleteItem(String id, String s, String p, String q){
        int k = 0;
        ConSQL connection = new ConSQL();
          //'" + id + "'"
        try {
            String query = "DELETE FROM Bill WHERE user_id = '" +id+ "' and service = '" +s+ "' and price = '" +p+ "'and quantity ='" +q+"'";
            PreparedStatement pst = connection.con.prepareStatement(query);
            k = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (k > 0) {
            JOptionPane.showMessageDialog(null, "Data deleted successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);

        }
    }
}
