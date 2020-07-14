package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetoweb.dao.DiretorDao;
import br.gov.sp.fatec.projetoweb.entity.Diretor;



public class DiretorController extends HttpServlet {

    private static final long serialVersionUID = 1L;

@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Recupera o parâmetro id (de trabalho?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));
        // Busca trabalho com o id
        DiretorDao diretorDao = new DiretorDao();
        Diretor diretor = diretorDao.buscar(id);
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        String DiretorJson = mapper.writeValueAsString(diretor);
        // Formatamos a resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(DiretorJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Recuperamos o corpo da requisição e transformamos o JSON em objeto
        try{
        ObjectMapper mapper = new ObjectMapper();
        Diretor diretor = mapper.readValue(req.getReader(), Diretor.class);
        // Salvamos no Banco de Dados
        DiretorDao diretorDao = new DiretorDao();
        diretorDao.salvar(diretor);
        // Retornamos o registro gerado
        String DiretorJson = mapper.writeValueAsString(diretor);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // O código 201 requer que retornemos um header de Location
        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/Diretor?id=" + diretor.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(DiretorJson);
        out.flush();
        }
        catch(Exception e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Diretor diretor = mapper.readValue(req.getReader(), Diretor.class);
        DiretorDao diretorDao = new DiretorDao();
        diretorDao.excluir(diretor.getId());
        
        resp.setStatus(201);
        
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Diretor diretor = mapper.readValue(req.getReader(), Diretor.class);
        DiretorDao diretorDao = new DiretorDao();
        Diretor diretorInstancia = diretorDao.update(diretor); 
        diretorDao.salvar(diretorInstancia);
        resp.setStatus(204);
        // Retornamos o registro gerado
        String DiretorJson = mapper.writeValueAsString(diretor);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/Diretor?id=" + diretorInstancia.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(DiretorJson);
        out.flush();
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }
}