package team1.asm_project1_team1.model;


import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Book_ChauNV {
    private String openLibraryId;
    private String author;
    private String title;

    public static Book_ChauNV fromJson(JSONObject jsonObject) {
        Book_ChauNV bookChauNV = new Book_ChauNV();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            if (jsonObject.has("cover_edition_key"))  {
                bookChauNV.openLibraryId = jsonObject.getString("cover_edition_key");
            } else if(jsonObject.has("edition_key")) {
                final JSONArray ids = jsonObject.getJSONArray("edition_key");
                bookChauNV.openLibraryId = ids.getString(0);
            }
            bookChauNV.title = jsonObject.has("title_suggest") ? jsonObject.getString("title_suggest") : "";
            bookChauNV.author = getAuthor(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return bookChauNV;
    }

    // Return comma separated author list when there is more than one author
    private static String getAuthor(final JSONObject jsonObject) {
        try {
            final JSONArray authors = jsonObject.getJSONArray("author_name");
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }
    }

    public static ArrayList<Book_ChauNV> fromJson(JSONArray jsonArray) {
        ArrayList<Book_ChauNV> bookChauNVS = new ArrayList<Book_ChauNV>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book_ChauNV bookChauNV = Book_ChauNV.fromJson(bookJson);
            if (bookChauNV != null) {
                bookChauNVS.add(bookChauNV);
            }
        }
        return bookChauNVS;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryId + "-M.jpg?default=false";
    }

    public String getAuthor() {
        return author;
    }
}
