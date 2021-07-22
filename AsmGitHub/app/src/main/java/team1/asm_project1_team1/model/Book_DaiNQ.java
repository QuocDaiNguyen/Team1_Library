package team1.asm_project1_team1.model;

public class Book_DaiNQ {
    public String id_Book;
    public String name_Book;
    public String author_Book;
    public String lengths_Book;

    public Book_DaiNQ(String id_Book, String name_Book, String author_Book, String lengths_Book) {
        this.id_Book = id_Book;
        this.name_Book = name_Book;
        this.author_Book = author_Book;
        this.lengths_Book = lengths_Book;
    }

    public String getId_Book() {
        return id_Book;
    }

    public void setId_Book(String id_Book) {
        this.id_Book = id_Book;
    }

    public String getName_Book() {
        return name_Book;
    }

    public void setName_Book(String name_Book) {
        this.name_Book = name_Book;
    }

    public String getAuthor_Book() {
        return author_Book;
    }

    public void setAuthor_Book(String author_Book) {
        this.author_Book = author_Book;
    }

    public String getLengths_Book() {
        return lengths_Book;
    }

    public void setLengths_Book(String lengths_Book) {
        this.lengths_Book = lengths_Book;
    }
}
