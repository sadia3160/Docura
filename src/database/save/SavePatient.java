package database.save;

import com.mysql.cj.util.StringUtils;
import database.ConSQL;

import javax.swing.*;
import java.sql.PreparedStatement;

public class SavePatient {

    public SavePatient(JDialog dialog, String pid, String pnm, String bdt, String bgrp, String gndr, String pphn, String gphn, String addr, String prf, String w, String r, String ptype, String notes){

        if(!StringUtils.isEmptyOrWhitespaceOnly(pid) && !StringUtils.isEmptyOrWhitespaceOnly(pnm) &&
                !StringUtils.isEmptyOrWhitespaceOnly(bdt) && !StringUtils.isEmptyOrWhitespaceOnly(bgrp) &&
                !StringUtils.isEmptyOrWhitespaceOnly(gndr) && !StringUtils.isEmptyOrWhitespaceOnly(pphn) &&
                !StringUtils.isEmptyOrWhitespaceOnly(gphn) && !StringUtils.isEmptyOrWhitespaceOnly(addr) &&
                !StringUtils.isEmptyOrWhitespaceOnly(prf) && !StringUtils.isEmptyOrWhitespaceOnly(w) &&
                !StringUtils.isEmptyOrWhitespaceOnly(r) && !StringUtils.isEmptyOrWhitespaceOnly(ptype) &&
                !StringUtils.isEmptyOrWhitespaceOnly(notes)) {

            int k = 0;
            ConSQL connection = new ConSQL();

            try {
                String query = "INSERT INTO PatientForm (user_id,pat_name,birth_date,blood_group,gender,patient_phone,guardian_phone,address,profession,notes,patient_type,ward,room) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = connection.con.prepareStatement(query);
                pst.setString(1, pid);
                pst.setString(2, pnm);
                pst.setString(3, bdt);
                pst.setString(4, bgrp);
                pst.setString(5, gndr);
                pst.setString(6, pphn);
                pst.setString(7, gphn);
                pst.setString(8, addr);
                pst.setString(9, prf);
                pst.setString(10, notes);
                pst.setString(11, ptype);
                pst.setString(12, w);
                pst.setString(13, r);
                k = pst.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (k > 0) {
                JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, null);
                dialog.dispose();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter All Data", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}

