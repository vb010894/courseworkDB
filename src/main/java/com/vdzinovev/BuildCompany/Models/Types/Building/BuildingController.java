package com.vdzinovev.BuildCompany.Models.Types.Building;

import com.vdzinovev.BuildCompany.Core.Tools.ExceptionEvent;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер модели тип Здание.
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
public class BuildingController {

    /**
     * Получает данные от клиента для новой записи.
     * @param model Модель
     * @return ответ клиенту
     */
    @RequestMapping(value = "Building",
            method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    public String setter(@RequestBody BuildingModel model) {
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
    @RequestMapping(value = "Building",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public String getter() {
        try {
            BuildingModel model = new BuildingModel();
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
    @RequestMapping(value = "Building",
            method = RequestMethod.PATCH,
            produces = "application/json; charset=utf-8")
    public String updater(@RequestBody  BuildingModel model) {
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
    @RequestMapping(value = "Building",
            method = RequestMethod.DELETE,
            produces = "application/json; charset=utf-8")
    public String deleter(@RequestBody BuildingModel model) {
        try {
            return model.delete().toString();
        } catch (Exception ex) {
            return ExceptionEvent
                    .throwNewException(ex)
                    .toString();
        }
    }
}
