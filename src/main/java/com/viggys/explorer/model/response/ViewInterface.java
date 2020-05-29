package com.viggys.explorer.model.response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ViewInterface {

    ModelAndView generateInspectResponse(HttpServletRequest request) throws IOException;

    ResponseEntity generateDownloadResponse(HttpServletRequest request) throws IOException;

}
