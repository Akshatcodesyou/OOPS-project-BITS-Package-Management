package com.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PackageManager {
    private static final String PACKAGE_FILE = "packages.txt";
    private static final String REPORT_FILE = "admin_reports.txt";
    private static final String GLOBAL_REPORT = "reports.txt";

    public String generatePackageUid() {
        return "PKG" + System.currentTimeMillis();
    }

    public void registerPackage(String packageUid, String studentId, String description, String arrivalDate) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PACKAGE_FILE, true))) {
            writer.write(packageUid + "," + studentId.toUpperCase() + "," + description + "," + "Pending" + "," + arrivalDate);
            writer.newLine();
        }
    }

    public List<Package> getPackages() {
        List<Package> packages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PACKAGE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] packageDetails = line.split(",");
                packages.add(new Package(packageDetails[0], packageDetails[1], packageDetails[2], packageDetails[3], packageDetails[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packages;
    }

    public void pickUpPackage(String packageUid, String pickerId, String collegeId) {
        String timestamp = getCurrentDateTime();
        String report = "Package " + packageUid + " was picked up by " + pickerId + " at " + timestamp;
        String userReport = "User ID: " + collegeId + " - Your package " + packageUid + " was picked up by " + pickerId + " at " + timestamp;

        saveReportToFile(REPORT_FILE, report);
        saveReportToFile(GLOBAL_REPORT, userReport, collegeId);

        updatePackageStatus(packageUid, "Picked Up");
        deletePackage(packageUid);
    }

    public void deletePackage(String packageUid) {
        try {
            File inputFile = new File(PACKAGE_FILE);
            File tempFile = new File("packages_temp.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] packageDetails = line.split(",");
                    if (!packageDetails[0].equals(packageUid)) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            inputFile.delete();
            tempFile.renameTo(inputFile);

            String timestamp = getCurrentDateTime();
            String report = "Package " + packageUid + " was deleted at " + timestamp;
            saveReportToFile(REPORT_FILE, report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePackageStatus(String packageUid, String newStatus) {
        try {
            File inputFile = new File(PACKAGE_FILE);
            File tempFile = new File("packages_temp.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] packageDetails = line.split(",");
                    if (packageDetails[0].equals(packageUid)) {
                        packageDetails[3] = newStatus;
                    }
                    writer.write(String.join(",", packageDetails));
                    writer.newLine();
                }
            }

            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveReportToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(report);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveReportToFile(String fileName, String report, String collegeId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("User ID: " + collegeId + " - " + report);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public List<String> getReports() {
        List<String> reports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reports.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
