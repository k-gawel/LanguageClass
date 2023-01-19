package utils.dummyrepositories;

import com.google.common.io.Files;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Generator {

    public Generator() throws IOException {
    }

    private record RepositoryProperties(
           String sourceName,
           String fileName,
           String className,
           String interfaceName,
           String classImportLine,
           String interfaceImportLine
    ) {}

    private static final String moduleName = "answer-and-evaluation";
    private static final String projectPath = System.getProperty("user.dir") + "\\";

    private static final String targetPath = projectPath + moduleName + "\\" + "src/test/java/dummyrepositories".replaceAll("/", "\\\\");
    private static final String repositoriesPackage = projectPath  + moduleName + "\\" + "src/main/java/language/contentandrepository/repository".replaceAll("/", "\\\\");

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> successfulFiles = new ArrayList<>();
        List<String> unsuccessfulFiles = new ArrayList<>();

        var properties = getProperties(unsuccessfulFiles, successfulFiles);
        deleteFilesIfExists(properties);
        for (RepositoryProperties property : properties) {
            try {
                createFile(property);
                successfulFiles.add(property.sourceName());
            } catch (IOException e) {
                e.printStackTrace();
                unsuccessfulFiles.add(property.sourceName());
            }
        }

        System.out.println("Successful files");
        successfulFiles.forEach(System.out::println);
        System.out.println("Unsucessfull: ");
        unsuccessfulFiles.forEach(System.out::println);
    }

    private static void createFile(RepositoryProperties properties) throws IOException {
        var file = new File(properties.fileName);
        var sourceCode = generateSourceCode(properties.className(), properties.interfaceName(), properties.classImportLine(), properties.interfaceImportLine());

            Files.write(sourceCode.getBytes(StandardCharsets.UTF_8), file);
    }

    private static void deleteFilesIfExists(List<RepositoryProperties> properties) {
        properties.stream()
                .map(RepositoryProperties::fileName)
                .map(File::new)
                .filter(File::exists)
                .forEach(f -> {
                    var fileName = f.getAbsolutePath();
                    f.delete();
                    System.out.println("DELETED: " + fileName);
                });
    }

    private static List<RepositoryProperties> getProperties(List<String> unsuccessfulFiles, List<String> successfulFiles) {
        var filesToMap = getRepositoriesList();
        var properties = new ArrayList<RepositoryProperties>();

        for (String path : filesToMap) {
            try {
                var source = new JavaProjectBuilder().addSource(new File(path));
                var property = new RepositoryProperties(
                        path,
                        generateFileName(source),
                        getClassName(source),
                        getInterfaceName(source),
                        getClassImportLine(source),
                        getInterfaceImportLine(source)
                );
                properties.add(property);
                successfulFiles.add(path);
            } catch (Exception e) {
                unsuccessfulFiles.add(path);
                System.err.println(path);
                e.printStackTrace();
            }
        }

        return properties;
    }


    private static String getInterfaceImportLine(JavaSource source) {
        return "import " +
                source.getPackage().toString().replaceFirst("package ", "") +
                "." +
                source.getClasses().get(0).getName() +
                ";";
    }

    private static String getInterfaceName(JavaSource source) {
        return source.getClasses().get(0).getName();
    }

    private static String getClassName(JavaSource source) throws IllegalStateException {
        var implementations = source.getClasses().get(0).getImplements();
        if(implementations.size() != 1)
            throw new IllegalStateException("Invalid number of implementations " + source.getClasses().get(0));
        return implementations.get(0).getGenericCanonicalName()
                .replaceAll(".+\\.", "")
                .replaceAll(">", "");
    }

    private static String getClassImportLine(JavaSource source) {
        var implementations = source.getClasses().get(0).getImplements();
        if(implementations.size() != 1)
            throw new IllegalStateException("Invalid number of implementations " + source.getClasses().get(0));
        return "import " +
                implementations.get(0).getGenericFullyQualifiedName()
                        .replaceAll(".+<", "")
                        .replaceAll(">", "")+
                ";";
    }

    private static List<String> getRepositoriesList() {
        System.out.println(repositoriesPackage);
        var directories = new File(repositoriesPackage).listFiles(File::isDirectory);
        return Arrays.stream(directories)
                .filter(f -> !f.getName().equals("impl"))
                .flatMap(f -> Arrays.stream(f.listFiles()))
                .map(File::getAbsolutePath)
                .toList();
    }

    private static String generateFileName(JavaSource source) {
        return targetPath + "\\" + "Test" + source.getClasses().get(0).getName() + "Factory.java";
    }

    private static String generateSourceCode(String className, String repositoryName, String importClass, String importRepository) {
        return "package dummyrepositories;\n" +
                "\n" +
                importClass + "\n" +
                importRepository + "\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public final class Test" + repositoryName + "Factory {\n" +
                "\n" +
                "    private static class Dummy" + repositoryName + " extends DummyContentRepository<"+ className + "> implements " + repositoryName + " {\n" +
                "        public Dummy" + repositoryName + "() {\n" +
                "            super(" + className + ".class);\n" +
                "        }\n" +
                "\n" +
                "        public Dummy" + repositoryName + "(List<" + className + "> list) {\n" +
                "            super(list, " + className + ".class);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static " + repositoryName + " emptyRepository() {\n" +
                "        return new Dummy" + repositoryName + "();\n" +
                "    }\n" +
                "\n" +
                "    public static " + repositoryName + " repository(List<" + className + "> list) {\n" +
                "        return new Dummy" + repositoryName + "(list);\n" +
                "    }\n" +
                "\n" +
                "}";
    }

    private final String testSourcePath = "C:\\Users\\kamil\\IdeaProjects\\LanguageClass\\answer-and-evaluation\\src\\main\\java\\language\\contentandrepository\\repository\\textbook\\TextbookRepository.java";

    private final JavaSource testSource = new JavaProjectBuilder()
            .addSource(new File(testSourcePath));

    private final RepositoryProperties testProperties = new RepositoryProperties(
            testSourcePath,
            "C:\\\\Users\\\\kamil\\\\IdeaProjects\\\\LanguageClass\\\\answer-and-evaluation\\\\src\\\\test\\\\java\\\\dummyrepositories\\\\TestTextbookRepositoryFactory.java",
            "Textbook",
            "TextbookRepository",
            "import language.contentandrepository.model.domain.textbook.Textbook;",
            "import language.contentandrepository.repository.textbook.TextbookRepository;"
    );

    @Test
    public void getClassImportLine() {
        Assertions.assertEquals(
                testProperties.classImportLine,
                getClassImportLine(testSource)
        );
    }

    @Test
    public void getClassNameTest() {
        Assertions.assertEquals(
                testProperties.className,
                getClassName(testSource)
        );
    }

    @Test
    public void getInterfaceName() {
        Assertions.assertEquals(
                testProperties.interfaceName(),
                getInterfaceName(testSource)
        );
    }

    @Test
    public void getInterfaceImportLine() throws IOException {
        Assertions.assertEquals(
                testProperties.interfaceImportLine(),
                getInterfaceImportLine(testSource)
        );
    }

    @Test
    public void generateFileNameTest() {
        Assertions.assertEquals(testProperties, generateFileName(testSource));
    }


    @Test
    public void generatorTest() {
        var correct = """
                         package dummyrepositories;
                         
                         import language.contentandrepository.model.domain.textbook.Textbook;
                         import language.contentandrepository.repository.textbook.TextbookRepository;
                         
                         import java.util.List;
                         
                         public final class TestTextbookRepositoryFactory {
                         
                             private static class DummyTextbookRepository extends DummyContentRepository<Textbook> implements TextbookRepository {
                                 public DummyTextbookRepository() {
                                     super(Textbook.class);
                                 }
                         
                                 public DummyTextbookRepository(List<Textbook> list) {
                                     super(list, Textbook.class);
                                 }
                             }
                         
                             public static TextbookRepository emptyRepository() {
                                 return new DummyTextbookRepository();
                             }
                         
                             public static TextbookRepository repository(List<Textbook> list) {
                                 return new DummyTextbookRepository(list);
                             }
                         
                         }""";

        var actual = generateSourceCode(testProperties.className(), testProperties.interfaceName(), testProperties.classImportLine(), testProperties.interfaceImportLine());

        assertEquals(correct, actual);
    }
}
