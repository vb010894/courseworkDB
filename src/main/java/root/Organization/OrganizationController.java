package root.Organization;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_apis")
public class OrganizationController
{
    @RequestMapping(value="/org", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrganization()
    {
        return "{total:1,state:true}";
    }

    @RequestMapping(value="/org", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setOrganization(@RequestBody OrganizationModel model)
    {
        return model.getMess();
    }

}
