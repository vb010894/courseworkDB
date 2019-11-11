package root.Test;

import org.springframework.web.bind.annotation.*;
import root.AnnotationTest;
import root.Materials.MaterialsModel;
import root.core.SqlWork;


@RestController
@RequestMapping("/_apis/")
@AnnotationTest(value = "TestCont", base = TestController.class)
public class TestController
{


    @RequestMapping(value = "Test", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String get() throws Exception
    {
        return TestModel.get();
    }

    @RequestMapping(value = "Test", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String set
            (
                    @RequestBody TestModel model
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

    @RequestMapping(value = "Test/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String update
            (
                    @PathVariable String id,
                    @RequestBody TestModel model
            )
    {
        try
        {
           return model.updater(id);
        }
        catch (Exception ex)
        {
            return "{\"state\":false, \"count\":0, \"message\": \" " + ex.getMessage() + "\" items[]}";
        }
    }

}
