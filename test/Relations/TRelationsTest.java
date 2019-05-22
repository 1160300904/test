package Relations;

import static org.junit.Assert.*;
import org.junit.Test;

import applications.*;
import physicalObject.*;
import Graph.*;

public class TRelationsTest {
    /*
     * Testing strategy: 1.two constructors: input :null, not null. 2.addRelation():
     * relation has existed in current relations, or not. the object on the relation
     * has existed in current relations, or not. 3.deleRelation(): relation has
     * existed in current relations, or not. the object on the relation has existed
     * in current relations, or not. 4.relatedTo(): the relation between the inputs
     * has existed in current relations, or not. 5.make sure this relation class
     * works depends on the content of objects, not on its reference. 6.Test on some
     * reall PhysicalObjects.
     */
    @Test
    public void testTRlations() {
        TRelations<String> tr = new TRelations<>();
        tr.addRelation("1", "2");
        assertTrue(tr.relatedTo(new String("1"), "2"));
        Athlete a11 = AthleteFactory.getInstance("1", 10, "CHI", 20, 10.0);
        Athlete a12 = AthleteFactory.getInstance("1", 10, "CHI", 20, 10.0);
        Athlete a2 = AthleteFactory.getInstance("2", 10, "CHI", 20, 10.0);
        Athlete a3 = AthleteFactory.getInstance("3", 10, "CHI", 20, 10.0);
        Athlete a4 = AthleteFactory.getInstance("4", 10, "CHI", 20, 10.0);
        TRelations<Athlete> tra = new TRelations<>();
        tra.addRelation(a11, a2);
        tra.addRelation(a2, a11);
        tra.addRelation(a11, a3);
        tra.addRelation(a3, a11);
        tra.addRelation(a3, a2);
        tra.addRelation(a2, a3);
        tra.addRelation(a2, a3);
        assertTrue(tra.relatedTo(a11, a2));
        assertTrue(tra.relatedTo(a3, a2));
        assertTrue(tra.relatedTo(a11, a3));
        assertFalse(tra.relatedTo(a11, a4));
        assertTrue(tra.relatedTo(a12, a3));
        assertFalse(tra.relatedTo(a12, a4));

        // tra.addRelation(a2, a2);
        ApplicationFactory af = new AppEcosystemFactory();
        PersonalAppEcosystem p = (PersonalAppEcosystem) af.getApplication();
        PersonalApp wechat = PersonalAppFactory.getInstance("wechat", "1", "1", "1", "1");
        PersonalApp weibo = PersonalAppFactory.getInstance("weibo", "1", "1", "1", "1");
        PersonalApp QQ = PersonalAppFactory.getInstance("QQ", "1", "1", "1", "1");
        PersonalApp Eleme = PersonalAppFactory.getInstance("Eleme", "1", "1", "1", "1");
        PersonalApp Didi = PersonalAppFactory.getInstance("Didi", "1", "1", "1", "1");
        PersonalApp BaiduMap = PersonalAppFactory.getInstance("BaiduMap", "1", "1", "1", "1");

        p.addPhyRelation(wechat, QQ);
        p.addPhyRelation(wechat, Eleme);
        p.addPhyRelation(QQ, weibo);
        p.addPhyRelation(Didi, BaiduMap);

        p.addPhyRelation(QQ, wechat);
        p.addPhyRelation(Eleme, wechat);
        p.addPhyRelation(weibo, QQ);
        p.addPhyRelation(BaiduMap, Didi);

        assertEquals(1, p.getLogicalDistance(BaiduMap, Didi));

        TRelations<String> tr1 = new TRelations<>();
        tr1.addRelation("1", "2");
        tr1.deleRelation("1", "2");
        /*
         * Graph<PersonalApp> g=Graph.empty(); g.set(wechat, QQ, 1);g.set(wechat, Eleme,
         * 1);g.set(QQ, weibo, 1);g.set(Didi, BaiduMap, 1);
         * System.out.println(g.targets(wechat));
         */
    }
}
