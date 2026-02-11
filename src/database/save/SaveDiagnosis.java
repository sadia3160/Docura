package database.save;

import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class SaveDiagnosis {

    public SaveDiagnosis(String id, String note) {
        int k = 0;
        ConSQL dbConnect = new ConSQL();
        try {
            String que = "INSERT INTO PatientDiagnosis (user_id, doc_notes) VALUES (?,?)";
            PreparedStatement pst = dbConnect.con.prepareStatement(que);
            pst.setString(1, id);
            pst.setString(2, note);
            k = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (k > 0) {
            JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);

        } else {
            JOptionPane.showMessageDialog(null, "Enter All Data", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}


