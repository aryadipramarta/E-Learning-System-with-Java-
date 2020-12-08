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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Microsoft
 */
public class HandlerStudentPanel implements ActionListener {
    private GUI_StudentPanel student;
    private DatabaseConnection db;
    
    public HandlerStudentPanel(){
        student = new GUI_StudentPanel(UserData());
        student.setVisible(true);
        student.addActionListener(this);
        db = new DatabaseConnection();
        db.connect();
    }

    public Student UserData () {
        Student siswa = null;
        try{
            String databaseURL = "jdbc:mysql://localhost:3306/db_elearning";
            Connection con = DriverManager.getConnection(databaseURL, "root", "");
            String sql = "Select * from student where username= '" + HandlerLogin.username + "';";
            System.out.println("check query : " + sql);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                siswa = new Student(rs.getString("nim"),rs.getString("nama"),rs.getString("kelamin"),rs.getString("email"),rs.getString("noHP"),rs.getString("username"),rs.getString("password"));
            }
            return siswa;
        }catch(SQLException se){
            System.out.println("gagal fetch mahasiswa : "+se);
        }
        return siswa;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        Student siswa;
        if(source.equals(student.getBtnlogout())){
           HandlerLogin login = new HandlerLogin();
           student.dispose();
        }
        if(source.equals(student.getJbregistrasi())){
            String kelas = student.getSelectedKelas();
            String nim = student.getCurrentData().getNim();
            try{
            String databaseURL = "jdbc:mysql://localhost:3306/db_elearning";
            Connection con = DriverManager.getConnection(databaseURL, "root", "");
            String query ="insert into kelas_student (nim,kodekelas) values " + "('"+nim+"','"+kelas+"');";
            System.out.println(query);
            Statement s = con.createStatement();
            s.execute(query);
            student.showMessage("Registrasi Berhasil", "SUCCESS", 1);
            }catch(SQLException se){
                System.out.println(se);
            }   
        }
        if(source.equals(student.getButtonubah())){
            String namasiswa = student.getJtfnamasiswa().getText();
            String email = student.getJtfemail().getText();
            String nohp = student.getJtfnohp().getText();
            if(namasiswa.isEmpty() || email.isEmpty() || nohp.isEmpty()){
                System.out.println("Empty Data");
            }else{
                try{
                    siswa = new Student(namasiswa,email,nohp);
                    db.updatedatasiswa(siswa);
                    student.showMessage("UPDATE DATA DIRI BERHASIL", "UPDATED", 1);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}
