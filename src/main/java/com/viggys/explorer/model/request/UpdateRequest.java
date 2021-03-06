package com.viggys.explorer.model.request;

import lombok.*;

@ToString
@Getter
@RequiredArgsConstructor
public class UpdateRequest {

    @NonNull private String path;
    @NonNull private String content;
}
