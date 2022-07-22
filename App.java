
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        //BUSCAR DADOS
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url); //criação da uri --Uniform Resource Identifier
        HttpClient client = HttpClient.newHttpClient(); //Criação do cliente HTTP 
        var request = HttpRequest.newBuilder(endereco).GET().build(); //Criação  da requisição HTTP
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // envia a requisição e a armazena dentro do body do response
        String body = response.body(); //Armazena a resposta dentro da string body
        //System.out.println(body); //Mostra (body) no terminal

        //EXTRAIR OS DADOS QUE INTERESSAM --PARSER
        var parser = new jsonparserteste();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); //faz a lista de filmes passar pelo parser, uma clase definida em JsonParser
        //System.out.println(listaDeFilmes.size()); //pra mostrar o tamanho da lista
        //System.out.println(listaDeFilmes.get(index: 0));

        //EXIBIR OS DADOS
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: "+"\u001b[1m \u001b[41m" +filme.get("title")+"\u001b[m");
            //System.out.println(filme.get("image"));
            System.out.println("Nota: "+filme.get("imDbRating"));
            System.out.println();
        }
    }    
}