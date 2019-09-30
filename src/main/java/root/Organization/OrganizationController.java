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
        return new OrganizationModel().getOrganization(start, page, limit, id);
    }

    @RequestMapping(value="/org/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteOrganization
            (
                    @PathVariable String id
            )
    {
        return new OrganizationModel().deleteOrganization(id).toString();
    }

    @RequestMapping(value="/org/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateOrganization
            (
                    @RequestBody OrganizationModel model,
                    @PathVariable String id
            )
    {
        return model.updateOrganization(id).toString();
    }

    @RequestMapping(value="/org", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrganization
            (
                    @RequestParam int start,
                    @RequestParam int page,
                    @RequestParam int limit
            )
    {
        return new OrganizationModel().getOrganization(start, page, limit, "");
    }

    @RequestMapping(value="/org", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setOrganization(@RequestBody OrganizationModel model)
    {
        return model.setOrganization().toString();
    }

}
