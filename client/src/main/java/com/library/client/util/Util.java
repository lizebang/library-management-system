package com.library.client.util;

import com.library.client.Constant;
import com.library.client.models.Book;
import com.library.client.models.Event;
import com.library.client.models.User;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class Util {

    private static HttpClient httpClient = new HttpClient();
    private static String tmpcookies = "";

    // Configure
    private final static String HTTP_Host = "127.0.0.1";
    private final static String HTTP_Port = "8080";
    private final static String HTTP_User = "/user";
    private final static String HTTP_Book = "/book";
    private final static String HTTP_Event = "/event";

    private final static String getUrl(String group, String work) {
        return String.format("http://%s:%s%s%s", HTTP_Host, HTTP_Port, group, work);
    }

    // User
    private final static String User_Add = getUrl(HTTP_User, "/add");
    private final static String User_Reset = getUrl(HTTP_User, "/reset");
    private final static String User_ChangeInfo = getUrl(HTTP_User, "/changeinfo");
    private final static String User_ChangePhone = getUrl(HTTP_User, "/changephone");
    private final static String User_ChangePassword = getUrl(HTTP_User, "/changepassword");
    private final static String User_Delete = getUrl(HTTP_User, "/delete");
    private final static String User_GetByName = getUrl(HTTP_User, "/getbyname");
    private final static String User_GetById = getUrl(HTTP_User, "/getbyid");
    private final static String User_All = getUrl(HTTP_User, "/all");
    private final static String User_Login = getUrl(HTTP_User, "/login");
    private final static String User_Logout = getUrl(HTTP_User, "/logout");

    // Book
    private final static String Book_Add = getUrl(HTTP_Book, "/add");
    private final static String Book_Delete = getUrl(HTTP_Book, "/delete");
    private final static String Book_GetByIsbn = getUrl(HTTP_Book, "/getbyisbn");
    private final static String Book_GetByAuthor = getUrl(HTTP_Book, "/getbyauthor");
    private final static String Book_GetByTag = getUrl(HTTP_Book, "/getbytag");
    private final static String Book_GetByKeyword = getUrl(HTTP_Book, "/getbykeyword");
    private final static String Book_GetFuzzy = getUrl(HTTP_Book, "/getfuzzy");
    private final static String Book_All = getUrl(HTTP_Book, "/all");

    // Event
    private final static String Event_Borrow = getUrl(HTTP_Event, "/borrow");
    private final static String Event_Return = getUrl(HTTP_Event, "/return");
    private final static String Event_GetByPhone = getUrl(HTTP_Event, "/getbyphone");

    public final static Integer addUser(String name, String sex, String phone, String password)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_Add);

        NameValuePair[] reqBody = {new NameValuePair(User.UserName, name),
                new NameValuePair(User.UserSex, sex), new NameValuePair(User.UserPhone, phone),
                new NameValuePair(User.UserPassword, password),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer resetPassword(String name, String phone)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_Reset);

        NameValuePair[] reqBody =
                {new NameValuePair(User.UserName, name), new NameValuePair(User.UserPhone, phone),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer changeInfo(String name, String sex)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_ChangeInfo);

        NameValuePair[] reqBody =
                {new NameValuePair(User.UserName, name), new NameValuePair(User.UserSex, sex),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer changePhone(String newPhone)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_ChangePhone);

        NameValuePair[] reqBody = {new NameValuePair("newPhone", newPhone),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer changePassword(String oldPassword, String newPassword)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_ChangePassword);

        NameValuePair[] reqBody = {new NameValuePair("oldPassword", oldPassword),
                new NameValuePair("newPassword", newPassword),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer deleteUser(String id, Integer isAdmin)
            throws LibraryException, JSONException, IOException {
        if (isAdmin != Constant.AdminUser) {
            throw new LibraryException(Constant.Permission_Denied);
        }

        PostMethod postMethod = new PostMethod(User_Delete);

        NameValuePair[] reqBody = {new NameValuePair(User.UserId, id),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static User[] getUserByName(String name, int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_GetByName);

        NameValuePair[] reqBody = {new NameValuePair(User.UserName, name),
                new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                User[] users = new User[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    User user = new User(body.getLong(User.UserId), body.getString(User.UserName),
                            body.getInt(User.UserSex), body.getString(User.UserPhone),
                            body.getInt(User.UserIsAdmin), body.getInt(User.UserAmount));
                    user.setTotalPages(totalPages);

                    users[i] = user;
                }
                return users;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static User getUserById(String id)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_GetById);

        NameValuePair[] reqBody = {new NameValuePair(User.UserId, id),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                JSONObject body = json.getJSONObject(Constant.Body);

                User user = new User(body.getLong(User.UserId), body.getString(User.UserName),
                        body.getInt(User.UserSex), body.getString(User.UserPhone),
                        body.getInt(User.UserIsAdmin), body.getInt(User.UserAmount));
                return user;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static User[] getAllUser(int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_All);

        NameValuePair[] reqBody = {new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                User[] users = new User[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    User user = new User(body.getLong(User.UserId), body.getString(User.UserName),
                            body.getInt(User.UserSex), body.getString(User.UserPhone),
                            body.getInt(User.UserIsAdmin), body.getInt(User.UserAmount));

                    user.setTotalPages(totalPages);
                    users[i] = user;
                }
                return users;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static User login(String phone, String password)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(User_Login);

        NameValuePair[] reqBody = {new NameValuePair(User.UserPhone, phone),
                new NameValuePair(User.UserPassword, password),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            httpClient.executeMethod(postMethod);

            Cookie[] cookies = httpClient.getState().getCookies();
            for (Cookie c : cookies) {
                tmpcookies += c.toString() + ";";
            }

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                JSONObject body = json.getJSONObject(Constant.Body);

                User user = new User(body.getLong(User.UserId), body.getString(User.UserName),
                        body.getInt(User.UserSex), body.getString(User.UserPhone),
                        body.getInt(User.UserIsAdmin), body.getInt(User.UserAmount));
                return user;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer logout() throws LibraryException, JSONException, IOException {
        GetMethod getMethod = new GetMethod(User_Logout);

        try {
            getMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(getMethod);

            String respBody = getMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            tmpcookies = "";
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer addBook(String isbn, String mark, String name, String tag,
            String author, String introduction, String amount, String inventory, Integer isAdmin)
            throws LibraryException, JSONException, IOException {
        if (isAdmin != Constant.AdminUser) {
            throw new LibraryException(Constant.Permission_Denied);
        }

        PostMethod postMethod = new PostMethod(Book_Add);

        NameValuePair[] reqBody = {new NameValuePair(Book.BookIsbn, isbn),
                new NameValuePair(Book.BookMark, mark), new NameValuePair(Book.BookName, name),
                new NameValuePair(Book.BookTag, tag), new NameValuePair(Book.BookAuthor, author),
                new NameValuePair(Book.BookIntroduction, introduction),
                new NameValuePair(Book.BookAmount, amount),
                new NameValuePair(Book.BookInventory, inventory),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer deleteBook(String isbn, Integer isAdmin)
            throws LibraryException, JSONException, IOException {
        if (isAdmin != Constant.AdminUser) {
            throw new LibraryException(Constant.Permission_Denied);
        }

        PostMethod postMethod = new PostMethod(Book_Delete);

        NameValuePair[] reqBody = {new NameValuePair(Book.BookIsbn, isbn),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Book getBookByIsbn(String isbn)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Book_GetByIsbn);

        NameValuePair[] reqBody = {new NameValuePair(Book.BookIsbn, isbn),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                JSONObject body = json.getJSONObject(Constant.Body);

                Book book = new Book(body.getLong(Book.BookId), body.getString(Book.BookIsbn),
                        body.getString(Book.BookMark), body.getString(Book.BookName),
                        body.getString(Book.BookTag), body.getString(Book.BookAuthor),
                        body.getString(Book.BookIntroduction), body.getInt(Book.BookAmount),
                        body.getInt(Book.BookInventory));
                return book;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Book[] getBookByAuthor(String author, int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Book_GetByAuthor);

        NameValuePair[] reqBody = {new NameValuePair(Book.BookAuthor, author),
                new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                Book[] books = new Book[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    Book book = new Book(body.getLong(Book.BookId), body.getString(Book.BookIsbn),
                            body.getString(Book.BookMark), body.getString(Book.BookName),
                            body.getString(Book.BookTag), body.getString(Book.BookAuthor),
                            body.getString(Book.BookIntroduction), body.getInt(Book.BookAmount),
                            body.getInt(Book.BookInventory));

                    book.setTotalPages(totalPages);
                    books[i] = book;
                }
                return books;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Book[] getBookByTag(String tag, int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Book_GetByTag);

        NameValuePair[] reqBody = {new NameValuePair(Book.BookTag, tag),
                new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                Book[] books = new Book[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    Book book = new Book(body.getLong(Book.BookId), body.getString(Book.BookIsbn),
                            body.getString(Book.BookMark), body.getString(Book.BookName),
                            body.getString(Book.BookTag), body.getString(Book.BookAuthor),
                            body.getString(Book.BookIntroduction), body.getInt(Book.BookAmount),
                            body.getInt(Book.BookInventory));

                    book.setTotalPages(totalPages);
                    books[i] = book;
                }
                return books;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Book[] getBookByKeyword(String keyword, int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Book_GetByKeyword);

        NameValuePair[] reqBody = {new NameValuePair("keyword", keyword),
                new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                Book[] books = new Book[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    Book book = new Book(body.getLong(Book.BookId), body.getString(Book.BookIsbn),
                            body.getString(Book.BookMark), body.getString(Book.BookName),
                            body.getString(Book.BookTag), body.getString(Book.BookAuthor),
                            body.getString(Book.BookIntroduction), body.getInt(Book.BookAmount),
                            body.getInt(Book.BookInventory));

                    book.setTotalPages(totalPages);
                    books[i] = book;
                }
                return books;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Book[] getBookFuzzy(String keyword, int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Book_GetFuzzy);

        NameValuePair[] reqBody = {new NameValuePair("keyword", keyword),
                new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                Book[] books = new Book[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    Book book = new Book(body.getLong(Book.BookId), body.getString(Book.BookIsbn),
                            body.getString(Book.BookMark), body.getString(Book.BookName),
                            body.getString(Book.BookTag), body.getString(Book.BookAuthor),
                            body.getString(Book.BookIntroduction), body.getInt(Book.BookAmount),
                            body.getInt(Book.BookInventory));

                    book.setTotalPages(totalPages);
                    books[i] = book;
                }
                return books;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Book[] getAllBook(int page)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Book_All);

        NameValuePair[] reqBody = {new NameValuePair("page", String.valueOf(page)),};

        postMethod.setRequestBody(reqBody);

        try {
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                Book[] books = new Book[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    Book book = new Book(body.getLong(Book.BookId), body.getString(Book.BookIsbn),
                            body.getString(Book.BookMark), body.getString(Book.BookName),
                            body.getString(Book.BookTag), body.getString(Book.BookAuthor),
                            body.getString(Book.BookIntroduction), body.getInt(Book.BookAmount),
                            body.getInt(Book.BookInventory));

                    book.setTotalPages(totalPages);
                    books[i] = book;
                }
                return books;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer borrowBook(String isbn)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Event_Borrow);

        NameValuePair[] reqBody = {new NameValuePair(Event.EventIsbn, isbn),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Integer returnBook(String isbn)
            throws LibraryException, JSONException, IOException {
        PostMethod postMethod = new PostMethod(Event_Return);

        NameValuePair[] reqBody = {new NameValuePair(Event.EventIsbn, isbn),};

        postMethod.setRequestBody(reqBody);

        try {
            postMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(postMethod);

            String respBody = postMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            }
            return status;
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }

    public final static Event[] getEventByPhone()
            throws LibraryException, JSONException, IOException {
        GetMethod getMethod = new GetMethod(Event_GetByPhone);

        try {
            getMethod.setRequestHeader("cookie", tmpcookies);
            httpClient.executeMethod(getMethod);

            String respBody = getMethod.getResponseBodyAsString();
            JSONObject json = new JSONObject(respBody);

            Integer status = json.getInt(Constant.Status);
            if (!status.equals(Constant.HTTP_OK)) {
                throw new LibraryException(status);
            } else {
                Integer totalPages = json.getInt(Constant.TotalPages);

                JSONArray array = json.getJSONArray(Constant.Body);

                Event[] events = new Event[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    JSONObject body = new JSONObject(array.get(i).toString());

                    Event event = new Event(body.getLong(Event.EventId),
                            body.getString(Event.EventPhone), body.getString(Event.EventIsbn),
                            body.getString(Event.EventName), body.getString(Event.EventTag),
                            body.getString(Event.EventAuthor));

                    event.setTotalPages(totalPages);
                    events[i] = event;
                }
                return events;
            }
        } catch (JSONException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
    }
}
