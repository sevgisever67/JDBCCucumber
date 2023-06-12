package utilities;

public class Manage {

    private String querySabah = "SELECT count(*) FROM heallife_hospitaltraining.appointment WHERE HOUR(time) < 12";
    private String queryAksam = "SELECT count(*) FROM heallife_hospitaltraining.appointment WHERE HOUR(time) > 12";

    private String languagesQuery= "Select language From heallife_hospitaltraining.languages Where short_code = 'yi'";
    public String getQuerySabah() {
        return querySabah;
    }

    public String getQueryAksam() {
        return queryAksam;
    }

    public String getLanguagesQuery() {

        return languagesQuery;
    }
}