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
public class Kelas {
    private List <Student> listStudent;
    private List <Materi> listMateri;
    private MataPelajaran mapel;
    private String kodekelas;
    private String jam;
    private String namakelas;
    
    public void addStudent(Student siswa){
        listStudent.add(siswa);
    }

    public MataPelajaran getMapel() {
        return mapel;
    }

    public void setMapel(MataPelajaran mapel) {
        this.mapel = mapel;
    }
    
    public void createMateri(String kodemateri,String namamateri, int bagian){
        Materi newMateri = new Materi(kodemateri,namamateri,bagian);
        listMateri.add(newMateri);
    }

    public String getKodekelas() {
        return kodekelas;
    }

    public void setKodekelas(String kodekelas) {
        this.kodekelas = kodekelas;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNamakelas() {
        return namakelas;
    }

    public void setNamakelas(String namakelas) {
        this.namakelas = namakelas;
    }
    
    public Kelas(MataPelajaran mapel, String kodekelas, String jam, String namakelas){
        this.kodekelas = kodekelas;
        this.mapel = mapel;
        this.jam = jam;
        this.namakelas = namakelas;
        listStudent = new ArrayList<>();
        listMateri = new ArrayList<>();
    }
}
