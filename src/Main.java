import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 17);
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
        });
    }
}