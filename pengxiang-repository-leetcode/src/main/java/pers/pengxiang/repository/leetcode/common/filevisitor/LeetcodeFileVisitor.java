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
     * ?????? leetcode ??????????????????
     *
     * @param file leetcode ????????????
     * @return leetcode ??????????????????
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
     * ?????? leetcode ???????????????????????????????????????
     *
     * @param questionName leetcode ????????????
     * @return ????????????
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
     * ???????????? leetcode ?????????????????????
     *
     * @param questionId leetcode ????????????
     * @return ???????????????
     */
    private String getPackageName(String questionId) {
        PackageNameComputer packageNameComputer;
        if (questionId.contains("LCP") || questionId.contains("LCS")) {
            packageNameComputer = new LcpLcsPackageNameComputer();
        } else if (questionId.contains("?????? Offer II")) {
            packageNameComputer = new Offer2PackageNameComputer();
        } else if (questionId.contains("?????? Offer")) {
            packageNameComputer = new OfferPackageNameComputer();
        } else if (questionId.contains("?????????")) {
            packageNameComputer = new InterviewPackageNameComputer();
        } else {
            packageNameComputer = new OriginalPackageNameComputer();
        }
        return packageNameComputer.getPackageName(questionId);
    }

    /**
     * ?????? leetcode ???????????????????????????
     *
     * @param content leetcode ??????
     * @return content ?????????????????????
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
     * ?????? leetcode ????????????????????????
     *
     * @param content leetcode ??????
     * @return content ??????????????????
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
     * ?????? leetcode ????????????????????????????????????
     *
     * @param content leetcode ??????
     * @return content ??????????????????????????????
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
     * ?????? leetcode ??????????????????????????????
     *
     * @param content leetcode ??????
     * @return content ??? leetcode ?????????????????????
     */
    private String getDescription(List<String> content) {
        int begin = indexOf(content, 0, BeginWith.of("/**"));
        int end = indexOf(content, begin, " */"::equals);
        return String.join("\n", content.subList(begin, end + 1));
    }
}