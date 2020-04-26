package com.viggys.explorer.model;

import com.viggys.explorer.util.PathUtil;
import lombok.Getter;

import java.nio.file.Path;
import java.util.Date;

import static com.viggys.explorer.util.Constants.DATE_FORMATTER;

@Getter
public class Artifact {

    private PathLink pathLink;
    private String downloadHref;
    private String lastModifiedAt;
    private Boolean isHidden;
    private Boolean isDirectory;
    private Boolean isFile;
//    private IconType icon;

    public Artifact(Path path) {
        this.pathLink = PathUtil.getPathLink(path);
        this.downloadHref = PathUtil.getDownloadUrl(path);
        this.lastModifiedAt = DATE_FORMATTER.format(new Date(path.toFile().lastModified()));
        this.isHidden = path.toFile().isHidden();
        this.isDirectory = path.toFile().isDirectory();
        this.isFile = path.toFile().isFile();
//        this.icon = this.isDirectory ? IconType.DIRECTORY : IconType.FILE;
    }

}
