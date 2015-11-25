package ejb;

import javax.ejb.Stateful;

@Stateful
public class TestBean {

    private String mTestField;

    public String getTestField() {
        return mTestField;
    }

    public void setTestField(String testField) {
        mTestField = testField;
    }

}
