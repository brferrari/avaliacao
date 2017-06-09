package br.com.avaliacao.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.avaliacao.controller.ReprovacaoController;
import br.com.avaliacao.model.Aluno;
import br.com.avaliacao.model.Boletim;
import br.com.avaliacao.model.Disciplina;
import br.com.avaliacao.model.MalaDireta;

import com.google.gson.Gson;

@Path("/alerta")
public class ReprovacaoService {

	@POST
	@Path("/reprovacao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response alertaReprovacao(@Context HttpServletRequest request) {

		Boletim boletim;

		ReprovacaoController controller = new ReprovacaoController();
		ArrayList<MalaDireta> malasDiretas = new ArrayList<MalaDireta>();

		try {
			for (Aluno aluno : controller.getAlunos()) {

				boletim = controller.getBoletim(aluno);

				for (Disciplina disciplina : boletim.getDisciplinas()) {

					if (disciplina.getNota() < 7) {

						malasDiretas.add(new MalaDireta(aluno.getNome(), aluno
								.getEndereco(), aluno.getCep()));
						break;
					}
				}

			}

			controller.setMalaDireta(malasDiretas);

		} catch (IOException e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		return Response.ok(gson.toJson(malasDiretas.size())).build();

	}

}
