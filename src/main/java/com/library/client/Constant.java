package com.library.client;

public class Constant {

	// ResponseBody
	public final static String Status = "status";
	public final static String Body = "body";

	// Session
	public final static String Name = "name";  
	public final static String Phone = "phone"; 
	public final static String IsAdmin = "isAdmin";
	public final static String Amount = "amount";

	// Sex
	public final static Integer SexNo = 0;
	public final static Integer SexMale = 1;
	public final static Integer SexFemale = 2;

	public final static String getSex(Integer sex) {
		if (sex.equals(SexMale)) {
			return "男";
		}
		if (sex.equals(SexFemale)) {
			return "女";
		}
		return "保密";
	}

	// Is Admin
	public final static Integer NormalUser = 1;
	public final static Integer AdminUser = 2;

	// DB
	public final static Integer Page_Size = 10;

	// Max Margin
	public final static Integer Max_Margin = 15;

	// HTTP Status
	public final static Integer HTTP_OK = 200;
	public final static Integer Bad_Request = 400;

	// Common Status 600
	public final static Integer Repeated = 600;

	// Event Status 700
	public final static Integer No_Margin = 700;
	public final static Integer Error_Borrow = 701;
	public final static Integer Error_Return = 701;

	// Book Status 800
	public final static Integer Zore_Inventory = 800;
	public final static Integer Book_Not_Found = 801;

	// User Status 900
	public final static Integer Login_Fail = 900;
	public final static Integer User_Not_Found = 901;
	public final static Integer Not_Login = 902;
	public final static Integer Permission_Denied = 903;
}
