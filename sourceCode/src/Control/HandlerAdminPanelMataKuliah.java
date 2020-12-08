/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.event.ActionListener;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Microsoft
 */
public class HandlerAdminPanelMataKuliah extends MouseAdapter implements ActionListener {
    private GUI_AdminPanel_DataMataKuliah kuliah;
    Application model;
    private DatabaseConnection db;
    
    public HandlerAdminPanelMataKuliah(){
        kuliah = new GUI_AdminPanel_DataMataKuliah();
        db = new DatabaseConnection();
        kuliah.setVisible(true);
        kuliah.addActionListener(this);
        kuliah.addMouseAdapter(this);
        db.loadMatkul();
        loadTable();
        kuliah.resetview();
        model = new Application();
    }
     public void loadTable(){
        DefaultTableModel model;
        model = new DefaultTableModel(new String[]{"Kode Mata Pelajaran","Nama Mata Pelajaran"},0);
        ArrayList <MataPelajaran> mapel = db.getMapel();
        for (MataPelajaran mp : mapel){
            model.addRow(new Object[]{mp.getKodemapel(),mp.getNamamapel()});
        }
        kuliah.setJtablematkul(model);
    }
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(kuliah.getJtablematkul())){
            int i = kuliah.getselectedmatkul();
            String kodematapelajaran = kuliah.getJtablematkul().getModel().getValueAt(i, 0).toString();
            String namamatapelajaran = kuliah.getJtablematkul().getModel().getValueAt(i,1).toString();
            
            kuliah.setTfnamamatapelajaran(namamatapelajaran);
            kuliah.setTxkodematapelajaran(kodematapelajaran);
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(kuliah.getjHome())){
            HandlerAdminPanel admin = new HandlerAdminPanel();
            kuliah.dispose();
        }
        if(source.equals(kuliah.getjDataTentor())){
            HandlerAdminPanelTentorr tentor = new HandlerAdminPanelTentorr(); 
            kuliah.dispose();
        }
        if(source.equals(kuliah.getjDataSiswa())){
            HandlerAdminPanelSiswa siswa = new HandlerAdminPanelSiswa();
            kuliah.dispose();
        }
        if(source.equals(kuliah.getjDataKelas())){
            HandlerAdminPanelKelas kelas = new HandlerAdminPanelKelas();
            kuliah.dispose();
        }
        if(source.equals(kuliah.getjLogout())){
           HandlerLogin login = new HandlerLogin();
           kuliah.dispose();
        }
        if(source.equals(kuliah.getJbtambah())){
            if(true){
                try{
                    String kodemapel = kuliah.getTxkodematapelajaran();
                    String namamapel = kuliah.getTfnamamatapelajaran();
                    MataPelajaran mp = new MataPelajaran(kodemapel,namamapel);
                    model.addMataPelajaran(mp);
                    kuliah.showMessage("BERHASIL TAMBAH MATA PELAJARAN", "SUCCESS", 1);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            kuliah.resetview();
        }
        if(source.equals(kuliah.getJbhapus())){
            System.out.println("berhasil");
            String kodematkul = kuliah.getTxkodematapelajaran();
            db.delMatkul(kodematkul);
            kuliah.resetview();
            kuliah.showMessage("Data Berhasil Dihapus","Success",1);
        }
        if(source.equals(kuliah.getJbupdate())){
            String kodematkul = kuliah.getTxkodematapelajaran();
            String namamatkul = kuliah.getTfnamamatapelajaran();
            if(kodematkul.isEmpty() || namamatkul.isEmpty()){
                kuliah.showMessage("Data Kosong", "Error", 0);
            }else{
                db.updateMatkul(new MataPelajaran(kodematkul,namamatkul));
                kuliah.resetview();
                kuliah.showMessage("Data Berhasil DiUpdate", "Success", 1);
            }
        }
    }
    
}
