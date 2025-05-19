### 🏛️ VenueZar - Venue Booking Management System

## 📋 Overview

VenueZar is a comprehensive web-based venue booking and management system that connects venue owners with customers looking to book venues for events. The platform provides an intuitive interface for venue owners to list their properties and manage bookings, while customers can easily browse, favorite, and book venues.

## ✨ Features

### For Venue Owners

- 🔐 Secure registration and login system
- 📝 Add and manage venue listings with details (name, location, price, etc.)
- 📊 Dashboard to view all listed venues
- 🗑️ Delete venues when needed
- 📅 View and manage booking requests
- 🔔 Real-time notification system for new bookings


### For Customers

- 🔐 Separate customer registration and login system
- 🔍 Browse all available venues
- ❤️ Add venues to favorites for easy access
- 📍 View venue locations via Google Maps integration
- 📅 Book venues with date and time slot selection
- 📱 Mobile-responsive interface


## 🛠️ Technologies Used

- **Backend**: Java Servlets, JSP, JDBC
- **Database**: MySQL
- **Frontend**: HTML, CSS, JavaScript
- **Security**: Password hashing with SHA-256
- **Session Management**: Cookies
- **External APIs**: Google Maps integration
- **Build Tool**: Apache Ant


## 🚀 Installation and Setup

### Prerequisites

- JDK 8 or higher
- Apache Tomcat 8 or higher
- MySQL 5.7 or higher
- NetBeans IDE (recommended)


### Database Setup

1. Create a MySQL database named `venuezar`
2. Execute the following SQL scripts to create the required tables:


```sql
-- Create owner table
CREATE TABLE ownerTable (
    ownerId VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create customer table
CREATE TABLE customerTable (
    customerId VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create venue table
CREATE TABLE venueTable (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    area VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL,
    food_include VARCHAR(10),
    phoneNumber VARCHAR(20) NOT NULL,
    location_link VARCHAR(255) NOT NULL,
    ownerId VARCHAR(255) NOT NULL,
    FOREIGN KEY (ownerId) REFERENCES ownerTable(ownerId)
);

-- Create booking table
CREATE TABLE bookingTable (
    bookingId VARCHAR(255) PRIMARY KEY,
    customerId VARCHAR(255) NOT NULL,
    venueId VARCHAR(255) NOT NULL,
    ownerId VARCHAR(255) NOT NULL,
    date VARCHAR(20) NOT NULL,
    slot VARCHAR(5) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customerTable(customerId),
    FOREIGN KEY (venueId) REFERENCES venueTable(id),
    FOREIGN KEY (ownerId) REFERENCES ownerTable(ownerId)
);

-- Create favorites table
CREATE TABLE favorites (
    venueId VARCHAR(255) NOT NULL,
    customerId VARCHAR(255) NOT NULL,
    PRIMARY KEY (venueId, customerId),
    FOREIGN KEY (venueId) REFERENCES venueTable(id),
    FOREIGN KEY (customerId) REFERENCES customerTable(customerId)
);
```

### Project Setup

1. Clone the repository or download the project files
2. Open the project in NetBeans IDE
3. Update the database connection settings in the Java files:

1. Look for `DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", "root", "Viral@6566")` and replace with your MySQL credentials



4. Build the project
5. Deploy to Tomcat server


## 📱 Usage

### For Venue Owners

1. Register as a venue owner
2. Log in to your account
3. Add venues with details (name, area, city, price, etc.)
4. View your listed venues on the dashboard
5. Check booking notifications and manage bookings
6. Delete venues when needed


### For Customers

1. Register as a customer
2. Log in to your account
3. Browse available venues
4. Add venues to favorites by clicking the heart icon
5. Click "Book Now" to book a venue
6. Select date and time slot (Morning, Evening, or Whole Day)
7. Confirm booking


## 📸 Screenshots

*[Suggestion: Add screenshots of key pages here, such as:]*

- Home page
- Owner dashboard
- Customer dashboard
- Venue listing
- Booking form
- Bookings page


## 📁 Project Structure

```plaintext
VenueZar/
├── src/
│   └── java/
│       ├── booking/
│       │   ├── BookingVenueServlet.java
│       │   └── BookingsServlet.java
│       ├── cookie/
│       │   └── RedirectBasedOnCookie.java
│       ├── login_registration/
│       │   ├── LoginCustomerServlet.java
│       │   ├── LoginOwnerServlet.java
│       │   ├── LogoutCustomerServlet.java
│       │   ├── LogoutOwnerServlet.java
│       │   ├── RegisterCustomerServlet.java
│       │   └── RegisterOwnerServlet.java
│       ├── utils/
│       │   ├── AlertMessage.java
│       │   ├── GetCookieValue.java
│       │   ├── GetNumberOfNotification.java
│       │   ├── GetUserName.java
│       │   ├── IsDatePass.java
│       │   ├── PasswordHasher.java
│       │   └── UserAuthenticat.java
│       └── venue/
│           ├── AddFavoriteVenueServlet.java
│           ├── AddVenueServlet.java
│           ├── DeleteVenueServlet.java
│           ├── FetchCustomerSideContent.java
│           └── FetchOwnerSideContent.java
└── web/
    ├── AddVenueForm.html
    ├── BookingForm.html
    ├── BookingsPage.html
    ├── CustomerDashboard.html
    ├── LoginForCustomer.html
    ├── LoginForOwner.html
    ├── OwnerDashboard.html
    ├── RegisterForCustomer.html
    ├── RegisterForOwner.html
    ├── home.html
    ├── META-INF/
    │   └── context.xml
    └── WEB-INF/
        └── web.xml
```

## 🔒 Security Features

- Password hashing using SHA-256 algorithm
- Session management with cookies
- Input validation
- Protection against SQL injection


## 🌟 Future Enhancements

- Payment gateway integration
- Email notifications
- Admin panel for system management
- Advanced search and filtering options
- User reviews and ratings for venues
- Image gallery for venues
- Mobile application


## 👥 Contributors

- [Your Name] - Initial work and development


## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- [NetBeans](https://netbeans.apache.org/) - IDE for development
- [Apache Tomcat](http://tomcat.apache.org/) - Servlet container
- [MySQL](https://www.mysql.com/) - Database
- [Cloudinary](https://cloudinary.com/) - Image hosting


---

⭐ Star this repository if you find it useful! ⭐
