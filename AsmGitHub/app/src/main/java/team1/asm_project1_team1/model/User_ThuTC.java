package team1.asm_project1_team1.model;

public class User_ThuTC {
    String ma;
    String ten;
    String gmail;
    String tendangnhap;
    String matkhau;
    public User_ThuTC(){

    }
    public User_ThuTC(String ma, String ten, String gmail, String tendangnhap, String matkhau){
        this.ma=ma;
        this.ten=ten;
        this.gmail=gmail;
        this.tendangnhap=tendangnhap;
        this.matkhau=matkhau;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
