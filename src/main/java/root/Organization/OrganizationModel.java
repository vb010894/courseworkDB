package root.Organization;

import org.json.JSONArray;
import org.json.JSONObject;

public class OrganizationModel
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    private String name;
    private String adress;
    private String inn;
    private String kpp;

    public String getOrganization(int start, int page, int limit)
    {
        JSONObject result = new JSONObject();
        JSONArray itrms = new JSONArray();

        return result.toString();
    }

    public void setOrganization()
    {

    }

}
