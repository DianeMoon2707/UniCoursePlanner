# UniCoursePlanner
A web application for planning and managing university courses, modules and events.

## Motivation:
In my Bachelor's thesis, I decided to develop a full-stack web application using Java and Spring Boot. The resulting application, **MyCourse**, was designed to manage extracurricular activities (AGs) and automatically assign students to their selected activities. It was my first project using Java Spring Boot.

After completing my Bachelor's thesis, I wanted to further improve my skills in Java Spring Boot and web development. I therefore revisited the fundamentals of **HTML, CSS and JavaScript** and realized that there was still considerable room for improvement, particularly in creating modern and professional user interfaces.

To improve in this area, I studied different approaches to designing and implementing web interfaces, including **navigation menus and logIn pages**. I used tutorials and examples as a starting point, experimented with the techniques myself and adapted them to my own applications. Through this process, I developed a better understanding of how to create and implement professional-looking interfaces independently.

Testing was another area I wanted to improve. Since automated testing had not been part of my Bachelor's thesis, I specifically learned how to write and apply unit tests for a Spring Boot application.

For this project, I therefore chose technologies that allowed me to build on my existing knowledge while addressing these areas of improvement, including **Thymeleaf, JPA, PostgreSQL, Spring Security and email functionality**, as well as **unit testing**.

I also decided to use **Git and GitHub** throughout the development process in order to gain practical experience with version control and Git-based development workflows.

## Pages:
### Register
![Register pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/Register.png)
- Register Users (with hashed passwords)
- Proofing, if the new user choose an already exisisting username or email
- Visibility of the password field

### LogIn
![LogIn pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/LogIn.png)
- User authentication (with Spring Security)
- "Remember me"-Checkbox

### Home
![Home pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/a642f24f23299b1942debf2d97781094c778ab47/images/Home.png)
Home menu

### Module
![Module pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/a642f24f23299b1942debf2d97781094c778ab47/images/Module.png)
Table with modules and their events

![Edit Module pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/ModulEdit.png)
- Modal of editing one existing module
- A table row must be clicked to edit or delete data
- User input will be validated

### Timetable
![Timetable pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/Timetable.png)
- Timetable with Gridlayout
- Lectures can take place at the same time
- Colors vary depending on the event type

![Insert Lecture in the timetable pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/TimetableInsert.png)
- Modal of saving a new lecture
- Lectures have to be clicked to become changed or deleted

### Calendar
![Calendar pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/Calendar.png)
- Calendar with Gridlayout
- Arrow-Buttons lead to other months
- Days with events are marked red
- The current day has a blue background

![Calendar with day schedule pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/CalendarAndDaySchedule.png)
- A container that includes the day schedule pops up next to the calendar by clicking on a specific day
- All events of the day are list in a table

![Delete Event in Calendar pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/CalendarDelete.png)Modal (Delete) opens by choosing a row

### Credits
![Credits pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/Credits.png)
- Grands table
- Grands of a specific module can be added, changed or deleted by clicking at a row
- Credits of modules that have grade are summed up under the table

### Profile
![Profile pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/Profile.png)
- Profile page for handling their own datas
- Username and Email can be changed right here
- For changing a password, the link will guid the user to another page

### Forgot Password
![Change password to test456! pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/ForgotPassword.png)
- If the user gets to the page by clicking at the link on the profile page, his username will be print in the first input box
- Unless, the user have to enter his username by himself or alternativ his email

![Give the code pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/VerifyCode.png)
- If the user enter an existing email or username, he will get an email with a code.
- Right code = new password activate
  
![Wrong code pic](https://github.com/DianeMoon2707/UniCoursePlanner/blob/cb2f7fe2143054213860858e73ce34503462f623/images/VerifyCodeWrongCode.png)
Wrong code = try again

## Technologies
### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

### Frontend
- Thymeleaf
- HTML
- CSS
- JavaScript

### Database
- PostgreSQL

### Development Tools
- Eclipse
- Git(Hub)
- pgAdmin

## Architecture
Packet-Diagramm

## Database
ER-Diagramm

## Authentication & Security

## Testing

## Future Improvements
