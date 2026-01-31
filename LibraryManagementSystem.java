import java.util.*;
import java.time.*;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;
    private LocalDate dueDate;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean isBorrowed() {
        return isBorrowed;
    }
    
    public void borrowBook() {
        this.isBorrowed = true;
        this.dueDate = LocalDate.now().plusDays(14);
    }
    
    public void returnBook() {
        this.isBorrowed = false;
        this.dueDate = null;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
}

class Student {
    private String name;
    private String rollNumber;
    private String password;
    private List<Book> borrowedBooks;
    
    public Student(String name, String rollNumber, String password) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
    
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= 3) {
            System.out.println("You cannot borrow more than 3 books.");
            return false;
        }
        book.borrowBook();
        borrowedBooks.add(book);
        return true;
    }
    
    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
            return true;
        }
        return false;
    }
}

class Library {
    private List<Book> books;
    private Map<String, Student> students;
    
    public Library() {
        books = new ArrayList<>();
        students = new HashMap<>();
        books.add(new Book("Java Programming", "Herbert Schildt"));
        books.add(new Book("Effective Java", "Joshua Bloch"));
        books.add(new Book("Clean Code", "Robert C. Martin"));
        books.add(new Book("Head First Java", "Kathy Sierra"));
        books.add(new Book("The Pragmatic Programmer", "Andrew Hunt"));
        books.add(new Book("Design Patterns", "Erich Gamma"));
        books.add(new Book("Refactoring", "Martin Fowler"));
        books.add(new Book("Introduction to Algorithms", "Cormen"));
        books.add(new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell"));
        books.add(new Book("Artificial Intelligence: A Modern Approach", "Stuart Russell"));
        books.add(new Book("Computer Networking", "James F. Kurose"));
        books.add(new Book("Operating System Concepts", "Abraham Silberschatz"));
        books.add(new Book("Database System Concepts", "Abraham Silberschatz"));
        books.add(new Book("Computer Organization and Design", "David A. Patterson"));
        books.add(new Book("Computer Architecture", "John L. Hennessy"));
    }

    public void signUpStudent(String name, String rollNumber, String password) {
        if (students.containsKey(rollNumber)) {
            System.out.println("Student with this roll number already exists.");
            return;
        }
        students.put(rollNumber, new Student(name, rollNumber, password));
        System.out.println("Signup successful!");
    }

    public Student loginStudent(String rollNumber, String password) {
        Student student = students.get(rollNumber);
        if (student != null && student.verifyPassword(password)) {
            return student;
        }
        return null;
    }
    
    public void displayAvailableBooks() {
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
    
    public void viewBorrowedBooks(Student student) {
        for (Book book : student.getBorrowedBooks()) {
            System.out.println(book.getTitle() + " (Due Date: " + book.getDueDate() + ")");
        }
    }
    
    public void viewUpcomingBooks() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);
        System.out.println("Books due in the next 7 days:");
        for (Book book : books) {
            if (book.isBorrowed() && book.getDueDate().isAfter(today) && book.getDueDate().isBefore(nextWeek)) {
                System.out.println(book.getTitle() + " (Due Date: " + book.getDueDate() + ")");
            }
        }
    }
    
    public void borrowBook(Student student, String bookTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && !book.isBorrowed()) {
                if (student.borrowBook(book)) {
                    System.out.println("Book borrowed successfully.");
                }
                return;
            }
        }
        System.out.println("Book is not available.");
    }
    
    public void returnBook(Student student, String bookTitle) {
        for (Book book : student.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                if (student.returnBook(book)) {
                    System.out.println("Book returned successfully.");
                }
                return;
            }
        }
        System.out.println("You haven't borrowed this book.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Student currentStudent = null;
        
        while (true) {
            System.out.println("1. Sign Up\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Roll Number: ");
                String roll = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();
                library.signUpStudent(name, roll, password);
            } else if (choice == 2) {
                System.out.print("Enter Roll Number: ");
                String roll = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();
                currentStudent = library.loginStudent(roll, password);
                if (currentStudent == null) {
                    System.out.println("Invalid Roll Number or Password!");
                } else {
                    while (true) {
                        System.out.println("4. View Available Books\n5. View Borrowed Books\n6. Borrow Book\n7. Return Book\n8. View Upcoming Books\n9. Logout");
                        int subChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (subChoice == 4) {
                            library.displayAvailableBooks();
                        } else if (subChoice == 5) {
                            library.viewBorrowedBooks(currentStudent);
                        } else if (subChoice == 6) {
                            System.out.print("Enter Book Title: ");
                            String bookTitle = scanner.nextLine();
                            library.borrowBook(currentStudent, bookTitle);
                        } else if (subChoice == 7) {
                            System.out.print("Enter Book Title: ");
                            String bookTitle = scanner.nextLine();
                            library.returnBook(currentStudent, bookTitle);
                        } else if (subChoice == 8) {
                            library.viewUpcomingBooks();
                        } else if (subChoice == 9) {
                            break;
                        }
                    }
                }
            } else if (choice == 3) {
                break;
            }
        }
    }
}