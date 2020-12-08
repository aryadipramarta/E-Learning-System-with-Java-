/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Control.HandlerLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Microsoft
 */
public class DatabaseConnection {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private static ArrayList<Tentor> tentor = new ArrayList<>();
    private static ArrayList<Student> student = new ArrayList<>();
    private static ArrayList<MataPelajaran> mapel = new ArrayList<>();
    private static ArrayList<Kelas> kelas = new ArrayList<>();
    private static ArrayList<Materi> materi = new ArrayList<>();
    
    public void connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/db_elearning";
            String username="root";
            String password = "";
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database.");
            stmt = con.createStatement();
        }catch(SQLException se){
            System.out.println("Connection error.");
        }
    }
    
    public void disconnect(){
        try{
            con.close();
            stmt.close();
        }catch(SQLException ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public void tambahMataPelajaran(MataPelajaran mp){
        try{
            String query ="insert into matapelajaran (kodemapel,namamapel) values " + "('"+mp.getKodemapel()+"','"+mp.getNamamapel()+"');";
            System.out.println(query);
            Statement s = con.createStatement();
            s.execute(query);
            System.out.println("Saving Success");
        }catch(SQLException se){
            System.out.println(se);
        }
    }
    public void loadMatkul(){
        mapel.clear();
        connect();
        try{
            String query = "select * from matapelajaran";
            Statement s = con.createStatement();
            rs = s.executeQuery(query);
            System.out.println(rs);
            while(rs.next()){
                mapel.add(new MataPelajaran(rs.getString("kodemapel"),rs.getString("namamapel")));
            }
        }catch (SQLException ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    
    public ArrayList<MataPelajaran> getMapel(){
        return mapel;
    }
    public ArrayList<Kelas> getKelas(){
        return kelas;
    }
    public void delMatkul(String kodematkul){
        connect();
        String query = "DELETE FROM matapelajaran where kodemapel ='"+ kodematkul + "'";
        if(manipulate(query)){
            for(MataPelajaran mp : mapel){
                if(mp.getKodemapel().equals(kodematkul)){
                    mapel.remove(mp);
                    break;
                }
            }
        }
    }
    public void delMateri(String kodemateri){
        connect();
        String query ="DELETE FROM materi where kodemateri ='" + kodemateri +"'";
        if(manipulate(query)){
            for(Materi m : materi){
                if(m.getKodemateri().equals(kodemateri)){
                    materi.remove(m);
                    break;
                }
            }
        }
    }
    public void delKelas(String kodekelas){
        connect();
        String query ="DELETE FROM kelas where kodekelas ='" + kodekelas + "'";
        if(manipulate(query)){
            for(Kelas k : kelas){
                if(k.getKodekelas().equals(kodekelas)){
                    kelas.remove(k);
                    break;
                }
            }
        }
    }
    
    public void updateMatkul(MataPelajaran mapels){
        connect();
        String query = "UPDATE matapelajaran SET";
        query += " namamapel='" + mapels.getNamamapel() + "'";
        query += " WHERE kodemapel='" + mapels.getKodemapel() +"'";
        if(manipulate(query)){
            for(MataPelajaran mp : mapel){
                if(mp.getKodemapel().equals(mapels.getKodemapel())){
                    mp.setNamamapel(mapels.getNamamapel());
                    break;
                }
            }
        }
        disconnect();
    }
    
    public void updatedatasiswa(Student S){
        connect();
        String query ="UPDATE student set ";
        query += "nama='" + S.getNama()+"',";
        query += "email='" + S.getEmail()+"',";
        query += "noHP='" + S.getNohp()+"' ";
        query += "WHERE username='"+ HandlerLogin.username +"';";
        System.out.println("update dengan query : " + query);
        if(manipulate(query)){
            System.out.println("Berhasilupdatedata");
            for(Student s : student){
                if(s.getUsername().equals(S.getUsername())){
                    s.setNama(S.getNama());
                    s.setEmail(S.getEmail());
                    s.setNohp(S.getNohp());
                    break;
                }
            }
        }
    }
    
    public void updatedatatentor(Tentor T){
        connect();
        String query = "UPDATE tentortable set ";
        query += "nama='" + T.getNama()+ "', ";
        query += "Email='" + T.getEmail() + "', ";
        query += "No_HP='" + T.getNohp() + "' ";
        query += "WHERE Username ='"+ HandlerLogin.username + "';";
        System.out.println("update dengan query : " + query);
        if(manipulate(query)){
            JOptionPane.showMessageDialog(null, "Berhasil update data!");
            for(Tentor t : tentor){
                if(t.getTentor_ID().equals(T.getTentor_ID())){
                    t.setNama(T.getNama());
                    t.setEmail(T.getEmail());
                    t.setNohp(T.getNohp());
                    break;
                }
            }
        }else {
            JOptionPane.showMessageDialog(null, "Gagal Update Data!");
        }
        disconnect();
    }
    
    public void updatemateri(Materi M){
        connect();
        String query = "UPDATE materi set ";
        query += "namamateri='" + M.getNamamateri()+ "', ";
        query += "bagian='" + M.getBagian()+"' "; 
        query += "WHERE kodemateri ='"+ M.getKodemateri() + "';";
        System.out.println("update dengan query : " + query);
        if(manipulate(query)){
            JOptionPane.showMessageDialog(null, "Berhasil update data!");
            for(Materi m : materi){
                if(m.getKodemateri().equals(M.getKodemateri())){
                    m.setNamamateri(M.getNamamateri());
                    m.setBagian(M.getBagian());
                    break;
                }
            }
        }else {
            JOptionPane.showMessageDialog(null, "Gagal Update Data!");
        }
        disconnect();
    }
    
    public void tambahMateri(Materi M,Tentor T,Kelas K){
        try{
            String query ="insert into materi(kodemateri,namamateri,bagian,ID_Tentor,kodekelas) values "
                    + "('"+M.getKodemateri()+"','"+M.getNamamateri()+"','"+M.getBagian()+"','"+T.getTentor_ID()+"','"+K.getKodekelas()+"');";
            System.out.println(query);
            Statement s = con.createStatement();
            s.execute(query);
            System.out.println("Saving Success. ");
        }catch(SQLException se){
            System.out.println(se);
        }
    }
    
    public void tambahTentor(Tentor T){
        try{
        String query ="insert into tentortable (ID_Tentor,Nama,Jenis_Kelamin,Email,No_HP,Lama_Kerja,Username,Password) values "
                + "('"+T.getTentor_ID()+"','"+T.getNama()+"','"+T.getKelamin()+"','"+T.getEmail()+"','"+T.getNohp()+"','"+T.getLamakerja()+"','"+T.getUsername()+"','"+T.getPassword()+"');";
            System.out.println(query);
        Statement s = con.createStatement();
        s.execute(query);
            System.out.println("Saving success. ");
        }catch(SQLException se){
            System.out.println(se);
        }
    }
    
    public void tambahKelas(Kelas K,Tentor T){
        try{
            String query ="insert into kelas (kodekelas,namakelas,matapelajaran,jam,tentor_id) values "
                    + "('"+K.getKodekelas()+"','"+K.getNamakelas()+"','"+K.getMapel().getKodemapel()+"','"+K.getJam()+"','"+T.getTentor_ID()+"');";
                   System.out.println(query);
            Statement s = con.createStatement();
            s.execute(query);
                System.out.println("Saving Success. ");
        }catch(SQLException se){
            System.out.println(se);
        }
    }
    
    
    public void loadKelas() {
        try {
            kelas.clear();
            connect();
            String query = "select * from kelas";
            Statement s = con.createStatement();
            rs = s.executeQuery(query);
            while(rs.next()){
                MataPelajaran mapel = new MataPelajaran(rs.getString("matapelajaran"));
                kelas.add(new Kelas(mapel,rs.getString("kodekelas"),rs.getString("jam"),rs.getString("namakelas")));
            }
        }catch(SQLException ex) {
            System.out.println("Gagal load kelas!");
        }
    }
    
    
    public void updateKelas(Kelas K){
        connect();
        String query = "UPDATE kelas SET";
        query += " namakelas='" + K.getNamakelas() + "', ";
        query += " matapelajaran='" + K.getMapel().getKodemapel() + "',";
        query += " jam='" + K.getJam() + "' ";
        query += " WHERE kodekelas='" + K.getKodekelas() +"';";
        if(manipulate(query)){
            for(Kelas k : kelas){
                if(k.getKodekelas().equals(K.getKodekelas())){
                    k.setNamakelas(K.getNamakelas());
                    k.setJam(K.getJam());
                    MataPelajaran mapel = new MataPelajaran(K.getMapel().getKodemapel());
                    k.setMapel(mapel);
                    break;
                }
            }
        }
        disconnect();
    }
    
    public void loadStudent(){
        student.clear();
        connect();
        try{
            String query = "select * from student";
            Statement s = con.createStatement();
            rs = s.executeQuery(query);
            System.out.println(rs);
            while(rs.next()){
                student.add(new Student(rs.getString("nim"),rs.getString("nama"),rs.getString("kelamin"),rs.getString("email"),rs.getString("noHP"),rs.getString("username"),rs.getString("password")));
            }
        }catch (SQLException ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    
    public ArrayList<Materi> loadMateriTentor(String idtentor){
        materi.clear();
        connect();
        try{
            String query = "select * from materi WHERE ID_Tentor = '" + idtentor + "';";
            System.out.println(query);
            Statement s = con.createStatement();
            rs = s.executeQuery(query);
            System.out.println(rs);
            while(rs.next()){
                materi.add(new Materi(rs.getString("kodemateri"),rs.getString("namamateri"),rs.getInt("bagian")));
            }
        }catch (SQLException ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materi;
    }
    
    public ArrayList<Student> getStudent(){
        return student;
    }
    
    
    public ResultSet getData(String SQLString){
        try {
            rs = stmt.executeQuery(SQLString);
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error :"+e.getMessage(),"Communication Error",JOptionPane.WARNING_MESSAGE);
        }
        return rs;
    }
    public boolean cekAkunStudent(String x, String y){ 
        boolean cek =false;
        DatabaseConnection db = new DatabaseConnection();
        String query1="SELECT username, password FROM student;";
        ResultSet rs = db.getData(query1);
        try{
            while(rs.next()){
                if(rs.getString("username").equals(x)){
                    if(rs.getString("password").equals(y)){
                        cek=true;
                    }
                }
            }
            rs.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,""+e.getMessage(),"Gagal Query",JOptionPane.WARNING_MESSAGE);
        }
        return cek;
    }

    public List<Tentor> loadAllTentor() {
        tentor.clear();
        try {
            connect();
            String query = "select * from tentortable";
            Statement s = con.createStatement();
            rs = s.executeQuery(query);
            while(rs.next()){
                tentor.add(new Tentor(rs.getString("ID_Tentor") , rs.getString("Nama") , rs.getString("Jenis_Kelamin"), rs.getString("Email") 
                , rs.getString("No_HP") , rs.getInt("Lama_Kerja") , rs.getString("Username") , rs.getString("Password") ) );
            }
        }catch(SQLException ex) {
            System.out.println("Gagal load tentor!");
        }
        return tentor;
    }
    
    public List<Materi> loadAllMateri() {
        materi.clear();
        try {
            connect();
            String query = "select * from materi";
            Statement s = con.createStatement();
            rs = s.executeQuery(query);
            while(rs.next()){
                materi.add(new Materi(rs.getString("kodemateri") , rs.getString("namamateri") , rs.getInt("bagian") ) );
            }
        }catch(SQLException ex) {
            System.out.println("Gagal load tentor!");
        }
        return materi;
    }
    
    public ArrayList<Student> loadStudentFromKelas (String kodekelas) {
        ArrayList<Student> st = new ArrayList<>();
        st.clear();
        connect();
        String query = "SELECT * "
                + "FROM student NATURAL JOIN kelas_student "
                + "NATURAL JOIN kelas "
                + "WHERE kodekelas = '" + kodekelas + "';";
        System.out.println(query);
        try{
            if (true) {
                Statement s = con.createStatement();
                rs = s.executeQuery(query);
                while(rs.next()){
                    st.add(new Student(rs.getString("nim") , rs.getString("nama") , rs.getString("kelamin"),
                            rs.getString("email") , rs.getString("noHP") , rs.getString("password") , rs.getString("username") ));
                }
            }else {
                JOptionPane.showMessageDialog(null, "Kelas masih kosong!");
            }
        }catch (SQLException ex) {
            System.out.println("error load student from kelas!");
        }
        return st;
    }
    
    public String searchIdTentor () {
        connect();
        String query = "SELECT * FROM tentortable WHERE Username = '" 
                + HandlerLogin.username 
                + "';";
        String hasil="";
        try{
            if (true) {
                Statement s = con.createStatement();
                rs = s.executeQuery(query);
                while (rs.next()) {
                    hasil = rs.getString("ID_Tentor");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Kelas masih kosong!");
            }
        }catch (SQLException ex) {
            System.out.println("error find id tentor!" + ex.getSQLState());
        }
        System.out.println("Hasil search id tentor : " +hasil);
        return hasil;
    }
    
    public ArrayList<String> searchKodeKelas(String id) {
        ArrayList<String> kodeKelas = new ArrayList<>();
        connect();
        String query = "SELECT kodekelas FROM kelas "
                + " WHERE tentor_id = '" 
                + id 
                + "';";
        try{
            if (true) {
                Statement s = con.createStatement();
                rs = s.executeQuery(query);
                while(rs.next()) {
                    kodeKelas.add( rs.getString("kodekelas") );
                }
            }else {
            }
        }catch (SQLException ex) {
            System.out.println("error find kelas!");
        }
        return kodeKelas;
    }
}