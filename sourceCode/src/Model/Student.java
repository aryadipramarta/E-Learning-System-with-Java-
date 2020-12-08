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
public class Student extends Person {
    private String nim;
    private String username;
    private String password;

    public Student(String namasiswa, String email, String nohp) {
        this.nama = namasiswa;
        this.email = email;
        this.nohp = nohp;
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
    
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Student(String nim,String nama,String kelamin,String email,String nohp,String password,String username){
        super(nama,kelamin,email,nohp);
        setNim(nim);
        setPassword(password);
        setUsername(username);
    }
  
}
