package root.Materials;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_apis/")
public class MaterialsController
{
    @RequestMapping(value = "Materials", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String set
            (
                    @RequestBody MaterialsModel model
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

    @RequestMapping(value = "Materials", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String get()
    {
        try
        {
            return MaterialsModel.get();
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Materials/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getById
            (
                    @PathVariable String id
            )
    {
        try
        {
            return MaterialsModel.getById(id);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

    @RequestMapping(value = "Materials/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String update
            (
                    @PathVariable String id,
                    @RequestBody MaterialsModel model
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

    @RequestMapping(value = "Materials/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String Delete
            (
                    @PathVariable String id
            )
    {
        try
        {
            MaterialsModel.delete(id);
            return "{state:true, count:1, items:[]}";
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

}
