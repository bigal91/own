package ui;

import javax.servlet.http.HttpServletRequest;

import resources.ResourcePaths;
import util.HTMLUtil;
import model.User;

public class StartContainer extends AbstractContainer {

	@Override
	public void provideContent(HttpServletRequest request,
			StringBuilder content, User currentUser) {
		String userName = "";
		if (currentUser != null){
			userName = currentUser.getUserName();
		}
		content.append("Welcome " + userName + " to the StartContainer page!<br \\>");
		content.append(HTMLUtil.getHTMLFile(ResourcePaths.HTML_FILE_PATH + "/startPage.html", null));
		
	}
}
