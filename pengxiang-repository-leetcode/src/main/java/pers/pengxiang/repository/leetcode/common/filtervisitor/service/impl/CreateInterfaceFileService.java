package pers.pengxiang.repository.leetcode.common.filtervisitor.service.impl;

import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filtervisitor.service.CreateFileService;
import pers.pengxiang.repository.utils.io.PathUtils;

/**
 * 创建 Leetcode 题目对应的接口文件
 *
 * @author pengxiang
 * Create on 2023-06-30 00:39:20
 */
@Service
public class CreateInterfaceFileService implements CreateFileService {
    private static final String INTERFACE_BEGIN = "public interface {0} '{'";

    @Override
    public Set<String> getExtension() {
        return JAVA;
    }

    @SneakyThrows
    @Override
    public void accept(LeetcodeFileInfo fileInfo, Path file, Path newFolder) {
        StringBuilder statement = new StringBuilder();
        statement.append(fileInfo.getPackageStatement()).append("\n\n");
        String imports = fileInfo.getImports();
        if (!imports.isEmpty()) {
            statement.append(imports).append("\n\n");
        }
        statement.append(fileInfo.getDescription()).append('\n');
        String interfaceName = fileInfo.getInterfaceName();
        statement.append(MessageFormat.format(INTERFACE_BEGIN, interfaceName)).append('\n');
        statement.append(fileInfo.getMethods()).append('\n');
        statement.append("}");
        PathUtils.writeString(newFolder.resolve(interfaceName + ".java"), statement, StandardOpenOption.CREATE);
    }
}