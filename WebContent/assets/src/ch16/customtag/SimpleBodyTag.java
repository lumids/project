package ch16.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleBodyTag extends SimpleTagSupport{
	public void doTag() throws JspException {
        try {
			JspFragment fragment = getJspBody();
			fragment.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
