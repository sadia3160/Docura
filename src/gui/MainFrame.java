package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    public JFrame frame;
    public JPanel side_bar, top_panel, center_panel;

    public MainFrame(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout(3,3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("src/gui/pictures/docura_logo.png");
        frame.setIconImage(icon.getImage());

        FramePanels();

        frame.add(top_panel, BorderLayout.NORTH);
        frame.add(side_bar, BorderLayout.WEST);
        frame.add(center_panel, BorderLayout.CENTER);

        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screen_size.width, screen_size.height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void FramePanels(){

        top_panel = new JPanel();
        side_bar = new JPanel();
        center_panel = new JPanel();

        top_panel.setPreferredSize(new Dimension(150,150));
        side_bar.setPreferredSize(new Dimension(200,200));

        center_panel.setLayout(new BorderLayout());
        top_panel.setLayout(null);

        JButton logOut = new JButton("Log Out");
        logOut.setBounds(1411,25,91, 25);
        top_panel.add(logOut);
        logOut.addActionListener(e->lg());
    }
    private void lg(){
        frame.dispose();
    }
}
