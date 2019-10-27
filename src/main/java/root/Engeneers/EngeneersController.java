package root.Engeneers;

import org.springframework.web.bind.annotation.*;
import root.Group.GroupModel;

@RestController
@RequestMapping("/_apis/")
public class EngeneersController
{
    @RequestMapping(value = "Engeneers", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String set
            (
                    @RequestBody EngeneersModel model
            )
    {
        try
        {
            model.setEngeneer();
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Engeneers", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String get()
    {
        try
        {
            return EngeneersModel.getEngeneer();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Engeneers/{idEngeneer}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getById
            (
                    @PathVariable String idEngeneer
            )
    {
        try
        {
            return EngeneersModel.getEngeneerById(idEngeneer);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Engeneers/{idEngeneer}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String update
            (
                    @PathVariable String idEngeneer,
                    @RequestBody EngeneersModel model
            )
    {
        try
        {
            model.updateEngeneer(idEngeneer);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Engeneers/{idEngeneer}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String Delete
            (
                    @PathVariable String idEngeneer
            )
    {
        try
        {
            EngeneersModel.deleteEngeneer(idEngeneer);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }


}
