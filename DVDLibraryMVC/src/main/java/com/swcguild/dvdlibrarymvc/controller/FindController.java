package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.dao.SearchTerm;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FindController {
    
    private DvdLibraryDao dao;
    
    @Inject
    public FindController(DvdLibraryDao dao){
        this.dao = dao;
    }

    @RequestMapping(value = "/Find", method = RequestMethod.GET)
    public String displayFindPage() {
        return "Find";
    }
    
    
    @RequestMapping(value = "Find/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap) {
// Create the map of search criteria to send to the DAO
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
// Determine which search terms have values, translate the String // keys into SearchTerm enums, and set the corresponding values // appropriately.
        String currentTerm = searchMap.get("title");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        currentTerm = searchMap.get("releaseDate");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RELEASE_DATE, currentTerm);
        }
        currentTerm = searchMap.get("director");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR, currentTerm);
        }
        currentTerm = searchMap.get("studio");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }
        currentTerm = searchMap.get("comments");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.COMMENTS, currentTerm);
        }
        return dao.searchDvds(criteriaMap);
    }
}