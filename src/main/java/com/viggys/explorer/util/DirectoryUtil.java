package com.viggys.explorer.util;

import com.viggys.explorer.model.PathLink;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DirectoryUtil extends PathUtil {

    public static List<PathLink> getPathTreeMap(Path path) {
        List<PathLink> pathTreeStack = new Stack<>();
        while (!path.equals(path.getRoot())) {
            PathLink pathLink = getPathLink(path);
            pathTreeStack.add(pathLink);
            path = path.getParent();
        }
        Collections.reverse(pathTreeStack);
        return pathTreeStack;
    }
}
