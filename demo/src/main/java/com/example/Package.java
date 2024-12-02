package com.example;

public class Package {
    private String packageUid;
    private String studentId;
    private String description;
    private String status;
    private String arrivalDate;

    public Package(String packageUid, String studentId, String description, String status, String arrivalDate) {
        this.packageUid = packageUid;
        this.studentId = studentId;
        this.description = description;
        this.status = status;
        this.arrivalDate = arrivalDate;
    }

    public String getPackageUid() { return packageUid; }
    public String getStudentId() { return studentId; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getArrivalDate() { return arrivalDate; }
}