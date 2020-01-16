/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;
import Dto.CourseDTO;
import entities.Course;
import entities.SchoolClass;
import entities.SignedUp;
import entities.Student;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
/**
 *
 * @author jojus1101
 */
public class SchoolFacade {
    
    private static SchoolFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private SchoolFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static SchoolFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SchoolFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
        
    
    public List<CourseDTO> getCourseList() {
        EntityManager em = getEntityManager();

        List<CourseDTO> coursesDTO = new ArrayList();

        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> courses = query.getResultList();

        for (Course course : courses) {
            coursesDTO.add(new CourseDTO(course));
        }

        return coursesDTO;

    }
    public CourseDTO addClass(String name, String desc)
     {
         EntityManager em = getEntityManager();
         try
         {
             Course result = new Course(name, desc);
             em.getTransaction().begin();
             em.persist(result);
             em.getTransaction().commit();
             return new CourseDTO(result);
         }
         finally
         {
             em.close();
         }
     }
        
    public void Populate(){
          EntityManager em = emf.createEntityManager();

        Course c1 = new Course("Dansk", "Skrive og snakke dansk");
        Course c2 = new Course("Englsk", "Learn proper english");
        Course c3 = new Course("Spansk", "Hablas espanol");
        Course c4 = new Course("Tysk", "Ich bin ein berliner");

        SchoolClass sc1 = new SchoolClass("fall2018", 30, c1);
        SchoolClass sc2 = new SchoolClass("summer2019", 30, c1);
        SchoolClass sc3 = new SchoolClass("fall2019", 30, c2);
        SchoolClass sc4 = new SchoolClass("summer2020", 30, c3);
        SchoolClass sc5 = new SchoolClass("fall 2020", 30, c4);

        Teacher t1 = new Teacher("Arne");
        Teacher t2 = new Teacher("Pernille");
        Teacher t3 = new Teacher("Carsten");
        Teacher t4 = new Teacher("Lars");

        Student s1 = new Student("Niels", "niels@mail.com");
        Student s2 = new Student("Torben", "torben@mail.com");
        Student s3 = new Student("Lars", "lars@mail.com");
        Student s4 = new Student("Carla", "carla@gail.com");
        Student s5 = new Student("Frederik", "fred@mail.com");

        SignedUp su1 = new SignedUp(-10, "null");
        SignedUp su2 = new SignedUp(12, "2018-03-23");
        SignedUp su3 = new SignedUp(12, "2019-10-10");
        SignedUp su4 = new SignedUp(7, "2020-01-27");
        SignedUp su5 = new SignedUp(02, "2019-6-8");

        sc1.addTeacher(t1);
        sc2.addTeacher(t2);
        sc3.addTeacher(t2);
        sc4.addTeacher(t3);
        sc5.addTeacher(t4);

        c1.addSchoolClass(sc1);
        c2.addSchoolClass(sc1);
        c1.addSchoolClass(sc2);
        c2.addSchoolClass(sc3);
        c3.addSchoolClass(sc4);
        c4.addSchoolClass(sc5);
        c3.addSchoolClass(sc1);
        c4.addSchoolClass(sc5);

        s1.addSignedUp(su1);
        s1.addSignedUp(su2);
        s2.addSignedUp(su4);
        s3.addSignedUp(su3);
        s3.addSignedUp(su2);
        s4.addSignedUp(su5);
        s5.addSignedUp(su3);

        su1.setSchoolclass(sc3);
        su2.setSchoolclass(sc1);
        su3.setSchoolclass(sc1);
        su4.setSchoolclass(sc3);
        su2.setSchoolclass(sc2);
        su5.setSchoolclass(sc5);
        su1.setSchoolclass(sc4);

        try {

            em.getTransaction().begin();

            em.createNamedQuery("Teacher.deleteAllRows").executeUpdate();
            em.createNamedQuery("Student.deleteAllRows").executeUpdate();
            em.createNamedQuery("SignedUp.deleteAllRows").executeUpdate();
            em.createNamedQuery("SchoolClass.deleteAllRows").executeUpdate();
            em.createNamedQuery("Course.deleteAllRows").executeUpdate();

            em.persist(s1);
            em.persist(s2);
            em.persist(s3);
            em.persist(s4);
            em.persist(s5);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    
    }
}
