# FileOperation
Java Project to download,delete and clone a file.
VERB  API call
GET  http://localhost:8080/file/download  - download a file
DELETE http://localhost:8080/file/delete  - delete a file
POST http://localhost:8080/file/copy - creates a clone of a file.

when project is imported or deployed it will create a folder under C:\ProgramData\ with name "infrrd" in which sample video will be copied and logs are created.
route to http://localhost:8080/file/swagger-ui.html for api doc.
