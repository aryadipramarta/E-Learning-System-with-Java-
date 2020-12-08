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
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Microsoft
 */
public class HandlerAdminPanelSiswa extends MouseAdapter implements ActionListener {
    private GUI_AdminPanel_DataSiswa siswa;
    private DatabaseConnection db;
    
    public HandlerAdminPanelSiswa(){
        siswa = new GUI_AdminPanel_DataSiswa();
        db = new DatabaseConnection();
        db.loadStudent();
        loadTable();
        siswa.setVisible(true);
        siswa.addActionListener(this);
        siswa.addMouseAdapter(this);
    }
    public void loadTable(){
        DefaultTableModel model;
        model = new DefaultTableModel(new String[]{"nim","nama","kelamin","email","noHP","username","password"},0);
        ArrayList <Student> student = db.getStudent();
        for (Student s : student){
            model.addRow(new Object[]{s.getNim(),s.getNama(),s.getKelamin(),s.getEmail(),s.getNohp(),s.getUsername(),s.getPassword()});
        }
        siswa.setTbMahasiswa(model);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(siswa.getjHome())){
            HandlerAdminPanel admin = new HandlerAdminPanel();
            siswa.dispose();
        }
        if(source.equals(siswa.getjTentor())){
            HandlerAdminPanelTentorr tentor = new HandlerAdminPanelTentorr();
            siswa.dispose();
        }
        if(source.equals(siswa.getjMatakuliah())) {
            HandlerAdminPanelMataKuliah kuliah = new HandlerAdminPanelMataKuliah();
            siswa.dispose();
        }
        if(source.equals(siswa.getjKelas())){
            HandlerAdminPanelKelas kelas = new HandlerAdminPanelKelas();
            siswa.dispose();
        }
        if(source.equals(siswa.getjLogout())){
           HandlerLogin login = new HandlerLogin();
           siswa.dispose();
        }
    }
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(siswa.getTabelsiswa())){
            int i = siswa.getSelectedSiswa();
            String nama = siswa.getTabelsiswa().getModel().getValueAt(i, 0).toString();
            String nim = siswa.getTabelsiswa().getModel().getValueAt(i, 1).toString();
            String kelamin = siswa.getTabelsiswa().getModel().getValueAt(i, 2).toString();
            String email = siswa.getTabelsiswa().getModel().getValueAt(i, 3).toString();
            String Nohp = siswa.getTabelsiswa().getModel().getValueAt(i, 4).toString();
            String username = siswa.getTabelsiswa().getModel().getValueAt(i, 5).toString();
            String password = siswa.getTabelsiswa().getModel().getValueAt(i, 6).toString();
        }
    }
    
}
