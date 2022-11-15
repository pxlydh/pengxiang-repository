package pers.pengxiang.repository.leetcode.common.filevisitor.service.impl;

import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import pers.pengxiang.repository.leetcode.common.filevisitor.model.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.CreateFileService;
import pers.pengxiang.repository.utils.file.PathUtils;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:00:47
 */
public class CreateSqlShellFileService implements CreateFileService {
    @Override
    public Set<String> getExtension() {
        return SQL_AND_SHELL;
    }

    @Override
    public void accept(LeetcodeFileInfo fileInfo, Path file, Path newFolder) {
        String extension = fileInfo.getExtension();
        String newFileName = fileInfo.getInterfaceName();
        Path target = newFolder.resolve(newFileName + "." + extension);
        PathUtils.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
    }
}