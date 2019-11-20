/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thuan
 */
@Entity
@Table(name = "product", catalog = "MobileDB", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
    , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
    , @NamedQuery(name = "Product.findByAfterCamera", query = "SELECT p FROM Product p WHERE p.afterCamera = :afterCamera")
    , @NamedQuery(name = "Product.findByBeforeCamera", query = "SELECT p FROM Product p WHERE p.beforeCamera = :beforeCamera")
    , @NamedQuery(name = "Product.findByColor", query = "SELECT p FROM Product p WHERE p.color = :color")
    , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")
    , @NamedQuery(name = "Product.findByMemory", query = "SELECT p FROM Product p WHERE p.memory = :memory")
    , @NamedQuery(name = "Product.findByOperator", query = "SELECT p FROM Product p WHERE p.operator = :operator")
    , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")
    , @NamedQuery(name = "Product.findByWarranty", query = "SELECT p FROM Product p WHERE p.warranty = :warranty")
    , @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status")
    , @NamedQuery(name = "Product.findByScreen", query = "SELECT p FROM Product p WHERE p.screen = :screen")
    , @NamedQuery(name = "Product.findByPin", query = "SELECT p FROM Product p WHERE p.pin = :pin")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "after_camera")
    private Integer afterCamera;
    @Column(name = "before_camera")
    private Integer beforeCamera;
    @Size(max = 30)
    @Column(name = "color", length = 30)
    private String color;
    @Size(max = 2147483647)
    @Column(name = "description", length = 2147483647)
    private String description;
    @Column(name = "memory")
    private Integer memory;
    @Size(max = 30)
    @Column(name = "operator", length = 30)
    private String operator;
    @Column(name = "price")
    private Integer price;
    @Column(name = "warranty")
    private Integer warranty;
    @Column(name = "status")
    private Boolean status;
    @Size(max = 100)
    @Column(name = "screen", length = 100)
    private String screen;
    @Size(max = 100)
    @Column(name = "pin", length = 100)
    private String pin;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplierId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch=FetchType.EAGER)
    private Collection<IoDetail> ioDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch=FetchType.EAGER)
    private Collection<OrderDetail> orderDetailCollection;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAfterCamera() {
        return afterCamera;
    }

    public void setAfterCamera(Integer afterCamera) {
        this.afterCamera = afterCamera;
    }

    public Integer getBeforeCamera() {
        return beforeCamera;
    }

    public void setBeforeCamera(Integer beforeCamera) {
        this.beforeCamera = beforeCamera;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }

    @XmlTransient
    public Collection<IoDetail> getIoDetailCollection() {
        return ioDetailCollection;
    }

    public void setIoDetailCollection(Collection<IoDetail> ioDetailCollection) {
        this.ioDetailCollection = ioDetailCollection;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%3s %2s %30s %2s %10s %2s %19s %2s %8s %2s %20s %2s %30s %2s %20s %2s %35s %2s %2s", this.id, "|", this.name.replaceAll("\\s\\s", ""), "|", this.supplierId.getName().replaceAll("\\s\\s", ""), "|", this.operator.replaceAll("\\s\\s", ""), "|", this.memory +" GB", "|", this.color.replaceAll("\\s\\s", ""), "|", this.screen.replaceAll("\\s\\s", ""), "|", this.afterCamera + " MP - " + this.beforeCamera +" MP", "|", this.pin.replaceAll("\\s\\s", ""), "|", this.price);
    }
    
}
