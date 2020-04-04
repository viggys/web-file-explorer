package com.viggys.explorer.controller;

import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

@RestController
public class PathController {

    private static final Logger log = LoggerFactory.getLogger(PathController.class);

    @GetMapping
    public ResponseEntity list() {
        try{
            Set<Path> files = PathUtil.list(SystemUtil.getUserHome());
            return ResponseEntity.status(HttpStatus.OK).body(files);
        }
        catch(IOException eio) {
            log.error("Exception in PathController :: ", eio);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eio.getMessage());
        }
    }

}
