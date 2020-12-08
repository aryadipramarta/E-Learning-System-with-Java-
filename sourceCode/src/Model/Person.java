/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Microsoft
 */
public abstract class Person {
    String nama;
    String kelamin;
    String email;
    String nohp;

    public String getNohp() {
        return nohp;
    }
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public Person(){
        
    }
    public Person(String nama,String kelamin,String email,String nohp){
        setNama(nama);
        setKelamin(kelamin);
        setEmail(email);
        setNohp(nohp);
    }
}
