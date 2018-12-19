package com.estudo.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Interceptor
@Transactional
public class InterceptorTransactional implements Serializable {

	private static final long serialVersionUID = -8995179343324890718L;

	@Inject
	private EntityManager entity;			
	
	@AroundInvoke
	public Object contextInterceptor(InvocationContext context) throws Exception {
		EntityTransaction trx = entity.getTransaction();
		try {
			entity.getTransaction().begin();
			Object object = context.proceed();
			entity.getTransaction().commit();
	
        return object;
        }catch(Exception e) {
			if(trx != null) {
				trx.rollback();
			}
			throw e;
		}finally {
			if (trx != null && trx.isActive()) {
				trx.commit();
			}
		}
    }
}
