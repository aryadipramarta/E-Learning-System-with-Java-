/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Model.*;
import View.GUI_AdminPanel;
import View.GUI_AdminPanel_DataKelas;
import View.GUI_AdminPanel_DataMataKuliah;
import View.GUI_AdminPanel_DataTentorr;
import View.GUI_AdminPanel_DataSiswa;
import View.GUI_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Microsoft
 */
public class HandlerAdminPanel implements ActionListener{
    private GUI_AdminPanel admin;
    
    public HandlerAdminPanel(){
        admin = new GUI_AdminPanel();
        admin.setVisible(true);
        admin.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(admin.getDataKelasButton())){
            HandlerAdminPanelKelas kelas = new HandlerAdminPanelKelas();
            admin.dispose();
        }
        if(source.equals(admin.getDataTentorButton())){
            HandlerAdminPanelTentorr tentor = new HandlerAdminPanelTentorr();
            admin.dispose();
        }
        if(source.equals(admin.getDataMataKuliahButton())){
            HandlerAdminPanelMataKuliah kuliah = new HandlerAdminPanelMataKuliah();
            admin.dispose();
        }
        if(source.equals(admin.getDataSiswaButton())){
            HandlerAdminPanelSiswa siswa = new HandlerAdminPanelSiswa();
            admin.dispose();
        }
        if(source.equals(admin.getjLogout())){
           HandlerLogin login = new HandlerLogin();
           admin.dispose();
        }
    }
}
