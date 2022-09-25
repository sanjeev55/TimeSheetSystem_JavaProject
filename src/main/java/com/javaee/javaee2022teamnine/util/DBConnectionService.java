package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.model.User;

import java.sql.*;


public class DBConnectionService {

    Connection connection;
    Statement statement;


    public DBConnectionService() {
        try {
            String url = "jdbc:mariadb://localhost:3306/javaee";
            String user = "root";
            String password = "%password%";

            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();


            // TODO: Add foreign key relations
            String userTable = "users";

            String userSql = "CREATE TABLE IF NOT EXISTS " + userTable
                    + " (id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY , "
                    + " fullName VARCHAR(255), "
                    + " username VARCHAR(255), "
                    + " password VARCHAR(255), "
                    + " dob DATE, "
                    + " tos BOOLEAN, "
                    + " federalState VARCHAR(255), "
                    + " role VARCHAR(10))";

            // ALL ENUM TABLES
            String roleSql = "CREATE TABLE IF NOT EXISTS roles " +
                    "(role_id int NOT NULL, " +
                    "role VARCHAR(10), " +
                    "PRIMARY KEY (role_id)) ";
//                    "FOREIGN KEY (id) REFERENCES users(role))";

            String roleInsert = "INSERT IGNORE roles(role_id, role) VALUES " +
                    "(1, 'EMPLOYEE')," +
                    "(2, 'ASSISTANT')," +
                    "(3, 'SECRETARY')," +
                    "(4, 'SUPERVISOR')";

            String contractStatusSql = "CREATE TABLE IF NOT EXISTS contract_status " +
                    "(contract_status_id int NOT NULL, " +
                    "contract_status VARCHAR(25), " +
                    "PRIMARY KEY (contract_status_id)) ";
            String contractStatusInsert = "INSERT IGNORE contract_status(contract_status_id, contract_status) VALUES " +
                    "(1, 'PREPARED')," +
                    "(2, 'STARTED')," +
                    "(3, 'TERMINATED')," +
                    "(4, 'ARCHIVED')";

            String TimesheetFrequencySql = "CREATE TABLE IF NOT EXISTS timesheet_frequency " +
                    "(ts_frequency_id int NOT NULL, " +
                    "timesheet_frequency VARCHAR(25), " +
                    "PRIMARY KEY (ts_frequency_id)) ";
            String TimesheetFrequencyInsert = "INSERT IGNORE timesheet_frequency(ts_frequency_id, timesheet_frequency) VALUES " +
                    "(1, 'WEEKLY')," +
                    "(2, 'MONTHLY')";

            String TimesheetStatusSql = "CREATE TABLE IF NOT EXISTS timesheet_status " +
                    "(ts_status_id int NOT NULL, " +
                    "timesheet_status VARCHAR(25), " +
                    "PRIMARY KEY (ts_status_id)) ";
            String TimesheetStatusInsert = "INSERT IGNORE timesheet_status(ts_status_id, timesheet_status) VALUES " +
                    "(1, 'IN_PROGRESS')," +
                    "(2, 'SIGNED_BY_EMPLOYEE')," +
                    "(3, 'SIGNED_BY_SUPERVISOR')," +
                    "(4, 'ARCHIVED')";

            String ReportTypeSql = "CREATE TABLE IF NOT EXISTS report_type " +
                    "(report_type_id int NOT NULL, " +
                    "report_type VARCHAR(25), " +
                    "PRIMARY KEY (report_type_id))";

            String ReportTypeInsert = "INSERT IGNORE report_type(report_type_id, report_type) VALUES " +
                    "(1, 'WORK')," +
                    "(2, 'VACATION')," +
                    "(3, 'SICK_LEAVE')";
            // END ENUM TABLES

            // Foreign Keys
            String fk_contract_status = "ALTER TABLE contract " +
//                    "ADD CONSTRAINT FK_contractStatus " +
                    "ADD FOREIGN KEY (c_status) REFERENCES contract_status(contract_status_id);";

            statement.executeUpdate(userSql);

            statement.executeUpdate(roleSql);
            statement.executeUpdate(roleInsert);

            statement.executeUpdate(contractStatusSql);
            statement.executeUpdate(contractStatusInsert);

            statement.executeUpdate(TimesheetFrequencySql);
            statement.executeUpdate(TimesheetFrequencyInsert);

            statement.executeUpdate(TimesheetStatusSql);
            statement.executeUpdate(TimesheetStatusInsert);

            statement.executeUpdate(ReportTypeSql);
            statement.executeUpdate(ReportTypeInsert);

            String contractSql = createContractTable();
            statement.executeUpdate(contractSql);
            statement.executeUpdate(fk_contract_status);
//            System.out.println("Table " + userTable + " has been created!");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String createContractTable() {
        return "CREATE TABLE IF NOT EXISTS contract"
                + " (contract_id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY , "
                + " c_status int, "
                + " userId int, "
                + " name VARCHAR(255), "
                + " start_date DATE, "
                + " end_date DATE, "
                + " frequency VARCHAR(255), "  //todo: add fk
                + " termination_date DATE, "
                + " hours_per_week DOUBLE, "
                + " vacation_hours DOUBLE, "
                + " hours_due DOUBLE, "
                + " working_days_per_week int, "
                + " vacation_days_per_year int);";
    }

    public Connection initDB() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mariadb://localhost:3306/javaee", "root", "%password%");
    }

    public void registerUser(User user) {
        String insertQuery = "Insert into users(fullname, username, password, tos, role) values(?, ?, ?, ? ,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertQuery);
//            ps.setInt(1, user.getId());
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
//            ps.setDate(5, user.getDob());
            ps.setBoolean(4, user.isTos());
            ps.setString(5, user.getRole());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
