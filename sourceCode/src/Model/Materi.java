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
public class Materi {
    private String namamateri;
    private int bagian;
    private String kodemateri;

    public String getKodemateri() {
        return kodemateri;
    }

    public void setKodemateri(String kodemateri) {
        this.kodemateri = kodemateri;
    }

    public int getBagian() {
        return bagian;
    }

    public void setBagian(int bagian) {
        this.bagian = bagian;
    }

    public String getNamamateri() {
        return namamateri;
    }

    public void setNamamateri(String namamateri) {
        this.namamateri = namamateri;
    }
    public Materi(String kodemateri,String namamateri,int bagian){
        setKodemateri(kodemateri);
        setNamamateri(namamateri);
        setBagian(bagian);
    }
}
