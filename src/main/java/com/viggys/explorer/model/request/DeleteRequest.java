package com.viggys.explorer.model.request;

import lombok.*;

@ToString
@Getter
@RequiredArgsConstructor
public class DeleteRequest {

    @NonNull private String path;
    @NonNull private String filename;

}
