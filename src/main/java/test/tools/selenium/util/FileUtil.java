package test.tools.selenium.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class FileUtil {
    private static FileUtil instance;

    // 7 gün'ün milisaniye değeri = 604800000
    public static final long DISTANCEFROMCREATEDDATE = 604800000;

    public static FileUtil getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }
    }

    /**
     * @param dir
     */
    public void createDirectory(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public void copyFile(File srcFile, File destFile) throws IOException {
        FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * @param root
     * @param datas
     * @param tokenId
     */
    @SuppressWarnings("deprecation")
    public void writeDatasInLogFile(String root, String datas, String tokenId) {
        PrintWriter pWriter = null;
        File file = new File(root + ".log");
        String data = null;
        try {
            String log = FileUtils.readFileToString(file);

            pWriter = new PrintWriter(root + ".log");
            data = "Kullanilan veriler: -" + datas;

            for (int i = 0; i < data.split("-").length; i++) {

                pWriter.println(data.split("-")[i]);
            }
            pWriter.println("");
            pWriter.println("Log: ");
            pWriter.println(log);

        } catch (Exception e) {
            // TODO Auto-generated catch block
        } finally {
            if (pWriter != null)
                pWriter.close();
        }

    }

    /**
     * @param file
     */
    public void deleteFileListing(File file) {

        File[] directoryListing = file.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    deleteOlderFile(child, DISTANCEFROMCREATEDDATE);
                    if (child.isDirectory()) {
                        deleteFileListing(child);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                }
            }
        }
    }

    /**
     * @param file
     * @param time
     */
    public void deleteOlderFile(File file, Long time) {

        try {

            Path path = file.toPath();
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime creationTime = attributes.creationTime();
            long epoch = creationTime.toMillis();
            Date currentTime = new Date();
            if (time <= (currentTime.getTime() - epoch)) {
                if (file.isDirectory()) {
                    FileUtils.deleteDirectory(new File(file.getCanonicalPath()));
                } else if (file.isFile()) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }

    }

    /**
     * @param path
     */
    public void deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 5; i <= files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        path.delete();
    }
}
