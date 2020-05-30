package com.viggys.explorer.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRequest {

    private String path;
    private String content;
}
