package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class SQLHelper {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static String getPaymentByCardStatus() {
        String query =
                "SELECT *\n" +
                        "FROM order_entity INNER JOIN payment_entity ON order_entity.payment_id=payment_entity.transaction_id\n" +
                        "WHERE order_entity.payment_id=payment_entity.transaction_id\n" +
                        "ORDER BY payment_entity.created DESC\n" +
                        "LIMIT 1";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("status");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "NO DATA";
    }

    public static String getPaymentOnCreditStatus() {
        String query =
                "SELECT *\n" +
                        "FROM order_entity INNER JOIN credit_request_entity ON order_entity.payment_id=credit_request_entity.bank_id\n" +
                        "WHERE order_entity.payment_id=credit_request_entity.bank_id\n" +
                        "ORDER BY credit_request_entity.created DESC\n" +
                        "LIMIT 1";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("status");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "NO DATA";
    }

    public static void clearDB() {
        String credit_request_entity = "DELETE FROM credit_request_entity";
        String order_entity = "DELETE FROM order_entity";
        String payment_entity = "DELETE FROM payment_entity";
        QueryRunner runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            runner.update(connection, credit_request_entity);
            runner.update(connection, order_entity);
            runner.update(connection, payment_entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}