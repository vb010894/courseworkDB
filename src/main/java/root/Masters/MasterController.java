package root.Masters;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_apis/")
public class MasterController
{
    @RequestMapping(value = "Master", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String setTeamLeads
            (
                    @RequestBody MasterModel model
            )
    {
        try
        {
            model.setMaster();
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Master", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTeamLeads()
    {
        try
        {
            return MasterModel.getMaster();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Master/{idMaster}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTeamLeadsById
            (
                    @PathVariable String idMaster
            )
    {
        try
        {
            return MasterModel.getMasterById(idMaster);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Master/{idMaster}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateTeamLeads
            (
                    @PathVariable String idMaster,
                    @RequestBody MasterModel model
            )
    {
        try
        {
            model.updateMaster(idMaster);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Master/{idMaster}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String DeleteTeamLeads
            (
                    @PathVariable String idMaster
            )
    {
        try
        {
            MasterModel.deleteMaster(idMaster);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

}
