package root.TeamLeads;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_apis/")
public class TeamLeadController
{
    @RequestMapping(value = "TeamLeads", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setTeamLeads
            (
                    @RequestBody TeamLeadsModel model
            )
    {
        try
        {
            model.setTeamLeads();
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "TeamLeads", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTeamLeads()
    {
        try
        {
            return TeamLeadsModel.getTeamLeads();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "TeamLeads/{idTeamLeads}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTeamLeadsById
            (
                    @PathVariable String idTeamLeads
            )
    {
        try
        {
            return TeamLeadsModel.getTeamLeadsById(idTeamLeads);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "TeamLeads/{idTeamLeads}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateTeamLeads
            (
                    @PathVariable String idTeamLeads,
                    @RequestBody TeamLeadsModel model
            )
    {
        try
        {
            model.updateTeamLeads(idTeamLeads);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "TeamLeads/{idTeamLeads}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String DeleteTeamLeads
            (
                    @PathVariable String idTeamLeads
            )
    {
        try
        {
            TeamLeadsModel.deleteTeamLeads(idTeamLeads);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

}
