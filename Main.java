package br.com.apiFilmes;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Qual o nome do filme?");
			String nome = scanner.next();
			
			URL url = new URL("https://www.omdbapi.com/?t="+ nome + "&apikey=[suaChaveAqui]");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			scanner.close();
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String linha = resposta.readLine();
			
			JSONObject object = new JSONObject(linha.toString());
			System.out.println(retornaFilme(object));
		}
		catch(Exception ex) {
			throw new Exception("Erro: " + ex);
		}
	}
	
	public static String retornaFilme(JSONObject object) {
		String nomeDoFilme = object.getString("Title");
		String anoDoFilme = object.getString("Year");
		String duracaoDoFilme = object.getString("Runtime");
		String dataDeLancamento = object.getString("Released");
		String diretorDoFilme = object.getString("Director");
		return "\nNome: " + nomeDoFilme + "\nAno: " + anoDoFilme + "\nDuracao: " + duracaoDoFilme + "\nData de lancamento: " + dataDeLancamento + "\nDiretor: " + diretorDoFilme;
	}
}
