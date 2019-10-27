package root.Workers;

import org.springframework.web.bind.annotation.*;
import root.Engeneers.EngeneersModel;

@RestController
@RequestMapping("/_apis/")
public class WorkersController
{
    @RequestMapping(value = "Workers", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String set
            (
                    @RequestBody WorkersModel model
            )
    {
        try
        {
            model.set();
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Workers", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String get()
    {
        try
        {
            return WorkersModel.get();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Workers/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getById
            (
                    @PathVariable String id
            )
    {
        try
        {
            return WorkersModel.getById(id);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Workers/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String update
            (
                    @PathVariable String id,
                    @RequestBody WorkersModel model
            )
    {
        try
        {
            model.update(id);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Workers/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String Delete
            (
                    @PathVariable String id
            )
    {
        try
        {
            WorkersModel.delete(id);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }
}
