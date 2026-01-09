package sbp.io;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MyIOExample
{
    /**
     * Создать объект класса {@link java.io.File}, проверить существование и чем является (файл или директория).
     * Если сущность существует, то вывести в консоль информацию:
     *      - абсолютный путь
     *      - родительский путь
     * Если сущность является файлом, то вывести в консоль:
     *      - размер
     *      - время последнего изменения
     * Необходимо использовать класс {@link java.io.File}
     * @param fileName - имя файла
     * @return - true, если файл успешно создан
     */
    public boolean workWithFile(String fileName)
    {
        File file = new File(fileName);

        System.out.println("Проверка сущности: " + fileName);

        if (file.exists()) {
            System.out.println("Абсолютный путь: " + file.getAbsolutePath());
            System.out.println("Родительский путь: " + (file.getParent() != null ? file.getParent() : "отсутствует"));

            if (file.isFile()) {
                System.out.println("Размер: " + file.length() + " байт");
                System.out.println("Время последнего изменения: " + file.lastModified());
            }

            return true;
        }

        return false;
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.FileInputStream} и {@link java.io.FileOutputStream}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyFile(String sourceFileName, String destinationFileName)
    {
        try (FileInputStream fis = new FileInputStream(sourceFileName);
             FileOutputStream fos = new FileOutputStream(destinationFileName)) {

            int bytesRead;
            while ((bytesRead = fis.read()) != -1) {
                fos.write( bytesRead);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при копировании файла: " + e.getMessage());
            return false;
        }
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.BufferedInputStream} и {@link java.io.BufferedOutputStream}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyBufferedFile(String sourceFileName, String destinationFileName)
    {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFileName));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destinationFileName))) {

            int bytesRead;
            while ((bytesRead = bis.read()) != -1) {
                bos.write(bytesRead);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при буферизованном копировании файла: " + e.getMessage());
            return false;
        }
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.FileReader} и {@link java.io.FileWriter}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyFileWithReaderAndWriter(String sourceFileName, String destinationFileName)
    {
        try (FileReader fr = new FileReader(sourceFileName);
             FileWriter fw = new FileWriter(destinationFileName)) {

            int charsRead;
            while ((charsRead = fr.read()) != -1) {
                fw.write(charsRead);
            }

            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при копировании с использованием Reader/Writer: " + e.getMessage());
            return false;
        }
    }
}
