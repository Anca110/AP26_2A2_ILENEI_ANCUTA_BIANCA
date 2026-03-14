import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SocialNetworkTest {

    @Test
    void testFindArticulationPoints() {

        SocialNetwork network = new SocialNetwork();

        Person anca = new Person(11, "Anca", "11.02.2005");
        Person salome = new Person(35, "Salome", "12.05.2003");
        Person ingrid = new Person(40, "Ingrid", "10.07.2006");

        Company amazon = new Company(7, "Amazon", "Iasi");
        Company centric = new Company(9, "Centric", "Cluj");
        Company bitdefender = new Company(10, "Bitdefender", "Bucuresti");

        network.addProfile(anca);
        network.addProfile(salome);
        network.addProfile(amazon);
        network.addProfile(centric);
        network.addProfile(ingrid);
        network.addProfile(bitdefender);

        anca.addRelationships(salome, "friends");
        salome.addRelationships(amazon, "works");
        anca.addRelationships(amazon, "employees");
        salome.addRelationships(bitdefender, "director");
        salome.addRelationships(centric, "collaboration");
        ingrid.addRelationships(salome, "friends");
        ingrid.addRelationships(amazon, "works");

        List<Profile> critical = network.findArticulationPoints();

        assertEquals(1, critical.size());

        boolean foundSalome = false;

        for (int indexI = 0; indexI < critical.size(); indexI++) {
            if (critical.get(indexI).getName().equals("Salome")) {
                foundSalome = true;
            }
        }

        assertTrue(foundSalome);
    }

    @Test
    void testGetNeighbors() {

        SocialNetwork network = new SocialNetwork();

        Person anca = new Person(11, "Anca", "11.02.2005");
        Person salome = new Person(35, "Salome", "12.05.2003");
        Person ingrid = new Person(40, "Ingrid", "10.07.2006");

        Company amazon = new Company(7, "Amazon", "Iasi");
        Company centric = new Company(9, "Centric", "Cluj");
        Company bitdefender = new Company(10, "Bitdefender", "Bucuresti");

        network.addProfile(anca);
        network.addProfile(salome);
        network.addProfile(amazon);
        network.addProfile(centric);
        network.addProfile(ingrid);
        network.addProfile(bitdefender);

        anca.addRelationships(salome, "friends");
        salome.addRelationships(amazon, "works");
        anca.addRelationships(amazon, "employees");
        salome.addRelationships(bitdefender, "director");
        salome.addRelationships(centric, "collaboration");
        ingrid.addRelationships(salome, "friends");
        ingrid.addRelationships(amazon, "works");

        List<Profile> neighbors = network.getNeighbors(amazon);

        assertEquals(3, neighbors.size());

        boolean foundAnca = false;
        boolean foundSalome = false;
        boolean foundIngrid = false;

        for (int indexI = 0; indexI < neighbors.size(); indexI++) {
            String name = neighbors.get(indexI).getName();

            if (name.equals("Anca")) {
                foundAnca = true;
            }
            if (name.equals("Salome")) {
                foundSalome = true;
            }
            if (name.equals("Ingrid")) {
                foundIngrid = true;
            }
        }

        assertTrue(foundAnca);
        assertTrue(foundSalome);
        assertTrue(foundIngrid);
    }

    @Test
    void testFindBiconectedComponents() {

        SocialNetwork network = new SocialNetwork();

        Person anca = new Person(11, "Anca", "11.02.2005");
        Person salome = new Person(35, "Salome", "12.05.2003");
        Person ingrid = new Person(40, "Ingrid", "10.07.2006");

        Company amazon = new Company(7, "Amazon", "Iasi");
        Company centric = new Company(9, "Centric", "Cluj");
        Company bitdefender = new Company(10, "Bitdefender", "Bucuresti");

        network.addProfile(anca);
        network.addProfile(salome);
        network.addProfile(amazon);
        network.addProfile(centric);
        network.addProfile(ingrid);
        network.addProfile(bitdefender);

        anca.addRelationships(salome, "friends");
        salome.addRelationships(amazon, "works");
        anca.addRelationships(amazon, "employees");
        salome.addRelationships(bitdefender, "director");
        salome.addRelationships(centric, "collaboration");
        ingrid.addRelationships(salome, "friends");
        ingrid.addRelationships(amazon, "works");

        List<List<Profile>> components = network.findBiconectedComponents();

        assertEquals(3, components.size());
    }
}