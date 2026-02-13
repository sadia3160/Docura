package database.save;

import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class SaveBill {

    public  SaveBill(String id, String s, String p, String q){
        int k = 0;
        ConSQL connection = new ConSQL();

        try {
            String query = "INSERT INTO Bill(user_id, service, price, quantity) VALUES (?,?,?,?)";
            PreparedStatement pst = connection.con.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, p);
            pst.setString(4, q);
            k = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (k > 0) {
            JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);

        }
    }
}
