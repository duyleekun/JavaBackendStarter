package migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 * Example of a Java-based migration using Spring JDBC.
 */
public class V20200303134925__migrate_java extends BaseJavaMigration {
    public void migrate(Context context) {
        SingleConnectionDataSource ds = new SingleConnectionDataSource(context.getConnection(), true);
        System.out.println("This is java migration");
        new JdbcTemplate(ds).query("select * from users", (rs -> {

        }));
    }
}
