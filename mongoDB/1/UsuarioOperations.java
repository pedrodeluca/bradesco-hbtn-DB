import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class UsuarioOperations {

    public void inserirUsuario(MongoDBConnection connection, Usuario usuario) {

        MongoCollection<Document> collection = connection.getDatabase().getCollection("usuario");
        Document doc = new Document("nome", "João")
                .append("idade", 30)
                .append("cidade", "São Paulo");
        collection.insertOne(doc);
        System.out.println("Documento inserido com sucesso!");
    }
}
