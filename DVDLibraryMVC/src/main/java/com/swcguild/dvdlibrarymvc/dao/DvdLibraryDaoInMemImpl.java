package com.swcguild.dvdlibrarymvc.dao;
import com.swcguild.dvdlibrarymvc.model.Dvd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class DvdLibraryDaoInMemImpl implements DvdLibraryDao {
    
    private Map<Integer, Dvd> dvdMap = new HashMap<>();
// used to assign ids to Contacts - simulates the auto increment
// primary key for Contacts in a database
    private static int dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
// assign the current counter values as the contactid
        dvd.setDvdId(dvdIdCounter);
// increment the counter so it is ready for use for the next contact
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> c = dvdMap.values();
        return new ArrayList(c);
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
// Get all the search terms from the map
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        String commentsCriteria = criteria.get(SearchTerm.COMMENTS);
// Declare all the predicate conditions 
                Predicate<Dvd> titleMatches;
                Predicate<Dvd> releaseDateMatches;
                Predicate<Dvd> directorMatches;
                Predicate<Dvd> studioMatches;
                Predicate<Dvd> commentsMatches;
               
       
// Placeholder predicate - always returns true. Used for search terms
// that are empty
        Predicate<Dvd> truePredicate = (c) -> {
            return true;
        };
// Assign values to predicates. If a given search term is empty, just
// assign the default truePredicate, otherwise assign the predicate that
// properly filters for the given term.
        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equals(titleCriteria);
        releaseDateMatches = (releaseDateCriteria == null || releaseDateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getReleaseDate().equals(releaseDateCriteria);
        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirector().equals(directorCriteria);
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStudio().equals(studioCriteria);
        commentsMatches = (commentsCriteria == null || commentsCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getComments().equals(commentsCriteria);
// Return the list of Contacts that match the given criteria. To do this we
// just AND all the predicates together in a filter operation.
        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releaseDateMatches)
                        .and(directorMatches)
                        .and(studioMatches)
                        .and(commentsMatches))
                .collect(Collectors.toList());
    }
}
