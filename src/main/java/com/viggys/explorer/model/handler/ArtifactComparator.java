package com.viggys.explorer.model.handler;

import com.viggys.explorer.model.Artifact;
import org.springframework.util.Assert;

import java.util.Comparator;

public class ArtifactComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Assert.isTrue(o1 instanceof Artifact, o1 + " is not a FileResource.");
        Assert.isTrue(o2 instanceof Artifact, o2 + " is not a FileResource.");

        Artifact file1 = (Artifact) o1;
        Artifact file2 = (Artifact) o2;

        return file1.getName().compareTo(file2.getName());
    }
}
