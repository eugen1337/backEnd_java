package Infrustructure.storage;

import app.IDataBase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

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
}