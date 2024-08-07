import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    //1.adım: Driver'a kaydol
    Class.forName("org.postgresql.Driver");

    //2.adım:Datbas'e bağlan
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","kart1234");

    //3.adım:Statement oluştur
    Statement st = con.createStatement();

    //4.adım:Query çalıştır
    /*
    * execute() methodu DDL(create, drop, alter table) ve DQL(select) için kullanılabilir.
    * 1) Eğer execute() methodu DDL için kullanılırsa 'false' return yapar.
    * 2) Eğer execute() methodu DQL için kullanılırsa ResultSet alındığında 'true' aksi halde 'false' verir
    * */

        //1.örnek:  "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 =st.execute("CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary int)");
        System.out.println("sql1 = " + sql1); // false return eder çünkü data çağırmıyoruz

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "ALTER TABLE workers ADD worker_adress VARCHAR(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        //3.Örnek: Drop workers table
        String sql3 = "drop table workers";
        boolean sql3b = st.execute(sql3);
        System.out.println("sql3b = " + sql3b);

        //5.adım: Bağlantı ve Statement'i kapatın
        con.close();
        st.close();


    }
}
