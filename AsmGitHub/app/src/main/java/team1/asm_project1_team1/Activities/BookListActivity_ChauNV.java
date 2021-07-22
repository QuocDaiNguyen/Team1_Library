package team1.asm_project1_team1.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import team1.asm_project1_team1.Databases.Adapter.BookAdapter_ChauNV;
import team1.asm_project1_team1.Net.BookClient_ChauNV;
import team1.asm_project1_team1.R;
import team1.asm_project1_team1.model.Book_ChauNV;

public class BookListActivity_ChauNV extends AppCompatActivity {
    private ListView lvBooks;
    private BookAdapter_ChauNV bookAdapter;
    private BookClient_ChauNV client;
    //private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        lvBooks = (ListView) findViewById(R.id.lvBooks);
      //  progress = (ProgressBar) findViewById(R.id.progress);
        ArrayList<Book_ChauNV> aBooks = new ArrayList<Book_ChauNV>();
        bookAdapter = new BookAdapter_ChauNV(this, aBooks);
        lvBooks.setAdapter(bookAdapter);
        fetchBooks();
    }
    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchBooks() {
        client = new BookClient_ChauNV();
        client.getBooks("oscar Wilde", new JsonHttpResponseHandler()
        {


            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray docs = null;
                    if(response != null) {
                        // Get the docs json array
                        docs = response.getJSONArray("docs");
                        // Parse json array into array of model objects
                        final ArrayList<Book_ChauNV> books = Book_ChauNV.fromJson(docs);
                        // Remove all books from the adapter
                        bookAdapter.clear();
                        // Load model objects into the adapter
                        for (Book_ChauNV book : books) {
                            bookAdapter.add(book); // add book through the adapter
                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }
        });
    }
}