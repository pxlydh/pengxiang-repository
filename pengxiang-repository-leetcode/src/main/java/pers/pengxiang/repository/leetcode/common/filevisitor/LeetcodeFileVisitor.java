package pers.pengxiang.repository.leetcode.common.filevisitor;

import static pers.pengxiang.repository.leetcode.common.filevisitor.service.CreateFileService.ALL_EXTENSION;
import static pers.pengxiang.repository.utils.collection.ListUtils.indexOf;
import static pers.pengxiang.repository.utils.collection.ListUtils.lastIndexOf;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filevisitor.computer.PackageNameComputer;
import pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl.InterviewPackageNameComputer;
import pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl.LcpLcsPackageNameComputer;
import pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl.Offer2PackageNameComputer;
import pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl.OfferPackageNameComputer;
import pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl.OriginalPackageNameComputer;
import pers.pengxiang.repository.leetcode.common.filevisitor.model.LeetcodeFileInfo;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.CreateFileService;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.impl.CreateImplementFileService;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.impl.CreateInterfaceFileService;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.impl.CreateProblemFileService;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.impl.CreateSolutionFileService;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.impl.CreateSqlShellFileService;
import pers.pengxiang.repository.leetcode.common.filevisitor.service.utils.BeginWith;
import pers.pengxiang.repository.utils.file.PathUtils;

/**
 * @author pengxiang
 * Create on 2022-11-15 22:24:28
 */
public class LeetcodeFileVisitor implements FileVisitor<Path> {
    private final List<CreateFileService> createFileServiceList = List.of(
            new CreateProblemFileService(),
            new CreateSolutionFileService(),
            new CreateSqlShellFileService(),
            new CreateImplementFileService(),
            new CreateInterfaceFileService()
    );

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @SneakyThrows
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String extension = PathUtils.getExtension(file);
        if (!ALL_EXTENSION.contains(extension)) {
            return FileVisitResult.CONTINUE;
        }
        LeetcodeFileInfo fileInfo = getLeetcodeFileInfo(file);
        Path leetcodeFolder = file.getParent().getParent().getParent();
        String path = fileInfo.getPackageName().replace(".", "/");
        Path newFolder = leetcodeFolder.resolve(path);
        for (CreateFileService service : createFileServiceList) {
            if (service.getExtension().contains(extension)) {
                service.accept(fileInfo, file, newFolder);
            }
        }
        PathUtils.move(file, Paths.get("temp/" + file.toFile().getName()), StandardCopyOption.REPLACE_EXISTING);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    /**
     * 获取 leetcode 题目文件信息
     *
     * @param file leetcode 题目文件
     * @return leetcode 题目文件信息
     */
    @SneakyThrows
    private LeetcodeFileInfo getLeetcodeFileInfo(Path file) {
        String fileName = file.toFile().getName();
        List<String> content = Files.readAllLines(file);
        int dot = fileName.lastIndexOf('.'), dollar = fileName.indexOf('$');
        String prefix = fileName.substring(0, dot);
        String extension = fileName.substring(dot + 1);
        String questionName = prefix.substring(dollar + 1);
        String questionId = prefix.substring(0, dollar + 1).replace("$", "_");
        String packageName = getPackageName(questionId);

        return LeetcodeFileInfo.builder()
                .prefix(prefix)
                .extension(extension)
                .questionId(questionId)
                .questionName(questionName)
                .interfaceName(getInterfaceName(questionName))
                .packageName(packageName)
                .imports(getImports(content))
                .description(getDescription(content))
                .methods(getMethods(content))
                .classes(getClasses(content))
                .packageStatement(content.get(0).replace("editor.cn", packageName)).build();
    }

    /**
     * 根据 leetcode 题目名称获取对应的接口名称
     *
     * @param questionName leetcode 题目名称
     * @return 接口名称
     */
    private String getInterfaceName(String questionName) {
        if (questionName.endsWith("Ii")) {
            questionName = questionName.replace("Ii", "");
        } else if (questionName.endsWith("Iii")) {
            questionName = questionName.replace("Iii", "");
        } else if (questionName.endsWith("Iv")) {
            questionName = questionName.replace("Iv", "");
        }
        return questionName.replace("Lcci", "").replace("Bst", "BinarySearchTree");
    }

    /**
     * 计算指定 leetcode 文件期望的包名
     *
     * @param questionId leetcode 题目编号
     * @return 期望的包名
     */
    private String getPackageName(String questionId) {
        PackageNameComputer packageNameComputer;
        if (questionId.contains("LCP") || questionId.contains("LCS")) {
            packageNameComputer = new LcpLcsPackageNameComputer();
        } else if (questionId.contains("剑指 Offer II")) {
            packageNameComputer = new Offer2PackageNameComputer();
        } else if (questionId.contains("剑指 Offer")) {
            packageNameComputer = new OfferPackageNameComputer();
        } else if (questionId.contains("面试题")) {
            packageNameComputer = new InterviewPackageNameComputer();
        } else {
            packageNameComputer = new OriginalPackageNameComputer();
        }
        return packageNameComputer.getPackageName(questionId);
    }

    /**
     * 获取 leetcode 代码中所有导包语句
     *
     * @param content leetcode 代码
     * @return content 中所有导包语句
     */
    private String getImports(List<String> content) {
        BeginWith beginWith = BeginWith.of("import");
        int begin = indexOf(content, 0, beginWith);
        if (begin == -1) {
            return "";
        }
        int n = content.size();
        int end = lastIndexOf(content, n - 1, beginWith);
        return String.join("\n", content.subList(begin, end + 1));
    }

    /**
     * 获取 leetcode 代码中所有实现类
     *
     * @param content leetcode 代码
     * @return content 中所有实现类
     */
    private List<List<String>> getClasses(List<String> content) {
        List<List<String>> classes = new ArrayList<>();
        int n = content.size();
        for (int begin = 0, end; begin < n; begin = end + 1) {
            begin = indexOf(content, begin, BeginWith.of("class "));
            if (begin == -1) {
                break;
            }
            begin = lastIndexOf(content, begin, BeginWith.of("/**"));
            end = indexOf(content, begin, "}"::equals);
            classes.add(content.subList(begin, end + 1));
        }
        return classes;
    }

    /**
     * 获取 leetcode 代码中所有公有方法的签名
     *
     * @param content leetcode 代码
     * @return content 中所有公有方法的签名
     */
    private String getMethods(List<String> content) {
        return content.stream()
                .filter(BeginWith.of("    public "))
                .map(line -> line.replace("public ", ""))
                .map(line -> line.replace(" {", ""))
                .map(line -> line + ';')
                .distinct().collect(Collectors.joining("\n\n"));
    }

    /**
     * 获取 leetcode 代码中题目的描述信息
     *
     * @param content leetcode 代码
     * @return content 中 leetcode 题目的描述信息
     */
    private String getDescription(List<String> content) {
        int begin = indexOf(content, 0, BeginWith.of("/**"));
        int end = indexOf(content, begin, " */"::equals);
        return String.join("\n", content.subList(begin, end + 1));
    }
}