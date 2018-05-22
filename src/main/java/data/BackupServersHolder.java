package data;

import model.BackupServer;

import java.util.ArrayList;
import java.util.List;

public class BackupServersHolder {

    public static final List<BackupServer> SERVERS = new ArrayList<>();

    static {
        for (int i = 0; i < BackupServerGenerator.SERVERS_COUNT; i++) {
            SERVERS.add(BackupServerSerializer.deserialize(
                    BackupServerGenerator.getFilename(i))
            );
        }
    }
}