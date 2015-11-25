import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by beta on 9/22/15.
 */
@ManagedBean(name = "test")
@RequestScoped
public class TestBean {

    private int mNumber = -1;

    public int getNumber() {
        if (mNumber == -1) {
            mNumber = new Random().nextInt(10);
        }

        return mNumber;
    }

    public String getUrl() {
        HttpServletRequest request = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURI();
        return url;
    }

    public String getName() {
        HttpServletRequest request = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParameter("name");
    }

}
