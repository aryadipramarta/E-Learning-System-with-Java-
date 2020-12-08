/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.*;
import View.GUI_TampilanAwal;
import View.GUI_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Microsoft
 */
public class HandlerLogin implements ActionListener {
    private GUI_Login login;
    public static String username;

    public HandlerLogin() {
        login = new GUI_Login();
        login.setVisible(true);
        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(login.getjButtonLogin())) {
            String username = login.getjTextUserName().getText();
            String password = login.getjPasswordlogin().getText();
            String tipeakun = login.getPilihAkun().getSelectedItem().toString();
            if (tipeakun == "STUDENT") {
                if((username.equals("admin")) && (password.equals("admin"))){
                this.username = "admin";
                HandlerAdminPanel adminpanel = new HandlerAdminPanel();
                login.dispose();
                }
                else{
                try {
                    String databaseURL = "jdbc:mysql://localhost:3306/db_elearning";
                    Connection con = DriverManager.getConnection(databaseURL, "root", "");
                    String sql = "Select * from student where username=? and password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, login.getjTextUserName().getText());
                    pst.setString(2, login.getjPasswordlogin().getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        this.username = login.getjTextUserName().getText();
                        HandlerStudentPanel studentpanel = new HandlerStudentPanel();
                        login.dispose();
                    }else{
                        login.showMessage("LOGIN GAGAL COBA CEK KEMBALI", "FAILED", 0);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            }
            else if(tipeakun == "TENTOR"){
                try {
                    String databaseURL = "jdbc:mysql://localhost:3306/db_elearning";
                    Connection con = DriverManager.getConnection(databaseURL, "root", "");
                    String sql = "Select * from tentortable where username=? and password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, login.getjTextUserName().getText());
                    pst.setString(2, login.getjPasswordlogin().getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        this.username = login.getjTextUserName().getText();
                        HandlerTentorPanel tentorpanel = new HandlerTentorPanel();
                        login.dispose();
                    }else{
                        login.showMessage("LOGIN GAGAL COBA CEK KEMBALI", "FAILED", 0);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}