package compulsory.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    //EntityManagerFactory sunt mai multe conexiuniJPA(se creaza o singura data)
    private static final EntityManagerFactory entityManagerFactory =

            //cauta in persistence.xml <persistence-unit name="lab11PU">
            Persistence.createEntityManagerFactory("lab11PU");

    //entityManager-obiectul concret prin care lucrez cu bd
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
}

//clasa acre porneste JPA