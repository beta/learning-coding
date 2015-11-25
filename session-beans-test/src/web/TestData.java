package web;

import ejb.TestBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "testData")
@NoneScoped
public class TestData implements Serializable {

    @EJB
    private TestBean mTestBean;

    public String getTestField() {
        return mTestBean.getTestField();
    }

    public void setTestField(String testField) {
        mTestBean.setTestField(testField);
    }

}
