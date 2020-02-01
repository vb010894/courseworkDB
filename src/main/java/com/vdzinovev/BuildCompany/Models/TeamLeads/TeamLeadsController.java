package com.vdzinovev.BuildCompany.Models.TeamLeads;

import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Tools.ExceptionEvent;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер модели Начальников упралений.
 * <p>Поддерживает cors</>
 * <p>Начальная ссылка /_apis/</>
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 * @apiNote REST
 */
@RestController
@CrossOrigin
@RequestMapping("/_apis/")
public class TeamLeadsController {

    /**
     * Получает данные от клиента для новой записи.
     * @param model Модель
     * @return ответ клиенту
     */
    @RequestMapping(value = "TeamLeads",
            method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    public String setter(@RequestBody TeamLeadsModel model) {
        try {
            return model.set().toString();
        } catch (Exception ex) {
            return ExceptionEvent
                    .throwNewException(ex)
                    .toString();
        }
    }

    /**
     * Возращает клиенту данные.
     * @return данные об оршанизации
     */
    @RequestMapping(value = "TeamLeads",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public String getter(@RequestParam(value = "id",
            defaultValue = "0",
            required = false) int id) {
        try {
            TeamLeadsModel model = new TeamLeadsModel();

            if(id != 0) {
                model.setIdFilter(id);
            }

            return model.get().toString();
        }  catch (Exception ex) {
            return ExceptionEvent
                    .throwNewException(ex)
                    .toString();
        }
    }

    /**
     * Получает данные от клиента для обновления записи.
     * @param model Модель
     * @return ответ клиенту
     */
    @RequestMapping(value = "TeamLeads",
            method = RequestMethod.PATCH,
            produces = "application/json; charset=utf-8")
    public String updater(@RequestBody  TeamLeadsModel model) {
        try {
            return model.update().toString();
        } catch (Exception ex) {
            return ExceptionEvent
                    .throwNewException(ex)
                    .toString();
        }
    }

    /**
     * Удаляет запись.
     * @param model Модель
     * @return ответ клиенту
     */
    @RequestMapping(value = "TeamLeads",
            method = RequestMethod.DELETE,
            produces = "application/json; charset=utf-8")
    public String deleter(@RequestBody TeamLeadsModel model) {
        try {
            return model.delete().toString();
        } catch (Exception ex) {
            return ExceptionEvent
                    .throwNewException(ex)
                    .toString();
        }
    }
}
