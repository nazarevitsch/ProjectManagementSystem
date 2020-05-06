package com.bida.dbconection.domain;

import java.math.BigDecimal;

public class Developer {
    private long id;
    private String name;
    private int age;
    private Sex sex;
    private long itCompanyId;
    private BigDecimal salary;

    public Developer(long id, String name, int age, String sex, long itCompanyId, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.itCompanyId = itCompanyId;
        this.salary = salary;
        this.sex = Sex.getSex(sex);
    }

    public Developer(long id, String name, int age, Sex sex, long itCompanyId, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.itCompanyId = itCompanyId;
        this.salary = salary;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setSex(String sex) {
        this.sex = Sex.getSex(sex);
    }

    public long getItCompanyId() {
        return itCompanyId;
    }

    public void setItCompanyId(long itCompanyId) {
        this.itCompanyId = itCompanyId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", itCompanyId=" + itCompanyId +
                ", salary=" + salary +
                '}';
    }
}
