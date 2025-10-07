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

    public void atualizarUsuario(MongoDBConnection connection, String nome, int novaIdade) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("usuario");
        Document query = new Document("nome", nome);
        Document update = new Document("$set", new Document("idade", novaIdade));
        collection.updateOne(query, update);
        System.out.println("Documento atualizado com sucesso!");
    }

    public void consultarUsuarios(MongoDBConnection connection) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("usuario");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println("Consulta concluída com sucesso!");
    }

    public void apagarUsuario(MongoDBConnection connection, String usuarioNome) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("usuario");
        Document query = new Document("nome", usuarioNome);
        collection.deleteOne(query);
        System.out.println("Documento apagado com sucesso!");
    }
}
