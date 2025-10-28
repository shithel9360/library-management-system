# Library Management System

## Overview
This is a Java-based Object-Oriented Programming (OOP) project designed to manage library operations efficiently. The system provides comprehensive functionality for managing library resources and user interactions.

## Features
- **Book Catalog Management**: Add, update, and search books in the library catalog
- **Member Account Management**: Register and manage library member accounts
- **Issue Books**: Track book borrowing by members with issue dates
- **Return Books**: Process book returns and update availability status
- **Search Functionality**: Search books by title, author, or ISBN
- **Member History**: View borrowing history for each member

## Project Structure
The project implements core OOP principles including:
- **Encapsulation**: Private data members with public getter/setter methods
- **Inheritance**: Extensible class hierarchy for different book types
- **Polymorphism**: Method overriding for specialized behavior
- **Abstraction**: Clear separation of concerns through well-defined classes

## Classes
- `Book`: Represents a book with properties like title, author, ISBN, and availability status
- `Member`: Represents a library member with personal information and borrowing history
- `Library`: Main class that manages the collection of books and members, handles operations

## Technologies Used
- Java
- Object-Oriented Programming principles

## Getting Started
1. Clone this repository
2. Compile the Java files
3. Run the Library.java file to start the application

## Future Enhancements
- Database integration for persistent storage
- GUI interface for better user experience
- Fine calculation for overdue books
- Email notifications for due dates
