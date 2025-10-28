import java.util.*;

class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available = true;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("Book{ISBN='%s', title='%s', author='%s', available=%s}", isbn, title, author, available);
    }
}

class Member {
    private String memberId;
    private String name;
    private List<String> borrowedIsbns = new ArrayList<>();

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<String> getBorrowedIsbns() { return Collections.unmodifiableList(borrowedIsbns); }

    void borrow(String isbn) { borrowedIsbns.add(isbn); }
    void returns(String isbn) { borrowedIsbns.remove(isbn); }

    @Override
    public String toString() {
        return String.format("Member{id='%s', name='%s', borrowed=%s}", memberId, name, borrowedIsbns);
    }
}

public class Library {
    private Map<String, Book> catalog = new HashMap<>(); // isbn -> book
    private Map<String, Member> members = new HashMap<>(); // memberId -> member

    // Book operations
    public void addBook(Book book) { catalog.put(book.getIsbn(), book); }
    public Book getBookByIsbn(String isbn) { return catalog.get(isbn); }
    public List<Book> searchByTitle(String title) {
        List<Book> res = new ArrayList<>();
        for (Book b : catalog.values()) if (b.getTitle().toLowerCase().contains(title.toLowerCase())) res.add(b);
        return res;
    }
    public List<Book> searchByAuthor(String author) {
        List<Book> res = new ArrayList<>();
        for (Book b : catalog.values()) if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) res.add(b);
        return res;
    }

    // Member operations
    public void registerMember(Member m) { members.put(m.getMemberId(), m); }
    public Member getMember(String memberId) { return members.get(memberId); }

    // Issue a book
    public boolean issueBook(String isbn, String memberId) {
        Book b = catalog.get(isbn);
        Member m = members.get(memberId);
        if (b == null || m == null || !b.isAvailable()) return false;
        b.setAvailable(false);
        m.borrow(isbn);
        return true;
    }

    // Return a book
    public boolean returnBook(String isbn, String memberId) {
        Book b = catalog.get(isbn);
        Member m = members.get(memberId);
        if (b == null || m == null) return false;
        if (!m.getBorrowedIsbns().contains(isbn)) return false;
        b.setAvailable(true);
        m.returns(isbn);
        return true;
    }

    public void printCatalog() { catalog.values().forEach(System.out::println); }
    public void printMembers() { members.values().forEach(System.out::println); }

    public static void main(String[] args) {
        Library lib = new Library();
        // seed data
        lib.addBook(new Book("9780134685991", "Effective Java", "Joshua Bloch"));
        lib.addBook(new Book("9780596009205", "Head First Java", "Kathy Sierra"));
        lib.registerMember(new Member("M001", "Alice"));
        lib.registerMember(new Member("M002", "Bob"));

        System.out.println("Initial catalog:");
        lib.printCatalog();

        System.out.println("\nIssue a book to Alice:");
        System.out.println("Issued: " + lib.issueBook("9780134685991", "M001"));
        lib.printCatalog();
        lib.printMembers();

        System.out.println("\nReturn the book:");
        System.out.println("Returned: " + lib.returnBook("9780134685991", "M001"));
        lib.printCatalog();
        lib.printMembers();
    }
}
