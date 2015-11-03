package com.swcguild.dvdlibrarymvc;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.swcguild.dvdlibrarymvc.dao.SearchTerm;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from dvds");
    }

    @Test
    public void addDvdTest() {

        Dvd temp = new Dvd();

        temp.setTitle("Titanic");
        temp.setReleaseDate("1997");
        temp.setDirector("James Cameron");
        temp.setStudio("Fox Film");
        temp.setComments("Fine");

        dao.addDvd(temp);
        Dvd fromDb = dao.getDvdById(temp.getDvdId());
        assertEquals(fromDb, temp);

    }

    public void removeDvd() {

        Dvd temp = new Dvd();

        temp.setTitle("Titanic");
        temp.setReleaseDate("1997");
        temp.setDirector("James Cameron");
        temp.setStudio("Fox Film Corporation");
        temp.setComments("Fine");

        dao.addDvd(temp);
        Dvd fromDb = dao.getDvdById(temp.getDvdId());
        assertEquals(fromDb, temp);
        dao.removeDvd(temp.getDvdId());
        assertNull(dao.getDvdById(temp.getDvdId()));
    }

    @Test
    public void updateDvd() {

        Dvd up = new Dvd();

        up.setTitle("Gladiator");
        up.setReleaseDate("2000");
        up.setDirector("Ridley Scott");
        up.setStudio("Scott Free Productions");
        up.setComments("great");
        dao.addDvd(up);

        up.setStudio("Garage Works");
        dao.updateDvd(up);
        Dvd fromDb = dao.getDvdById(up.getDvdId());
        assertEquals(fromDb, up);

    }

    @Test
    public void getAllDvds() {
        Dvd temp = new Dvd();

        temp.setTitle("Gladiator");
        temp.setReleaseDate("2000");
        temp.setDirector("Ridley Scott");
        temp.setStudio("Scott Free Productions");
        temp.setComments("great");
        dao.addDvd(temp);

        Dvd temp1 = new Dvd();

        temp1.setTitle("Titanic");
        temp1.setReleaseDate("1997");
        temp1.setDirector("James Cameron");
        temp1.setStudio("Fox Film Corporation");
        temp1.setComments("Fine");
        dao.addDvd(temp1);

        List<Dvd> dList = dao.getAllDvds();
        assertEquals(dList.size(), 2);

    }

    @Test
    public void Search() {
        Dvd temp = new Dvd();

        temp.setTitle("Gladiator");
        temp.setReleaseDate("2000");
        temp.setDirector("Ridley Scott");
        temp.setStudio("Scott Free Productions");
        temp.setComments("Fine");
        dao.addDvd(temp);

        Dvd temp1 = new Dvd();

        temp1.setTitle("Titanic");
        temp1.setReleaseDate("1997");
        temp1.setDirector("James Cameron");
        temp1.setStudio("Fox Film Corporation");
        temp1.setComments("Fine");
        dao.addDvd(temp1);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "Titanic");
        List<Dvd> dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());

    }
}
