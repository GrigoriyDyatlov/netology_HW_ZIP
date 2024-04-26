import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(99, 190, 6, 0.34);
        GameProgress player2 = new GameProgress(88, 67, 10, 0.55);
        GameProgress player3 = new GameProgress(77, 10, 2, 0.02);
        List<String> pathList = new ArrayList<>();
        pathList.add("C:\\games\\Games\\savegames\\player1.dat");
        pathList.add("C:\\games\\Games\\savegames\\player2.dat");
        pathList.add("C:\\games\\Games\\savegames\\player3.dat");

        saveGame("C:\\games\\Games\\savegames\\player1.dat", player1);
        saveGame("C:\\games\\Games\\savegames\\player2.dat", player2);// "C:\\games\\Games\\savegames");
        saveGame("C:\\games\\Games\\savegames\\player3.dat", player3);
        zipFiles("C:\\games\\Games\\savegames\\zip.zip", pathList);

        for (int i = 0; i < pathList.size(); i++) {
            deleteFile(pathList.get(i));
        }


    }

    public static void saveGame(String path, GameProgress player) {
        File gamesSavegames = new File(path);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(player);
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }

    }

    public static void zipFiles(String zip_path, List<String> pathList) {
        for (int i = 0; i < pathList.size() - 1; i++) {
            try {
                ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip_path));
                try {
                    FileInputStream fis = new FileInputStream(pathList.get(i));
                    ZipEntry entry = new ZipEntry("zip_player" + i + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException iox) {
                    System.out.println(iox.getMessage());
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

        public static void deleteFile(String path) {
            File file = new File(path);
            if (file.delete()) {
                System.out.println("Deleted");
            } else System.out.println("Exception delete");
        }
    }

