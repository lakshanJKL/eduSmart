package com.devstack.edu.validator;

public class SimpleTextValidator {

    public static boolean validateName(String name){
    return name.matches("^[a-zA-Z]{3,45}$"); // anura
}
    public static boolean validateEmail(String email){
    return email.matches("^[a-zA-Z0-9]{1,45}@[a-zA-Z0-9]{1,45}+\\.+[a-zA-Z0-9]{1,45}+$"); // ranil@gmail.com

}
    public static boolean validateDob(String dob){
    return dob.matches("\\d{4}-\\d{1,2}-\\d{1,2}");//2024-11-25
}

    public static boolean validateAddress(String address){
        return address.matches("^[a-zA-Z]{3,45}$"); // kaluthara
    }
    public static boolean validateNic(String nic){
    return  nic.matches("^[0-9]{1,45}[vV0-9]{1}$"); //200129802998v
    }
    public static boolean validateProgram(String program){
        return program.matches("^[0-9]{1,50}\\s+[a-zA-Z0-9]+$"); //1  Java
    }
    public static boolean validateIntake(String intake){
        return intake.matches("^[0-9]{1,50}\\s+[a-zA-Z0-9]+$"); //1  May
    }
    public static boolean validateTrainer(String trainer){
        return trainer.matches("^[0-9]{1,50}\\s+[a-zA-Z0-9]+$"); //1  sunil
    }
    public static boolean validateHousr(String hour){
        return hour.matches("^[0-9]{1,2}$"); // 3
    }
    public static boolean validateAmount(String amount){
        return amount.matches("^[0-9]{4,50}$");//15000
    }
    public static boolean validateContent(String content) {
        System.out.println(content);
        return content.matches("^[a-zA-Z0-9]{1,50}\\s+[a-zA-Z0-9]{1,50}+$"); // Exception Handling
    }
}
