package com.viggys.explorer.model.request;

import lombok.*;

@ToString
@Getter
@RequiredArgsConstructor
public class AddRequest {

    @NonNull private String path;
    @NonNull private String filename;
    @NonNull private boolean isDirectory;
    @NonNull private boolean isFile;

}
