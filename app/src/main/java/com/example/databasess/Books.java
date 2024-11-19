package com.example.databasess;

public class Books {
    private int ID;
    private String Name;
    private String Autor;

    public Books(int ID_Book, String Book_Name, String Book_Autor){
        this.ID=ID_Book;
        this.Name=Book_Name;
        this.Autor=Book_Autor;
    }

    public int getID() {
        return ID;
    }

    public String getAutor() {
        return Autor;
    }

    public String getName() {
        return Name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public void setName(String name) {
        Name = name;
    }

}
