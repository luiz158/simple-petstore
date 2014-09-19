package org.testinfected.petstore;

import com.googlecode.flyway.core.Flyway;
import org.testinfected.petstore.db.DatabaseCleaner;
import org.testinfected.petstore.db.support.DriverManagerDataSource;
import com.googlecode.flyway.core.util.logging.Log;
import com.googlecode.flyway.core.util.logging.LogFactory;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Migrations {
    private static final Log LOG = LogFactory.getLog(Flyway.class);

    private static final int ENVIRONMENT = 1;
    private static final int ACTION = 2;

    private final Flyway flyway;
    private final DatabaseCleaner databaseCleaner;
    private final DataSource dataSource;

    public Migrations(DataSource dataSource) {
        flyway = new Flyway();
        this.dataSource = dataSource;
        flyway.setDataSource(dataSource);
        flyway.setSqlMigrationPrefix("");
        databaseCleaner = new DatabaseCleaner(dataSource);
    }

    public void migrate() {
        flyway.migrate();
    }

    public void clean() throws Exception {
        databaseCleaner.clean();
    }

    public void drop() {
        flyway.clean();
    }

    public void init() {
        flyway.init();
    }

    public void populate(File file) throws SQLException, IOException{
        if(!file.isFile())
            throw new FileNotFoundException(file.getPath());
        final String delimiter = ";";
        Connection connection = dataSource.getConnection();
        try (Scanner scanner = new Scanner(file).useDelimiter(delimiter)) {
            int cpt = 0;
            while(scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                cpt++;
                try (Statement statement = connection.createStatement()) {
                    // Execute statement
                    statement.execute(rawStatement);
                }
            }
            connection.commit();
            LOG.info(Integer.toString(cpt)+" statements loaded");
        }catch(Exception e){
            connection.rollback();
            throw e;
        }
    }

    public void reset() {
        flyway.clean();
        flyway.migrate();
    }

    public static void main(String... args) throws Exception {
        try {
            LOG.info("main - start");
            Environment env = Environment.load(args[ENVIRONMENT]);

            DataSource dataSource = new DriverManagerDataSource(env.databaseUrl, env.databaseUsername, env.databasePassword);
            Migrations migrations = new Migrations(dataSource);
            String action = args[ACTION];
            LOG.info("main - action: "+action);
            if (action.equals("migrate")) migrations.migrate();
            else if (action.equals("drop")) migrations.drop();
            else if (action.equals("init")) migrations.init();
            else if (action.equals("reset")) migrations.reset();
            else if (action.equals("clean")) migrations.clean();
            else if (action.equals("populate")) migrations.populate(new File("server/src/main/scripts/seeds/items.sql"));
        }catch(Exception e){
            LOG.error("error", e);
            throw e;
        }
    }
}
