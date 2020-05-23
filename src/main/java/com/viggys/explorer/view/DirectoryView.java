package com.viggys.explorer.view;

import com.viggys.explorer.model.Artifact;
import com.viggys.explorer.model.PathLink;
import com.viggys.explorer.model.handler.ArtifactComparator;
import com.viggys.explorer.util.DirectoryUtil;
import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import com.viggys.explorer.util.ViewUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@RequiredArgsConstructor
public class DirectoryView implements ViewInterface {

    private static final String INSPECT_VIEW = "/views/directory/inspect.html";

    @NonNull
    private List<PathLink> currentPathTree;
    private PathLink parentPathLink;
    private PathLink rootPathLink;
    private List<Artifact> artifacts;

    public DirectoryView(@NonNull Path currentPath, List<Path> artifactPaths, boolean showHidden) {
        this.currentPathTree = DirectoryUtil.getPathTreeMap(currentPath);
        this.parentPathLink = PathUtil.getPathLink(currentPath.getParent());
        this.rootPathLink = PathUtil.getPathLink(SystemUtil.getUserHome());
        this.artifacts = artifactPaths.stream()
                .filter(path -> (showHidden || !path.toFile().isHidden()))
                .map(Artifact::new)
                .collect(Collectors.toList());
        Collections.sort(this.artifacts, new ArtifactComparator());
    }

    @Override
    public ModelAndView generateInspectResponse(HttpServletRequest request) throws UnknownHostException {
        ModelAndView modelAndView = ViewUtil.getModelViewWithMetadata();
        modelAndView.setViewName(INSPECT_VIEW);
        modelAndView.addObject("data", this);

        return modelAndView;
    }

    @Override
    public ResponseEntity generateDownloadResponse(HttpServletRequest request) {
        return null;
    }
}
