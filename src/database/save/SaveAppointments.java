package database.save;

import com.mysql.cj.util.StringUtils;
import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class SaveAppointments {

    public SaveAppointments(JDialog dialog1, String pid, String aid, String adate, String atime, String docInC){

        if(!StringUtils.isEmptyOrWhitespaceOnly(pid) && !StringUtils.isEmptyOrWhitespaceOnly(aid) &&
                !StringUtils.isNullOrEmpty(pid) && !StringUtils.isNullOrEmpty(aid)) {
            int k = 0;
            ConSQL dbConnect = new ConSQL();
            try {
                String que = "INSERT INTO AppointmentForm (user_id, apnt_id, apnt_date, apnt_time, doc_in_charge) VALUES (?,?,?,?,?)";
                PreparedStatement pst = dbConnect.con.prepareStatement(que);
                pst.setString(1, pid);
                pst.setString(2, aid);
                pst.setString(3, adate);
                pst.setString(4, atime);
                pst.setString(5, docInC);
                k = pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (k > 0) {
                JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);
                dialog1.dispose();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter All Data", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
