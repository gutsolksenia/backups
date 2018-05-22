package data;

import model.BackupServer;

import java.io.*;

public class BackupServerSerializer {
    private static final String FILE_PATH_PREFIX = "src/main/resources/";

    public static BackupServer deserialize(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream oin = new ObjectInputStream(fis);
            BackupServer server = (BackupServer) oin.readObject();
            return server;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void serialize(BackupServer backupServer, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(backupServer);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception while writing object to file: "+ e.getMessage());
        }
    }
    private BackupServerSerializer() {}
}