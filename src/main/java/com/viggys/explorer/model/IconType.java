package com.viggys.explorer.model;

public enum IconType {
    DIRECTORY("folder.png"),
    FILE("file.png");

    private String resource;

    IconType(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return resource;
    }
}
