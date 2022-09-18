package com.javaee.javaee2022teamnine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "contract_status")
public class ContractStatus implements Serializable {
    @Id
    @Column(name = "contract_status_id")
    private int id;

    @Column(name = "contract_status")
    private String contractStatus;

    public ContractStatus(int id, String contractStatus) {
        this.id = id;
        this.contractStatus = contractStatus;
    }

    public ContractStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public ContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public ContractStatus(int id) {
        this.id = id;
    }
}
