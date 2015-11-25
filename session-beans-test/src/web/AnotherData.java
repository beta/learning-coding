package web;

import ejb.TestBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "anotherData")
@SessionScoped
public class AnotherData implements Serializable {

    @EJB
    private TestBean mTestBean;

    public String getTestField() {
        return mTestBean.getTestField();
    }
}
