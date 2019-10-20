package root.Departament;

import org.springframework.web.bind.annotation.*;
import root.Organization.OrganizationModel;

@RestController
@RequestMapping("/_apis")
public class DepartamentIDController
{
    @RequestMapping(value="/org/{idOrg}/departament", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setOrganizationDepartament
            (
                    @RequestBody DepartamentIDModel model,
                    @PathVariable int idOrg
            )
    {
        try
        {
            model.setDepartament(idOrg);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }

    }

    @RequestMapping(value="/org/{idOrg}/departament", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrganizationDepartament
            (
                    @PathVariable String idOrg
            )
    {
        try
        {
           return DepartamentIDModel.getDepartaments(null, idOrg);
        }
        catch (Exception ex)
        {
            return "{'state':false, 'count': 0, 'message':'cant work with sql' " + ex + " 'items':[]}";
        }

    }

    @RequestMapping(value="/org/{idOrg}/departament/{idDepartament}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrganizationDepartament
            (
                    @PathVariable String idOrg,
                    @PathVariable String idDepartament
            )
    {
        try
        {
            return DepartamentIDModel.getDepartaments(idDepartament, idOrg);
        }
        catch (Exception ex)
        {
            return "{'state':false, 'count': 0, 'message':'cant work with sql' " + ex.getMessage() + " 'items':[]}";
        }

    }

    @RequestMapping(value="/org/{idOrg}/departament/{idDepartament}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateOrganizationDepartament
            (
                    @RequestBody DepartamentIDModel model,
                    @PathVariable String idOrg,
                    @PathVariable String idDepartament
            )
    {
        try
        {
           model.updateDepartament(idDepartament, idOrg);
           return "{state:true, count:1, item:[]}";
        }
        catch (Exception ex)
        {
            return "{'state':false, 'count': 0, 'message':'cant work with sql' " + ex.getMessage() + " 'items':[]}";
        }

    }

    @RequestMapping(value="/org/{idOrg}/departament/{idDepartament}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteOrganizationDepartament
            (
                    @PathVariable String idOrg,
                    @PathVariable String idDepartament
            )
    {
        try
        {
            DepartamentIDModel.deleteDepartament(idDepartament, idOrg);
            return "{state:true, count:1, item:[]}";
        }
        catch (Exception ex)
        {
            return "{'state':false, 'count': 0, 'message':'cant work with sql' " + ex.getMessage() + " 'items':[]}";
        }

    }
}
