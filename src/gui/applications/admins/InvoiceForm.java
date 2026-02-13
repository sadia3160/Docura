package gui.applications.admins;

import javax.swing.*;
import java.awt.*;

public class InvoiceForm {

    JTextField serviceField, priceField, quanField;
    public JPanel inForm(){

        JPanel bill = new JPanel();
        bill.setLayout(null);
        bill.setBackground(new Color(0xdddddd));

        JLabel service = new JLabel("Service");
        service.setBounds(20, 60, 125, 30);
        serviceField = new JTextField();
        serviceField.setBounds(139, 63, 329, 30);

        JLabel price = new JLabel("Price");
        price.setBounds(20, 100, 125, 30);
        priceField = new JTextField();
        priceField.setBounds(139, 103, 329, 30);

        JLabel quan = new JLabel("Quantity");
        quan.setBounds(20, 140, 125, 30);
        quanField = new JTextField();
        quanField.setBounds(139, 143, 329, 30);

        bill.add(service);
        bill.add(serviceField);
        bill.add(price);
        bill.add(priceField);
        bill.add(quan);
        bill.add(quanField);

        bill.setSize(551, 300);

        return bill;
    }

    public String getService(){
        try {
            String sr = serviceField.getText();
            if(sr != "") return sr;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }

    public String getPrice(){
        try {
            String pr = priceField.getText();
            if(pr != "") return pr;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }

    public String getQuantity(){
        try {
            String qn = quanField.getText();
            if(qn != "") return qn;
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }

}
