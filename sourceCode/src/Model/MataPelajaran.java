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
public class MataPelajaran {
    private String kodemapel;
    private String namamapel;

    public MataPelajaran(String kodemapel, String namamapel) {
        this.kodemapel = kodemapel;
        this.namamapel = namamapel;
    }

    public MataPelajaran(String kodemapel) {
        this.kodemapel = kodemapel;
    }

    public String getKodemapel() {
        return kodemapel;
    }

    public void setKodemapel(String kodemapel) {
        this.kodemapel = kodemapel;
    }

    public String getNamamapel() {
        return namamapel;
    }

    public void setNamamapel(String namamapel) {
        this.namamapel = namamapel;
    }
 
}
