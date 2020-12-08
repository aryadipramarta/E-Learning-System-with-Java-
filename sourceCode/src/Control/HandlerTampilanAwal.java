/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Model.*;
import View.GUI_TampilanAwal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Microsoft
 */
public class HandlerTampilanAwal implements ActionListener{
    private GUI_TampilanAwal tampilan;
    
    public HandlerTampilanAwal(){
        tampilan = new GUI_TampilanAwal();
        tampilan.setVisible(true);
        tampilan.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(tampilan.getButtonToLogin())){
            HandlerLogin log = new HandlerLogin();
            tampilan.dispose();
        }
    }
    
}
