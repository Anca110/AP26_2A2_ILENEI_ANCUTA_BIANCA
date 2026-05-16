package compulsory.repository;

import compulsory.model.Player;
import compulsory.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PlayerRepository {

    //salveaza un jucator in bd
    public void save(Player player) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            //porneste tranzactia
            em.getTransaction().begin();
            em.persist(player);//spune lui JPA sa insereze obiectul in bd
            em.getTransaction().commit();//confirma modificarea
        } finally {
            em.close();
        }
    }

    //cauta un jucator dupa id
    public Player findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.find(Player.class, id);
        } finally {
            em.close();
        }
    }

    //JPQL, SQL
    //select * from players
    //select p from Player p
    public List<Player> findAll() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery("select p from Player p", Player.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}