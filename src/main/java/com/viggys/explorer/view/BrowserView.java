package com.viggys.explorer.view;

import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.UnknownHostException;

public interface BrowserView {

    ResponseEntity generateInspectResponse(HttpServletRequest request, TemplateEngine templateEngine) throws IOException;

    ResponseEntity generateDownloadResponse(HttpServletRequest request) throws IOException;

}
