package MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/*
数据库(database)
     |-集合(collection)
	   |-文档(document)
 */
public class MongoDB_Test {

    public static final String HOST = "localhost";
    public static final int PORT = 27017;

    static MongoClient mongoClient = null;
    static MongoDatabase mongoDatabase;

    static {
        // 无密码 连接数据库
        mongoClient = new MongoClient(HOST, PORT);
        System.out.println("Connected to MongoDB successfully");
    }

    /*
    调用MongoDB的find方法来查询集合的全部信息
     */
    public static void findAll(String database, String collection) {

        // 获得数据库对象
        mongoDatabase = mongoClient.getDatabase(database);
        // 获取集合(返回的是一个文档的list)
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        // 获取文档迭代器对象
        FindIterable<Document> findIterable = mongoCollection.find();
        // 获取游标对象
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        System.out.println(database + "--" + collection + ":");
        while (mongoCursor.hasNext()) {

            System.out.println(mongoCursor.next());
        }
    }

    /*
    插入单个信息
     */
    public static void insertOne(String database, String collection, Document document) {

        // 获得数据库对象
        mongoDatabase = mongoClient.getDatabase(database);
        // 获取集合(返回的是一个文档的list)
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        // 直接插入参数传入的文档对象document
        mongoCollection.insertOne(document);
        System.out.println("插入成功 :");
        System.out.println(document);
    }

    /*
    插入多个信息
    需要创建一个List<Document>

     */
    public static void insertMany(String database, String collection, List documents) {

        // 获得数据库对象
        mongoDatabase = mongoClient.getDatabase(database);
        // 获取集合(返回的是一个文档的list)
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        mongoCollection.insertMany(documents);
        System.out.println("插入成功 :");
        System.out.println(documents);
    }


    /*
    update
    $set
    匹配方式:  Filters.eq("name", "zhao")
     */
    public static void update(String database, String collection) {

        // 获得数据库对象
        mongoDatabase = mongoClient.getDatabase(database);
        // 获取集合(返回的是一个文档的list)
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        // 用集合的update方法,添加document对象,Filters查询条件
        mongoCollection.updateOne(
                Filters.eq("name", "zhao"),
                new Document("$set",
                        new Document("age", 16))
        );
        System.out.println("update successfully");

    }

    /*
    删除文档
    flag=true--->删除多个匹配项
    flag=false-->删除第一个匹配项
    匹配方式:  Filters.eq("name", "zhao")
     */
    public static void delet(String database, String collection, boolean flag) {

        // 获得数据库对象
        mongoDatabase = mongoClient.getDatabase(database);
        // 获取集合(返回的是一个文档的list)
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        //
        if (flag==false) {
            mongoCollection.deleteOne(Filters.eq("name", "zhao"));
        }else
            mongoCollection.deleteMany(Filters.eq("name","zhao"));
        System.out.println("delete successfully");
        findAll(database,collection);

    }


    public static void main(String[] args) {


        // 创建一个文档对象
        Document document = new Document("name", "zhao");
        insertOne("test", "stu", document);
        // 插入多个,这里一个document代表一个文档
        Document document1 = new Document("name", "liu").append("age", 15).append("gender", "male");
        List<Document> documents = new ArrayList<Document>();
        // add多个document,代表多个文档
        documents.add(document1);
        insertMany("test", "stu", documents);
        //更新
        update("test", "stu");
        //查询全部
        findAll("test", "stu");
        delet("test","stu",true);

    }
}
