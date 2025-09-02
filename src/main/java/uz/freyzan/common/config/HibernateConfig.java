// package uz.freyzan.common.config;

// import org.hibernate.SessionFactory;
// import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
// import org.hibernate.cfg.Configuration;
// import uz.freyzan.user.UserEntity;

// public class HibernateConfig {

//     private static final SessionFactory sessionFactory = buildSessionFactory();

//     private static SessionFactory buildSessionFactory() {
//         try {
//             Configuration configuration = new Configuration();
//             configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
//             configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mini_crm");
//             configuration.setProperty("hibernate.connection.username", "postgres");
//             configuration.setProperty("hibernate.connection.password", "postgres");
//             configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//             configuration.setProperty("hibernate.show_sql", "true");
//             configuration.setProperty("hibernate.hbm2ddl.auto", "update"); 

//             configuration.addAnnotatedClass(UserEntity.class);

//             return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
//         } catch (Throwable ex) {
//             throw new ExceptionInInitializerError(ex);
//         }
//     }

//     public static SessionFactory getSessionFactory() {
//         return sessionFactory;
//     }
// }

package uz.freyzan.common.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import uz.freyzan.user.UserEntity;

public class HibernateConfig {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // берём значения из environment variables или используем дефолт
            String dbUrl = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/mini_crm");
            String dbUser = System.getenv().getOrDefault("DB_USERNAME", "postgres");
            String dbPass = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", dbUrl);
            configuration.setProperty("hibernate.connection.username", dbUser);
            configuration.setProperty("hibernate.connection.password", dbPass);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(UserEntity.class);

            return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build()
            );
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
