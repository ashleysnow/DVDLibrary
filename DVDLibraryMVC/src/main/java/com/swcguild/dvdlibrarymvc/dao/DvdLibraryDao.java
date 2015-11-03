package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.util.List;
import java.util.Map;

public interface DvdLibraryDao {

    public Dvd addDvd(Dvd dvd);

    public void removeDvd(int dvdId);

    public void updateDvd(Dvd dvd);

    public List<Dvd> getAllDvds();

    public Dvd getDvdById(int dvdId);

    public List<Dvd>searchDvds(Map<SearchTerm, String> criteria);

}
