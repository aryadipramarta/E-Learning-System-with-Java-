/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Microsoft
 */
public class HandlerAdminPanelTentorr extends MouseAdapter implements ActionListener {
    GUI_AdminPanel_DataTentorr tentor;
    private static int sid = 0;
    Application model;
    
    public HandlerAdminPanelTentorr(){
        tentor = new GUI_AdminPanel_DataTentorr();
        tentor.setVisible(true);
        tentor.addActionListener(this);
        tentor.ResetView();
        model = new Application();
        model.loadTentor();
        sid = model.getDaftarTentor().size();
        tentor.setTentorID(sid);
        loadTableTentor();
    }
    
     public void loadTableTentor(){
        DefaultTableModel models;
        models = new DefaultTableModel(new String[]{"idtentor","Nama","Jenis_kelamin","Email","No_HP","Lama_Kerja","Username","Password"},0);
        List <Tentor> T = model.getDaftarTentor();
        for (Tentor t : T){
            models.addRow(new Object[]{t.getTentor_ID(),t.getNama(),t.getKelamin(),t.getEmail(),t.getNohp(),t.getLamakerja(),t.getUsername(),t.getPassword()});
        }
        tentor.setTabletentor(models);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source.equals(tentor.getjHome())){
            HandlerAdminPanel admin = new HandlerAdminPanel();
            tentor.dispose();
        }
        if(source.equals(tentor.getjSiswa())){
            HandlerAdminPanelSiswa siswa = new HandlerAdminPanelSiswa();
            tentor.dispose();
        }
        if(source.equals(tentor.getjMatakuliah())){
            HandlerAdminPanelMataKuliah kuliah = new HandlerAdminPanelMataKuliah();
            tentor.dispose();
        }
        if(source.equals(tentor.getjKelas())){
            HandlerAdminPanelKelas kelas = new HandlerAdminPanelKelas();
            tentor.dispose();
        }
        if(source.equals(tentor.getjLogout())){
           HandlerLogin login = new HandlerLogin();
           tentor.dispose();
        }
        if(source.equals(tentor.getjTambah())){
                if (true) {
                try{
                    String Tentor_ID = tentor.getIdtentor().getText();
                    String nama = tentor.getTnama().getText();
                    String kelamin;
                    if(tentor.getJrbLaki().isSelected()){
                        kelamin = "laki-laki";
                    }else{
                        kelamin = "perempuan";
                    }
                    String email = tentor.getJtemail().getText();
                    String nohp = tentor.getJtnohp().getText();
                    int lamakerja = Integer.parseInt(tentor.getCblamakerja().getSelectedItem().toString());
                    String username =tentor.getJusername().getText();
                    String password = tentor.getJpassword().getText();
                    Tentor T = new Tentor(Tentor_ID,nama,kelamin,email,nohp,lamakerja,username,password);
                    model.addTentor(T);
                    tentor.showMessage("BERHASIL TAMBAH TENTOR", "SUCCESS", 1);
                }catch(Exception e){
                    System.out.println(e);
                }
                tentor.ResetView();
                model.loadTentor();
                sid = model.getDaftarTentor().size();
                tentor.setTentorID(sid);
            }
        }
    }
    
}
