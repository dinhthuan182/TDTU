/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thuan
 */
@Entity
@Table(name = "io_warehouse", catalog = "MobileDB", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IoWarehouse.findAll", query = "SELECT i FROM IoWarehouse i")
    , @NamedQuery(name = "IoWarehouse.findById", query = "SELECT i FROM IoWarehouse i WHERE i.id = :id")
    , @NamedQuery(name = "IoWarehouse.findByCreatedAt", query = "SELECT i FROM IoWarehouse i WHERE i.createdAt = :createdAt")
    , @NamedQuery(name = "IoWarehouse.findByTotal", query = "SELECT i FROM IoWarehouse i WHERE i.total = :total")
    , @NamedQuery(name = "IoWarehouse.findByImport1", query = "SELECT i FROM IoWarehouse i WHERE i.import1 = :import1")})
public class IoWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "total")
    private Integer total;
    @Column(name = "import")
    private Boolean import1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ioWarehouse", fetch=FetchType.EAGER)
    private Collection<IoDetail> ioDetailCollection;
    @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Store storeId;
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User staffId;

    public IoWarehouse() {
    }

    public IoWarehouse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getImport1() {
        return import1;
    }

    public void setImport1(Boolean import1) {
        this.import1 = import1;
    }

    @XmlTransient
    public Collection<IoDetail> getIoDetailCollection() {
        return ioDetailCollection;
    }

    public void setIoDetailCollection(Collection<IoDetail> ioDetailCollection) {
        this.ioDetailCollection = ioDetailCollection;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public User getStaffId() {
        return staffId;
    }

    public void setStaffId(User staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IoWarehouse)) {
            return false;
        }
        IoWarehouse other = (IoWarehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(this.import1) {
            return String.format("%3s %2s %30s %2s %12s %2s %10s %2s %5s %5s %8s", this.id, "|", this.storeId.getName().replaceAll("\\s\\s", ""), "|", this.staffId.getUserName().replaceAll("\\s\\s", ""), "|", this.createdAt, "|", this.total, "|", "Import");
        } else {
            return String.format("%3s %2s %30s %2s %12s %2s %10s %2s %5s %5s %8s", this.id, "|", this.storeId.getName().replaceAll("\\s\\s", ""), "|", this.staffId.getUserName().replaceAll("\\s\\s", ""), "|", this.createdAt, "|", this.total, "|", "Export");
        }
    }
    
}
