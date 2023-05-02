# School - Hexagonal API
School API - This is a simple backend aplication using two entities: course and students. There are some API like CRUD and get information.

Port to test 8081

Import this API to Postman:

## To enroll
curl --location --request POST 'http://localhost:8081/course/enroll?studentid=1&courseId=6'

## To get students by course
curl --location --request GET 'http://localhost:8081/student/getStudentsByCourse?courseId=1'

## To get students without course
curl --location --request GET 'http://localhost:8081/student/getStudentsWithoutCourse'

## To get Courses without students
curl --location --request GET 'http://localhost:8081/course/getCoursesWithoutStudents'

## To get courses by student
curl --location --request GET 'http://localhost:8081/course/getCoursesByStudent?courseId=1'

## To get all
### Students
curl --location --request GET 'http://localhost:8081/student/getStudents'
### Course
curl --location --request GET 'http://localhost:8081/course/getCourses'

## To CRUD Students
### Delete
curl --location --request DELETE 'http://localhost:8081/student/3' \
--data-raw ''

### Insert
curl --location --request POST 'http://localhost:8081/student/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"test"
}'

### Update
curl --location --request PUT 'http://localhost:8081/student/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":8,
    "name":"test Bruno"
}'

## To CRUD Courses
### Insert
curl --location --request POST 'http://localhost:8081/course/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"test"
}'
### UPDATE
curl --location --request PUT 'http://localhost:8081/course/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":1,
    "name":"test"
}'
### Delete
curl --location --request DELETE 'http://localhost:8081/course/5' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"test"
}'
