package com.viggys.explorer.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class AddRequest {

    @NonNull private String path;
    @NonNull private String filename;
    @NonNull private boolean isDirectory;
    @NonNull private boolean isFile;

}
