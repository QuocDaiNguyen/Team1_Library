package team1.asm_project1_team1.Databases.Firebases;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import team1.asm_project1_team1.model.Book_DaiNQ;

public class FirebaseDataHelper_DaiNQ {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<Book_DaiNQ> books = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Book_DaiNQ> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDataHelper_DaiNQ(){
        mDatabase=FirebaseDatabase.getInstance();
        mReferenceBook=mDatabase.getReference().child("Book");
    }

    public void readBooks(final DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                books.clear();
                List<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Book_DaiNQ book = keyNode.getValue(Book_DaiNQ.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
