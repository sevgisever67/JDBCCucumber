package stepDefinitions;

import com.mysql.cj.protocol.Resultset;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;
import utilities.ConfigReader;
import utilities.JDBCReusableMethods;
import utilities.Manage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;
import static utilities.JDBCReusableMethods.*;


public class Stepdefinition {
    String query;
    String query1;
    ResultSet rs;
    ResultSet rs1;
    Statement st;
    String queryOgleneKadar;
    String getQueryOgledenSonra;
    Statement st1;
    Manage manage =new Manage();


    int flag;


    @Given("Database baglantisi kurulur.")
    public void database_baglantisi_kurulur() {
        JDBCReusableMethods.createConnection();

    }

    @Given("Query hazirlanir.")
    public void query_hazirlanir() {
        query = "Select charge_id From heallife_hospitaltraining.ambulance_call Where patient_id=1 AND driver='Smith';";
    }

    @Given("Query calistirilir ve sonuclari alinir.")
    public void query_calistirilir_ve_sonuclari_alinir() throws SQLException {

        rs = getStatement().executeQuery(query);

    }

    @Given("Query sonuclari dogrulanir.")
    public void query_sonuclari_dogrulanir() throws SQLException {
        int expectedData = 2;

        flag = 0;
        while (rs.next()) {
            flag++;
        }
        assertEquals(expectedData, flag);
    }

    @Given("Database baglantisi kapatilir.")
    public void database_baglantisi_kapatilir() {

        JDBCReusableMethods.closeConnection();
    }

    //--------------------------------------------------------
    @Given("Update Query'si hazirlanir.")
    public void update_query_si_hazirlanir() {
        query1 = "insert into heallife_hospitaltraining.appointment (priority,specialist,doctor,amount,message,appointment_status,source,is_opd,is_ipd,live_consult) values (1,2,2,0,'helloTeam113','approved','OFFline','no','yes','yes');";

    }

    @Given("Sonuclari alinir ve dogrulanir.")
    public void sonuclari_alinir_ve_dogrulanir() throws SQLException {

        int sonuc = getStatement().executeUpdate(query1);

        int verify = 0;
        if (sonuc > 0) {
            verify++;
        }
        assertEquals(verify, 1);
    }

    //-------------------------------------------------------------------------------

    @Given("Randevu sayilarini ogrenebilecegimiz sql querysi hazirlanir.")
    public void randevu_sayilarini_ogrenebilecegimiz_sql_querysi_hazirlanir() {

    }

    @Given("Query calistirilir ve sonuclar dogrulanir.")
    public void query_calistirilir_ve_sonuclar_dogrulanir() throws SQLException {

        rs = getStatement().executeQuery(manage.getQuerySabah());
        rs1 = getStatement().executeQuery(manage.getQueryAksam());
        rs.next();
        int sabah = rs.getInt(1);
        rs1.next();
        int aksam = rs1.getInt(1);
        assertTrue(sabah < aksam);

    }
    //**********************************************************************************

    @Given("Languages tablosuna query gönderilir ve sonuclar dogrulanir.")
    public void languages_tablosuna_query_gönderilir_ve_sonuclar_dogrulanir() throws SQLException {

        ResultSet rs3=JDBCReusableMethods.getStatement().executeQuery(manage.getLanguagesQuery());
        rs3.next();
        String expectedLanguages= "Yiddish";
        String actualLanguages= rs3.getString(1);

        assertEquals(expectedLanguages,actualLanguages);

    }
}