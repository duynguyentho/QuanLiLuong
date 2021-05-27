public class CBGV extends Nguoi{
    public double luongCung, thuong, phat;
    public CBGV(String name, int dob, String address, double luongCung, double thuong, double phat) {
        super(name, dob, address);
        this.luongCung = luongCung;
        this.thuong = thuong;
        this.phat = phat;
    }

    public double getLuongCung() {
        return luongCung;
    }

    public void setLuongCung(double luongCung) {
        this.luongCung = luongCung;
    }

    public double getThuong() {
        return thuong;
    }

    public void setThuong(double thuong) {
        this.thuong = thuong;
    }

    public double getPhat() {
        return phat;
    }

    public void setPhat(double phat) {
        this.phat = phat;
    }
    public double luongThucLinh(){
        return this.luongCung + this.thuong - this.phat;
    }
}
