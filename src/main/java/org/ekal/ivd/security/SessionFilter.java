package org.ekal.ivd.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Order(1)
@Log4j2
public class SessionFilter implements Filter {


    private static final String[] SESSIONLESS_URLS = {
            "\\/LoginController\\/.*.",
            "\\/public\\/.*.",
            "\\/index.html",
            "\\/error404.html"
    };
    private static final ArrayList<Pattern> SESSIONLESS_PATTERNS = new ArrayList<>();

    private static boolean checkIfSessionLessUrl(String uri) {
        for (Pattern pat : SESSIONLESS_PATTERNS) {
            Matcher matcher = pat.matcher(uri);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        String contextPath = filterConfig.getServletContext().getContextPath();
        for (String urlStr : SESSIONLESS_URLS) {
            String pattern = "\\" + contextPath + urlStr;
            SESSIONLESS_PATTERNS.add(Pattern.compile(pattern));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String path = req.getContextPath();
        String uri = req.getRequestURI();
        String ip = req.getHeader("X-Forwarded-For");
        ip = StringUtils.defaultIfBlank(ip, req.getRemoteAddr());
        ThreadContext.clearAll();
        ThreadContext.put("USERID", "0");
        ThreadContext.put("IP", ip);
        int userID = NumberUtils.toInt(String.valueOf(session.getAttribute("userId")), 0);
        if (userID > 0) {
            ThreadContext.put("USERID", String.valueOf(userID));
            if (uri.endsWith("/public/login.html")) {
                res.sendRedirect(path + "/new_project_list.html");
            } else {
                chain.doFilter(req, res);
            }
        } else if (checkIfSessionLessUrl(uri)) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect(path + "/public/login.html");
        }
    }
}
