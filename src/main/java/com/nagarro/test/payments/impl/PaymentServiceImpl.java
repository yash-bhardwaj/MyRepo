package com.nagarro.test.payments.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nagarro.test.payments.dao.EmployeeDataDAO;
import com.nagarro.test.payments.service.PaymentService;

/**
 * The Payment service.
 */
public class PaymentServiceImpl implements PaymentService {

    /**
     * @return size of data
     *
     * @throws IOException
     */
    public int processEmpPayments() throws IOException {
        final AllFileReader allFileReader = new AllFileReader.Builder().setFileType(".csv")
                .setFileLocation("src/main/resources/employeePayments.csv").build();
        final List<EmployeeDataDAO> targetData = getTargetData(getEmployeeData(allFileReader.getFileReader()));
        final File targetDataFile = new File("src/main/resources/salaryStats.csv");
        final FileWriter writer = new FileWriter(targetDataFile);
        final List<String> stream = targetData.stream().map(EmployeeDataDAO::toString).collect(Collectors.toList());
        stream.forEach(record -> {
            try {
                writer.write(record);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
        return targetData.size();
    }

    /**
     * @param employees
     *
     * @return list of target Data
     */
    private List<EmployeeDataDAO> getTargetData(final List<EmployeeDataDAO> employees) {
        final List<EmployeeDataDAO> targetData = new ArrayList<>();
        final Map<String, List<EmployeeDataDAO>> genderedData = employees.stream().collect(Collectors.groupingBy(EmployeeDataDAO::getGender));
        genderedData.forEach((gender, data) -> data.stream().collect(Collectors.groupingBy(EmployeeDataDAO::getCountry)).forEach((country, employeeData) -> {
            final EmployeeDataDAO countryDataDAO = new EmployeeDataDAO();
            final Double avg = employeeData.stream().collect(Collectors.averagingDouble(EmployeeDataDAO::getSalary));
            countryDataDAO.setSalary(avg);
            countryDataDAO.setGender(gender);
            countryDataDAO.setCountry(country);
            targetData.add(countryDataDAO);
        }));
        return targetData;
    }

    /**
     * @param reader
     *
     * @return list of employee data country-wise
     *
     * @throws IOException
     */
    private List<EmployeeDataDAO> getEmployeeData(final BufferedReader reader) throws IOException {
        String line;
        String[] employeeData;
        EmployeeDataDAO dao;
        final List<EmployeeDataDAO> dataDAOS = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            employeeData = line.split(",");
            dao = new EmployeeDataDAO();
            dao.setCountry(employeeData[0]);
            dao.setGender(employeeData[1]);
            final String salary = employeeData[2];
            dao.setSalary(Double.parseDouble(salary));
            dataDAOS.add(dao);
        }
        return dataDAOS;
    }
}
