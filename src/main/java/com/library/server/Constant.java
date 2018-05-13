package com.library.server;

public class Constant {

    // ResponseBody
    public final static String Status = "status";
    public final static String Body = "body";

    // Session
    public final static String Name = "name";  
    public final static String Phone = "phone"; 
    public final static String IsAdmin = "isAdmin";

    // HTTP Status
    public final static Integer HTTP_OK = 200;
    public final static Integer Bad_Request = 400;

    // Logical Status
    public final static Integer Repeated = 800;
    public final static Integer Login_Fail = 801;
    public final static Integer Not_Login = 802;

    // Sex
    public final static Short SexNo = 0;
    public final static Short SexMale = 1;
    public final static Short SexFemale = 2;

    // Is Admin
    public final static Short NormalUser = 1;
    public final static Short AdminUser = 2;

    // DB
    public final static Integer Page_Size = 10;
}
