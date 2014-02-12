/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.semeru.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Samuel
 */
public class HibernateUtil {
    
    
    private static final SessionFactory sessionFactory;
    public static final String HIBERNATE_SESSION = "hiberante_session";
    
    
    static {
        try{
            System.out.println("Tentando abrir uma SF");
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistryBuilder = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder);
            System.out.println("Session factory criada corretamente");
        }catch(Exception ex){
            System.out.println("Ocorreu um erro ao iniciar a SF." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    
    
}
