docker run -d --name mogodb-server -p 27017:27017 mongo:latest

docker exec -it <container_id> mongodbsh

show dbs;
use testdb;
show collections;

db.users.find();

db.users.find({lastName: "Polimetla"})

