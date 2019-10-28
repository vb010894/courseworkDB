package root.Estimate;

import org.springframework.web.bind.annotation.*;
import root.Reports.ReportsModel;

@RestController
@RequestMapping("/_apis/")
public class EstimateController
{
    @RequestMapping(value = "Estimate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String set
            (
                    @RequestBody EstimateModel model
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

    @RequestMapping(value = "Estimate", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String get()
    {
        try
        {
            return EstimateModel.get();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Estimate/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getById
            (
                    @PathVariable String id
            )
    {
        try
        {
            return EstimateModel.getById(id);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Estimate/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String update
            (
                    @PathVariable String id,
                    @RequestBody EstimateModel model
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

    @RequestMapping(value = "Estimate/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String Delete
            (
                    @PathVariable String id
            )
    {
        try
        {
            EstimateModel.delete(id);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }
}
