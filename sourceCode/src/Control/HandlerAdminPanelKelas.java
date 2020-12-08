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
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Microsoft
 */
public class HandlerAdminPanelKelas implements ActionListener{
    private GUI_AdminPanel_DataKelas kelas;
    Application model;
    private DatabaseConnection db;

    public HandlerAdminPanelKelas(){
        kelas = new GUI_AdminPanel_DataKelas();
        kelas.setVisible(true);
        kelas.addActionListener(this);
        db = new DatabaseConnection();
        model = new Application();
    }
    public void loadTablekelastentor(){
        DefaultTableModel models;
        models = new DefaultTableModel(new String[]{"Kode Kelas","Tentor ID"},0);
        ArrayList <Kelas> kelass = db.getKelas();
        for (Kelas k : kelass){
            models.addRow(new Object[]{k.getKodekelas(),k.getJam()});
        }
        kelas.setJtablekelastentor(models);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(kelas.getjHome())){
            HandlerAdminPanel admin = new HandlerAdminPanel();
            kelas.dispose();
        }
        if(source.equals(kelas.getjMatakuliah())){
            HandlerAdminPanelMataKuliah kuliah = new HandlerAdminPanelMataKuliah();
            kelas.dispose();
        }
        if(source.equals(kelas.getjSiswa())){
            HandlerAdminPanelSiswa siswa = new HandlerAdminPanelSiswa();
            kelas.dispose();
        }
        if(source.equals(kelas.getjTentor())){
            HandlerAdminPanelTentorr tentor = new HandlerAdminPanelTentorr();
            kelas.dispose();
        }
        if(source.equals(kelas.getjLogout())){
           HandlerLogin login = new HandlerLogin();
           kelas.dispose();
        }
        if(source.equals(kelas.getJbtambah())){
            if(true){
                try{
                    String kodekelas = kelas.getJtfkodekelas().getText();
                    String jam = kelas.getJcbjam().getSelectedItem().toString();
                    String namakelas = kelas.getJtfNamakelas().getText();
                    String namamapel = kelas.getJcbmapel().getSelectedItem().toString();
                    String namatentor = kelas.getJcbtentor().getSelectedItem().toString();
                    MataPelajaran mp = model.searchmapel(namamapel);
                    Tentor t = model.searchObjectTentor(namatentor);
                    if (t != null && mp != null ) {
                        Kelas K = new Kelas(mp,kodekelas,jam,namakelas);
                        t.createKelas(K);
                        model.addKelas(K, t);
                        kelas.showMessage("KELAS BERHASIL DITAMBAH", "SUCCESS", 1);
                    }else {
                        JOptionPane.showMessageDialog(null,"Masukan data dengan benar!");
                    }
                    
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        if(source.equals(kelas.getJbupdate())){
            String kodekelas = kelas.getJtfkodekelas().getText();
            String namakelas = kelas.getJtfNamakelas().getText();
            String matapelajaran = kelas.getJcbmapel().getSelectedItem().toString();
            String jam = kelas.getJcbjam().getSelectedItem().toString();
            String tentor_id = kelas.getJcbtentor().getSelectedItem().toString();
            if(kodekelas.isEmpty() || namakelas.isEmpty()|| matapelajaran.isEmpty() || jam.isEmpty() || tentor_id.isEmpty()){
            
            }else{
                MataPelajaran mapel = new MataPelajaran(matapelajaran);
                db.updateKelas(new Kelas(mapel,kodekelas,jam,namakelas));
                kelas.showMessage("Data Berhasil Diupdate", "Success", 1);
                kelas.resetview();
            }
        }
        if(source.equals(kelas.getJbhapus())){
            String kodekelas = kelas.getJtfkodekelas().getText();
            db.delKelas(kodekelas);
            kelas.resetview();
            kelas.showMessage("Data Berhasil Dihapus", "Success", 1);
        }
    }
}
