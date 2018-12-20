package com.estudo.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
public class InterceptorTransactional implements Serializable{

	private static final long serialVersionUID = -6414365805388183593L;

	@Inject
	private EntityManager entityManager;


	@AroundInvoke
	public Object contextInterceptor(InvocationContext context) throws Exception {
		EntityTransaction trx = entityManager.getTransaction();
		try {
			if (!trx.isActive()) {
				trx.begin();
			}
			return context.proceed();
		} catch (Exception e) {
			if (trx != null) {
				trx.rollback();
			}

			throw e;
		} finally {
			if (trx != null && trx.isActive()) {
				trx.commit();
			}
		}
	}
}
