/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Application;
import Model.DatabaseConnection;
import Model.Kelas;
import Model.Materi;
import Model.Student;
import Model.Tentor;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Microsoft
 */
public class HandlerTentorPanel implements ActionListener {
      private GUI_TentorPanel tentor;
      private DatabaseConnection db;
      private Application app;
    
    public HandlerTentorPanel(){
        tentor = new GUI_TentorPanel(UserData());
        tentor.setVisible(true);
        tentor.addActionListener(this);
        app = new Application();
        db = new DatabaseConnection();
        db.connect();
        kelascbfilldata(UserData());
        loadTable();
    }
    
    public void loadTable(){
        DefaultTableModel model;
        model = new DefaultTableModel(new String[]{"Id Materi","Nama Materi","Bagian"},0);
        Tentor T = UserData();
        ArrayList <Materi> materi = db.loadMateriTentor(T.getTentor_ID());
        for (Materi m : materi){
            model.addRow(new Object[]{m.getKodemateri(),m.getNamamateri(),m.getBagian()});
        }
        tentor.setJtablemateri(model);
    }
    public Tentor UserData () {
        Tentor t = null;
        try{
            String databaseURL = "jdbc:mysql://localhost:3306/db_elearning";
            Connection con = DriverManager.getConnection(databaseURL, "root", "");
            String sql = "Select * from tentortable where Username= '" + HandlerLogin.username + "';";
            System.out.println("check query : " + sql);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                t = new Tentor(rs.getString("ID_Tentor"),rs.getString("Nama"),rs.getString("Jenis_Kelamin"),rs.getString("Email"),rs.getString("No_HP"),rs.getInt("Lama_Kerja"),rs.getString("Username"),rs.getString("Password"));
            }
            return t;
        }catch(SQLException se){
            System.out.println("gagal fetch Tentor : "+se);
        }
        return t;
    }
    
    public void kelascbfilldata(Tentor t){
         try{
             Class.forName("com.mysql.jdbc.Driver");
             String databaseURL = "jdbc:mysql://localhost:3306/db_elearning";
             Connection con = DriverManager.getConnection(databaseURL,"root","");
             Statement stat = con.createStatement();
             String selectQuery = "select kodekelas from kelas where tentor_id = '" 
                    + t.getTentor_ID()+"';" ;
             System.out.println(selectQuery);
             ResultSet rs = stat.executeQuery(selectQuery);
             while(rs.next()){
                 tentor.getJcbkelastentor().addItem(rs.getString("kodekelas"));
             }
         }
         catch(Exception e){
             System.out.println(e);
         }
     }
    
    
    public void setStudentTable(String kodekelas) {
        ArrayList<Student> dataStudent = new ArrayList<>();
        dataStudent = db.loadStudentFromKelas(kodekelas);
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "nim", 
            "nama", 
            "kelamin", 
            "email"
        }, 0);
        for (Student m : dataStudent) {
            model.addRow(new Object[]{
                m.getNim(),
                m.getNama(),
                m.getKelamin(),
                m.getEmail()
            });
        }
        tentor.setjtabledaftarsiswa(model);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        Tentor t;
        if(source.equals(tentor.getBtnlogout())){
           HandlerLogin login = new HandlerLogin();
           tentor.dispose();
        }
        if(source.equals(tentor.getUbahbutton())){
            String namatentor = tentor.getJtnama();
            String email = tentor.getJtemail();
            String nohp = tentor.getJtnohp();
            if(namatentor.isEmpty() || email.isEmpty() || nohp.isEmpty()){
                System.out.println("Empty Data");
            }else{
                try {
                    t = new Tentor(namatentor,email,nohp);
                    db.updatedatatentor(t);
                    tentor.showMessage("Update Data Diri Berhasil", "Success", 1);
                }catch (Exception e ){
                    JOptionPane.showMessageDialog(null, "Gagal update data!");
                }
                
            }
        }
        if(source.equals(tentor.getjbCari())){
            System.out.println("Berhasil!");
            setStudentTable(tentor.getJcbkelastentor().getSelectedItem().toString());
        }
        if(source.equals(tentor.getJbtambah())){
            ArrayList<Kelas> kelasTentor = new ArrayList<>();
            ArrayList<String> kodeKelas = new ArrayList<>();
            if(true){
                String idmateri = tentor.getJtfidmateri().getText();
                String namamateri = tentor.getJtfnamateri().getText();
                int bagian = Integer.parseInt(tentor.getJcbbagian().getSelectedItem().toString());
                Materi materi = new Materi(idmateri,namamateri,bagian);
                Tentor T = app.searchObjectTentorByID(db.searchIdTentor());
                kodeKelas = db.searchKodeKelas(db.searchIdTentor());
                for (String a : kodeKelas) {
                    kelasTentor.add( app.searchKelas(a) );
                }
                if (T != null && kelasTentor.isEmpty() == false) {
                    for (Kelas kelas : kelasTentor  ) {
                        try{
                            app.addMateri(materi, T, kelas);
                        }catch(Exception e) {
                            System.out.println("Gagal menambahkan materi");
                        }
                    }
                    tentor.showMessage("Berhasil Menambahkan Materi", "SUCCESS", 1);
                }else {
                    System.out.println("Error salah satu null/kosong");
                }
                tentor.ResetView();
            }
            
        }
        if(source.equals(tentor.getJbupdate())){
            String idmateri = tentor.getJtfidmateri().getText();
            String namamateri = tentor.getJtfnamateri().getText();
            int bagian = Integer.parseInt(tentor.getJcbbagian().getSelectedItem().toString());
            if(idmateri.isEmpty() && namamateri.isEmpty()){
                System.out.println("Data Kosong");
            }else{
                System.out.println("Update");
                Materi M = new Materi(idmateri,namamateri,bagian);
                db.updatemateri(M);
                tentor.ResetView();
                tentor.showMessage("UPDATE DATA BERHASIL", "UPDATED", 1);
            }
        }
        if(source.equals(tentor.getJbhapus())){
            String kodemateri = tentor.getJtfidmateri().getText();
            db.delMateri(kodemateri);
            System.out.println("Hapus");
            tentor.ResetView();
            tentor.showMessage("DELETE BERHASIL", "SUCCESS", 1);
        }
    }
}