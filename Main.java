package br.com.apiFilmes;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {
	public static void main(String[] args) throws Exception {
		try {
			URL url = new URL("https://www.omdbapi.com/?t=Goodfellas&apikey=suaChaveAqui");		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String linha = resposta.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = resposta.readLine();
			}
		}
		catch(Exception ex) {
			throw new Exception("Erro: " + ex);
		}
	}
}
