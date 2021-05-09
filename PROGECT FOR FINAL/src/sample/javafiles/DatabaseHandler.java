package sample.javafiles;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends Configs {
    final static String connectionString = "jdbc:sqlserver://" + dbHost + "; databaseName = " + dbName ;
    static Connection dbConnection = null;
    final static String SELECT_QUERY =
            "SELECT taskId, task, taskDate FROM tasks";

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_FIRSTNAME + "," +Const.USERS_LASTNAME
                + "," + Const.USERS_LOGIN + "," + Const.USERS_PASS + "," + Const.USERS_GENDER + ")"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getGender());

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USERS_LOGIN + " = ? AND " + Const.USERS_PASS + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    public static List<Tasks> init() {
        Statement statement = null;
        List<Tasks> tasks = new ArrayList<>();
        try {
            if (dbConnection == null) {
                dbConnection = DriverManager.getConnection(connectionString, Configs.dbUser, Configs.dbPass);
            }
            statement = dbConnection.createStatement();
            ResultSet res = statement.executeQuery(SELECT_QUERY);
            while (res.next()) {
                Tasks task = new Tasks();
                task.setTaskId(Integer.toString(res.getInt("taskId")));
                task.setTask(res.getString("task"));
                task.setTaskDate(res.getString("taskDate"));
                tasks.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }
    public static void addTask(Tasks task) {
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("INSERT INTO "+ Const.TASK_TABLE + "(" + Const.TASK_NAME + "," +
                    Const.TASK_DATE +") " + "VALUES ('" + task.getTask() +"','" + task.getTaskDate() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteTask(int id){
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("DELETE FROM " + Const.TASK_TABLE + " WHERE " + Const.TASK_ID + "=" + Integer.toString(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
