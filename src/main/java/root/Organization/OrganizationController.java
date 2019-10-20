package root.Organization;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_apis")
public class OrganizationController
{
    @RequestMapping(value="/org/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrganization
            (
                    @PathVariable String id,
                    @RequestParam int start,
                    @RequestParam int page,
                    @RequestParam int limit
            )
    {
        try
        {
            return new OrganizationModel().getOrganization(start, page, limit, id);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \"" + ex.getMessage() + "\", items:[]}";
        }
    }

    @RequestMapping(value="/org/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteOrganization
            (
                    @PathVariable String id
            )
    {
        try
        {
            new OrganizationModel().deleteOrganization(id);
            return "{\"state\": true, \"Count\":1}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }
    @RequestMapping(value="/org/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateOrganization
            (
                    @RequestBody OrganizationModel model,
                    @PathVariable String id
            )
    {
        try
        {
            return model.updateOrganization(id).toString();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }

    }

    @RequestMapping(value="/org", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrganization
            (
                    @RequestParam int start,
                    @RequestParam int page,
                    @RequestParam int limit
            )
    {
        try
        {
            return new OrganizationModel().getOrganization(start, page, limit, "");
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value="/org", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setOrganization(@RequestBody OrganizationModel model)
    {
        try
        {
            return model.setOrganization().toString();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }

    }

}
