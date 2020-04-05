package com.viggys.explorer.view;

import com.viggys.explorer.model.Artifact;
import com.viggys.explorer.model.handler.ArtifactComparator;
import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class DirectoryView implements BrowserView {

    private static final String DIRECTORY_VIEW = "directory.html";

    @NonNull
    private String currentPath;
    private String parentHref;
    private String rootHref;
    private List<Artifact> artifacts;

    public DirectoryView(@NonNull Path currentPath, List<Path> artifactPaths) {
        this.currentPath = currentPath.toString();
        this.parentHref = PathUtil.getInspectUrl(currentPath.getParent());
        this.rootHref = PathUtil.getInspectUrl(currentPath.getRoot());
        this.artifacts = artifactPaths.stream()
                .map(Artifact::new)
                .collect(Collectors.toList());
        Collections.sort(this.artifacts, new ArtifactComparator());
    }

    @Override
    public ResponseEntity generateInspectResponse(HttpServletRequest request, TemplateEngine templateEngine) throws UnknownHostException {
        Context context = new Context();
        context.setVariable("hostName", SystemUtil.getHostName());
        context.setVariable("userName",SystemUtil.getUserName());
        context.setVariable("ip",SystemUtil.getIPAddress());
        context.setVariable("data", this);

        String view = templateEngine.process(DIRECTORY_VIEW, context);
        return ResponseEntity.status(HttpStatus.OK).body(view);
    }

    @Override
    public ResponseEntity generateDownloadResponse(HttpServletRequest request) {
        return null;
    }
}
