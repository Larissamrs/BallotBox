package ws;

import com.ifpe.prova.entidades.Candidate;
import com.ifpe.prova.entidades.PoliticalParty;
import com.ifpe.prova.entidades.Repository;
import com.ifpe.prova.entidades.Result;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/eleicao")
public class WebService {
    private static Repository repositorio = new Repository(); 
    @GET
    @Path("/politicalparty/{politicalpartyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partido(@PathParam("politicalpartyId") Integer politicalpartyId){
        PoliticalParty party = repositorio.findPartyByNumber(politicalpartyId);
        if (party == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(party).build();
    }

    @GET
    @Path("/candidate/{candidateId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getCandidate(@PathParam("candidateId") Integer candidateId) {
        Candidate candidate = repositorio.findCandidateByNumber(candidateId);
        if (candidate == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(candidate).build();
    }

    @POST
    @Path("/votar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response votar(VotoRequest votoRequest) {
        try {
            int votarId = votoRequest.getVoto();
            repositorio.countVote(votarId);
            return Response.ok()
                    .entity("{\"mensagem\": \"Voto registrado com sucesso!\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao processar o voto.\"}")
                    .build();
        }
    }

    @GET
    @Path("/result")
    @Produces(MediaType.APPLICATION_XML)
    public Response result(){
        Result r = repositorio.computeElectionResults();
        return Response.ok(r).build();
    }
    public static class VotoRequest {
        private int voto;

        public int getVoto() {
            return voto;
        }

        public void setVoto(int voto) {
            this.voto = voto;
        }
    }
}