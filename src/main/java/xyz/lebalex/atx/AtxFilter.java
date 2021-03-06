/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xyz.lebalex.atx;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ivc_LebedevAV
 */
@WebFilter(filterName = "AtxFilter", urlPatterns = {"/index.jsf", "/users.jsf","/userinfo.jsf","/listavto.jsf","/search.jsf","/libr.jsf","/librModel.jsf","/include/*"})
public class AtxFilter implements Filter {

    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    @Inject
    private AtxLogin atxLogin;

    /**
     *
     */
    public AtxFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) log("AtxFilter:DoBeforeProcessing");

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.

        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
	*/
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) log("AtxFilter:DoAfterProcessing");

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.

        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed.
	/*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
	*/

        // For example, a filter might append something to the response.
	/*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
	*/
    }

    private FacesContext getFacesContext(ServletRequest request, ServletResponse response) {
        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        ServletContext servletContext = ((HttpServletRequest) request).getSession().getServletContext();
        FacesContext facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);
        return facesContext;

    }

    private boolean accessForUrl(List<String> urlAccess, String url) {
        for (String a : urlAccess) {
            if (url.indexOf(a) >= 0) return true;
        }
        return false;
    }

    /**
     * @param request  The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain    The filter chain we are processing
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String url = httpServletRequest.getServletPath();
            if (!isSessionInvalid(httpServletRequest)) {
                if (atxLogin.getUsername() == null) {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsf");

                    /*if (!accessForUrl(atxLogin.getUrlAccess(), url))
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/main.jsf");*/
                    

                    /*if(alisaAppBean.getIdUser()!=0)
                    {
                        if(url.indexOf("admin")!=-1) {
                            if(!alisaAppBean.isAdmin())
                                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/main.jsf");
                        }
                    }else//id=0
                        {
                            if(url.indexOf("admin")!=-1 || url.indexOf("vcp")!=-1 || url.indexOf("nsi")!=-1 || url.indexOf("scheduler")!=-1 || url.indexOf("zarpl")!=-1)
                                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/main.jsf");
                        }*/
                } else {
                    log(url);
                    log("getUserLogin "+atxLogin.getUsername());
                }
            }else
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsf");
        }

        chain.doFilter(request, response);
    }

    private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
        try {
            String s_id = httpServletRequest.getRequestedSessionId();
            boolean s_v = httpServletRequest.isRequestedSessionIdValid();
            boolean sessionInValid = (s_id != null)
                    && !s_v;
            return sessionInValid;
        } catch (Exception e) {
            log(e.getMessage());
            return false;
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AlisaFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        if (filterConfig == null) return ("AlisaFilter()");
        StringBuffer sb = new StringBuffer("AlisaFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    /**
     * @param msg
     */
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
