package club.iandroid.sevenchartslib.entity;

public class LineEntity {
    private float mValue;
    private String mLabel;

    public float getmValue() {
        return mValue;
    }

    public void setmValue(float mValue) {
        this.mValue = mValue;
    }

    public String getmLabel() {
        return mLabel;
    }

    public void setmLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public LineEntity(float mValue, String mLabel) {
        this.mValue = mValue;
        this.mLabel = mLabel;
    }
}