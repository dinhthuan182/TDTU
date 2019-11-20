/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuan
 */
@Entity
@Table(name = "io_detail", catalog = "MobileDB", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IoDetail.findAll", query = "SELECT i FROM IoDetail i")
    , @NamedQuery(name = "IoDetail.findByWarehouseId", query = "SELECT i FROM IoDetail i WHERE i.ioDetailPK.warehouseId = :warehouseId")
    , @NamedQuery(name = "IoDetail.findByProductId", query = "SELECT i FROM IoDetail i WHERE i.ioDetailPK.productId = :productId")
    , @NamedQuery(name = "IoDetail.findByPrice", query = "SELECT i FROM IoDetail i WHERE i.price = :price")
    , @NamedQuery(name = "IoDetail.findByQuantity", query = "SELECT i FROM IoDetail i WHERE i.quantity = :quantity")})
public class IoDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IoDetailPK ioDetailPK;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IoWarehouse ioWarehouse;
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public IoDetail() {
    }

    public IoDetail(IoDetailPK ioDetailPK) {
        this.ioDetailPK = ioDetailPK;
    }

    public IoDetail(long warehouseId, long productId) {
        this.ioDetailPK = new IoDetailPK(warehouseId, productId);
    }

    public IoDetailPK getIoDetailPK() {
        return ioDetailPK;
    }

    public void setIoDetailPK(IoDetailPK ioDetailPK) {
        this.ioDetailPK = ioDetailPK;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public IoWarehouse getIoWarehouse() {
        return ioWarehouse;
    }

    public void setIoWarehouse(IoWarehouse ioWarehouse) {
        this.ioWarehouse = ioWarehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ioDetailPK != null ? ioDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IoDetail)) {
            return false;
        }
        IoDetail other = (IoDetail) object;
        if ((this.ioDetailPK == null && other.ioDetailPK != null) || (this.ioDetailPK != null && !this.ioDetailPK.equals(other.ioDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%25s %5s %6s %5s", this.product.getName().replaceAll("\\s\\s", ""), "|", this.price, "|", this.quantity);
    }
    
}
