package tom.ui.util;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CacheControlPhaseListener implements PhaseListener {
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; 
		//return PhaseId.RENDER_RESPONSE;
	}
	public void afterPhase(PhaseEvent event) {}
	public void beforePhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addHeader("pragma","no-cache");
		response.addHeader("cache-control", "no-cache");
		response.addHeader("cache-control", "no-store");
		response.addHeader("cache-control", "must-revalidate");
		response.addHeader("expires", "Mon, 8 Aug 2006 10:00:00 GMT"); // some date in the past

		ExternalContext extCtx = facesContext.getExternalContext();
		HttpSession session = (HttpSession)extCtx.getSession(false);
		boolean newSession = (session == null) || (session.isNew());
		boolean postback = !extCtx.getRequestParameterMap().isEmpty();
		boolean timedout = postback && newSession;
		if(timedout) { 
			Application app = facesContext.getApplication();
			ViewHandler viewHandler = app.getViewHandler();
			UIViewRoot view = viewHandler.createView( facesContext, "/html/run.jsf");
			//System.out.println("facesContext.getViewRoot() "); 
			//System.out.println(FacesContext.getCurrentInstance().getViewRoot());
			//System.out.println("Inside pahselistener view id is " + facesContext.getViewRoot().getViewId());
			//UIViewRoot view = viewHandler.createView( facesContext, facesContext.getViewRoot().getViewId());
			facesContext.setViewRoot(view);
			facesContext.renderResponse();
			try { 
				viewHandler.renderView(facesContext, view);
				facesContext.responseComplete();
			} catch(Throwable t) { 
				throw new FacesException( "Session timed out", t);
			} 
		} 
	}
}
