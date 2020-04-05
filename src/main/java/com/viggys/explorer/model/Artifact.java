package com.viggys.explorer.model;

import com.viggys.explorer.util.PathUtil;
import lombok.Getter;

import java.nio.file.Path;
import java.util.Date;

@Getter
public class Artifact {

    private String name;
    private String inspectUrl;
    private String downloadUrl;
    private Date lastModifiedAt;
    private boolean isHidden;
    private boolean isDirectory;
    private boolean isFile;
//    private IconType icon;

    public Artifact(Path path) {
        this.name = path.getFileName().toString();
        this.inspectUrl = PathUtil.getInspectUrl(path);
        this.downloadUrl = PathUtil.getDownloadUrl(path);
        this.lastModifiedAt = new Date(path.toFile().lastModified());
        this.isHidden = path.toFile().isHidden();
        this.isDirectory = path.toFile().isDirectory();
        this.isFile = path.toFile().isFile();
//        this.icon = this.isDirectory ? IconType.DIRECTORY : IconType.FILE;
    }

}
