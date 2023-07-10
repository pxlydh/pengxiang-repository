package pers.pengxiang.repository.leetcode.common.filtervisitor.service.impl;

import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import org.springframework.stereotype.Service;

import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filtervisitor.service.CreateFileService;
import pers.pengxiang.repository.utils.io.PathUtils;

/**
 * @author pengxiang
 * Create on 2023-06-30 00:42:52
 */
@Service
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