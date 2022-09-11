package com.javaee.javaee2022teamnine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "report_type")
public class ReportType implements Serializable {
    @Id
    @Column(name = "report_type_id")
    private int id;

    @Column(name = "report_type")
    private String reportType;

    public ReportType() {
    }

    public ReportType(int id, String reportType) {
        this.id = id;
        this.reportType = reportType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
