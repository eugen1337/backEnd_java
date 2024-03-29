package back.Infrustructure.storage;

import java.util.Date;
import java.math.BigDecimal;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDB implements IDataBase {

    private String uri = "mongodb://localhost:27017";

    @Override
    public boolean isRegistredUser(String login, String password) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("database connection successful for finding");

            MongoDatabase database = mongoClient.getDatabase("java_back");
            MongoCollection<Document> collection = database.getCollection("users");

            Bson andComparison = and(eq("login", login), eq("password", password));
            Document doc = collection.find(andComparison).first();

            if (doc != null) {
                System.out.println(doc.toJson());
                return true;
            } else {
                System.out.println("no matching users found.");
                return false;
            }
        }
    }

    @Override
    public boolean addUser(String login, String password) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            System.out.println("database connection successful for adding");
            MongoDatabase database = mongoClient.getDatabase("java_back");
            MongoCollection<Document> collection = database.getCollection("users");
            System.out.println("users connected");
            System.out.println("login = " + login + " password = " + password);

            System.out.println(collection.find(eq("login", login)).first());
            if (collection.find(eq("login", login)).first() == null) {
                Document doc = new Document("login", login).append("password", password);
                collection.insertOne(doc);
                if (isRegistredUser(login, password)) {
                    System.out.println(doc.toJson());
                    return true;
                } else {
                    System.out.println("error while registration");
                    return false;
                }
            } else {
                System.out.println("this login is already exist");
                return false;
            }
        }
    }

    @Override
    public String[] getTasks(String login) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            String[] result;
            System.out.println("database connection successful for getting tasks");
            MongoDatabase database = mongoClient.getDatabase("java_back");
            MongoCollection<Document> tasks = database.getCollection("tasks");
            MongoCollection<Document> task = database.getCollection("task");
            System.out.println("login = " + login);

            ArrayList<String> docs = new ArrayList<>();
            if (tasks.find(eq("login", login)).first() != null) {
                docs.add("{ \"docs\": [");
                for (Document doc : tasks.find(eq("login", login))) {
                    Document ltask = task.find(eq("id", doc.get("id"))).first();
                    assert ltask != null;
                    ltask.remove("_id");
                    docs.add(ltask.toJson());
                    docs.add(", ");
                }
                docs.remove(docs.size() - 1);
                docs.add(" ]}");
                result = new String[docs.size()];
                for (int i = 0; i < docs.size(); i++) {
                    result[i] = docs.get(i);
                }
                return result;
            } else {
                System.out.println("there is no tasks from this user");
                return null;
            }
        }
    }

    @Override
    public int createTask(String login, int value1, int value2) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("database connection successful for creating task");
            MongoDatabase database = mongoClient.getDatabase("java_back");
            MongoCollection<Document> tasks = database.getCollection("tasks");
            System.out.println("tasks connected");
            MongoCollection<Document> task = database.getCollection("task");
            System.out.println("task connected");
            System.out.println("login = " + login);

            int id = new BigDecimal(new Date().getTime() / 100 % 1000000000).intValueExact();

            Document doc = new Document("id", id).append("value1", value1).append("value2", value2)
                    .append("result", "null").append("status", "not started");
            task.insertOne(doc);
            doc = new Document("id", id).append("login", login);
            System.out.println(doc);
            tasks.insertOne(doc);
            if (tasks.find(eq("id", id)).first() != null) {
                return id;
            } else {
                System.out.println("error while creating task");
                return -1;
            }
        }
    }

    @Override
    public boolean deleteTask(int id) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("database connection successful for deleting task");
            MongoDatabase database = mongoClient.getDatabase("java_back");
            MongoCollection<Document> tasks = database.getCollection("tasks");
            System.out.println("tasks connected");
            MongoCollection<Document> task = database.getCollection("task");
            System.out.println("task connected");
            System.out.println("id = " + id);
            if (task.find(eq("id", id)).first() != null && tasks.find(eq("id", id)).first() != null) {
                task.findOneAndDelete(eq("id", id));
                tasks.findOneAndDelete(eq("id", id));
                return task.find(eq("id", id)).first() == null && tasks.find(eq("id", id)).first() == null;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean modifyTask(int id, int result, String status) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("database connection successful for modifying task");
            MongoDatabase database = mongoClient.getDatabase("java_back");
            MongoCollection<Document> task = database.getCollection("task");
            System.out.println("task connected");
            System.out.println("id = " + id);
            if (task.find(eq("id", id)).first() != null) {
                Bson updates = Updates.combine(Updates.set("result", result), Updates.set("status", status));
                task.updateOne(eq("id", id), updates);
                return true;
            } else {
                System.out.println("error while modifying task");
                return false;
            }
        }
    }

}