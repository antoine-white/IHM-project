package ihm.Components;

import javax.swing.*;
import ihm.consts.*;

/**
 * All button in this application should 
 * inherit from it 
 */
public class CustomButton extends JButton {

    public CustomButton(String str) {
       super(str);
       this.setFont(ConstFonts.DEFAULT);
       this.setBackground(ConstColors.BACKGROUND_BUTTON);
       this.setForeground(ConstColors.TEXT);
       this.setBorder(BorderFactory.createLineBorder(ConstColors.BUTTON_BORDER));
    }

    public CustomButton(){
        this("");
    }

    

}