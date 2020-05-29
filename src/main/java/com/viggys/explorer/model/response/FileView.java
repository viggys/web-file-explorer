package com.viggys.explorer.model.response;

import com.viggys.explorer.util.DirectoryUtil;
import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import com.viggys.explorer.util.ViewUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Getter @Setter @ToString
public class FileView implements ViewInterface {

    private static final String INSPECT_VIEW = "/views/file/inspect.html";

    private Resource resource;
    private String content;
    private List<PathLink> currentPathTree;
    private PathLink parentPathLink;
    private PathLink rootPathLink;

    public FileView(@NonNull Path currentPath) {
        this.currentPathTree = DirectoryUtil.getPathTreeMap(currentPath);
        this.parentPathLink = PathUtil.getPathLink(currentPath.getParent());
        this.rootPathLink = PathUtil.getPathLink(SystemUtil.getUserHome());
        this.resource = new PathResource(currentPath);
    }

    @Override
    public ModelAndView generateInspectResponse(HttpServletRequest request) throws IOException {
        this.content = generateContent();

        ModelAndView modelAndView = ViewUtil.getModelViewWithMetadata();
        modelAndView.setViewName(INSPECT_VIEW);
        modelAndView.addObject("data", this);

        return modelAndView;
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
