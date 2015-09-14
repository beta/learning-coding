package wamCalculator;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Bean for calculating the WAM.
 *
 * @author Beta
 */
@ManagedBean(name = "WamCalculatorBean")
@SessionScoped
public class WamCalculatorBean implements Serializable {

    String response = null;
    private float credit1 = 0;
    private float credit2 = 0;
    private float credit3 = 0;
    private float credit4 = 0;
    private float credit5 = 0;
    private float score1 = 0;
    private float score2 = 0;
    private float score3 = 0;
    private float score4 = 0;
    private float score5 = 0;
    private float wam = 0;
    
    public WamCalculatorBean() {
    }
    
    public float getCredit1() {
        return credit1;
    }

    public void setCredit1(float credit1) {
        this.credit1 = credit1;
    }

    public float getCredit2() {
        return credit2;
    }

    public void setCredit2(float credit2) {
        this.credit2 = credit2;
    }

    public float getCredit3() {
        return credit3;
    }

    public void setCredit3(float credit3) {
        this.credit3 = credit3;
    }

    public float getCredit4() {
        return credit4;
    }

    public void setCredit4(float credit4) {
        this.credit4 = credit4;
    }

    public float getCredit5() {
        return credit5;
    }

    public void setCredit5(float credit5) {
        this.credit5 = credit5;
    }

    public String getResponse() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        ans = (credit1 * score1 + credit2 * score2 + credit3 * score3
            + credit4 * score4 + credit5 * score5)
            / (credit1 + credit2 + credit3 + credit4 + credit5);

        return "<p>Your WAM is: " + ans + ".</p>";
    }
    
}
