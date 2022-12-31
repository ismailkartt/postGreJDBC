import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "kart1234");
        Statement st = con.createStatement();

        /*
        * PreparedStatement01 interface, birden çok kez çalıştırılabilen önceden derlenmiş bir SQL kodunu temsil eder.
        * Parametrelendirilmiş SQL sorguları(query) ile çalışır. Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz.
        * */

        //1.Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1.adım: Prepared statement query'sini oluşur.
        String sql1 = "update companies set number_of_employees = ? where company = ? ";

        //2.adım: Prepared statement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.adım: setInt(), setString(), ... methodlarını kullanarak soru işaretleri yerlerine değer ata.
        pst1.setInt(1, 9999);
        pst1.setString(2,"IBM");

        //4.adım:Query'yi çalıştır.
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "Select * from companies";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while(resultSet1.next()){
            System.out.println(resultSet1.getInt(1) + "--" + resultSet1.getString(2) + "--" + resultSet1.getInt(3));
        }
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        int guncellenenSatirSayisi1 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi1);

        String sql3 = "Select * from companies";
        ResultSet resultSet2 = st.executeQuery(sql3);

        while(resultSet2.next()){
            System.out.println(resultSet2.getInt(1) + "--" + resultSet2.getString(2) + "--" + resultSet2.getInt(3));
        }

        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();
        pst1.close();


    }
}