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


public class AtorController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        AtorDao atorDao = new AtorDao();
        Ator ator = atorDao.buscar(id);

        ObjectMapper mapper = new ObjectMapper();
        String atorJson = mapper.writeValueAsString(ator);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(atorJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        try{
        ObjectMapper mapper = new ObjectMapper();
        Ator ator = mapper.readValue(req.getReader(), Ator.class);

        AtorDao atorDao = new AtorDao();
        atorDao.salvar(ator);

        String atorJson = mapper.writeValueAsString(ator);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/ator?id=" + ator.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(atorJson);
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
        Ator ator = mapper.readValue(req.getReader(), Ator.class);
        AtorDao atorDao = new AtorDao();
        atorDao.excluir(ator.getId());
        
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
        Ator ator = mapper.readValue(req.getReader(), Ator.class);
        AtorDao atorDao = new AtorDao();
        Ator atorInstancia = atorDao.update(ator); 
        atorDao.salvar(atorInstancia);
        resp.setStatus(204);
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }
}