import com.ayesh.webapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Run {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }
}
