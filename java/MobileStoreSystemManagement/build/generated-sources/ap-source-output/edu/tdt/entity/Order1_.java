package edu.tdt.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order1.class)
public abstract class Order1_ {

	public static volatile SingularAttribute<Order1, Date> createdAt;
	public static volatile SingularAttribute<Order1, String> note;
	public static volatile SingularAttribute<Order1, Integer> total;
	public static volatile CollectionAttribute<Order1, OrderDetail> orderDetailCollection;
	public static volatile SingularAttribute<Order1, Customer> customerId;
	public static volatile SingularAttribute<Order1, Long> id;
	public static volatile SingularAttribute<Order1, Store> storeId;
	public static volatile SingularAttribute<Order1, User> staffId;

}

