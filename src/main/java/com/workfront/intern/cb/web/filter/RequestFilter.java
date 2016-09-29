package com.workfront.intern.cb.web.filter;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.web.Initializer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RequestFilter implements Filter {
    final Set<String> adminURIs = new HashSet<>(Arrays.asList(
            "/tournament-page",
            "/match-page",
            "/group-page",
            "/group-participant-page",
            "/assign-participant-to-group-page",
            "/contact-page",
            "/unknown-1-uri"
    ));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // Acquire sign-in manager
        Manager manager = (Manager) session.getAttribute("manager");
        boolean authenticated = manager != null;

        // Acquire request URI
        String requestURI = request.getRequestURI();

        // Redirect to home page if one of admin/manager pages is requested
        if (!authenticated && adminURIs.contains(requestURI)) {
            if (Initializer.isRedirectUnauthorizedRequestsToHome()) {
                response.sendRedirect("/");
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return;
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        chain.doFilter(request, response);
    }
}