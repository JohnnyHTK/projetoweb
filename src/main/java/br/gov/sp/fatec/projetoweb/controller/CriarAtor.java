package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetoweb.dao.AtorDao;
import br.gov.sp.fatec.projetoweb.entity.Ator;

public class CriarAtor extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Recupera o parâmetro id (de trabalho?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));
        // Busca trabalho com o id
        AtorDao atorDao = new AtorDao();
        Ator ator = atorDao.buscar(id);
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        String atorJson = mapper.writeValueAsString(ator);
        // Formatamos a resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(atorJson);
        out.flush();
    }

    
    
}