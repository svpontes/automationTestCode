// Importa a classe GoogleIdTokenVerifier para verificar tokens de identidade do Google
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
// Importa a classe Builder usada para construir uma instância de GoogleIdTokenVerifier
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.Builder;
// Importa a interface HttpTransport para manipular o transporte HTTP
import com.google.api.client.http.HttpTransport;
// Importa a implementação padrão de transporte HTTP
import com.google.api.client.http.javanet.NetHttpTransport;
// Importa a interface JsonFactory para manipulação de JSON
import com.google.api.client.json.JsonFactory;
// Importa a classe Collections para manipular coleções como listas e conjuntos
import java.util.Collections;

// Classe principal TokenVerifier, usada para verificar tokens de identidade do Google
public class TokenVerifier {

    // Atributos da classe TokenVerifier
    private final String clientId; // O ID do cliente usado para a verificação
    private final HttpTransport transport; // O transporte HTTP usado para comunicação
    private final JsonFactory factory; // A fábrica de JSON usada para processar JSON

    // Construtor da classe TokenVerifier que inicializa os atributos com valores fornecidos
    public TokenVerifier(String clientId, HttpTransport transport, JsonFactory factory) {
        this.clientId = clientId; // Inicializa o ID do cliente
        this.transport = transport; // Inicializa o transporte HTTP
        this.factory = factory; // Inicializa a fábrica de JSON
    }

    // Método para verificar o token de identidade fornecido
    public Payload verify(String idTokenString) throws Exception {
        // Cria uma instância de GoogleIdTokenVerifier usando o transporte e a fábrica de JSON
        GoogleIdTokenVerifier verifier = new Builder(transport, factory)
                .setAudience(Collections.singleton(clientId)) // Define o público-alvo como o ID do cliente
                .build(); // Constrói o verificador de token

        // Verifica o token de identidade e retorna o payload se for válido
        Payload payload = verifier.verify(idTokenString);

        return payload; // Retorna o payload do token verificado
    }

    // Método principal para executar a verificação de token
    public static void main(String[] args) throws Exception {
        // Cria uma instância de TokenVerifier com o ID do cliente, transporte HTTP e fábrica de JSON
        TokenVerifier verifier = new TokenVerifier("my_client_id_string",
                new NetHttpTransport(), new JacksonFactory());

        // Define um token de identidade para verificação
        String idTokenString = "my_id_token_string";
        // Verifica o token e obtém o payload
        Payload payload = verifier.verify(idTokenString);

        // Imprime o payload obtido
        System.out.println(payload);
    }
}