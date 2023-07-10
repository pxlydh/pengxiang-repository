package pers.pengxiang.repository.leetcode.common.filtervisitor.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filtervisitor.service.CreateFileService;
import pers.pengxiang.repository.utils.io.PathUtils;

/**
 * 创建 Leetcode 题目对应的题目描述文件
 *
 * @author pengxiang
 * Create on 2023-06-30 00:40:12
 */
@Service
public class CreateProblemFileService implements CreateFileService {
    @Override
    public Set<String> getExtension() {
        return ALL_EXTENSION;
    }

    @SneakyThrows
    @Override
    public void accept(LeetcodeFileInfo fileInfo, Path file, Path newFolder) {
        String interfaceName = fileInfo.getInterfaceName();
        Path markdownPath = file.getParent().resolve("doc").resolve("content");
        Path markdown = markdownPath.resolve(fileInfo.getPrefix() + ".md");
        String newFolderPath = newFolder.toFile().getCanonicalPath();
        Path newMarkdownPath = Paths.get(newFolderPath.replace("java", "resources"));
        Path newMarkdown = newMarkdownPath.resolve(interfaceName + ".md");
        PathUtils.move(markdown, newMarkdown, StandardCopyOption.REPLACE_EXISTING);
    }
}