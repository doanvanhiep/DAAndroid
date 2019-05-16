package hiephandsome.com.doancuoiki.CauHoi;

import java.io.Serializable;

public class BoCauHoi implements Serializable {
    private int cauhoiId;
    private String cauHoi;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String cauTraLoi;
    private String loai;



    public BoCauHoi() {
    }

    public BoCauHoi(String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String cauTraLoi,String loai) {
        this.cauHoi = cauHoi;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.cauTraLoi = cauTraLoi;
        this.loai=loai;
    }

    public BoCauHoi(int cauhoiId, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String cauTraLoi,String loai) {
        this.cauhoiId = cauhoiId;
        this.cauHoi = cauHoi;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.cauTraLoi = cauTraLoi;
        this.loai=loai;
    }

    public int getCauhoiId() {
        return cauhoiId;
    }

    public void setCauhoiId(int cauhoiId) {
        this.cauhoiId = cauhoiId;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }
    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }


}
