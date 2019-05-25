package com.myweb.content.properties;

public class FlywayProperties {

    private boolean baseLineOnMigrate;

    private String table;

    private String baseLineVersion;

    private boolean skipDefaultResolvers;

    private String[] locations;

    private boolean isEnableFlywayMigration;

    private boolean outOfOrder;

    private boolean repair;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public boolean isBaseLineOnMigrate() {
        return baseLineOnMigrate;
    }

    public void setBaseLineOnMigrate(boolean baseLineOnMigrate) {
        this.baseLineOnMigrate = baseLineOnMigrate;
    }

    public String getBaseLineVersion() {
        return baseLineVersion;
    }

    public void setBaseLineVersion(String baseLineVersion) {
        this.baseLineVersion = baseLineVersion;
    }

    public boolean isSkipDefaultResolvers() {
        return skipDefaultResolvers;
    }

    public void setSkipDefaultResolvers(boolean skipDefaultResolvers) {
        this.skipDefaultResolvers = skipDefaultResolvers;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] path) {
        this.locations = path;
    }

    public boolean getIsEnableFlywayMigration() {
        return isEnableFlywayMigration;
    }

    public void setIsEnableFlywayMigration(boolean enableFlywayMigration) {
        isEnableFlywayMigration = enableFlywayMigration;
    }

    public boolean isOutOfOrder() {
        return outOfOrder;
    }

    public void setOutOfOrder(boolean outOfOrder) {
        this.outOfOrder = outOfOrder;
    }

    public boolean isRepair() {
        return repair;
    }

    public void setRepair(boolean repair) {
        this.repair = repair;
    }
}
