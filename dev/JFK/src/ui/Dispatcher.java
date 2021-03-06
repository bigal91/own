package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import resources.ResourcePaths;
import util.HTMLUtil;
import util.Logger;
import constants.Pages;
import model.User;

public class Dispatcher extends AbstractContainer{

	private LoginContainer loginContainer = new LoginContainer();
	private StartContainer startContainer = new StartContainer();
	private PrMarketingContainer prContainer = new PrMarketingContainer();
	private BlogContainer blogContainer = new BlogContainer();
	private StorytellingContainer storyContainer = new StorytellingContainer();
	
	@Override
	public void provideContent(HttpServletRequest request,
			StringBuilder content, User currentUser) {
		final StringBuilder innerContent = new StringBuilder();
		final Map<String, String> replacements = new HashMap<String, String>();
		String page = request.getParameter(Pages.PAGE_MAIN_PARAM);
		
		try {
			if (page == null){
				page = Pages.START;
			}
			
			if (page.equals(Pages.LOGOUT)){
				request.getSession().invalidate();
				page = Pages.START;
				if (currentUser != null) {
					Logger.log("User " + currentUser.getUserName() + " logged out.");
				}
				currentUser = null;
			} else if (page.equals(Pages.LOGIN)){
				if (currentUser == null){
					loginContainer.provideContent(request, innerContent, currentUser);					
				} else {
					startContainer.provideContent(request, innerContent, currentUser);
				}
			}
			
			if (page.equals(Pages.START)){
				startContainer.provideContent(request, innerContent, currentUser);
			} else if (page.equals(Pages.PR_AND_CONTENTMARKETING)){
				prContainer.provideContent(request, innerContent, currentUser);
			} else if (page.equals(Pages.BLOG)){
				blogContainer.provideContent(request, innerContent, currentUser);
			} else if (page.equals(Pages.STORYTELLING)){
				storyContainer.provideContent(request, innerContent, currentUser);
			}
			if (currentUser != null) {
				replacements.put("LOGOUT_LINK", "<a href=\"?page=logout\">logout</a>");
			}
			replacements.put("CONTENT", innerContent.toString());
			replacements.put("HEADMENU", HTMLUtil.getHTMLFile(ResourcePaths.HTML_FILE_PATH + "/headerName.html", replacements));
			
		}catch (Exception e) {
			replacements.put("CONTENT", "<p style=\"text-align: center;\"> the DISPATCHER caused an Error. (" + new SimpleDateFormat().format(new Date()) + ")<br /><br /></p>");
		}
		
		content.append(HTMLUtil.getHTMLFile(ResourcePaths.HTML_FILE_PATH + "/layout.html", replacements));
		
	}

}
