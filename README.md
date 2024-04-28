# Olik Assignment

This is a Spring Boot application that provides RESTful APIs to perform CRUD operations on `Author` and `Book` entities.

## Author Controller

The `AuthorController` provides the following endpoints:

1. **Save an Author**
    - **Endpoint:** `/author/save`
    - **Method:** POST
    - **Request Body:** `AuthorRequestDto`
    - **Description:** Saves a new author to the database.

2. **Get an Author by ID**
    - **Endpoint:** `/author/get{id}`
    - **Method:** GET
    - **Path Variable:** `id` (Integer)
    - **Description:** Retrieves an author by ID from the database.

3. **Update an Author**
    - **Endpoint:** `/author/update`
    - **Method:** PUT
    - **Request Body:** `AuthorUpdateRequestDto`
    - **Description:** Updates an existing author in the database.

4. **Delete an Author by ID**
    - **Endpoint:** `/author/delete{id}`
    - **Method:** DELETE
    - **Path Variable:** `id` (Integer)
    - **Description:** Deletes an author by ID from the database.

## Book Controller

The `BookController` provides the following endpoints:

1. **Save a Book**
    - **Endpoint:** `/book/save`
    - **Method:** POST
    - **Request Body:** `BookRequestDto`
    - **Description:** Saves a new book to the database.

2. **Get a Book by Title**
    - **Endpoint:** `/book/get/{title}`
    - **Method:** GET
    - **Path Variable:** `title` (String)
    - **Description:** Retrieves a book by title from the database.

3. **Get All Books**
    - **Endpoint:** `/book/getAll`
    - **Method:** GET
    - **Description:** Retrieves all books from the database.

4. **Update a Book**
    - **Endpoint:** `/book/update`
    - **Method:** PUT
    - **Request Body:** `BookRequestDto`
    - **Description:** Updates an existing book in the database.

5. **Delete a Book by ID**
    - **Endpoint:** `/book/delete/{Id}`
    - **Method:** DELETE
    - **Path Variable:** `id` (Integer)
    - **Description:** Deletes a book by ID from the database.

## Setup

To run this project, you will need to add the following environment variables to your .env file:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## Run Locally

Clone the project

```bash
  git clone https://github.com/your_username/your_project_name
  cd your_project_name
  mvn install
  mvn spring-boot:run
