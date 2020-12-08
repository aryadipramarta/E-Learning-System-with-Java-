/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Microsoft
 */
public class Tentor extends Person {
    private List<Kelas> listkelas = new ArrayList();
    private int lamakerja;
    private String username;
    private String password;
    private String Tentor_ID;   
    
    public Tentor(String Tentor_ID,String nama, String kelamin, String email,String nohp,int lamakerja,String username,String password) {
        super(nama, kelamin, email,nohp);  
        this.username = username;
        this.password = password;
        this.lamakerja = lamakerja;
        this.Tentor_ID = Tentor_ID;
        listkelas = new ArrayList<>();
    }

    public Tentor(String namatentor, String email, String nohp) {
        this.nama = namatentor;
        this.email = email;
        this.nohp = nohp;
    }
    
    public String getTentor_ID() {
        return Tentor_ID;
    }

    public void setTentor_ID(String Tentor_ID) {
        this.Tentor_ID = Tentor_ID;
    }

    public int getLamakerja() {
        return lamakerja;
    }

    public void setLamakerja(int lamakerja) {
        this.lamakerja = lamakerja;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString(){
        return"ID: " + Tentor_ID + "\nNama : " + nama + "\nKelamin : " + kelamin + "\nEmail : " + email + "\nNo Hp : " + nohp + "\n Lama Kerja : " + lamakerja + 
                "\nUsername : "+ username + "\nPassword : " + password;
    }
    
    public void createKelas(MataPelajaran mapel,String kodekelas, String jam, String namakelas){
        Kelas Kelasnew = new Kelas(mapel,kodekelas,jam,namakelas);
        listkelas.add(Kelasnew);
    }
    
    public void createKelas(Kelas K){
        listkelas.add(K);
    }
    
}
