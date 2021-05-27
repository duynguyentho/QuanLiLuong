public class Nguoi {
    public String name;
    public int dob;
    public String address;
    public Nguoi(){
        this.name = "";
        this.dob = 0;
        this.address = "";
    }
    public Nguoi(String name, int dob, String address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public int getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

}