package root.Group;

import org.springframework.web.bind.annotation.*;
import root.Masters.MasterModel;

@RestController
@RequestMapping("/_apis/")
public class GroupController
{
    @RequestMapping(value = "Group", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setTeamLeads
            (
                    @RequestBody GroupModel model
            )
    {
        try
        {
            model.setGroup();
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Group", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTeamLeads()
    {
        try
        {
            return GroupModel.getGroup();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Group/{idGroup}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTeamLeadsById
            (
                    @PathVariable String idGroup
            )
    {
        try
        {
            return GroupModel.getGroupById(idGroup);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Group/{idGroup}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateTeamLeads
            (
                    @PathVariable String idGroup,
                    @RequestBody MasterModel model
            )
    {
        try
        {
            model.updateMaster(idGroup);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Group/{idGroup}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String DeleteTeamLeads
            (
                    @PathVariable String idGroup
            )
    {
        try
        {
            GroupModel.deleteGroup(idGroup);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

}
