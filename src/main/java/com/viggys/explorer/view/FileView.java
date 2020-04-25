package com.viggys.explorer.view;

import com.viggys.explorer.util.SystemUtil;
import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RequiredArgsConstructor
@Getter @Setter @ToString
public class FileView implements BrowserView {

    private static final String FILE_VIEW = "file.html";

    @NonNull
    private Resource resource;

    @Override
    public ResponseEntity generateInspectResponse(HttpServletRequest request, TemplateEngine templateEngine) throws IOException {
        Context context = new Context();
        context.setVariable("hostName", SystemUtil.getHostName());
        context.setVariable("userName",SystemUtil.getUserName());
        context.setVariable("ip",SystemUtil.getIPAddress());
        context.setVariable("data", this);

//        String view = templateEngine.process(FILE_VIEW, context);
        String view = generateContent();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(view);
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

    private String generateContent() throws IOException {
        BufferedReader reader = null;
        String content = null;
        try {
            reader = new BufferedReader(new FileReader(resource.getFile()));
            StringBuilder contentBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                contentBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            content = contentBuilder.toString();
        }
        finally {
            reader.close();
        }
        return content;
    }
}
