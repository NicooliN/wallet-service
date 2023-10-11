package ru.wallet.model;

import ru.wallet.service.RegUser;
/**
 * Класс пользователя с полями <b>firstName</b>  <b>lastName</b> <b>phone</b>
 * @author Coolin Paul
 * @version 0.1
 * * <p>
 *  Класс с данными пользователя.
 */
public class Person {
    private String firstName;

    private String lastName;

    private String phone;

    public Person() {
    }
    /**
     * Конструктор - создание нового пользователя
     * @see Person#Person()
     * @param firstName  - Фамилия
     * @param lastName - Имя
     * @param phone - Телефон
     */
    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(person.getLastName()) : person.getLastName() != null)
            return false;
        return getPhone() != null ? getPhone().equals(person.getPhone()) : person.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}