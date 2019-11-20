package edu.tdt.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Store.class)
public abstract class Store_ {

	public static volatile SingularAttribute<Store, String> address;
	public static volatile CollectionAttribute<Store, Order1> order1Collection;
	public static volatile SingularAttribute<Store, String> phone;
	public static volatile CollectionAttribute<Store, User> userCollection;
	public static volatile SingularAttribute<Store, String> name;
	public static volatile SingularAttribute<Store, Long> id;
	public static volatile CollectionAttribute<Store, IoWarehouse> ioWarehouseCollection;
	public static volatile SingularAttribute<Store, String> email;
	public static volatile SingularAttribute<Store, Boolean> status;

}

