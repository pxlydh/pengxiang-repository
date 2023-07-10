package pers.pengxiang.repository.leetcode.common.filtervisitor.service.impl;

import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filtervisitor.service.CreateFileService;
import pers.pengxiang.repository.utils.io.PathUtils;

/**
 * 创建 Leetcode 题目对应的实现类文件
 *
 * @author pengxiang
 * Create on 2023-06-30 00:30:11
 */
@Service
public class CreateImplementFileService implements CreateFileService {
    private static final String CLASS_BEGIN = "public class {0} implements {1} '{'";

    @Override
    public Set<String> getExtension() {
        return JAVA;
    }

    @SneakyThrows
    @Override
    public void accept(LeetcodeFileInfo fileInfo, Path file, Path newFolder) {
        String imports = fileInfo.getImports();
        for (List<String> code : fileInfo.getClasses()) {
            StringBuilder statement = new StringBuilder();
            statement.append(fileInfo.getPackageStatement()).append("\n\n");
            if (!imports.isEmpty()) {
                statement.append(imports).append("\n\n");
            }
            String className = "";
            for (String line : code) {
                if (line.startsWith("    public ")) {
                    statement.append("    @Override\n");
                }
                if (line.startsWith("class")) {
                    line = line.replace("class ", "");
                    int under = line.lastIndexOf("_");
                    line = line.substring(under + 1);
                    className = line.replace(" {", "");
                    line = MessageFormat.format(CLASS_BEGIN, className, fileInfo.getInterfaceName());
                }
                statement.append(line).append('\n');
            }
            statement.deleteCharAt(statement.length() - 1);
            if (className.contains("extends")) {
                className = className.split(" ")[0];
            }
            if (!className.endsWith("Impl")) {
                System.out.println("类" + className + "名称不是以 Impl 结尾");
            }
            PathUtils.writeString(newFolder.resolve(className + ".java"), statement, StandardOpenOption.CREATE);
        }
    }
}