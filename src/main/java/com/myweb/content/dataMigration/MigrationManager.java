package com.myweb.content.dataMigration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.myweb.content.properties.FlywayProperties;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MigrationManager {

    private static final Logger log = LoggerFactory.getLogger(MigrationManager.class);

    private static Flyway flyway = new Flyway();

    private DataSource dataSource;

    private FlywayProperties flywayProperties;

    public MigrationManager(FlywayProperties flywayProperties, DataSource dataSource) {
        this.flywayProperties = flywayProperties;
        this.dataSource = dataSource;

    }

    @PostConstruct
    public void migrate() {
        if (flywayProperties.getIsEnableFlywayMigration()) {
            log.debug("Flyway Migrations enabled, running.");
            setupFlyway();
            if (flywayProperties.isRepair()) {
                flyway.repair();
            }
            flyway.migrate();
        } else {
            //do nothing
        }
    }

    private void setupFlyway() {
        this.flyway.setBaselineVersion(MigrationVersion.fromVersion(flywayProperties.getBaseLineVersion()));
        this.flyway.setTable(flywayProperties.getTable());
        this.flyway.setBaselineOnMigrate(flywayProperties.isBaseLineOnMigrate());
        this.flyway.setLocations(flywayProperties.getLocations());
        this.flyway.setSkipDefaultResolvers(flywayProperties.isSkipDefaultResolvers());
        this.flyway.setOutOfOrder(flywayProperties.isOutOfOrder());
        this.flyway.setDataSource(dataSource);
    }


}
