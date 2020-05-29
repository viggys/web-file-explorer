package com.viggys.explorer.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter @ToString
public class PathLink {

    @NonNull private String label;
    private String href;

}
