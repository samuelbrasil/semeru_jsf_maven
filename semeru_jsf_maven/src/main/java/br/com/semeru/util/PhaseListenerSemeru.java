/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.semeru.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

/**
 *
 * @author Samuel
 */
public class PhaseListenerSemeru implements PhaseListener{
    
    
    //Antes da fase
    @Override
    public void beforePhase(PhaseEvent fase) {
        if(fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)){
            System.out.println("Antes da fase: " + getPhaseId().toString());
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            FacesContextUtil.setResquestSession(session);
        }
    }
    
    //Depois da fase
    @Override
    public void afterPhase(PhaseEvent fase) {
        System.out.println("Depois da fase: " + getPhaseId().toString());
        if(fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
            Session session = FacesContextUtil.getRequestSesssion();
            try{
                session.getTransaction().commit();
            }catch(Exception e){
                if(session.getTransaction().isActive()){
                    session.getTransaction().rollback();
                }
            }finally{
                session.close();
            }
            
        }
    }

    
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
    
}
