/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Microsoft
 */
public class Application {
    static List <Student> daftarStudent = new ArrayList<>(); 
    static List <Tentor> daftarTentor = new ArrayList<>();
    static List <MataPelajaran> daftarMataPelajaran = new ArrayList<>();
    static List <Kelas> daftarKelas = new ArrayList<>();
    static List <Materi> daftarMateri = new ArrayList<>();
    DatabaseConnection db;
    public Application(){
        try {
            db = new DatabaseConnection();
            daftarStudent.clear();
            daftarTentor.clear();
            daftarMataPelajaran.clear();
            daftarKelas.clear();
            daftarMateri.clear();
            db.connect();
            db.loadMatkul();
            db.loadStudent();
            db.loadKelas();
            db.loadAllMateri();
            loadTentor();
            loadStudent();
            loadMatkul();
            loadKelas();
            loadMateri();
            System.out.println("Semua data sudah di load!");
        }catch (Exception e) {
            System.out.println("Gagal load data!");
        }
        
    }
    
    public void addStudent(Student siswa){
        daftarStudent.add(siswa);
    }
    
    public void addKelas(Kelas K,Tentor T){
        db.tambahKelas(K, T);
        
    }
    
    public void addTentor(Tentor t){
        db.tambahTentor(t);
    }
    
    public void addMateri(Materi M,Tentor T,Kelas K){
        db.tambahMateri(M, T, K);
    }
    
    public void loadMateri(){
        daftarMateri = db.loadAllMateri();
    }
    
    public void loadTentor(){
        daftarTentor = db.loadAllTentor();
    }
    
    public void loadStudent(){
        daftarStudent = db.getStudent();
    }
    
    public void loadMatkul() {
        daftarMataPelajaran = db.getMapel();
    }
    
    public void loadKelas() {
        daftarKelas = db.getKelas();
    }
   
    public void addMataPelajaran(MataPelajaran mapel){
        db.tambahMataPelajaran(mapel);
    }
    
    public MataPelajaran searchmapel(String x){
        MataPelajaran ma = null;
        if (daftarMataPelajaran.isEmpty()) {
            System.out.println("Error mapel kosong");
        }else {
        for (MataPelajaran m : daftarMataPelajaran) {
                if (m.getNamamapel().equals(x)) {
                    ma = m;
                }
            }
        }
        return ma;
    }
    
    public Kelas searchKelas (String kodekelas) {
        if (daftarKelas.isEmpty()) {
            System.out.println("Kelas kosong");
        }else {
            for (Kelas m : daftarKelas) {
                if (m.getKodekelas().equals(kodekelas)) {
                    return m;
                }
            }
        }
        return null;
    }
    
    public Tentor searchObjectTentor(String x){
        System.out.println("Tentor yang akan dicari : " + x);
        Tentor tn = null;
        if (this.daftarTentor.isEmpty()){
            System.out.println("Error tentor kosong");
        }else{
            for (Tentor T : daftarTentor) {
                if(T.getNama().equals(x)){
                    tn = T;
                }
            }
        }
        return tn;
    }
    
    public Tentor searchObjectTentorByID(String x){
        System.out.println("Tentor yang akan dicari : " + x);
        Tentor tn = null;
        if (this.daftarTentor.isEmpty()){
            System.out.println("Error tentor kosong");
        }else{
            for (Tentor T : daftarTentor) {
                if(T.getTentor_ID().equals(x)){
                    tn = T;
                }
            }
        }
        return tn;
    }

    public static List<Student> getDaftarStudent() {
        return daftarStudent;
    }

    public static List<Tentor> getDaftarTentor() {
        return daftarTentor;
    }

    public static List<MataPelajaran> getDaftarMataPelajaran() {
        return daftarMataPelajaran;
    }

    public static List<Kelas> getDaftarKelas() {
        return daftarKelas;
    }
    
    public static List<Materi> getDaftarMateri(){
        return daftarMateri;
    }
    
}
