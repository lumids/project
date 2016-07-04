package ch16.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleWelcomeTag extends SimpleTagSupport{
	public void doTag() throws JspException {
        try {
            getJspContext().getOut().print("SimpleTag: Welcome to My Custom Tag");
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
}