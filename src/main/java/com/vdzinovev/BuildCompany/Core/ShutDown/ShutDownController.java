package com.vdzinovev.BuildCompany.Core.ShutDown;

import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Tools.ExceptionEvent;
import com.vdzinovev.BuildCompany.Models.Departament.DepartmentModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/_apis/")
public class ShutDownController {

    /**
     * Остановка сервера.
     * @return ответ клиенту.
     */
    @RequestMapping(value = "ShutDown")
    public String shutDown() {
        try {
            DbLib.connectionClose();
            System.exit(0);
            return "{state:true}";

        } catch (Exception ex) {
            return ExceptionEvent
                    .throwNewException(ex)
                    .toString();
        }
    }
}
