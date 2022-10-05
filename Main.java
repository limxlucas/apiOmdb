package br.com.apiFilmes;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {
	public static void main(String[] args) throws Exception {
		try {
			URL url = new URL("https://www.omdbapi.com/?apikey=[SUA CHAVE AQUI]=Goodfellas");		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			System.out.println("RESPOSTA: " + resposta.readLine());
		}
		catch(Exception ex) {
			throw new Exception("Erro: " + ex);
		}
	}
}
