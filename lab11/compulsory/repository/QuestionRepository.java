package compulsory.repository;

import compulsory.model.Question;
import compulsory.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class QuestionRepository {

    public void save(Question question) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(question);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Question findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.find(Question.class, id);
        } finally {
            em.close();
        }
    }

    public List<Question> findAll() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery("select q from Question q", Question.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}