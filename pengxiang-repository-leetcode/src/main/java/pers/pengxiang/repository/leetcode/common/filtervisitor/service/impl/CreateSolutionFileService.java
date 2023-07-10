package pers.pengxiang.repository.leetcode.common.filtervisitor.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filtervisitor.service.CreateFileService;
import pers.pengxiang.repository.utils.collection.ListUtils;
import pers.pengxiang.repository.utils.function.predicate.BeginWith;
import pers.pengxiang.repository.utils.io.PathUtils;

/**
 * 创建 Leetcode 题目对应的解析文件
 *
 * @author pengxiang
 * Create on 2023-06-30 00:40:40
 */
@Service
public class CreateSolutionFileService implements CreateFileService {
    @Override
    public Set<String> getExtension() {
        return JAVA;
    }

    @SneakyThrows
    @Override
    public void accept(LeetcodeFileInfo fileInfo, Path file, Path newFolder) {
        String interfaceName = fileInfo.getInterfaceName();
        Path markdownPath = file.getParent().resolve("doc").resolve("note");
        Path markdown = markdownPath.resolve(fileInfo.getPrefix() + ".md");
        String newFolderPath = newFolder.toFile().getCanonicalPath();
        Path newMarkdownPath = Paths.get(newFolderPath.replace("java", "resources"));
        Path newMarkdown = newMarkdownPath.resolve(interfaceName + ".note.md");
        if (Files.notExists(markdown)) {
            if ("java".equals(fileInfo.getExtension())) {
                createSolutionMarkdown(fileInfo, newMarkdown);
            }
        } else {
            PathUtils.move(markdown, newMarkdown, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * 根据 leetcode 文件信息，创建题解文件
     *
     * @param fileInfo    leetcode 文件信息
     * @param newMarkdown 题解文件的路径
     */
    private void createSolutionMarkdown(LeetcodeFileInfo fileInfo, Path newMarkdown) {
        StringBuilder content = new StringBuilder();
        String description = fileInfo.getDescription();
        int begin = description.indexOf('>') + 1;
        int end = description.indexOf('<', begin);
        content.append("## ").append(description, begin, end).append("\n\n");
        List<List<String>> classes = fileInfo.getClasses();
        for (int i = 0; i < classes.size(); i++) {
            List<String> statement = classes.get(i);
            int classBegin = ListUtils.indexOf(statement, new BeginWith("class "));
            content.append("### ").append(i + 1).append(". ");
            int index = ListUtils.indexOf(statement, new BeginWith(" * 解题思路："));
            if (index >= 0) {
                String solution = statement.get(index);
                content.append(solution.replace(" * 解题思路：", "")).append("\n\n");
            } else {
                content.append("未知名称\n\n");
            }
            content.append("```java\nclass Solution {\n");
            for (int j = classBegin + 1; j < statement.size(); j++) {
                content.append(statement.get(j)).append('\n');
            }
            content.append("```\n\n");
        }
        PathUtils.writeString(newMarkdown, content, StandardOpenOption.CREATE);
    }
}