package data;

import factory.RandomBackupServerFactory;
import model.BackupServer;

public class BackupServerGenerator {
    private static final String FILENAME_PREFIX = "src/main/resources/server_";
    public static final int SERVERS_COUNT = 10;

    public static void main(String[] args) {
        for (int i = 0; i < SERVERS_COUNT; i++) {
            BackupServer server = RandomBackupServerFactory.getBackupServer();
            BackupServerSerializer.serialize(server, getFilename(i));
        }
    }

    public static String getFilename(int serverId) {
        return FILENAME_PREFIX + Integer.toString(serverId);
    }
}