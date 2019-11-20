package edu.tdt.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> address;
	public static volatile CollectionAttribute<User, Role> roleCollection;
	public static volatile CollectionAttribute<User, Order1> order1Collection;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, Store> storeId;
	public static volatile CollectionAttribute<User, IoWarehouse> ioWarehouseCollection;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> status;

}

