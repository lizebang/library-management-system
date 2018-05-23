package com.library.client;

import com.library.client.page.*;
import com.library.client.models.*;
import com.library.client.util.*;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class Application {
        
    private static Object[][] books;

    public static void main(String[] args) {
        // try {
        //     Book[] bs = Util.getAllBook(1);
        //     books = new Object[bs.length][];
        //     for (int i = 0; i < bs.length; i++) {
        //         books[i] = bs[i].toObject();
        //     }
        //     for (Object[] objects : books) {
        //         for (Object object : objects) {
        //             System.out.print(object+"   ");
        //         }
        //         System.out.println();
        //     }
        // } catch (LibraryException exception) {
        //     System.err.println("catch (LibraryException exception)" + exception);
        // } catch (JSONException exception) {
        //     System.err.println("catch (JSONException exception)" + exception);
        // } catch (IOException exception) {
        //     System.err.println("catch (IOException exception)" + exception);
        // }

        new LoginPage("图书馆管理系统");
        // new UserPage("图书馆管理系统", new User(new Long(11), "111", 1, "17300000000", 1, 0));
        // new AdminPage("图书馆管理系统", new User(new Long(11), "111", 2, "17300000000", 1, 0));
        // new UserInfoPage("图书馆管理系统", new User(new Long(11), "111", 1, "17300000000", 1, 0));
    }
}
