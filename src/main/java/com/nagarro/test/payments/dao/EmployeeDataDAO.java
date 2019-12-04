package com.nagarro.test.payments.dao;

/**
 * The Employee data dao.
 */
public class EmployeeDataDAO {

    /**
     * country.
     */
    private String country;

    /**
     * gender.
     */
    private String gender;

    /**
     * salary.
     */
    private Double salary;

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return country + ", " + gender + ", " + salary + "\n";
    }

    /**
     * Sets country.
     *
     * @param country
     *         the country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender
     *         the gender
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * Gets salary.
     *
     * @return the salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary
     *         the salary
     */
    public void setSalary(final Double salary) {
        this.salary = salary;
    }
}
