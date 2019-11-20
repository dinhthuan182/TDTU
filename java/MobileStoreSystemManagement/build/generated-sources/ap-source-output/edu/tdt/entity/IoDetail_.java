package edu.tdt.entity;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IoDetail.class)
public abstract class IoDetail_ {

	public static volatile SingularAttribute<IoDetail, Product> product;
	public static volatile SingularAttribute<IoDetail, Integer> quantity;
	public static volatile SingularAttribute<IoDetail, BigInteger> price;
	public static volatile SingularAttribute<IoDetail, IoDetailPK> ioDetailPK;
	public static volatile SingularAttribute<IoDetail, IoWarehouse> ioWarehouse;

}

