package br.com.avaliacao.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.com.avaliacao.model.Aluno;
import br.com.avaliacao.model.Boletim;
import br.com.avaliacao.model.MalaDireta;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReprovacaoController {

	public ReprovacaoController() {
		super();
	}

	public ArrayList<Aluno> getAlunos() throws IOException {
		
		URL url = new URL("http://localhost:8080/alunos");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		conn.disconnect();
		
		Gson json = new Gson();
	    ArrayList<Aluno> list = json.fromJson(br.readLine(), new TypeToken<ArrayList<Aluno>>() {}.getType());
	    
	    return list;
		
	}
	
	public Boletim getBoletim(Aluno aluno) throws IOException {
		
		URL url = new URL("http://localhost:8080/alunos/#" + aluno.getDocumento() + "/notas");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
		conn.disconnect();
		
		Gson json = new Gson();
	    Boletim boletim = new Boletim();
		
		boletim = json.fromJson(br.readLine(), new TypeToken<Boletim>() {}.getType());
	    
	    return boletim;
		
	}
	
	public void setMalaDireta(ArrayList<MalaDireta> malaDireta) throws IOException {
		
		URL url = new URL("http://localhost:8080/malaDireta");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		
		Gson json = new Gson();
		
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
        osw.write(json.toJson(malaDireta));
        osw.flush();
        osw.close();
		
		conn.disconnect();
		
	}
	
}
