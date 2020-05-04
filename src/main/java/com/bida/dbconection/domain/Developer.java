package com.bida.dbconection.domain;

public class Developer {
    private long id;
    private String name;
    private int age;
    private String sex;
    private long itCompanyId;
    private int salary;

    public Developer(long id, String name, int age, String sex, long itCompanyId, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.itCompanyId = itCompanyId;
        this.salary = salary;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getItCompanyId() {
        return itCompanyId;
    }

    public void setItCompanyId(long itCompanyId) {
        this.itCompanyId = itCompanyId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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
