package root.Schedule;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_apis/")
public class ScheduleController
{
    @RequestMapping(value = "Schedule", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String set
            (
                    @RequestBody ScheduleModel model
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

    @RequestMapping(value = "Schedule", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String get()
    {
        try
        {
            return ScheduleModel.get();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Schedule/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getById
            (
                    @PathVariable String id
            )
    {
        try
        {
            return ScheduleModel.getById(id);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Schedule/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String update
            (
                    @PathVariable String id,
                    @RequestBody ScheduleModel model
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

    @RequestMapping(value = "Schedule/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String Delete
            (
                    @PathVariable String id
            )
    {
        try
        {
            ScheduleModel.delete(id);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }
}
