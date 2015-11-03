package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import org.springframework.stereotype.Controller;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private DvdLibraryDao dao;

    @Inject
    public HomeController(DvdLibraryDao dao) {
        this.dao = dao;

    }

    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "Home";
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") int id) {

        return dao.getDvdById(id);
    }

    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd createDvd(@Valid @RequestBody Dvd dvd) {

        dao.addDvd(dvd);

        return dvd;
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id) {

        dao.removeDvd(id);
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putDvd(@PathVariable("id") int id, @RequestBody Dvd dvd) {

        dvd.setDvdId(id);

        dao.updateDvd(dvd);
    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds() {

        return dao.getAllDvds();
    }
    
    @RequestMapping(value={"/MainPage"}, method=RequestMethod.GET)
    public String displayMainPage() {
        return "MainPage";
    }

}
