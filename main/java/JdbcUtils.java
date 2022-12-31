import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDataBase(){

        //1.adım: Driver'a kaydol

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2.adım:Datbas'e bağlan

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","kart1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection!=null){
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Fail");
        }


        return connection;
    }
    //3.adım:Statement oluştur

    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }





}
