package Model.tools;

import java.util.regex.Pattern;

public class Validation {
    public String firstNameValidator(String firstName) throws Exception{
        if (Pattern.matches("^[a-zA-Z\\s]{3,20}+", firstName)) {
            return firstName;
        }else {
            throw new Exception("Invalid firstName");
        }
    }

    public String lastNameValidator(String lastName) throws Exception{
        if (Pattern.matches("^[a-zA-Z\\s]{3,25}+", lastName)) {
            return lastName;
        }else {
            throw new Exception("Invalid lastName");
        }
    }



    //todo: چجوری بنویسم  فقط 10 عدد وارد کنید و نباید کمتر یا بیشتر از 10 عدد نباشد
    public String nationalIDValidator(String nationalID) throws Exception{
        if (Pattern.matches("^{3,20}+", nationalID)) {
            return nationalID;
        }else {
            throw new Exception("Invalid nationalID");
        }
    }

    public String phoneNumberValidator(String phoneNumber) throws Exception{
        if (Pattern.matches("" , phoneNumber)) {
            return phoneNumber;
        }else {
            throw new Exception("Invalid phoneNumber");
        }
    }

    public String userNameValidator(String userName) throws Exception{
        if (Pattern.matches("^[a-zA-Z\\s]{3,20}+", userName)) {
            return userName;
        }else {
            throw new Exception("Invalid userName");
        }
    }
    public String passwordValidator(String password) throws Exception{
        if (Pattern.matches("^[a-zA-Z\\s]{3,20}+", password)) {
            return password;
        }else {
            throw new Exception("Invalid password");
        }
    }
}
