package gp.fall2020.thebanddatabase;

public class Band {
    private int mId;
    private String mName;
    private String mDescription;

    public Band() {}

    public Band(int mId, String mName, String mDescription) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
    }

    public int getmId() {
        return mId;
    }
    public void setmId(int mId) {
        this.mId = mId;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmDescription() {
        return mDescription;
    }
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
