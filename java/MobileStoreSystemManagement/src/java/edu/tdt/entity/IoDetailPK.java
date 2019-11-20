/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author thuan
 */
@Embeddable
public class IoDetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "warehouse_id", nullable = false)
    private long warehouseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id", nullable = false)
    private long productId;

    public IoDetailPK() {
    }

    public IoDetailPK(long warehouseId, long productId) {
        this.warehouseId = warehouseId;
        this.productId = productId;
    }

    public long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) warehouseId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IoDetailPK)) {
            return false;
        }
        IoDetailPK other = (IoDetailPK) object;
        if (this.warehouseId != other.warehouseId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.tdt.entity.IoDetailPK[ warehouseId=" + warehouseId + ", productId=" + productId + " ]";
    }
    
}
