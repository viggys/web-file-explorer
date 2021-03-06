package com.viggys.explorer.test.util;

import com.viggys.explorer.model.response.PathLink;
import com.viggys.explorer.util.DirectoryUtil;
import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

@Slf4j
public class DirectoryUtilTest {

    @Test
    public void getPathTreeMap() {
        String value = SystemUtil.getUserHome().toFile().getAbsolutePath(); // + "/Documents/My Tocuments";
        Path path = PathUtil.resolvePath(value);
        List<PathLink> pathTreeStack = DirectoryUtil.getPathTreeMap(path);
        pathTreeStack.forEach(System.out::println);
    }

}
