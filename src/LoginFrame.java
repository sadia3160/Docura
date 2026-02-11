import database.ConSQL;
import gui.mainpages.AdminMain;
import gui.mainpages.DoctorMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame implements ActionListener {

    JFrame frame;
    JLabel user_text, pass_text;
    JTextField user_input; JPasswordField password_input;
    JButton login, reset;

    public LoginFrame(){

        frame = new JFrame("Docura System Login");
        frame.getContentPane().setBackground(new Color(0xe2e8ef));

        ImageIcon icon = new ImageIcon("src/gui/pictures/docura_logo.png");
        frame.setIconImage(icon.getImage());

        user_text = new JLabel("User ID");
        pass_text = new JLabel("Password");

        user_text.setBounds(35,50,100,25);
        pass_text.setBounds(35,85,100,50);

        user_input = new JTextField();
        password_input = new JPasswordField();

        user_input.setBounds(145,50,270,25);
        password_input.setBounds(145,100,270,25);

        login = new JButton("Login");
        reset = new JButton("Reset");

        login.setBounds(145, 150, 75, 25);
        reset.setBounds(250, 150, 75, 25);

        login.setFocusable(false);
        reset.setFocusable(false);

        login.setBackground(new Color(0x4682b4));
        login.setForeground(Color.WHITE);
        reset.setBackground(Color.LIGHT_GRAY);

        login.addActionListener(this);
        reset.addActionListener(this);

        frame.add(user_text);
        frame.add(pass_text);
        frame.add(user_input);
        frame.add(password_input);
        frame.add(login);
        frame.add(reset);

        frame.setSize(470,270);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user_id = user_input.getText();
        String pass = password_input.getText();

        if(e.getSource() == login){

            if(user_id.equals("")){
                JOptionPane message1 = new JOptionPane();
                message1.showMessageDialog(null, "Enter User ID!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(pass.equals("")){
                JOptionPane message2 = new JOptionPane();
                message2.showMessageDialog(null, "Enter Password!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                try{
                    ConSQL cs = new ConSQL();
                    String query = "SELECT * FROM login WHERE user_id = \"" + user_id + "\" AND user_pass = \"" +pass+ "\"";
                    PreparedStatement pst = cs.con.prepareStatement(query); //Created Statement
                    ResultSet rs = pst.executeQuery(); //Executed Queries

                    if(rs.next()){
                        frame.dispose();
                        String role = rs.getString(3);

                        if(role.equals("Admin")){
                            new AdminMain(user_id);
                        }
                        else if(role.equals("Doctor")){
                            new DoctorMain(user_id);
                        }
                    }
                    else{
                        JOptionPane message3 = new JOptionPane();
                        message3.showMessageDialog(null, "Invalid User ID or Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getSource() == reset){
            user_input.setText("");
            password_input.setText("");
        }
    }
}