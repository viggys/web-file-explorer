package com.viggys.explorer.view;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Getter @Setter @ToString
public class FileView implements BrowserView {

    @NonNull
    private Resource resource;

    @Override
    public ResponseEntity generateInspectResponse(HttpServletRequest request, TemplateEngine templateEngine) {
        return null;
    }

    @Override
    public ResponseEntity generateDownloadResponse(HttpServletRequest request) throws IOException {
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if(contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
